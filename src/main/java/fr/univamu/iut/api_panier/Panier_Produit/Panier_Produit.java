package fr.univamu.iut.api_panier.Panier_Produit;

public class Panier_Produit implements java.io.Serializable {

    private int id_panier;
    private int id_produit;
    private int quantite_produit;

    /**
     * Constructeur de la classe Panier_Produit.
     *
     * @param id_panier L'ID du panier.
     * @param id_produit L'ID du produit.
     * @param quantite_produit La quantité de produit dans le panier.
     */
    public Panier_Produit(int id_panier, int id_produit, int quantite_produit) {
        this.id_panier = id_panier;
        this.id_produit = id_produit;
        this.quantite_produit = quantite_produit;
    }

    /**
     * Constructeur par défaut de la classe Panier_Produit.
     */
    public Panier_Produit() {
    }

    /**
     * Récupère l'ID du panier.
     *
     * @return L'ID du panier.
     */
    public int getId_panier() {
        return id_panier;
    }

    /**
     * Récupère l'ID du produit.
     *
     * @return L'ID du produit.
     */
    public int getId_produit() {
        return id_produit;
    }

    /**
     * Récupère la quantité de produit dans le panier.
     *
     * @return La quantité de produit dans le panier.
     */
    public int getQuantite() {
        return quantite_produit;
    }

    /**
     * Définit l'ID du panier.
     *
     * @param id_panier L'ID du panier.
     */
    public void setId_panier(int id_panier) {
        this.id_panier = id_panier;
    }

    /**
     * Définit l'ID du produit.
     *
     * @param id_produit L'ID du produit.
     */
    public void setId_produit(int id_produit) {
        this.id_produit = id_produit;
    }

    /**
     * Définit la quantité de produit dans le panier.
     *
     * @param quantite La quantité de produit dans le panier.
     */
    public void setQuantite(int quantite) {
        this.quantite_produit = quantite;
    }

    /**
     * Retourne une représentation sous forme de chaîne de caractères de l'objet Panier_Produit.
     *
     * @return Une chaîne de caractères représentant l'objet Panier_Produit.
     */
    @Override
    public String toString() {
        return "Panier_Produit{" +
                ", id_panier=" + id_panier +
                ", id_produit=" + id_produit +
                ", quantite=" + quantite_produit +
                '}';
    }
}