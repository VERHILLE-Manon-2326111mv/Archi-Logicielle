<?php

namespace data;

use service\AccessInterface;
include_once "service/AccessInterface.php";

use domain\Product;
include_once "domain/Product.php";

class ApiProducts implements AccessInterface
{
    public function curlApi()
    {
        $apiUrl = "https://github.com/VERHILLE-Manon-2326111mv/Archi-Logicielle/tree/Api_User_Produit-Albano/Api_User_Produit/src/main/java/fr/univamu/iut/api_user_produit/produit/ProduitRessource.java";
        $curlConnection = curl_init();

        $params = array(
            CURLOPT_URL => $apiUrl,
            CURLOPT_RETURNTRANSFER => true
        );

        curl_setopt_array($curlConnection, $params);
        $response = curl_exec($curlConnection);
        curl_close($curlConnection);

//        if (!$response)
//            echo curl_error($curlConnection);
//
//        $response = json_decode($response, true);

        return $response;
    }
}