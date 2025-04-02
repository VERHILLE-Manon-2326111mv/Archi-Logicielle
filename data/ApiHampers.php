<?php

namespace data;

use service\AccessInterface;
include_once "service/AccessInterface.php";

use domain\Hamper;
include_once "domain/Hamper.php";

class ApiHampers implements AccessInterface
{
    public function curlApiToJSON(string $end)
    {
        // URL de l'API
        $apiUrl = "http://localhost:7150/api_panier-1.0-SNAPSHOT/api/panier".$end;

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