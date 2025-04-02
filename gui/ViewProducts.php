<?php

namespace gui;

include_once "View.php";

class ViewProducts extends View
{
    public function __construct($layout, $id, $presenter)
    {
        parent::__construct($layout);

        $this->title = 'Liste des produits';

        if($id != null) {
            $this->content = $presenter->getCuurentProductHTML($id);
        } else {
            $this->content = $presenter->getAllProductsHTML();
        }

        $this->content .= '<a href="/index.php/home">Retour</a>';
    }
}