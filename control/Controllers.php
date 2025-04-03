<?php

namespace control;

/**
 * @Controllers
 *
 * Cette classe gère les actions des utilisateurs, des produits, des paniers et des commandes.
 */
class Controllers
{
    /**
     * @param $userCheck
     * @param $data
     * @return string|void
     *
     * Permet l'authetification d'un utilisateur.
     */
    public function  authenticateAction($userCheck, $data){

        // Si l'utilisateur n'a pas de session ouverte
        if( !isset($_SESSION['login']) ) {

            // Si la page d'origine est le formulaire de connexion
            if( isset($_POST['login']) && isset($_POST['password']) )
            {// Vérification de l'authentification si la précédente page était le formulaire de connexion
                if( !$userCheck->authenticate($_POST['login'], $_POST['password'], $data) )
                {
                    // retourne une erreur si le compte n'est pas enregistré
                    $error = 'bad login or pwd';
                    return $error;

                }
                // Enregistrement des informations de session après une authentification réussie
                else {
                    $_SESSION['login'] = $_POST['login'] ;
                }
            }
            else{
                // retourne une erreur si la personne ne passe pas par le forumlaire de connexion
                $error = 'not connected';
                return $error;
            }

        }
    }

    /**
     * @param $id
     * @param $data
     * @param $checking
     * @return void
     *
     * Permet la génération des produits.
     */
    public function productAction($id, $data, $checking)
    {
        if($id !== null){
            $checking->getProduct($id, $data);
        }
        else{
            $checking->getAllProducts($data);
        }
    }

    /**
     * @param $id
     * @param $data
     * @param $checking
     * @return void
     *
     * Permet la génération des paniers.
     */
    public function hamperAction($id, $data, $checking){
        if($id ==! null){
            $checking->getHamper($id, $data);
        }else{
            $checking->getAllHampers($data);
        }
    }

    /**
     * @param $id
     * @param $data
     * @param $checking
     * @return void
     *
     * Permet la génération des commandes.
     */
    public function commandeAction($id, $data, $checking){
        if($id ==! null){
            $checking->getCommande($id, $data);
        }else{
            $checking->getAllCommandsHTML($data);
        }
    }
}