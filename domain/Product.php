<?php

namespace domain;

class Product
{
    protected $id_product;
    protected $name;
    protected $quantity;
    protected $price;
    protected $unite;

    public function __construct($id_product, $name, $quantity, $price, $unite)
    {
        $this->id_product = $id_product;
        $this->name = $name;
        $this->quantity = $quantity;
        $this->price = $price;
        $this->unite = $unite;
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
    public function getQuantity()
    {
        return $this->quantity;
    }

    /**
     * @return mixed
     */
    public function getUnite()
    {
        return $this->unite;
    }
}