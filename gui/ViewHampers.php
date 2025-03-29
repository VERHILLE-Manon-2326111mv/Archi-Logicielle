<?php

namespace gui;

include_once "View.php";

class ViewHampers extends View
{
    public function __construct($layout, $login, $presenter)
    {
        parent::__construct($layout, $login);

        $this->title = 'Liste des paniers';

        $this->content = $presenter->getAllHampersHTML();

        $this->content .= '<a href="/index.php/home">Retour</a>';
    }
}