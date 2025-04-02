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

// route la requête en interne
// i.e. lance le bon contrôleur en fonction de la requête effectuée
if ( '/' == $uri || '/index.php' == $uri) {
    session_destroy();
    // affichage de la page d'accueil
    $layout = new Layout("gui/layout.html");
    $vueHome = new ViewHome($layout);

    $vueHome->display();

} else if ('/index.php/logout' == $uri) {
    // Si l'utilisateur est connecté
    if(isset($_SESSION['login'])){
        // affichage de la page d'accueil pour utilisateur connecté
        $layout = new Layout("gui/layout.html" );
        $vueHome = new ViewHomeLogout($_SESSION['login'], $layout );

        $vueHome->display();
    }
} else if('/index.php/login' == $uri) {
    $error = $controller->authenticateAction($checking, $apiUser);

    if( $error != null )
    {
        $uri='/index.php/error' ;
        if( $error == 'bad login or pwd' or $error == 'not connected')
            $redirect = '/index.php/login';
    }

    // affichage de la page de connexion
    $layout = new Layout("gui/layout.html" );
    $vueLogin = new ViewLogin( $layout );

    $vueLogin->display();
} else if('/index.php/produit' == $uri) {
    $controller->productAction(null, $apiProducts, $checking);

    $layout = new Layout("gui/layout.html" );
    $vueProducts = new ViewProducts( $layout, null, $presenter, $apiProducts );

    $vueProducts->display();
} else if (preg_match('#^/index.php/produit/([0-9]+)$#', $uri, $matches)) {
    $productId = $matches[1];

    $controller->productAction($productId, $apiProducts, $checking);

    $layout = new Layout("gui/layout.html");
    $vueProducts = new ViewProducts($layout, $productId, $presenter, $apiProducts);

    $vueProducts->display();
}

else if('/index.php/panier' == $uri) {
    $controller->hamperAction(null, $apiHampers, $checking);

    $layout = new Layout("gui/layout.html" );
    $vueHampers = new ViewHampers( $layout, null, $presenter,$apiHampers );

    $vueHampers->display();
} else if (preg_match('#^/index.php/panier/([0-9]+)$#', $uri, $matches)) {
    $panierID = $matches[1];

    $controller->hamperAction($panierID, $apiHampers, $checking);

    $layout = new Layout("gui/layout.html" );
    $vueHampers = new ViewHampers( $layout, $panierID, $presenter, $apiHampers );

    $vueHampers->display();
} else if('/index.php/commande' == $uri) {
    $controller->commandeAction(null, $apiCommandes, $checking);

        $layout = new Layout("gui/layout.html" );
        $vueCommands = new ViewCommands( $layout, null ,$presenter ,$apiCommandes );

        $vueCommands->display();

} else if (preg_match('#^/index.php/commande/([0-9]+)$#', $uri, $matches)) {
    $commandId = $matches[1];

    $controller->commandeAction($commandId, $apiCommandes, $checking);

    $layout = new Layout("gui/layout.html" );
    $vueCommands = new ViewCommands( $layout, $commandId, $presenter, $apiCommandes );

    $vueCommands->display();
} else if('/index.php/error' == $uri) {
    // affichage de la page d'erreur
    header('Status: 404 Not Found');
    echo '<html><body><h1>My Page NotFound test</h1></body></html>';

} else {
    header('Status: 404 Not Found');
    echo '<html><body><h1>My Page NotFound test</h1></body></html>';
}