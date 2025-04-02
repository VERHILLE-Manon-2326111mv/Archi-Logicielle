<?php

namespace gui;

include_once "View.php";

class ViewHome extends View
{
    public function __construct($layout){
        parent::__construct($layout);

        $this->title = 'Accueil';

        $this->content = '<h1>Home</h1>';

        $this->content .= '
            <nav>
                <ul>
                    <li><a href="/index.php/login">Se connecter</a></li>
                    <li><a href="/index.php/product">Voir les produits</a></li>
                    <li><a href="/index.php/hamper">Voir les paniers</a></li>
                </ul>
            </nav>';
    }
}