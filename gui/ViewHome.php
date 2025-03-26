<?php

namespace gui;

include_once "View.php";

class ViewHome extends View
{
    public function __construct($layout, $login, $presenter){
        parent::__construct($layout, $login);

        $this->title = 'Accueil';

        $this->content = '<h1>Home</h1>';

        $this->content .= '<a href="/index.php/produits">Voir les produits</a><br>';
        $this->content .= '<a href="/index.php/paniers">Voir les paniers</a><br>';
        $this->content .= '<a href="/index.php/commandes">Passer une commande</a><br>';
    }
}