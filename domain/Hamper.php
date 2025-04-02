<?php

namespace domain;

class Hamper
{
    protected $id_product;
    protected $name;
    protected $maj;
    protected $price;
    protected $quantity;

    protected $products = [];

    public function __construct($id_product, $name, $maj, $price, $quantity, $products = [])
    {
        $this->id_product = $id_product;
        $this->name = $name;
        $this->maj = $maj;
        $this->price = $price;
        $this->quantity = $quantity;
        $this->products = $products;
    }


    /**
     * @return mixed
     */
    public function getIdProduct()
    {
        return $this->id_product;
    }

    /**
     * @return mixed
     */
    public function getName()
    {
        return $this->name;
    }

    /**
     * @return mixed
     */
    public function getPrice()
    {
        return $this->price;
    }

    /**
     * @return mixed
     */
    public function getMaj()
    {
        return $this->maj;
    }

    /**
     * @return mixed
     */
    public function getQuantity()
    {
        return $this->quantity;
    }

    /**
     * @return array
     */
    public function getProducts()
    {
        return $this->products;
    }

    /**
     * @param array $products
     */
    public function setProducts(array $products)
    {
        $this->products = $products;
    }
}