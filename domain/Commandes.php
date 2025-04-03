<?php

namespace domain;

/**
 * @Commandes
 *
 * Classe représentant une commande.
 */
class Commandes {
    protected $id;
    protected $id_user;
    protected $prix;
    protected $valide;
    protected $date_echeance;
    protected $point_relai;
    protected $panier = null;

    /**
     * Constructeur de la classe Commande
     */
    public function __construct($id, $id_user = null, $prix = null, $valide = null, $panier=[],$date_echeance = null, $point_relai = null) {
        if ($id_user !== null) {
            $this->id_user = $id_user;
            $this->prix = $prix;
            $this->valide = $valide;
            $this->panier = $panier;
            $this->date_echeance = $date_echeance;
            $this->point_relai = $point_relai;
            $this->id = $id;
        }
    }

    public function getId() {
        return $this->id;
    }

    public function setId($id) {
        $this->id = $id;
    }

    public function getId_user() {
        return $this->id_user;
    }

    public function setId_user($id_user) {
        $this->id_user = $id_user;
    }

    public function getPrix() {
        return $this->prix;
    }

    public function setPrix($prix) {
        $this->prix = $prix;
    }

    public function isValide() {
        return $this->valide;
    }

    public function setValide($valide) {
        $this->valide = $valide;
    }

    public function getDate_echeance() {
        return $this->date_echeance;
    }

    public function setDate_echeance($date_echeance) {
        $this->date_echeance = $date_echeance;
    }

    public function getPoint_relai() {
        return $this->point_relai;
    }

    public function setPoint_relai($point_relai) {
        $this->point_relai = $point_relai;
    }

    /**
     * Getter de la classe Commande
     * @return array|null
     */
    public function getPanier() {
        return $this->panier;
    }

    /**
     * Setter de la classe Commande
     * @param array $panier
     */
    public function setPanier($panier) {
        $this->panier = $panier;
    }
}
?>