package fr.univamu.iut.api_panier.panier_produit;

import java.util.List;

public interface Panier_ProduitRepositoryInterface {

    public void close();
    public Panier_Produit createPanier_Produit(Panier_Produit panier_produit);
    public Panier_Produit getPanier_Produit(int id);
    public List<Panier_Produit> getPanier_Produits();
    public void deletePanier_Produit(int id);
    public void updatePanier_Produit(int id, int id_panier, int id_produit, int quantite);
    public int getIdPanier_Produit(Panier_Produit panier_produit);

}
