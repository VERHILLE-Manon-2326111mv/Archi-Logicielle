<?php

namespace service;

class Checking
{
    protected $txt;

    public function getTxt()
    {
        return $this->txt;
    }

    public function authenticate($login, $password, $data)
    {
        return ($data->getUser($login, $password) != null);
    }

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

    public function getProduct($id, $data){
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

    public function getAllCommandes($data)
    {
        $commandes = $data->getAllCommandes();
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