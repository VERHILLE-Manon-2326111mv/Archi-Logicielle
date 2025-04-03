<?php

namespace data;

use service\DataAccessInterface;
include_once "service/DataAccessInterface.php";

use domain\Product;
include_once "domain/Product.php";

/**
 * @ApiProducts
 *
 * Implémentation de l'API Produit
 */
class ApiProducts implements DataAccessInterface
{
    /**
     * @return array
     *
     * Permet de récupérer tous les produits.
     */
    public function getAllProducts()
    {
        $response = $this->curlApiToJSON("");

        $products = array();
        foreach ($response as $product){

            $id = $product['id_produit'];
            $name = $product['nom'];
            $quantity = $product['quantite']; // Corrigé de 'quantity' à 'quantite'
            $price = $product['prix'];
            $unite = $product['unite'];

            $currentProduct = new Product($id, $name, $quantity, $price, $unite);
            $products[$id] = $currentProduct;
        }

        // enregistrement des produits dans un fichier sur le serveur (serialisation)
        $productSerialized = serialize($products);
        file_put_contents('data/cache_produit', $productSerialized);

        return $products;
    }

    /**
     * @param $id
     * @return Product|null
     *
     * Permet de récupérer un produit par son ID.
     */
    public function getProductById($id){
        $response = $this->curlApiToJSON($id);

        if($response !== null){
            $id = $response['id_produit']; // Corrigé de 'id_user' à 'id_produit'
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

    /**
     * @param string $end
     * @return mixed
     *
     * Permet de récupérer l'API.
     */
    public function curlApiToJSON(string $end)
    {
        if($end === ""){
            $apiUrl = "http://localhost:8080/Api_User_Produit-1.0-SNAPSHOT/api/produit";
        }
        else{
            $apiUrl = "http://localhost:8080/Api_User_Produit-1.0-SNAPSHOT/api/produit/" . $end; // Ajout d'un "/" entre produit et $end
        }

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