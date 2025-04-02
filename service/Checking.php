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
            $this->txt[] = ['id' => $product->getId(), 'Nom' => $product->getName(), 'Quantité' => $product->getQuantity(), 'Prix' => $product->getPrice(), 'Unité' => $product->getPrice()];
        }
    }

    public function getProduct($id, $data){
        $product = $data->getProduct($id);

        $this->txt[] = ['id' => $product->getId(), 'Nom' => $product->getName(), 'Quantité' => $product->getQuantity(), 'Prix' => $product->getPrice(), 'Unité' => $product->getPrice()];
    }
}