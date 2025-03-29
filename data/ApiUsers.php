<?php

namespace data;

use service\AccessInterface;
include_once "service/AccessInterface.php";

use domain\User;
include_once "domain/User.php";

class ApiUsers implements AccessInterface
{
    public function getAllUserClient()
    {
        $apiUrl = $this->curlApi();

        // TODO : implementer la methode getAllUsers
        return array();
    }

    public function curlApi()
    {
        $apiUrl = "https://github.com/VERHILLE-Manon-2326111mv/Archi-Logicielle/tree/Api_User_Produit-Albano/Api_User_Produit/src/main/java/fr/univamu/iut/api_user_produit/user/UserRessource.java";
        $curlConnection = curl_init();

        $params = array(
            CURLOPT_URL => $apiUrl,
            CURLOPT_RETURNTRANSFER => true
        );

        curl_setopt_array($curlConnection, $params);
        $response = curl_exec($curlConnection);
        curl_close($curlConnection);

        return $response;
    }
}