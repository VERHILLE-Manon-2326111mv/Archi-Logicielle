package fr.univamu.iut.commande.Commande;

import java.util.*;


public interface CommandeRepositoryInterface {


    public void close();


    public Commande getCommande( int id );


    public ArrayList<Commande> getAllCommande() ;


    public boolean updateCommande( int id, int id_user, int prix, boolean valide, Date date_echeance, String point_relai);
}
