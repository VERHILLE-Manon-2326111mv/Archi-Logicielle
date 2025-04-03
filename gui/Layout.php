<?php
namespace gui;

/**
 * @Layout
 *
 * Gère l'affichage du template HTML de base du site
 */
class Layout
{
    protected $templateFile;

    public function __construct( $templateFile )
    {
        $this->templateFile = $templateFile;
    }

    /**
     * @param string $title
     * @param string $connexion
     * @param string $content
     *
     * Affiche le template HTML avec le contenu renseigné.
     */
    public function display($title, $connexion, $content)
    {
        $page = file_get_contents($this->templateFile);
        $page = str_replace(['%title%','%connexion%','%content%'], [$title, $connexion, $content], $page);
        echo $page;
    }

}