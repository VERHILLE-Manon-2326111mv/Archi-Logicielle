package fr.univamu.fr.iut.api_panier;

import java.util.Date;

/***
 * Classe Panier
 */
public class Panier {

    protected int id;
    protected String nom;
    protected Date datemaj;
    protected int prix;
    protected int quantite;

    public Panier(String nom, Date datemaj, int prix, int quantite) {
        this.nom = nom;
        this.datemaj = datemaj;
        this.prix = prix;
        this.quantite = quantite;
    }

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