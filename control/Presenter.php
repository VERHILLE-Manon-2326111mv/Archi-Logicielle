<?php
namespace control;
class Presenter
{
    protected $check;

    public function __construct($check)
    {
        $this->check = $check;
    }

    public function getAllProductsHTML($data){
        $content = null;
        if($this->check->getTxt != null){
            $content = '<h1>Liste des produits</h1> <ul>';
            foreach($this->check->getAllProducts($data) as $product){
                $content .= '<li>';
                $content .= '<a href="/index.php/product?id=' . $product['id'] . '">' . $product['name'] . '</a>';
                $content .= '</li>';
            }
            $content .= '</ul>';
        }
        return $content;
    }

    public function getCurrentProductHTML($id, $data)
    {
        $content = null;
        if ($this->check->getTxt != null) {
            $product = $this->check->getProduct($id, $data)[0];

            $content = '<h1>' . $product['name'] . '</h1>';
            $content .= '<div class="quantite">' . $product['quantity'] . '</div>';
            $content .= '<div class="prix">' . $product['price'] . '</div>';
            $content .= '<div class="unite">' . $product['unite'] . '</div>';
        }
        return $content;
    }

    public function getAllCommandsHTML(){
        $content = null;
        if($this->check->getTxt != null){
            $content = '<h1>Liste des Commandes</h1> <ul>';
            foreach($this->check->getTxt() as $command){
                $content .= '<li>';
                $content .= '<a href="/index.php/command?id=' . $command['id'] . '">' . $command['title'] . '</a>';
                $content .= '</li>';
            }
            $content .= '</ul>';
        }
        return $content;
    }

    public function getAllHampersHTML($data){
        $content = null;
        if($this->check->getTxt != null){
            $content = '<h1>Liste des Paniers</h1> <ul>';
            foreach($this->check->getAllHampers($data) as $hamper){
                $content .= '<li>';
                $content .= '<a href="/index.php/hamper?id=' . $hamper['id'] . '">' . $hamper['name'] . '</a>';
                $content .= '</li>';
            }
            $content .= '</ul>';
        }
        return $content;
    }

    public function getCurrentHampersHTML($id, $data)
    {
        $content = null;
        if ($this->check->getTxt != null) {
            $product = $this->check->getHamper($id, $data)[0];

            $content = '<h1>' . $product['name'] . '</h1>';
            $content .= '<div class="quantite">' . $product['quantity'] . '</div>';
            $content .= '<div class="prix">' . $product['price'] . '</div>';
            $content .= '<div class="maj">' . $product['maj'] . '</div>';
        }
        return $content;
    }
}