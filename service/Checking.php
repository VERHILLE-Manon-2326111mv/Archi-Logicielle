<?php

namespace service;

/**
 * @Checking
 *
 * Gère la vérification des données et la transformation des objets en tableaux.
 */
class Checking
{

    protected $txt;

    /**
     * Récupère les données stockées.
     *
     * @return array
     */
    public function getTxt()
    {
        return $this->txt;
    }

    /**
     * Authentifie un utilisateur.
     *
     * @param string $login Login de l'utilisateur
     * @param string $password Mot de passe de l'utilisateur
     * @param mixed $data Source de données pour la vérification
     * @return bool True si l'authentification réussit, false sinon
     */
    public function authenticate($login, $password, $data)
    {
        return ($data->getUser($login, $password) != null);
    }

    /**
     * Récupère tous les produits et les transforme en tableau.
     *
     * @param mixed $data Source de données contenant les produits
     * @return array Liste des produits formatée
     */
    public function getAllProducts($data)
    {
        $products = $data->getAllProducts();

        $this->txt = array();
        foreach ($products as $product) {
            $this->txt[] = [
                'id' => $product->getId_produit(),
                'Nom' => $product->getNom(),
                'Quantité' => $product->getQuantite(),
                'Prix' => $product->getPrix(),
                'Unité' => $product->getUnite()
            ];
        }

        return $this->txt;
    }

    /**
     * Récupère un produit spécifique par son ID.
     *
     * @param int $id Identifiant du produit
     * @param mixed $data Source de données contenant les produits
     * @return array Informations du produit formatées
     */
    public function getProduct($id, $data)
    {
        $product = $data->getProductById($id);

        $this->txt[] = [
            'id' => $product->getId_produit(),
            'Nom' => $product->getNom(),
            'Quantité' => $product->getQuantite(),
            'Prix' => $product->getPrix(),
            'Unité' => $product->getUnite()
        ];

        return $this->txt;
    }

    /**
     * Récupère tous les paniers et les transforme en tableau.
     *
     * @param mixed $data Source de données contenant les paniers
     * @return array Liste des paniers formatée
     */
    public function getAllHampers($data)
    {
        $hampers = $data->getAllHampers();

        $this->txt = [];
        foreach ($hampers as $hamper) {
            $this->txt[] = [
                'id_product' => $hamper->getIdProduct(),
                'Nom' => $hamper->getName(),
                'Date_Mise_à_Jour' => $hamper->getMaj(),
                'Prix' => $hamper->getPrice(),
                'Quantité' => $hamper->getQuantity()
            ];
        }

        return $this->txt;
    }

    /**
     * Récupère un panier spécifique par son ID.
     *
     * @param int $id Identifiant du panier
     * @param mixed $data Source de données contenant les paniers
     * @return array Informations du panier formatées
     */
    public function getHamper($id, $data)
    {
        $hamper = $data->getHamperById($id);

        $hamperData = [
            'id_product' => $hamper->getIdProduct(),
            'Nom' => $hamper->getName(),
            'Date_Mise_à_Jour' => $hamper->getMaj(),
            'Prix' => $hamper->getPrice(),
            'Quantité' => $hamper->getQuantity(),
            'Produits' => $hamper->getProducts()
        ];

        $this->txt[] = $hamperData;

        return $this->txt;
    }

    /**
     * Récupère toutes les commandes et les transforme en tableau.
     *
     * @param mixed $data Source de données contenant les commandes
     * @return array Liste des commandes formatée
     */
    public function getAllCommandsHTML($data)
    {
        $commandes = $data->getAllCommandsHTML();
        $this->txt = [];
        foreach ($commandes as $commande) {
            $this->txt[] = [
                'id' => $commande->getId(),
                'id_user' => $commande->getId_user(),
                'prix' => $commande->getPrix(),
                'valide' => $commande->isValide(),
                'date_echeance' => $commande->getDate_echeance(),
                'point_relai' => $commande->getPoint_relai()
            ];
        }

        return $this->txt;
    }

    /**
     * Récupère une commande spécifique par son ID.
     *
     * @param int $id Identifiant de la commande
     * @param mixed $data Source de données contenant les commandes
     * @return array Informations de la commande formatées
     */
    public function getCommande($id, $data)
    {
        $commande = $data->getCommandeById($id);
        $commandeData = [
            'id' => $commande->getId(),
            'id_user' => $commande->getId_user(),
            'prix' => $commande->getPrix(),
            'valide' => $commande->isValide(),
            'date_echeance' => $commande->getDate_echeance(),
            'point_relai' => $commande->getPoint_relai(),
            'paniers' => $commande->getPanier()
        ];

        $this->txt[] = $commandeData;

        return $this->txt;
    }
}