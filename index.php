<?php

//Charger les différentes bibliothèques
include_once "control/Controllers.php";
include_once "control/Presenter.php";

include_once "data/UserSqlAccess.php";

include_once "gui/Layout.php";
include_once "gui/ViewCommands.php";
include_once "gui/ViewHampers.php";
include_once "gui/ViewHome.php";
include_once "gui/ViewLogin.php";
include_once "gui/ViewProducts.php";

include_once "service/UserChecking.php";
include_once "service/UserCreation.php";

use control\{Controllers, Presenter};
use data\UserSqlAccess;
use gui\{Layout, ViewLogin};
use service\{UserChecking, UserCreation};

$data = null;
try {
    $bd = new PDO('mysql:host=mysql-vente-agricole-mrec.alwaysdata.net;dbname=vente-agricole-mrec_bdd', '406088_manon', 'Banane23!');
    // construction du modèle
    $dataUsers = new UserSqlAccess($bd);

} catch (PDOException $e) {
    print "Erreur de connexion !: " . $e->getMessage() . "<br/>";
    die();
}

// initialisation du controller
$controller = new Controllers();

// intialisation du cas d'utilisation service\UserChecking
$userCheck = new UserChecking() ;

// intialisation du cas d'utilisation service\UserCreation
$userCreation = new UserCreation() ;

// (p.ex. /index.php)
$uri = parse_url($_SERVER['REQUEST_URI'], PHP_URL_PATH);

// définition d'une session d'une heure
ini_set('session.gc_maxlifetime', 3600);
session_set_cookie_params(3600);
session_start();

// Authentification et création du compte (sauf pour le formulaire de connexion et de création de compte)
if ( '/' != $uri and '/index.php' != $uri and '/index.php/logout' != $uri){

    $error = $controller->authenticateAction($userCreation, $userCheck, $dataUsers);

    if( $error != null )
    {
        $uri='/index.php/error' ;
        if( $error == 'bad login or pwd' or $error == 'not connected')
            $redirect = '/index.php';
    }
}// route la requête en interne
// i.e. lance le bon contrôleur en fonction de la requête effectuée
if ( '/' == $uri || '/index.php' == $uri || '/index.php/logout' == $uri) {
    // affichage de la page de connexion

    session_destroy();
    $layout = new Layout("gui/layout.html" );
    $vueLogin = new ViewLogin( $layout );

    $vueLogin->display();
}


else {
    header('Status: 404 Not Found');
    echo '<html><body><h1>My Page NotFound</h1></body></html>';
}