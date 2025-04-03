<?php
namespace control;

/**
 * @Presenter
 *
 * Cette classe gère la présentation des produits, paniers et commandes en HTML.
 */
class Presenter
{
    protected $check;

    public function __construct($check)
    {
        $this->check = $check;
    }

    /**
     * @param $data
     * @return string|null
     * 
     * Permet d'afficher l'ensemble des produits.
     */
    public function getAllProductsHTML($data){
        $content = null;
        if($this->check->getTxt()!= null){
            $content = '<h1>Liste des produits</h1> <ul>';
            $products=$this->check->getAllProducts($data);
            foreach($products as $product){
                $content .= '<li>';
                $content .= '<a href="/index.php/produit/' . $product['id'] . '">' . $product['Nom'] . '</a>';
                $content .= '</li>';
            }
            $content .= '</ul>';
        }
        return $content;
    }

    /**
     * @param $id
     * @param $data
     * @return string|null
     * 
     * Permet d'afficher un produit et ses détails selon un ID.
     */
    public function getCurentProductHTML($id, $data)
    {
        $content = null;
        if ($this->check->getTxt() != null) {
            $product = $this->check->getProduct($id, $data)[0];

            $content = '<h1>Produit : ' . htmlspecialchars($product['Nom']) . '</h1>';
            $content .= '<div><strong>Quantité :</strong> ' . htmlspecialchars($product['Quantité']) . '</div>';
            $content .= '<div><strong>Prix :</strong> ' . htmlspecialchars($product['Prix']) . ' €</div>';
            $content .= '<div><strong>Unité :</strong> ' . htmlspecialchars($product['Unité']) . '</div>';
        }
        return $content;
    }

    /**
     * @param $data
     * @return string|null
     * 
     * Permet d'afficher l'ensemble des paniers.
     */
    public function getAllHampersHTML($data){
        $content = null;
        if($this->check->getTxt() != null){
            $content = '<h1>Liste des Paniers</h1> <ul>';
            $hampers = $this->check->getAllHampers($data);
            foreach($hampers as $hamper){
                $content .= '<li>';
                $content .= '<a href="/index.php/panier/' . $hamper['id_product'] . '">' . $hamper['Nom'] . '</a>';
                $content .= '</li>';
            }
            $content .= '</ul>';
        }
        return $content;
    }

    /**
     * @param $id
     * @param $data
     * @return string|null
     * 
     * Permet d'afficher un panier et ses détails selon un ID.
     */
    public function getCurrentHampersHTML($id, $data)
    {
        $content = null;
        if ($this->check->getTxt() != null) {
            $hamper = $this->check->getHamper($id, $data)[0];

            $content = '<h1>Panier : ' . htmlspecialchars($hamper['Nom']) . '</h1>';
            $content .= '<div><strong>Quantité :</strong> ' . htmlspecialchars($hamper['Quantité']) . '</div>';
            $content .= '<div><strong>Prix :</strong> ' . htmlspecialchars($hamper['Prix']) . ' €</div>';
            $content .= '<div><strong>Date de mise à jour :</strong> ' . htmlspecialchars($hamper['Date_Mise_à_Jour']) . '</div>';

            if (!empty($hamper['Produits'])) {
                $content .= '<h2>Produits inclus :</h2>';
                $content .= '<ul>';

                foreach ($hamper['Produits'] as $product) {
                    $content .= '<li>';
                    $content .= '<a href="/index.php/produit/' . htmlspecialchars($product['id_produit']) . '">';
                    $content .= htmlspecialchars($product['nom']);
                    $content .= '</li>';
                }

                $content .= '</ul>';
            } else {
                $content .= '<p>Aucun produit dans ce panier.</p>';
            }
        }
        return $content;
    }

    /**
     * @param $data
     * @return string|null
     * 
     * Permet d'afficher l'ensemble des commandes.
     */
    public function getAllCommandsHTML($data)
    {
        $content = null;
        if($this->check->getTxt() != null){
            $content = '<h1>Liste des Commandes</h1> <ul>';
            $commandes = $this->check->getAllCommandsHTML($data);
            foreach($commandes as $commande){
                $content .= '<li>';
                $content .= '<a href="/index.php/commande/' . $commande['id'] . '">' . "Commande " . $commande['id']  . '</a>';
                $content .= '</li>';
            }
            $content .= '</ul>';
        }
        return $content;
    }

    /**
     * @param $id
     * @param $data
     * @return string|null
     * 
     * Permet d'afficher une commande et ses détails selon un ID.
     */
    public function getCurrentCommandHTML($id, $data)
    {
        $content = null;
        if ($this->check->getTxt() != null) {
            $commande = $this->check->getCommande($id, $data)[0];

            // Affichage des infos de la commande
            $content = '<h1>Commande : ' . htmlspecialchars($commande['id']) . '</h1>';
            $content .= '<div><strong>Prix total :</strong> ' . htmlspecialchars($commande['prix']) . ' €</div>';
            $content .= '<div><strong>Validée :</strong> ' . ($commande['valide'] ? 'Oui' : 'Non') . '</div>';

            // Vérification et affichage des paniers de la commande
            if (!empty($commande['paniers'])) {
                $content .= '<h2>Paniers inclus :</h2>';
                $content .= '<ul>';

                foreach ($commande['paniers'] as $panier) {
                    $content .= '<li>';

                    // ✅ Lien cliquable sur le nom du panier
                    $content .= '<strong><a href="/index.php/panier/' . htmlspecialchars($panier['id']) . '">';
                    $content .= htmlspecialchars($panier['nom']);
                    $content .= '</a></strong>';

                    $content .= ' (Quantité : ' . htmlspecialchars($panier['quantite']) . ') - ';
                    $content .= htmlspecialchars($panier['prix']) . ' €';
                    $content .= '<div><strong>Date de mise à jour :</strong> ' . htmlspecialchars($panier['datemaj']) . '</div>';

                    // Vérification et affichage des produits du panier
                    if (!empty($panier['produits'])) {
                        $content .= '<h3>Produits :</h3>';
                        $content .= '<ul>';

                        foreach ($panier['produits'] as $product) {
                            $content .= '<li>';
                            $content .= '<a href="/index.php/produit/' . htmlspecialchars($product['id_produit']) . '">';
                            $content .= htmlspecialchars($product['nom']);
                            $content .= '</a>';
                            $content .= '</li>';
                        }

                        $content .= '</ul>';
                    } else {
                        $content .= '<p>Aucun produit dans ce panier.</p>';
                    }

                    $content .= '</li>';
                }

                $content .= '</ul>';
            } else {
                $content .= '<p>Aucun panier dans cette commande.</p>';
            }
        }
        return $content;
    }



}