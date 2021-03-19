# État interne :

---

• mode : une valeur parmi {DClasse, DAttribut, DMethode, DParametre, FClasse, FMethode}, commence à
FClasse.
• estAbstrait : un booléen, commence à faux.
• nbrOuverture : un entier, commence à zéro.

# Commandes :

---
### abstrait
* Il faut que estAbstrait soit à faux.
- Placer estAbstrait à vrai.

### classeDebut( identificateur )
* Cette commande est valide si mode ∈ {DClasse, FClasse, DAttribut, FMethode}.
- Augmenter nbrOuverture de 1.
- Le mode devient DClasse.
- Placer estAbstrait à faux.

### classeFin
* Cette commande est valide si mode ∈ {DClasse, FClasse, DAttribut, FMethode}.
* Il faut que estAbstrait soit à faux.
* Il faut aussi que nbrOuverture soit plus grand que zéro.
- Diminuer nbrOuverture de 1.
- Le mode devient FClasse.

### attribut( identificateur )
* Cette commande est valide si mode ∈ {DClasse, DAttribut}.
* Il faut que estAbstrait soit à faux.
- Le mode devient DAttribut.

### methodeDebut( identificateur, identificateur )
* Cette commande est valide si mode ∈ {DClasse, DAttribut, FMethode}.
- Le mode devient DMethode.
- Placer estAbstrait à faux.

### parametre( identificateur, identificateur )
* Cette commande est valide si mode ∈ {DMethode, DParametre}.
* Il faut que estAbstrait soit à faux.
- Le mode devient DParametre.

### methodeFin
* Cette commande est valide si mode ∈ {DMethode, DParametre}.
* Il faut que estAbstrait soit à faux.
- Le mode devient FMethode.