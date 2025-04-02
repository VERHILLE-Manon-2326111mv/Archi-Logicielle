<?php

namespace gui;

include_once "View.php";

class ViewProducts extends View
{
    public function __construct($layout, $id, $presenter, $data)
    {
        parent::__construct($layout);

        $this->title = 'Liste des produits';

        if($id != null) {
            $this->content = $presenter->getCurentProductHTML($id, $data);
        } else {
            $this->content = $presenter->getAllProductsHTML($data);
        }

        $this->content .= '<a href="/">Retour</a>';
    }
}