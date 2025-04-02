# API Commande - Projet Agricole

Cette section de l'API est dédiée à la gestion des commandes et des paniers dans un contexte agricole. Elle permet d'effectuer différentes opérations sur les commandes et les paniers via des endpoints spécifiques.

## Fonctionnalités Implémentées

### API Commande
- `getCommande` : Récupérer une commande spécifique.
- `getAllCommande` : Récupérer toutes les commandes.
- `updateCommande` : Mettre à jour une commande.
- `deleteCommande` : Supprimer une commande.
- `createCommande` : Créer une nouvelle commande.
- `valideCommande` : Valider une commande.

### API Panier (Gestion des paniers liés aux commandes)
- `getAllPanierCommande` : Récupérer tous les paniers d'une commande pour un utilisateur donné.
- `addPanier` : Ajouter un panier à une commande.
- `updatePanier` : Mettre à jour un panier.
- `deletePanier` : Supprimer un panier.

## Utilisation
L'API permet aux utilisateurs d'effectuer des actions sur les commandes et les paniers via des requêtes HTTP. Les données sont échangées au format JSON.

### Exemples de requêtes

#### Récupérer une commande spécifique
**GET** `/commande/{id}`

#### Récupérer toutes les commandes
**GET** `/commande`

#### Créer une nouvelle commande
**POST** `/commande`

Exemple de payload JSON :
```json
{
  "id": 1,
  "utilisateur_id": 123,
  "date": "2024-04-01",
  "status": "en cours"
}
```

#### Valider une commande
**POST** `/commande/{id}/valide`

#### Ajouter un panier à une commande
**POST** `/commande/{id}/panier`

Exemple de payload JSON :
```json
{
  "produit_id": 45,
  "quantite": 10
}
```

## Dépendances et Installation
L'API est développée en Java et nécessite les dépendances suivantes :
- Java 11+
- Framework Spring Boot
- Base de données relationnelle (PostgreSQL, MySQL...)

### Installation
1. Cloner le repository
2. Configurer la base de données dans `application.properties`
3. Compiler et exécuter le projet

## Auteur
**BOISDANGHIEN Evan**

