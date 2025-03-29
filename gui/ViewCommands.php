<?php

namespace gui;

include_once "View.php";

class ViewCommands extends View
{
    public function __construct($layout, $login, $presenter)
    {
        parent::__construct($layout, $login);

        $this->title = 'Passer une commande';

        $this->content = $presenter->getAllHampersHTML();

        $this->content .= '<a href="/index.php/home">Retour</a>';
    }
}