<?php

namespace gui;

include_once "View.php";

/**
 * @ViewHome
 *
 * Classe d'affichage de l'accueil.
 */
class ViewHome extends View
{
    public function __construct($layout){
        parent::__construct($layout);

        $this->title = 'Accueil';

        $this->content = '<h1>Home</h1>';

        $this->content .= '
            <nav>
                <ul>
                    <li><a href="/index.php">Acceuil</a></li>
                     <li><a href="/index.php/produit">Produit</a></li>
                    <li><a href="/index.php/panier">Panier</a></li>
                    <li><a href="/index.php/commande">Commande</a></li>
                </ul>
            </nav>';
    }
}