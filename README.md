# rules-refacto-example

Développement avec un moteur de règles (Rules Design Pattern) (avec toujours plus de tests :star: :star: :star:)

Quelques sources de lecture :
* https://martinfowler.com/apsupp/spec.pdf
* https://martinfowler.com/bliki/RulesEngine.html
* http://www.michael-whelan.net/rules-design-pattern/
* https://www.automatetheplanet.com/rules-design-pattern/


**Evolutions :** 
* Réduction de la complexité du calcul en séparant la logique en plusieurs "règles" : chaque règle ne traite qu'un cas et un seul (Single Responsability Principe). En cas de besoin, les règles peuvent s'appeler entre elles
* Plus de traitement procédural avec imbrication de if ou when : c'est la combinaison des règles (par un fold) qui gère tous les cas
* Les règles sont testables unitairement

**Critiques :**
* Contrairement au pattern académique, l'interface *IDiscountRule* mélange à la fois la condition d'application et le calcul de la remise. Donc toutes les règles sont évaluées même si ça n'est pas utile.
* *(même si ça n'est pas le cas ici puisque les règles sont indépendantes)*, le déboguage est moins aisé puisqu'on n'a plus la trame principale. 