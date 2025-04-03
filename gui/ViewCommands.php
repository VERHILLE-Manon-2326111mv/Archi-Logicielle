<?php

namespace gui;

include_once "View.php";

/**
 * @ViewCommands
 *
 * Classe d'affichage des commandes.
 */
class ViewCommands extends View
{
    public function __construct($layout, $id, $presenter,$data)
    {
        parent::__construct($layout);

        $this->title = 'Voir les commandes';

        if($id != null) {
            $this->content = $presenter->getCurrentCommandHTML($id,$data);
        } else {
            $this->content = $presenter->getAllCommandsHTML($data);
        }

        $this->content .= '<a href="/">Retour</a>';
    }
}