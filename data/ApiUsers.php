<?php

namespace data;

use service\DataAccessInterface;
include_once "service/DataAccessInterface.php";

use domain\User;
include_once "domain/User.php";

/**
 * @ApiUsers
 *
 * Implémentation de l'API User
 */
class ApiUsers implements DataAccessInterface
{
    /**
     * @return array
     *
     * Permet de récupérer tous les utilisateurs.
     */
    public function getAllUsers(){
        $response = $this->curlApiToJSON("");

        $users = array();
        foreach ($response as $user){

            $id = $user['id_user'];
            $password = $user['password'];
            $name = $user['nom'];
            $role = $user['role'];

            $currentUser = new User($id, $password, $name, $role);
            $users[$id] = $currentUser;
        }

        // enregistrement des utilisateurs dans un fichier sur le serveur (serialisation)
        $usersSerialized = serialize($users);
        file_put_contents('data/cache_alternance', $usersSerialized);

        return $users;
    }

    /**
     * @return array
     *
     * Permet de récupérer tous les clients du site.
     */
    public function getAllClients()
    {
        $response = $this->curlApiToJSON("/client");

        $clients = array();
        foreach ($response as $client){

            $id = $client['id_user'];
            $password = $client['password'];
            $name = $client['nom'];
            $role = $client['role'];

            $currentUser = new User($id, $password, $name, $role);
            $users[$id] = $currentUser;
        }

        // enregistrement des clients dans un fichier sur le serveur (serialisation)
        $usersSerialized = serialize($clients);
        file_put_contents('data/cache_user', $usersSerialized);

        return $clients;
    }

    /**
     * @param $login
     * @param $password
     * @return bool
     *
     * Permet de récupérer un utilisateur via son login et son mot de passe.
     */
    public function getUser($login, $password){
        $users = $this->getAllUsers();
        foreach ($users as $user){
            if($login == $user.getName() && $password == $user.getPassword()){
                return true;
            }
        }
        return false;
    }

    /**
     * @param int $id
     * @return User|null
     *
     * Permet de récupréer un utilisateur par son ID.
     */
    public function getUserById(int $id){
        $response = $this->curlApiToJSON('/'. $id);

        if($response !== null){
            $id = $response['id_user'];
            $password = $response['password'];
            $name = $response['nom'];
            $role = $response['role'];

            $currentUser = new User($id, $password, $name, $role);

            return $currentUser;
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
        // URL de l'API
        $apiUrl = "http://localhost:8080/Api_User_Produit-1.0-SNAPSHOT/api/user/".$end;

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