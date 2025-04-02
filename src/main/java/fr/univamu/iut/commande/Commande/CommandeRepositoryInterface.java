package fr.univamu.iut.commande.Commande;

import fr.univamu.iut.commande.Commande_Panier.Commande_Panier;

import java.util.*;


public interface CommandeRepositoryInterface {


    /**
     * Ferme la connexion
     */
    public void close();


    /**
     * Récupère une commande
     * @param id
     * @return
     */
    public Commande getCommande( int id );


    /**
     * Récupère toutes les commandes
     * @return
     */
    public ArrayList<Commande> getAllCommande() ;


    /**
     * Met à jour une commande
     * @param commande
     * @return
     */
    public boolean updateCommande(Commande commande);

    /**
     * Supprime une commande
     * @param commande
     * @return
     */
    public boolean deleteCommande( Commande commande );

    /**
     * Crée une commande
     * @param commande
     * @return
     */
    public boolean createCommande( Commande commande );

    /**
     * Valide une commande
     * @param commande
     * @return
     */
    public boolean valideCommande(Commande commande);

    /**
     * Récupère toutes les commandes d'un utilisateur
     * @param id
     * @return
     */
    public ArrayList<Commande_Panier> getAllPanierCommande(int id);

    /**
     * Ajoute un panier à une commande
     * @param commande
     * @return
     */
    public boolean addPanier(Commande_Panier commande);

    /**
     * Met à jour un panier
     * @param commande
     * @return
     */
    public boolean updatePanier(Commande_Panier commande);

    /**
     * Supprime un panier
     * @param commande
     * @return
     */
    public boolean deletePanier(Commande_Panier commande);
}
