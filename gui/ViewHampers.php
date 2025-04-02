<?php

namespace gui;

include_once "View.php";

class ViewHampers extends View
{
    public function __construct($layout, $id, $presenter)
    {
        parent::__construct($layout);

        $this->title = 'Liste des paniers';

        if($id != null) {
            $this->content = $presenter->getCuurentHamperHTML($id);
        } else {
            $this->content = $presenter->getAllHampersHTML();
        }

        $this->content .= '<a href="/index.php/home">Retour</a>';
    }
}