<?php

namespace gui;

include_once "View.php";

class ViewCommands extends View
{
    public function __construct($layout, $id, $presenter,$data)
    {
        parent::__construct($layout);

        $this->title = 'Voir les commandes';

        if($id != null) {
            $this->content = $presenter->getCurrentCommandesHTML($id,$data);
        } else {
            $this->content = $presenter->getAllCommandes($data);
        }

        $this->content .= '<a href="/">Retour</a>';
    }
}