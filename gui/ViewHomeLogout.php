<?php

namespace gui;

include_once "View.php";

class ViewHomeLogout extends View
{
    public function __construct($login, $layout){
        parent::__construct($layout);

        $this->title = 'Accueil';

        $this->content = '<h1>Home</h1>';

        $this->content .= '
            <nav>
                <ul>
                    <li><a href="/index.php">Se déconnecter</a></li>
                    <li><a href="/index.php/product">Voir les produits</a></li>
                    <li><a href="/index.php/hamper">Voir les paniers</a></li>
                    <li><a href="/index.php/commande">Passer une commande</a></li>
                </ul>
            </nav>';
    }
}