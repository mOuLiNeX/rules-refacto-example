# rules-refacto-example

Emergence d'un moteur de règles un peu plus générique

**Evolutions :** 
* Filtrage des règles à appliquer puisque maintenant on distingue la condition d'application et le calcul de la remise.
* Proposition d'une composition de règles (*composable/composite*) pour intégrer des "règles imbriquées"
* **Toutes** Les règles sont testables unitairement (même les sous-règles en cas de composition)

**Critiques :**
* Le déboguage reste un peu délicat (le plus efficace reste un point d'arrêt à chaque étape des *folds*). 