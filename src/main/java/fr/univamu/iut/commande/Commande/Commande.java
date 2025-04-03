package fr.univamu.iut.commande.Commande;

import fr.univamu.iut.commande.Panier.Panier;
import fr.univamu.iut.commande.Produit.Produit;

import java.util.ArrayList;
import java.util.Date;


public class Commande {
    protected int id;

    protected int id_user;

    protected int prix;

    protected boolean valide;

    protected Date date_echeance = null;

    protected String point_relai = null;

    protected ArrayList<Panier> panier = null;

    /**
     * Constructeur de la classe Commande
     */
    public Commande() {}


    /**
     * Constructeur de la classe Commande
     * @param id_user
     * @param prix
     * @param valide
     * @param date_echeance
     * @param point_relai
     */
    public Commande(int id_user, int prix, boolean valide, Date date_echeance, String point_relai ) {
        this.id_user = id_user;
        this.prix = prix;
        this.valide = valide;
        this.date_echeance = date_echeance;
        this.point_relai = point_relai;
    }

    /**
     * Constructeur de la classe Commande
     * @param id_user
     * @param prix
     */
    public Commande(int id_user, int prix) {
        this.id_user = id_user;
        this.prix = prix;
        this.valide = false;
        this.date_echeance = null;
        this.point_relai = null;
    }


    /**
     * Constructeur de la classe Commande
     * @param id
     * @param id_user
     * @param prix
     * @param valide
     * @param date_echeance
     * @param point_relai
     */
    public Commande(int id, int id_user, int prix, boolean valide, Date date_echeance, String point_relai) {
        this.id = id;
        this.id_user = id_user;
        this.prix = prix;
        this.valide = valide;
        this.date_echeance = date_echeance;
        this.point_relai = point_relai;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public int getPrix() {
        return prix;
    }

    public void setPrix(int prix) {
        this.prix = prix;
    }

    public boolean isValide() {
        return valide;
    }

    public void setValide(boolean valide) {
        this.valide = valide;
    }

    public Date getDate_echeance() {
        return date_echeance;
    }

    public void setDate_echeance(Date date_echeance) {
        this.date_echeance = date_echeance;
    }

    public String getPoint_relai() {
        return point_relai;
    }

    public void setPoint_relai(String point_relai) {
        this.point_relai = point_relai;
    }

    /**
     * Getter de la classe Commande
     * @return
     */
    public ArrayList<Panier> getPanier() {
        return panier;
    }

    /**
     * Setter de la classe Commande
     * @param panier
     */
    public void setPanier(ArrayList<Panier> panier) {
        this.panier = panier;
    }
}
