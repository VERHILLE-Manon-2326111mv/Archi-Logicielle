<?php

namespace gui;

include_once "View.php";

class ViewProducts extends View
{
    public function __construct($layout, $presenter)
    {
        parent::__construct($layout);

        $this->title = 'Liste des produits';

        $this->content = $presenter->getAllProductsHTML();

        $this->content .= '<a href="/index.php/home">Retour</a>';
    }
}