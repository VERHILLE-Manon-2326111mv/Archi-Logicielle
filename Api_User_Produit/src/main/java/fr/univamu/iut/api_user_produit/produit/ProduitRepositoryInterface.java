package fr.univamu.iut.api_user_produit.produit;
import java.util.*;


public interface ProduitRepositoryInterface {


    public void close();


    public Produit getProduit( int id );


    public ArrayList<Produit> getAllProduit() ;


    public boolean updateProduit(int id_produit, String nom, int quantite, int prix, String unite);

    public boolean deleteProduit(Produit produit);

    public boolean addProduit(Produit produit);
}