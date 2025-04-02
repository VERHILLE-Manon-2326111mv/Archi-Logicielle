<?php

//Charger les différentes bibliothèques
include_once "control/Controllers.php";
include_once "control/Presenter.php";

include_once "data/ApiUsers.php";
include_once "data/ApiProducts.php";
include_once "data/ApiCommands.php";
include_once "data/ApiHampers.php";

include_once "gui/Layout.php";
include_once "gui/ViewCommands.php";
include_once "gui/ViewHampers.php";
include_once "gui/ViewHome.php";
include_once "gui/ViewHomeLogout.php";
include_once "gui/ViewLogin.php";
include_once "gui/ViewProducts.php";

include_once "service/Checking.php";

use control\{Controllers, Presenter};
use data\{ApiCommands, ApiHampers, ApiProducts, ApiUsers};
use gui\{Layout, ViewCommands, ViewHampers, ViewHome, ViewHomeLogout, ViewLogin, ViewProducts};
use service\{Checking};

// initialisation du controller
$controller = new Controllers();

// intialisation du cas d'utilisation UserChecking et du cas d'utilisation Checking
$checking = new Checking() ;

// initialisation du presenter
$presenter = new Presenter($checking);

// intialisation des API
$apiUser = new ApiUsers();
$apiHampers = new ApiHampers();
$apiProducts = new ApiProducts();
$apiCommandes = new ApiCommands();

// (p.ex. /index.php)
$uri = parse_url($_SERVER['REQUEST_URI'], PHP_URL_PATH);

// définition d'une session d'une heure
ini_set('session.gc_maxlifetime', 3600);
session_set_cookie_params(3600);
session_start();

// Affichage de la page d'accueil lors de l'arrivée sur le site
if ( '/' != $uri and '/index.php' != $uri){

    $error = $controller->authenticateAction($checking, $apiUser);

    if( $error != null )
    {
        $uri='/index.php/error' ;
        if( $error == 'bad login or pwd' or $error == 'not connected')
            $redirect = '/index.php/login';
    }
}// route la requête en interne
// i.e. lance le bon contrôleur en fonction de la requête effectuée
if ( '/' == $uri || '/index.php' == $uri) {
    session_destroy();
    // affichage de la page d'accueil
    $layout = new Layout("gui/layout.html");
    $vueHome = new ViewHome($layout);

    $vueHome->display();

}else if ('/index.php/logout' == $uri) {
    // Si l'utilisateur est connecté
    if($_SESSION['login']){
        // affichage de la page d'accueil pour utilisateur connecté
        $layout = new Layout("gui/layout.html" );
        $vueHome = new ViewHomeLogout($_SESSION['login'], $layout );

        $vueHome->display();
    }
} else if('/index.php/login' == $uri) {
    // affichage de la page de connexion
    $layout = new Layout("gui/layout.html" );
    $vueLogin = new ViewLogin( $layout );

    $vueLogin->display();
} else if('/index.php/product' == $uri) {
    // affichage de la page des produits
    $controller->productAction(null, $apiProducts, $presenter);

    $layout = new Layout("gui/layout.html" );
    $vueProducts = new ViewProducts( $layout, $apiProducts );

    $vueProducts->display();
} else if('/index.php/product' == $uri && isset($_GET['id'])) {
    // affichage de la page des produits
    $controller->productAction($_GET['id'], $apiProducts, $presenter);

    $layout = new Layout("gui/layout.html" );
    $vueProducts = new ViewProducts( $layout, $apiProducts );

    $vueProducts->display();
} else if('/index.php/hamper' == $uri) {
    // affichage de la page des paniers
    $layout = new Layout("gui/layout.html" );
    $vueHampers = new ViewHampers( $layout, $apiHampers );

    $vueHampers->display();
} else if('/index.php/command' == $uri) {
    // Si l'utilisateur est connecté
    if($_SESSION['login']){
        // affichage de la page des commandes
        $layout = new Layout("gui/layout.html" );
        $vueCommands = new ViewCommands( $layout, $apiCommandes );

        $vueCommands->display();
    }
}
else {
    header('Status: 404 Not Found');
    echo '<html><body><h1>My Page NotFound</h1></body></html>';
}