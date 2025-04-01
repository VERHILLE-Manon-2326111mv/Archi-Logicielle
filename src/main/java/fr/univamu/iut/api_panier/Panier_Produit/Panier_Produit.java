package fr.univamu.iut.api_panier.Panier_Produit;

public class Panier_Produit
{

    private int id_panier;
    private int id_produit;
    private int quantite;

    public Panier_Produit(int id_panier, int id_produit, int quantite) {
        this.id_panier = id_panier;
        this.id_produit = id_produit;
        this.quantite = quantite;
    }

    public Panier_Produit() {
    }


    public int getId_panier() {
        return id_panier;
    }

    public int getId_produit() {
        return id_produit;
    }

    public int getQuantite() {
        return quantite;
    }


    public void setId_panier(int id_panier) {
        this.id_panier = id_panier;
    }

    public void setId_produit(int id_produit) {
        this.id_produit = id_produit;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    @Override
    public String toString() {
        return "Panier_Produit{" +
                ", id_panier=" + id_panier +
                ", id_produit=" + id_produit +
                ", quantite=" + quantite +
                '}';
    }

}
