# rules-refacto-example

Développement avec un moteur de règles (Rules Design Pattern) (avec toujours plus de tests :star: :star: :star:)

**Evolutions :** 
* Filtrage des règles à appliquer puisque maintenant on distingue la condition d'application et le calcul de la remise.
* Composition de règles basées sur la possibilité de combiner des prédicats et des fonctions (via les interfaces fonctionnelles de Java8)
* Les règles sont des objets (plutôt ques classes à usage unique) unitairement testables 
* Plus compréhensible que la composition des règles expérimentée sur la branche `rulesEnhanced`

**Critiques :**
* Le déboguage reste un peu délicat (le plus efficace reste un point d'arrêt à chaque étape des *folds*).  



[![Sonarcloud Status](https://sonarcloud.io/api/project_badges/measure?project=mOuLiNeX_rules-refacto-example&metric=alert_status)](https://sonarcloud.io/dashboard?id=mOuLiNeX_rules-refacto-example) 
[![SonarCloud Coverage](https://sonarcloud.io/api/project_badges/measure?project=mOuLiNeX_rules-refacto-example&metric=coverage)](https://sonarcloud.io/component_measures/metric/coverage/list?id=mOuLiNeX_rules-refacto-example)
[![SonarCloud Bugs](https://sonarcloud.io/api/project_badges/measure?project=mOuLiNeX_rules-refacto-example&metric=bugs)](https://sonarcloud.io/component_measures/metric/reliability_rating/list?id=mOuLiNeX_rules-refacto-example)
[![SonarCloud Vulnerabilities](https://sonarcloud.io/api/project_badges/measure?project=mOuLiNeX_rules-refacto-example&metric=vulnerabilities)](https://sonarcloud.io/component_measures/metric/security_rating/list?id=mOuLiNeX_rules-refacto-example)
