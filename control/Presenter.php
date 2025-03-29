<?php
namespace control;
class Presenter
{
    protected $check;

    public function __construct($check)
    {
        $this->check = $check;
    }

    public function getAllProductsHTML(){
        $content = null;
        if($this->check->getTxt != null){
            $content = '<h1>Liste des produits</h1> <ul>';
            foreach($this->check->getTxt() as $product){
                $content .= '<li>';
                $content .= '<a href="/index.php/product?id=' . $product['id'] . '">' . $product['title'] . '</a>';
                $content .= '</li>';
            }
            $content .= '</ul>';
        }
        return $content;
    }

    public function getAllCommandesHTML(){
        $content = null;
        if($this->check->getTxt != null){
            $content = '<h1>Liste des Commandes</h1> <ul>';
            foreach($this->check->getTxt() as $commande){
                $content .= '<li>';
                $content .= '<a href="/index.php/commandes?id=' . $commande['id'] . '">' . $commande['title'] . '</a>';
                $content .= '</li>';
            }
            $content .= '</ul>';
        }
        return $content;
    }

    public function getAllHampersHTML(){
        $content = null;
        if($this->check->getTxt != null){
            $content = '<h1>Liste des Paniers</h1> <ul>';
            foreach($this->check->getTxt() as $hamper){
                $content .= '<li>';
                $content .= '<a href="/index.php/commandes?id=' . $hamper['id'] . '">' . $hamper['title'] . '</a>';
                $content .= '</li>';
            }
            $content .= '</ul>';
        }
        return $content;
    }
}