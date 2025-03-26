package fr.univamu.iut.api_user_produit.produit;


import java.util.Date;

public class Produit {
    protected int id_produit;

    protected String nom;

    protected int quantite;

    protected int prix;

    protected String unite;

    public Produit() {}

    public Produit(int id_produit, String nom, int quantite, int prix, String unite) {
        this.id_produit = id_produit;
        this.nom = nom;
        this.quantite = quantite;
        this.prix = prix;
        this.unite = unite;
    }

    public int getId_produit() {
        return id_produit;
    }
    public void setId_produit(int id_produit) {
        this.id_produit = id_produit;
    }
    public String getNom() {
        return nom;
    }
    public void setNom(String nom) {
        this.nom = nom;
    }
    public int getQuantite() {
        return quantite;
    }
    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }
    public int getPrix() {
        return prix;
    }
    public void setPrix(int prix) {
        this.prix = prix;
    }
    public String getUnite() {
        return unite;
    }
    public void setUnite(String unite) {
        this.unite = unite;
    }

}