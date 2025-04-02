<?php

namespace data;

use service\DataAccessInterface;
include_once "service/DataAccessInterface.php";

use domain\Commandes;
include_once "domain/Commandes.php";

class ApiCommands implements DataAccessInterface
{
    public function addCommande(){
        //TODO: à implémenter
    }

    public function curlApiToJSON(string $end)
    {
        $apiUrl = "http://localhost:6410/commande-1.0-SNAPSHOT/api/produit" . $end;

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