<?php

namespace data;

use service\DataAccessInterface;
include_once "service/DataAccessInterface.php";

use domain\Commandes;
include_once "domain/Commandes.php";

class ApiCommands implements DataAccessInterface
{
    public function getAllCommandes(){
        $response = $this->curlApiToJSON("");
        if ($response == null) {
            return null;
        }
        $commandes = [];
        foreach ($response as $commande) {
            $id = $commande['id'];
            $id_user = $commande['id_user'];
            $prix = $commande['prix'];
            $valide = $commande['valide'];
            $currentCommande = new Commandes(
                $id,
                $id_user,
                $prix,
                $valide,
            );
            $commandes[] = $currentCommande;
        }

        $commandesSerialized = serialize($commandes);
        file_put_contents('data/cache_commandes', $commandesSerialized);
        return $commandes;
    }

    public function getCommandeById($id){
        $response = $this->curlApiToJSON($id);
        if($response !== null){
            $id = $response['id'];
            $id_user = $response['id_user'];
            $prix = $response['prix'];
            $valide = $response['valide'];
            $panier = $response['panier'];
            $currentCommande = new Commandes(
                $id,
                $id_user,
                $prix,
                $valide,
                $panier
            );
            return $currentCommande;
        }else{
            return null;
        }
    }


    public function curlApiToJSON(string $end)
    {
        if($end === ""){
            $apiUrl = "http://localhost:6140/commande-1.0-SNAPSHOT/api/commande";
        }
        else{
            $apiUrl = "http://localhost:6140/commande-1.0-SNAPSHOT/api/commande/". $end;
        }

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