package fr.univamu.iut.api_panier.panier;

import fr.univamu.iut.api_panier.Panier_Produit.Panier_Produit;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.*;


public interface PanierRepositoryInterface {
    public void close();
    public Panier createPanier(Panier panier);
    public Panier getPanier(int id);
    public List<Panier> getPaniers();
    public void deletePanier(int id);
    public void updatePanier(int id, String nom, Date datemaj, int prix, int quantite);
    public int getIdPanier(Panier panier);
    public void addProduitPanier(Panier_Produit panier_produit);
    public void deleteProduitPanier(Panier_Produit panier_produit);
    public void updateProduitPanier(Panier_Produit panier_produit);
    public List<Panier_Produit> getPaniersProduit();
    public Panier_Produit getPaniersProduit(int id_produit);

}

