package fr.univamu.iut.commande.Panier;

import fr.univamu.iut.commande.Produit.Produit;
import fr.univamu.iut.commande.DateAdapter;
import jakarta.json.bind.annotation.JsonbTypeAdapter;

import java.util.ArrayList;
import java.util.Date;

/***
 * Classe Panier
 */
public class Panier {

    protected int id;
    protected String nom;

    @JsonbTypeAdapter(DateAdapter.class)
    protected Date datemaj;

    protected int prix;
    protected int quantite;
    protected ArrayList<Produit> produits = null;

    public Panier(String nom, Date datemaj, int prix, int quantite) {
        this.nom = nom;
        this.datemaj = datemaj;
        this.prix = prix;
        this.quantite = quantite;
    }

    public Panier() {}

    // Reste de la classe inchangé...

    public int getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public Date getDatemaj() {
        return datemaj;
    }

    public int getPrix() {
        return prix;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setDatemaj(Date datemaj) {
        this.datemaj = datemaj;
    }

    public void setPrix(int prix) {
        this.prix = prix;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ArrayList<Produit> getProduits() {
        return produits;
    }

    public void setProduits(ArrayList<Produit> produits) {
        this.produits = produits;
    }

    @Override
    public String toString() {
        return "Panier{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", datemaj=" + datemaj +
                ", prix=" + prix +
                ", quantite=" + quantite +
                '}';
    }
}