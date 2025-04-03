<?php
namespace gui;

/**
 * @View
 *
 * Gère l'affichage des vues avec un layout.
 */
abstract class View
{
    protected $title = '';
    protected $content = '';
    protected $layout;

    public function __construct($layout)
    {
        $this->layout = $layout;
    }

    public function display()
    {
        $this->layout->display( $this->title, "", $this->content );
    }
}