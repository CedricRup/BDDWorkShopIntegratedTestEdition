# language: fr
Fonctionnalité: Calculer les points de sagesse

  Scénario: Une bonne action

    Etant donné un enfant qui s'appelle "CRU"
    Et que "CRU" croit au Père Noel
    Quand "CRU" fait
      | Action                           | Type  | Points | Date       |
      | Aider une grand mère à traverser | Bonne | 20     | 23/12/2017 |
    Alors les points de sagesse de "CRU" pour 2017 valent 20
