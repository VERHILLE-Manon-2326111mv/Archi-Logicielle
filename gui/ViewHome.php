<?php

namespace gui;

include_once "View.php";

class ViewHome extends View
{
    public function __construct($layout, $login, $presenter){
        parent::__construct($layout, $login);

        $this->title = 'Accueil';

        $this->content = '<h1>Home</h1>';

        $this->content .= '
            <nav>
                <ul>
                    <li><a href="/index.php">Acceuil</a></li>
                    <li><a href="/index.php/annonces">Annonces IUT</a></li>
                    <li><a href="/index.php/annoncesAlternance">Alternance - Pôle Emploi</a></li>
                    <li><a href="/index.php/annoncesEmploi">Jobs - Pôle Emploi</a></li>
                    <li><a href="/index.php/logout">Déconnexion</a></li>
                </ul>
            </nav>';
    }
}