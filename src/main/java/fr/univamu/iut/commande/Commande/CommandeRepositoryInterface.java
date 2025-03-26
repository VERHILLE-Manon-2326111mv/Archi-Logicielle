package fr.univamu.iut.commande.Commande;

import fr.univamu.iut.commande.Commande_Panier.Commande_Panier;

import java.util.*;


public interface CommandeRepositoryInterface {


    public void close();


    public Commande getCommande( int id );


    public ArrayList<Commande> getAllCommande() ;


    public boolean updateCommande(Commande commande);

    public boolean deleteCommande( Commande commande );

    public boolean createCommande( Commande commande );

    public boolean valideCommande(Commande commande);

    public boolean addPanier(Commande_Panier commande);

    public boolean updatePanier(Commande_Panier commande);

    public boolean deletePanier(Commande_Panier commande);
}
