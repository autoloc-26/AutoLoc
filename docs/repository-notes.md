# Atelier 3 - Notes sur la couche Repository

## 1. Choix des interfaces Spring Data JPA

| Interface | Étend | Justification |
|-----------|-------|---------------|
| IContratRepository | JpaRepository<Contrat, Long> | CRUD complet, findAll renvoie une List, saveAndFlush disponible. Gestion des Paiements avec cascade et orphanRemoval. |
| IPaiementRepository | JpaRepository<Paiement, Long> | CRUD complet pour lecture des paiements. Les modifications passent normalement par le Contrat (composition). |
| IVehiculeRepository | JpaRepository<Vehicule, Long> | CRUD complet, tri et pagination disponibles pour lister les véhicules. Méthodes flush utiles pour synchronisation immédiate. |
| IReservationRepository | JpaRepository<Reservation, Long> | CRUD complet avec List. Pagination nécessaire pour afficher les réservations par page. |
| IClientRepository | JpaRepository<Client, Long> | CRUD complet. Interface complète pour gérer les clients avec possibilité de tri et recherche. |
| IAgenceRepository | JpaRepository<Agence, Long> | CRUD complet. JpaRepository offre toutes les opérations nécessaires pour gérer les agences. |
| IEmployeRepository | JpaRepository<Employe, Long> | CRUD complet avec List. Méthodes de tri utiles pour lister les employés par rôle. |
| IEquipementRepository | JpaRepository<Equipement, Long> | CRUD complet. Interface standard pour gérer les équipements des véhicules. |
| IMaintenanceRepository | JpaRepository<Maintenance, Long> | CRUD complet. Pagination et tri nécessaires pour l'historique des maintenances. |

## 2. Anomalies détectées par SonarQube for IDE

| Anomalie SonarQube for IDE | Règle / explication | Correction apportée |
|----------------------------|---------------------|---------------------|
| Imports inutilisés dans les entités | java:S1128 - Les imports inutilisés encombrent le code et réduisent la lisibilité | Suppression des imports non utilisés dans toutes les classes d'entités |
| Utilisation de @Data sur les entités JPA | Avoid using Lombok's @Data on JPA entities - Peut causer des problèmes avec equals/hashCode et les proxies lazy | Remplacement de @Data par @Getter et @Setter ciblés, avec @EqualsAndHashCode(of="id") explicite |
| Classes ou méthodes inutilisées | java:S1068 / java:S1144 - Code mort qui réduit la maintenabilité | Suppression du code mort et des méthodes/attributs non référencés |

## 3. Points importants à retenir

### Comportement de save()
- **Identifiant null (nouvelle entité)** : déclenche un `persist` → INSERT. L'ID est généré par MySQL (IDENTITY).
- **Identifiant renseigné (entité existante)** : déclenche un `merge` → SELECT puis UPDATE si modification.
- Toujours utiliser l'objet retourné par save(), qui peut différer de celui passé en paramètre.

### Suppressions et cascade
- Les méthodes `delete()` et `deleteById()` de CrudRepository passent par le contexte de persistance : **cascade REMOVE et orphanRemoval s'appliquent**.
- Les méthodes `deleteAllInBatch()` et `deleteAllByIdInBatch()` **contournent le contexte** : la cascade ne s'applique pas, risque de violations de contraintes FK.

### JpaRepository vs autres interfaces
- **JpaRepository** combine ListCrudRepository + ListPagingAndSortingRepository + méthodes JPA (flush, batch).
- Depuis Spring Data 3, **PagingAndSortingRepository n'étend plus CrudRepository** : pour avoir les deux, utiliser JpaRepository.
- Méthodes retournant des `List` plutôt que des `Iterable` : plus pratique (size(), get(i), stream()).

## 4. Vérification du démarrage

Lors du démarrage de l'application, les logs doivent indiquer :
```
Found 9 JPA repository interfaces
```

Cela confirme que Spring Data a bien détecté et créé les proxies pour les 9 interfaces repository.
