# Notes API REST - Atelier 5

## A. Endpoints

| Contrôleur | Verbe et Route | Service Appelé | Succès | Erreurs |
|------------|----------------|----------------|--------|---------|
| ContratController | GET /api/contrats | findAll() | 200 | - |
| ContratController | GET /api/contrats/{id} | findById(id) | 200 | 404 |
| ContratController | POST /api/contrats | create(contrat) | 201 | 400 |
| ContratController | PUT /api/contrats/{id} | update(id, contrat) | 200 | 404, 400 |
| ContratController | DELETE /api/contrats/{id} | deleteById(id) | 204 | 404 |
| PaiementController | GET /api/paiements | findAll() | 200 | - |
| PaiementController | GET /api/paiements/{id} | findById(id) | 200 | 404 |
| AgenceController | GET /api/agences | findAll() | 200 | - |
| AgenceController | GET /api/agences/{id} | findById(id) | 200 | 404 |
| AgenceController | POST /api/agences | create(agence) | 201 | 400 |
| AgenceController | PUT /api/agences/{id} | update(id, agence) | 200 | 404, 400 |
| AgenceController | DELETE /api/agences/{id} | deleteById(id) | 204 | 404 |
| EmployeController | GET /api/employes | findAll() | 200 | - |
| EmployeController | GET /api/employes/{id} | findById(id) | 200 | 404 |
| EmployeController | POST /api/employes | create(employe) | 201 | 400 |
| EmployeController | PUT /api/employes/{id} | update(id, employe) | 200 | 404, 400 |
| EmployeController | DELETE /api/employes/{id} | deleteById(id) | 204 | 404 |
| VehiculeController | GET /api/vehicules | findAll() | 200 | - |
| VehiculeController | GET /api/vehicules/{id} | findById(id) | 200 | 404 |
| VehiculeController | POST /api/vehicules | create(vehicule) | 201 | 400 |
| VehiculeController | PUT /api/vehicules/{id} | update(id, vehicule) | 200 | 404, 400 |
| VehiculeController | DELETE /api/vehicules/{id} | deleteById(id) | 204 | 404 |
| EquipementController | GET /api/equipements | findAll() | 200 | - |
| EquipementController | GET /api/equipements/{id} | findById(id) | 200 | 404 |
| EquipementController | POST /api/equipements | create(equipement) | 201 | 400 |
| EquipementController | PUT /api/equipements/{id} | update(id, equipement) | 200 | 404, 400 |
| EquipementController | DELETE /api/equipements/{id} | deleteById(id) | 204 | 404 |
| ClientController | GET /api/clients | findAll() | 200 | - |
| ClientController | GET /api/clients/{id} | findById(id) | 200 | 404 |
| ClientController | POST /api/clients | create(client) | 201 | 400 |
| ClientController | PUT /api/clients/{id} | update(id, client) | 200 | 404, 400 |
| ClientController | DELETE /api/clients/{id} | deleteById(id) | 204 | 404 |
| ReservationController | GET /api/reservations | findAll() | 200 | - |
| ReservationController | GET /api/reservations/{id} | findById(id) | 200 | 404 |
| ReservationController | POST /api/reservations | create(reservation) | 201 | 400 |
| ReservationController | PUT /api/reservations/{id} | update(id, reservation) | 200 | 404, 400 |
| ReservationController | DELETE /api/reservations/{id} | deleteById(id) | 204 | 404 |
| MaintenanceController | GET /api/maintenances | findAll() | 200 | - |
| MaintenanceController | GET /api/maintenances/{id} | findById(id) | 200 | 404 |
| MaintenanceController | POST /api/maintenances | create(maintenance) | 201 | 400 |
| MaintenanceController | PUT /api/maintenances/{id} | update(id, maintenance) | 200 | 404, 400 |
| MaintenanceController | DELETE /api/maintenances/{id} | deleteById(id) | 204 | 404 |

## B. Annotations Jackson

### Contrat ↔ Paiement (Composition)
- **Association** : OneToMany bidirectionnelle avec cascade = ALL
- **Annotation retenue** : `@JsonManagedReference("contrat-paiements")` côté Contrat (parent, liste) et `@JsonBackReference("contrat-paiements")` côté Paiement (enfant, référence)
- **Justification** : Cette association représente une vraie composition où les paiements appartiennent au contrat et sont créés/supprimés avec lui. La référence gérée permet d'inclure les paiements dans le JSON du contrat lors de la création, tout en évitant la boucle infinie de sérialisation.

### Autres associations (Client ↔ Reservation, Agence ↔ Vehicule, etc.)
- **Association** : OneToMany bidirectionnelle sans composition totale
- **Annotation retenue** : `@JsonIgnore` sur la liste côté inverse
- **Justification** : Ces entités enfants sont créées indépendamment et référencent leur parent par un identifiant (ex: `{"agence": {"id": 1}}`). `@JsonIgnore` évite la boucle de sérialisation sans empêcher la lecture de la référence parent.

### Différence entre @JsonBackReference et @JsonIgnore
- **@JsonBackReference** : Ignore la propriété en sérialisation ET en désérialisation. Utilisé pour les compositions où l'enfant ne peut exister sans le parent et où le lien est géré automatiquement.
- **@JsonIgnore** : Ignore uniquement en sérialisation mais permet la lecture. Utilisé quand l'objet enfant doit pouvoir spécifier son parent lors de la création via JSON.

**Attention** : Ne pas utiliser `@JsonBackReference` sur `Reservation.client` car cela rendrait impossible de spécifier le client lors du POST d'une réservation.

## C. Postman et Swagger

### Postman
Postman est un client HTTP qui permet de **tester** l'API de manière manuelle et reproductible :
- Permet de construire des requêtes complexes (headers, body JSON, authentification)
- Sauvegarde les requêtes dans des collections pour les rejouer facilement
- Permet d'écrire des tests automatisés (assertions sur le statut, le corps de réponse)
- Gère des environnements (variables comme `baseUrl`, `contratId`)
- Utile pour la validation fonctionnelle et la détection de régressions

**Exemple concret** : Lors de la création d'un contrat avec ses paiements, Postman permet de vérifier que le statut est bien 201, que l'identifiant est généré, que les paiements sont cascadés, et de mémoriser l'ID dans une variable pour les requêtes suivantes (GET, PUT, DELETE).

### Swagger UI
Swagger UI est une interface web de **documentation interactive** générée automatiquement par SpringDoc à partir du code :
- Présente le contrat de l'API (endpoints, paramètres, schémas JSON) synchronisé avec le code
- Permet d'explorer l'API et de faire des essais rapides sans configuration
- Affiche les descriptions, les codes de retour documentés via annotations
- Accessible à tous les développeurs frontend et testeurs sans installation

**Exemple concret** : Un développeur frontend peut consulter Swagger UI (`/api/swagger-ui.html`) pour voir la structure exacte du JSON attendu par `POST /api/contrats` (quels champs sont requis, quels types, quelles énumérations), puis faire un essai directement dans l'interface pour valider sa compréhension.

### Complémentarité
- **Postman** : Pour des tests poussés, des scénarios complexes, l'automatisation, le debug approfondi
- **Swagger** : Pour la découverte rapide, la documentation toujours à jour, les tests exploratoires simples

## D. Anomalies SonarQube

### Anomalie 1 : Field injection is not recommended
- **Règle** : java:S3305 - Les dépendances ne doivent pas être injectées par champ (`@Autowired` sur un attribut) mais par constructeur
- **Explication** : L'injection par champ rend les tests unitaires plus difficiles (pas de mock possible sans réflexion), crée un couplage fort, et ne garantit pas l'immutabilité. L'injection par constructeur force l'explicitation des dépendances et permet de déclarer les champs `final`.
- **Correction** : Utiliser `@RequiredArgsConstructor` (Lombok) avec des champs `private final` au lieu de `@Autowired` sur les attributs. Cela génère automatiquement un constructeur avec toutes les dépendances finales.

### Anomalie 2 : Public APIs should have Javadoc
- **Règle** : java:S1176 - Les classes, méthodes et attributs publics devraient être documentés avec Javadoc
- **Explication** : La documentation Javadoc améliore la maintenabilité du code en expliquant le rôle de chaque élément, les paramètres attendus, les valeurs de retour et les exceptions levées. C'est particulièrement important pour les API publiques.
- **Correction** : Ajouter des commentaires Javadoc au-dessus de chaque classe publique et méthode publique avec `/** */`. Exemple :
```java
/**
 * Contrôleur REST pour la gestion des contrats.
 */
@RestController
public class ContratController {
    /**
     * Liste tous les contrats.
     * @return Liste de tous les contrats
     */
    @GetMapping
    public List<Contrat> findAll() { ... }
}
```

### Anomalie 3 : Remove this unused import
- **Règle** : java:S1128 - Les imports inutilisés polluent le code et réduisent la lisibilité
- **Explication** : Des imports non utilisés peuvent résulter de refactoring incomplets ou de code supprimé. Ils n'ont aucun impact fonctionnel mais augmentent la dette technique.
- **Correction** : Supprimer manuellement les imports non référencés, ou utiliser la fonction "Optimize Imports" de l'IDE (Ctrl+Alt+O dans IntelliJ).

### Anomalie 4 : Entities should not be exposed directly
- **Règle** : Architecture - Exposer directement les entités JPA couple l'API à la base de données
- **Explication** : Les entités JPA contiennent des détails d'implémentation (champs techniques, associations lazy, métadonnées Hibernate) qui ne devraient pas être visibles dans l'API. Tout changement de schéma impacte les clients.
- **Correction** : Introduire des DTO (Data Transfer Objects) dans le package `web.dto` avec des mappers pour convertir entités ↔ DTO. Cela sera traité dans le Chapitre 10.

## E. Questions

### 1. Quel composant reçoit toutes les requêtes, et comment retrouve-t-il la méthode du contrôleur à appeler ?

Le **DispatcherServlet** est le composant central de Spring MVC qui reçoit toutes les requêtes HTTP. Il agit comme un Front Controller.

**Processus de routage** :
1. Le DispatcherServlet reçoit la requête (ex: `GET /api/contrats/5`)
2. Il consulte le **HandlerMapping** qui analyse les annotations `@RequestMapping`, `@GetMapping`, etc. de tous les contrôleurs pour trouver une correspondance entre l'URL + le verbe HTTP et une méthode
3. Une fois la méthode identifiée, le DispatcherServlet l'invoque via un **HandlerAdapter**
4. La méthode retourne un objet (ex: `Contrat`), qui est converti en JSON par **Jackson** (car `@RestController` inclut `@ResponseBody`)
5. Le DispatcherServlet renvoie la réponse HTTP au client

### 2. Pourquoi ContratController dépend-il de IContratService et non de ContratRepository ni de ContratServiceImpl ?

**Dépendance à l'interface (IContratService)** :
- **Inversion de dépendance (SOLID)** : Le contrôleur dépend d'une abstraction, pas d'une implémentation concrète. Cela réduit le couplage.
- **Testabilité** : En dépendant de l'interface, on peut facilement injecter un mock dans les tests unitaires du contrôleur sans instancier tout le service réel.
- **Flexibilité** : Si l'implémentation change (ex: ajout d'un cache, migration vers une autre source de données), le contrôleur n'est pas impacté.

**Pourquoi pas le Repository** :
- **Séparation des couches** : Le contrôleur (web) ne doit pas connaître la couche d'accès aux données (repository). La couche Service encapsule la logique métier (validations, calculs, orchestration de plusieurs repositories). Accéder directement au repository violerait cette séparation et compliquerait l'ajout de règles métier.

**Pourquoi pas l'implémentation (ContratServiceImpl)** :
- Déclarer le type comme `ContratServiceImpl` couple le contrôleur à l'implémentation concrète et empêche le polymorphisme. Spring injecte automatiquement l'implémentation disponible de l'interface.

### 3. Pourquoi un POST renvoie-t-il 201 et un DELETE 204 ? Pourquoi une ressource absente donne-t-elle 404 et non 500 ?

**POST → 201 Created** :
- Le code `201` signifie "Created" : une nouvelle ressource a été créée avec succès sur le serveur
- C'est le code sémantiquement correct pour signaler la création (pas `200 OK` qui est trop générique)
- Le corps de la réponse contient généralement la ressource créée avec son identifiant généré

**DELETE → 204 No Content** :
- Le code `204` signifie "No Content" : l'opération a réussi mais il n'y a pas de corps à retourner
- C'est approprié pour DELETE car une fois la ressource supprimée, il n'y a plus rien à renvoyer
- Alternative : `200 OK` avec un message de confirmation, mais `204` est plus standard

**Ressource absente → 404 Not Found (pas 500)** :
- `404` signifie que la ressource demandée n'existe pas : c'est une erreur **client** (l'ID fourni est invalide ou la ressource a été supprimée)
- `500 Internal Server Error` signifie une erreur **serveur** (bug, exception non gérée, base inaccessible)
- Utiliser 404 pour une ressource absente est correct car le problème vient de la requête, pas du serveur. Le GlobalExceptionHandler traduit `ResourceNotFoundException` en 404.

### 4. Pourquoi PaiementController n'expose-t-il que des GET ? Quelle réponse reçoit un POST /api/paiements ?

**Lecture seule pour Paiement** :
- Les paiements sont créés **en cascade** via leur contrat parent (`cascade = ALL` sur `Contrat.paiements`)
- Ils n'ont pas de sens en dehors d'un contrat : un paiement appartient toujours à un contrat spécifique
- Pour créer un paiement, on passe par `POST /api/contrats` avec un tableau `paiements` dans le JSON
- Pour consulter les paiements, on peut soit lire le contrat entier, soit utiliser `GET /api/paiements` ou `GET /api/paiements/{id}`

**Réponse à POST /api/paiements** :
- **405 Method Not Allowed** : Le serveur indique que la méthode POST n'est pas autorisée sur cette ressource
- C'est correct car PaiementController ne déclare aucun `@PostMapping`, donc Spring MVC rejette automatiquement la requête POST

### 5. Quels risques présente l'exposition directe des entités JPA ? Pourquoi Contrat.paiements utilise-t-il @JsonManagedReference alors que Agence.vehicules utilise @JsonIgnore ?

**Risques de l'exposition directe des entités** :
1. **Couplage API/Base** : Tout changement de schéma (renommage de colonne, ajout de champ technique) impacte les clients de l'API
2. **Champs internes visibles** : Les champs techniques (version, timestamps Hibernate) ou sensibles sont exposés
3. **Associations LAZY problématiques** : Les proxies Hibernate causent des erreurs de sérialisation (`ByteBuddyInterceptor`) ou des requêtes N+1
4. **Pas de validation distincte** : Les règles de validation de l'entité (contraintes BDD) ne sont pas forcément adaptées aux besoins de l'API
5. **Sécurité** : Impossible de filtrer les champs selon le rôle de l'utilisateur

**Solution** : Utiliser des DTO (Data Transfer Objects) qui définissent un contrat stable et adapté à chaque endpoint.

**Contrat.paiements avec @JsonManagedReference** :
- **Relation de composition** : Les paiements appartiennent au contrat, sont créés et supprimés avec lui (`cascade = ALL, orphanRemoval = true`)
- **Besoin fonctionnel** : Lors de la création d'un contrat, on veut pouvoir fournir ses paiements dans le même JSON
- **@JsonManagedReference** permet d'inclure les paiements dans le JSON du contrat (sérialisation), tout en évitant la boucle infinie grâce à `@JsonBackReference` côté Paiement qui masque la référence inverse

**Agence.vehicules avec @JsonIgnore** :
- **Relation d'association** : Les véhicules existent indépendamment de leur agence, ils sont créés séparément et référencent leur agence par ID
- **Besoin fonctionnel** : Lors de la création d'un véhicule, on passe `{"agence": {"idAgence": 1}}`, pas la liste complète des véhicules de l'agence
- **@JsonIgnore** sur la liste évite la boucle de sérialisation et empêche de charger tous les véhicules lors d'un `GET /api/agences/{id}` (ce qui pourrait être lourd). Si on a besoin de la liste, on ferait `GET /api/vehicules?agenceId=1` (ou un DTO dédié).

**Différence clé** : `@JsonManagedReference/@JsonBackReference` pour les compositions où l'enfant est créé avec le parent, `@JsonIgnore` pour les associations où l'enfant est créé séparément et référence son parent.
