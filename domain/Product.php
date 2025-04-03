<?php

namespace domain;

/**
 * @Commandes
 *
 * Classe représentant un produit.
 */
class Product {
    protected $id_produit;
    protected $nom;
    protected $quantite;
    protected $prix;
    protected $unite;

    public function __construct($id_produit = null, $nom = null, $quantite = null, $prix = null, $unite = null) {
        if ($id_produit !== null) {
            $this->id_produit = $id_produit;
            $this->nom = $nom;
            $this->quantite = $quantite;
            $this->prix = $prix;
            $this->unite = $unite;
        }
    }

    public function getId_produit() {
        return $this->id_produit;
    }

    public function setId_produit($id_produit) {
        $this->id_produit = $id_produit;
    }

    public function getNom() {
        return $this->nom;
    }

    public function setNom($nom) {
        $this->nom = $nom;
    }

    public function getQuantite() {
        return $this->quantite;
    }

    public function setQuantite($quantite) {
        $this->quantite = $quantite;
    }

    public function getPrix() {
        return $this->prix;
    }

    public function setPrix($prix) {
        $this->prix = $prix;
    }

    public function getUnite() {
        return $this->unite;
    }

    public function setUnite($unite) {
        $this->unite = $unite;
    }
}
?>