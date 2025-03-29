<?php

//Charger les différentes bibliothèques
include_once "control/Controllers.php";
include_once "control/Presenter.php";

include_once "data/ApiUsers.php";
include_once "data/ApiProducts.php";
include_once "data/ApiCommandes.php";
include_once "data/ApiHampers.php";

include_once "gui/Layout.php";
include_once "gui/ViewCommands.php";
include_once "gui/ViewHampers.php";
include_once "gui/ViewHome.php";
include_once "gui/ViewLogin.php";
include_once "gui/ViewProducts.php";

include_once "service/UserChecking.php";

use control\{Controllers, Presenter};
use data\{ApiCommandes, ApiHampers, ApiProducts, ApiUsers};
use gui\{Layout, ViewCommands, ViewHampers, ViewHome, ViewLogin, ViewProducts};
use service\{UserChecking};

// initialisation du controller
$controller = new Controllers();

// intialisation du cas d'utilisation service\UserChecking
$userCheck = new UserChecking() ;

// intialisation des API
$apiUser = new ApiUsers();
$apiHampers = new ApiHampers();
$apiProducts = new ApiProducts();
$apiCommandes = new ApiCommandes();

// (p.ex. /index.php)
$uri = parse_url($_SERVER['REQUEST_URI'], PHP_URL_PATH);

// définition d'une session d'une heure
ini_set('session.gc_maxlifetime', 3600);
session_set_cookie_params(3600);
session_start();

// Authentification et création du compte (sauf pour le formulaire de connexion et de création de compte)
if ( '/' != $uri and '/index.php' != $uri and '/index.php/logout' != $uri){

    $error = $controller->authenticateAction($userCheck);

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
} else if('index.php/home' == $uri) {
    // affichage de la page d'accueil
    $layout = new Layout("gui/layout.html" );
    $vueHome = new ViewHome( $layout );

    $vueHome->display();
} else if('index.php/products' == $uri) {
    // affichage de la page des produits
    $layout = new Layout("gui/layout.html" );
    $vueProducts = new ViewProducts( $layout, $apiProducts );

    $vueProducts->display();
} else if('index.php/hampers' == $uri) {
    // affichage de la page des paniers
    $layout = new Layout("gui/layout.html" );
    $vueHampers = new ViewHampers( $layout, $apiHampers );

    $vueHampers->display();
} else if('index.php/commands' == $uri) {
    // affichage de la page des commandes
    $layout = new Layout("gui/layout.html" );
    $vueCommands = new ViewCommands( $layout, $apiCommandes );

    $vueCommands->display();
}
else {
    header('Status: 404 Not Found');
    echo '<html><body><h1>My Page NotFound</h1></body></html>';
}