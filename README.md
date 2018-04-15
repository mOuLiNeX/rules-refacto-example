# rules-refacto-example

Développement "brut de décoffrage" (mais avec des tests :star:)

**Critiques :**
* Pas de logique métier explicite dans le modèle *Customer* (qui se limite au strict minimum de données)
* Calcul procédural avec des if combinés (même si Kotlin réduit un peu la complexité du code grace au *when*)  
* Difficile de tester unitairement les règles (indépendamment les unes des autres) 