<?php

namespace data;

use service\DataAccessInterface;
include_once "service/DataAccessInterface.php";

use domain\Hamper;
use function Sodium\add;

include_once "domain/Hamper.php";

/**
 * @ApiHampers
 *
 * Implémentation de l'API Panier
 */
class ApiHampers implements DataAccessInterface
{
    /**
     * @return array|null
     *
     * Permet de récupérer tous les paniers.
     */
    public function getAllHampers()
    {
        $response = $this->curlApiToJSON("");

        $hampers = [];
        foreach ($response as $hamper){

            $id = $hamper['id'];
            $name = $hamper['nom'];
            $maj = $hamper['datemaj'];
            $price = $hamper['prix'];
            $quantity = $hamper['quantite'];

            $currentHamper = new Hamper($id, $name, $maj, $price, $quantity);
            $hampers[] = $currentHamper;
        }

        $hamperSerialized = serialize($hampers);
        file_put_contents('data/cache_panier', $hamperSerialized);

        return $hampers;
    }

    /**
     * @param $id
     * @return Hamper|null
     *
     * Permet de récupérer un panier par son ID.
     */
    public function getHamperById($id){
        $response = $this->curlApiToJSON($id);

        if($response !== null){
            $id = $response['id'];
            $name = $response['nom'];
            $maj = $response['datemaj'];
            $price = $response['prix'];
            $quantity = $response['quantite'];
            $products = $response['produits'];

            $currentHamper = new Hamper($id, $name, $maj, $price, $quantity, $products);

            return $currentHamper;
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
            $apiUrl = "http://localhost:7150/API_Panier-1.0-SNAPSHOT/api/panier";
        }
        else{
            $apiUrl = "http://localhost:7150/API_Panier-1.0-SNAPSHOT/api/panier/". $end;
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