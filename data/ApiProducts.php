<?php

namespace data;

use service\AccessInterface;
include_once "service/AccessInterface.php";

use domain\Product;
include_once "domain/Product.php";

class ApiProducts implements AccessInterface
{
    public function getAllProducts()
    {
        $response = $this->curlApiToJSON("");

        $products = array();
        foreach ($response as $product){

            $id = $product['id_produit'];
            $name = $product['nom'];
            $quantity = $product['quantity'];
            $price = $product['prix'];
            $unite = $product['unite'];

            $currentProduct = new Product($id, $name, $quantity, $price, $unite);
            $products[$id] = $currentProduct;
        }

        // enregistrement des produits dans un fichier sur le serveur (serialisation)
        $productSerialized = serialize($products);
        file_put_contents('data/cache_alternance', $productSerialized);

        return $products;
    }

    public function getProductById($id){
        $response = $this->curlApiToJSON($id);

        if($response !== null){
            $id = $response['id_user'];
            $name = $response['nom'];
            $quantity = $response['quantite'];
            $price = $response['prix'];
            $unite = $response['unite'];

            $currentProduct = new Product($id, $name, $quantity, $price, $unite);

            return $currentProduct;
    }else{
            return null;
        }
    }

    public function curlApiToJSON(string $end)
    {
        $apiUrl = "http://localhost:8080/api_user_produit-1.0-SNAPSHOT/api/produit" . $end;

        // initialisation de la connexion à l'API avec CURL
        $curlConnection  = curl_init();

        // définition des paramètres de la requête CURL
        $params = array(
            CURLOPT_URL =>  $apiUrl,
            CURLOPT_RETURNTRANSFER => true,
            CURLOPT_HTTPHEADER => array('accept: application/json')
        );
        curl_setopt_array($curlConnection, $params);

        // exécution de la requête HTTP avec CURL
        $response = curl_exec($curlConnection);
        curl_close($curlConnection);

        if( !$response )
            echo curl_error($curlConnection);

        // transformation du JSON récupéré en tableau associatif
        $response = json_decode( $response, true );

        return $response;
    }
}