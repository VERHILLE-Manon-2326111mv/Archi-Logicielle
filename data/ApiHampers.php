<?php

namespace data;

use service\AccessInterface;
include_once "service/AccessInterface.php";

use domain\Hamper;
include_once "domain/Hamper.php";

class ApiHampers implements AccessInterface
{
    public function curlApi()
    {
        $apiUrl = "https://github.com/VERHILLE-Manon-2326111mv/Archi-Logicielle/tree/";
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