<?php

namespace service;

class Checking
{
    protected $txt;

    public function getTxt()
    {
        return $this->txt;
    }

    public function authenticate($login, $password, $data)
    {
        return ($data->getUser($login, $password) != null);
    }

    public function getAllProducts($data)
    {
        $products = $data->getAllProducts();

        $this->txt = array();
        foreach ($products as $product) {
            $this->txt[] = ['id' => $product->getId(), 'Nom' => $product->getName(), 'Quantité' => $product->getQuantity(), 'Prix' => $product->getPrice(), 'Unité' => $product->getUnite()];
        }
    }

    public function getProduct($id, $data){
        $product = $data->getProductById($id);

        $this->txt[] = ['id' => $product->getId(), 'Nom' => $product->getName(), 'Quantité' => $product->getQuantity(), 'Prix' => $product->getPrice(), 'Unité' => $product->getUnite()];
    }

    public function getAllHampers($data)
    {
        $hampers = $data->getAllHampers();

        $this->txt = array();
        foreach ($hampers as $hamper) {
            $this->txt[] = ['id' => $hamper->getId(), 'Nom' => $hamper->getName(), 'Date_Mise_à_Jour' => $hamper->getMaj(), 'Prix' => $hamper->getPrice(), 'Quantité' => $hamper->getQuantity()];
        }
    }

    public function getHamper($id, $data){
        $hamper = $data->getHamperById($id);
        $product = $data->getProductInHamper();

        $this->txt[] = ['id' => $hamper->getId(), 'Nom' => $hamper->getName(), 'Date_Mise_à_Jour' => $hamper->getMaj(), 'Prix' => $hamper->getPrice(), 'Quantité' => $hamper->getQuantity()];
    }
}