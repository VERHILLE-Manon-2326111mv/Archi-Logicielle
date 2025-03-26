package fr.univamu.iut.commande.Commande_Panier;

public class Commande_Panier {

    protected int id_commande;

    protected int id_panier;

    protected int quantite;

    public Commande_Panier() {}

    public Commande_Panier(int id_commande, int id_panier, int quantite) {
        this.id_commande = id_commande;
        this.id_panier = id_panier;
        this.quantite = quantite;
    }


    public int getId_commande() {
        return id_commande;
    }

    public void setId_commande(int id_commande) {
        this.id_commande = id_commande;
    }

    public int getId_panier() {
        return id_panier;
    }

    public void setId_panier(int id_panier) {
        this.id_panier = id_panier;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }
}
