<?php

namespace gui;

include_once "View.php";

class ViewHampers extends View
{
    public function __construct($layout, $id, $presenter,$data)
    {
        parent::__construct($layout);

        $this->title = 'Liste des paniers';

        if($id != null) {
            $this->content = $presenter->getCurrentHampersHTML($id,$data);
        } else {
            $this->content = $presenter->getAllHampersHTML($data);
        }

        $this->content .= '<a href="/">Retour</a>';
    }
}