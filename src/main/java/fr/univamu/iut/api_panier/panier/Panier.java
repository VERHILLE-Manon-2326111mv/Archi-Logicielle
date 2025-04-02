package fr.univamu.iut.api_panier.panier;

import fr.univamu.iut.api_panier.Produit.Produit;

import java.util.ArrayList;
import java.util.Date;

/***
 * Classe Panier
 * Représente un panier avec des produits, un nom, une date de mise à jour, un prix et une quantité.
 */
public class Panier {

    protected int id;
    protected String nom;
    protected Date datemaj;
    protected int prix;
    protected int quantite;
    protected ArrayList<Produit> produits = null;

    /**
     * Constructeur de la classe Panier.
     *
     * @param nom Le nom du panier.
     * @param datemaj La date de mise à jour du panier.
     * @param prix Le prix du panier.
     * @param quantite La quantité de produits dans le panier.
     */
    public Panier(String nom, Date datemaj, int prix, int quantite) {
        this.nom = nom;
        this.datemaj = datemaj;
        this.prix = prix;
        this.quantite = quantite;
    }

    /**
     * Obtient l'ID du panier.
     *
     * @return L'ID du panier.
     */
    public int getId() {
        return id;
    }

    /**
     * Obtient le nom du panier.
     *
     * @return Le nom du panier.
     */
    public String getNom() {
        return nom;
    }

    /**
     * Obtient la date de mise à jour du panier.
     *
     * @return La date de mise à jour du panier.
     */
    public Date getDatemaj() {
        return datemaj;
    }

    /**
     * Obtient le prix du panier.
     *
     * @return Le prix du panier.
     */
    public int getPrix() {
        return prix;
    }

    /**
     * Obtient la quantité de produits dans le panier.
     *
     * @return La quantité de produits dans le panier.
     */
    public int getQuantite() {
        return quantite;
    }

    /**
     * Définit le nom du panier.
     *
     * @param nom Le nouveau nom du panier.
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     * Définit la date de mise à jour du panier.
     *
     * @param datemaj La nouvelle date de mise à jour du panier.
     */
    public void setDatemaj(Date datemaj) {
        this.datemaj = datemaj;
    }

    /**
     * Définit le prix du panier.
     *
     * @param prix Le nouveau prix du panier.
     */
    public void setPrix(int prix) {
        this.prix = prix;
    }

    /**
     * Définit la quantité de produits dans le panier.
     *
     * @param quantite La nouvelle quantité de produits dans le panier.
     */
    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    /**
     * Définit l'ID du panier.
     *
     * @param id Le nouvel ID du panier.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Obtient la liste des produits dans le panier.
     *
     * @return La liste des produits dans le panier.
     */
    public ArrayList<Produit> getProduits() {
        return produits;
    }

    /**
     * Définit la liste des produits dans le panier.
     *
     * @param produits La nouvelle liste des produits dans le panier.
     */
    public void setProduits(ArrayList<Produit> produits) {
        this.produits = produits;
    }

    /**
     * Retourne une représentation sous forme de chaîne de caractères du panier.
     *
     * @return Une chaîne de caractères représentant le panier.
     */
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