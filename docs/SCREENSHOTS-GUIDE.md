# 📸 Guide Rapide - Screenshots SonarQube for IDE

## 🎯 Fichiers créés pour les screenshots

J'ai créé 3 fichiers avec les problèmes **AVANT corrections** :

1. **`src/main/java/esprit/tn/autoloc/Entities/ContratAvantCorrections.java`**
2. **`src/main/java/esprit/tn/autoloc/Entities/ClientAvantCorrections.java`**
3. **`src/test/java/esprit/tn/autoloc/TestAvantCorrections.java`**

Ces fichiers contiennent volontairement les anomalies que SonarQube va détecter.

---

## 📋 Les 4 screenshots à capturer

### Screenshot #1 : Annotations Lombok groupées
- **Fichier** : `ContratAvantCorrections.java`
- **Ligne** : 19 (les annotations @Getter @Setter @ToString...)
- **Problème** : Lisibilité réduite
- **Comment** : Ouvrir le fichier et survoler la ligne

### Screenshot #2 : @EqualsAndHashCode sans configuration
- **Fichier** : `ContratAvantCorrections.java` ou `ClientAvantCorrections.java`
- **Ligne** : 19 (@EqualsAndHashCode)
- **Règle** : Lombok on JPA entities
- **Sévérité** : Major
- **Comment** : Cliquer sur @EqualsAndHashCode, voir l'avertissement

### Screenshot #3 : @EqualsAndHashCode.Exclude redondant
- **Comment** : Compiler le projet
```bash
./mvnw clean compile
```
- **Chercher** : [WARNING] The @Exclude annotation is not needed
- **Capturer** : Les warnings dans la console

### Screenshot #4 : Test vide sans assertion
- **Fichier** : `TestAvantCorrections.java`
- **Ligne** : 14-16 (méthode contextLoads vide)
- **Règle** : java:S2699 - Tests should include assertions
- **Sévérité** : Major

---

## 🔧 Comment lancer l'analyse SonarQube

### Méthode 1 : Analyse complète du projet
1. **Clic droit** sur le projet dans l'arbre
2. **Analyze** > **Analyze with SonarQube for IDE**
3. Attendre la fin de l'analyse
4. Ouvrir le panneau **SonarQube** en bas

### Méthode 2 : Analyse d'un fichier
1. Ouvrir un fichier (ex: ContratAvantCorrections.java)
2. Les problèmes apparaissent soulignés en temps réel
3. Survoler pour voir le message
4. Alt + Enter pour voir les suggestions

---

## 📊 Panneau SonarQube

**Pour l'ouvrir** :
- **View** > **Tool Windows** > **SonarQube**
- Ou cliquer sur l'onglet **SonarQube** en bas

**Ce que vous y verrez** :
- Liste de tous les problèmes détectés
- Filtres par sévérité (Blocker, Critical, Major, Minor, Info)
- Nombre total d'issues
- Fichiers concernés

**📸 Bon screenshot à prendre** : Vue d'ensemble du panneau

---

## ✅ Pour votre rapport

### Structure recommandée pour chaque anomalie :

```markdown
### Anomalie #X : [Titre]

**Règle SonarQube** : [Nom de la règle]
**Sévérité** : [Blocker/Critical/Major/Minor/Info]
**Fichier** : [Nom du fichier:ligne]

#### Screenshot
[Insérer l'image ici]

#### Code avant correction
[Copier le code avec le problème]

#### Code après correction
[Copier le code corrigé]

#### Explication
[Expliquer pourquoi c'est un problème et comment vous l'avez corrigé]
```

---

## 📝 Tableau pour docs/repository-notes.md

Le tableau des anomalies est déjà dans votre `docs/repository-notes.md` :

| Anomalie | Règle / explication | Correction apportée |
|----------|---------------------|---------------------|
| Annotations Lombok groupées | Lisibilité | Séparation sur plusieurs lignes |
| @EqualsAndHashCode sans config | Lombok on JPA entities | `@EqualsAndHashCode(onlyExplicitlyIncluded=true)` |
| @Exclude redondant | Lombok warning | Suppression des annotations redondantes |
| Test sans assertion | java:S2699 | Ajout de commentaires explicatifs |

---

## 🎓 Conseils

1. **Installez SonarQube for IDE** d'abord (Settings > Plugins > Marketplace)
2. **Redémarrez IntelliJ** après l'installation
3. **Attendez quelques secondes** après avoir ouvert un fichier (analyse en temps réel)
4. **Capturez en haute résolution** pour que le texte soit lisible
5. **Montrez le contexte** : un peu de code autour du problème

---

## 🚀 Comparaison AVANT/APRÈS

### Fichiers AVANT (avec problèmes)
- `ContratAvantCorrections.java`
- `ClientAvantCorrections.java`
- `TestAvantCorrections.java`

### Fichiers APRÈS (corrigés)
- `Contrat.java`
- `Client.java`
- `AutoLocApplicationTests.java`

Vous pouvez ouvrir les deux côte à côte pour montrer la différence !

---

## ✅ Checklist

- [ ] SonarQube for IDE installé et redémarré
- [ ] Ouvrir ContratAvantCorrections.java
- [ ] Screenshot #1 : Annotations groupées
- [ ] Screenshot #2 : @EqualsAndHashCode
- [ ] Compiler : `./mvnw clean compile`
- [ ] Screenshot #3 : Warnings compilation
- [ ] Ouvrir TestAvantCorrections.java
- [ ] Screenshot #4 : Test vide
- [ ] Screenshot bonus : Panneau SonarQube
- [ ] Compléter le rapport avec explications

---

Bonne chance pour vos screenshots ! 🎉
