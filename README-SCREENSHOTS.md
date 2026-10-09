# 📸 Guide Rapide - Screenshots SonarQube

## 🎯 Objectif
Capturer les anomalies SonarQube for IDE pour votre rapport d'Atelier 3.

---

## ⚡ 3 Fichiers de Démonstration Créés

### 1. ContratAvantCorrections.java
**Localisation** : `src/main/java/esprit/tn/autoloc/Entities/`  
**Problèmes** : Annotations groupées + @EqualsAndHashCode sans config

### 2. ClientAvantCorrections.java
**Localisation** : `src/main/java/esprit/tn/autoloc/Entities/`  
**Problèmes** : Alternative pour @EqualsAndHashCode

### 3. TestAvantCorrections.java
**Localisation** : `src/test/java/esprit/tn/autoloc/`  
**Problèmes** : Test vide sans assertion (java:S2699)

---

## 🔧 Installation SonarQube for IDE

```
IntelliJ > Settings > Plugins > Marketplace
Rechercher : "SonarQube for IDE"
Install > Restart
```

---

## 📸 4 Screenshots à Prendre

### #1 : Annotations Groupées
- **Fichier** : ContratAvantCorrections.java, ligne 19
- **Problème** : `@Getter @Setter @ToString...` sur une ligne
- **Action** : Ouvrir le fichier, survoler la ligne

### #2 : @EqualsAndHashCode Sans Config
- **Fichier** : ContratAvantCorrections.java, ligne 19
- **Problème** : `@EqualsAndHashCode` sans paramètre (risque JPA)
- **Action** : Cliquer sur l'annotation, voir l'avertissement

### #3 : Warnings Compilation
- **Action** : Exécuter `./mvnw clean compile`
- **Problème** : "[WARNING] The @Exclude annotation is not needed"
- **Action** : Capturer les warnings dans la console

### #4 : Test Vide
- **Fichier** : TestAvantCorrections.java, ligne 14-16
- **Problème** : Test sans assertion (java:S2699)
- **Action** : Ouvrir le fichier, survoler la méthode

---

## 📚 Documentation Disponible

1. **docs/repository-notes.md** - Tableau récapitulatif
2. **docs/SONARLINT-FIXES.md** - Documentation complète
3. **docs/SCREENSHOTS-GUIDE.md** - Guide détaillé
4. **docs/ATELIER3-SUMMARY.md** - Résumé Atelier 3

---

## ✅ Comparaison AVANT/APRÈS

### Fichiers AVANT (problèmes)
- ContratAvantCorrections.java
- ClientAvantCorrections.java
- TestAvantCorrections.java

### Fichiers APRÈS (corrigés)
- Contrat.java
- Client.java
- AutoLocApplicationTests.java

---

## 🚀 Checklist

- [ ] Installer SonarQube for IDE
- [ ] Redémarrer IntelliJ
- [ ] Ouvrir ContratAvantCorrections.java
- [ ] Screenshot #1 : Annotations groupées
- [ ] Screenshot #2 : @EqualsAndHashCode
- [ ] Exécuter ./mvnw clean compile
- [ ] Screenshot #3 : Warnings
- [ ] Ouvrir TestAvantCorrections.java
- [ ] Screenshot #4 : Test vide
- [ ] Rédiger le rapport avec les 4 anomalies

---

## 🎉 Tout Est Prêt !

Les fichiers de démonstration sont créés.  
La documentation est complète.  
Il ne reste plus qu'à installer SonarQube for IDE et capturer les screenshots !

**Bonne chance ! 📸**
