# rules-refacto-example

Développement avec un moteur de règles (Rules Design Pattern) (avec toujours plus de tests :star: :star: :star:)

**Evolutions :** 
* Filtrage des règles à appliquer puisque maintenant on distingue la condition d'application et le calcul de la remise.
* Composition de règles basées sur la possibilité de combiner des prédicats et des fonctions (via les interfaces fonctionnelles de Java8)
* Les règles sont des objets (plutôt ques classes à usage unique) unitairement testables 
* Plus compréhensible que la composition des règles expérimentée sur la branche `rulesEnhanced`

**Critiques :**
* Le débogage reste un peu délicat (le plus efficace reste un point d'arrêt à chaque étape des *folds*).  
