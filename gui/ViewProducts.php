<?php

namespace gui;

include_once "View.php";

class ViewProducts extends View
{
    public function __construct($layout, $login, $presenter)
    {
        parent::__construct($layout, $login);

        $this->title = 'Liste des produits';

        $this->content = $presenter->getAllProductsHTML();

        $this->content .= '<a href="/index.php/home">Retour</a>';
    }
}