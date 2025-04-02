package fr.univamu.iut.api_user_produit.produit;

import java.util.Date;
/**
 * Classe représentant un produit dans l'application.
 */
public class Produit {
    /**
     * Identifiant unique du produit.
     */
    protected int id_produit;

    /**
     * Nom du produit.
     */
    protected String nom;

    /**
     * Quantité disponible du produit.
     */
    protected int quantite;

    /**
     * Prix du produit.
     */
    protected int prix;

    /**
     * Unité de mesure du produit (ex: kg, litre, pièce, etc.).
     */
    protected String unite;

    /**
     * Unité de mesure du produit (ex: kg, litre, pièce, etc.).
     */
    public Produit() {}

    /**
     * Constructeur de la classe Produit.
     *
     * @param id_produit Identifiant du produit.
     * @param nom Nom du produit.
     * @param quantite Quantité disponible du produit.
     * @param prix Prix du produit.
     * @param unite Unité de mesure du produit.
     */
    public Produit (int id_produit, String nom, int quantite, int prix, String unite) {
        this.id_produit = id_produit;
        this.nom = nom;
        this.quantite = quantite;
        this.prix = prix;
        this.unite = unite;
    }

    /**
     * Retourne l'identifiant du produit.
     * @return Identifiant du produit.
     */
   public int getId_produit() {
        return id_produit;
   }

    /**
     * Modifie l'identifiant du produit.
     * @param id_produit Nouvel identifiant du produit.
     */
   public void setId_produit(int id_produit) {
        this.id_produit = id_produit;
   }

    /**
     * Retourne le nom du produit.
     * @return Nom du produit.
     */
   public String getNom() {
        return nom;
   }

    /**
     * Modifie le nom du produit.
     * @param nom Nouveau nom du produit.
     */
   public void setNom(String nom) {
        this.nom = nom;
   }

    /**
     * Retourne la quantité disponible du produit.
     * @return Quantité du produit.
     */
   public int getQuantite() {
        return quantite;
   }

    /**
     * Modifie la quantité disponible du produit.
     * @param quantite Nouvelle quantité du produit.
     */
   public void setQuantite(int quantite) {
        this.quantite = quantite;
   }

    /**
     * Retourne le prix du produit.
     * @return Prix du produit.
     */
   public int getPrix() {
        return prix;
   }

    /**
     * Modifie le prix du produit.
     * @param prix Nouveau prix du produit.
     */
   public void setPrix(int prix) {
        this.prix = prix;
   }

    /**
     * Retourne l'unité de mesure du produit.
     * @return Unité de mesure du produit.
     */
   public String getUnite() {
        return unite;
   }

    /**
     * Modifie l'unité de mesure du produit.
     * @param unite Nouvelle unité du produit.
     */
   public void setUnite(String unite) {
        this.unite = unite;
   }
}