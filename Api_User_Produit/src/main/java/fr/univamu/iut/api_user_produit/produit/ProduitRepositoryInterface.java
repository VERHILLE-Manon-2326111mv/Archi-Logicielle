package fr.univamu.iut.api_user_produit.produit;
import java.util.*;

/**
 * Interface définissant les opérations de gestion des produits dans un référentiel.
 */
public interface ProduitRepositoryInterface {

    /**
     * Ferme la connexion au référentiel de produits.
     */
    public void close();

    /**
     * Récupère un produit à partir de son identifiant.
     * @param id L'identifiant du produit.
     * @return L'objet {@link Produit} correspondant ou {@code null} si non trouvé.
     */
    public Produit getProduit( int id );

    /**
     * Récupère la liste de tous les produits disponibles.
     * @return Une liste d'objets {@link Produit}.
     */
    public ArrayList<Produit> getAllProduit() ;

    /**
     * Met à jour les informations d'un produit.
     *
     * @param id_produit L'identifiant du produit à mettre à jour.
     * @param nom        Le nouveau nom du produit.
     * @param quantite   La nouvelle quantité disponible du produit.
     * @param prix       Le nouveau prix du produit.
     * @param unite      La nouvelle unité de mesure du produit.
     * @return {@code true} si la mise à jour a été effectuée avec succès, sinon {@code false}.
     */
    public boolean updateProduit(int id_produit, String nom, int quantite, int prix, String unite);

    /**
     * Supprime un produit du référentiel.
     * @param produit Le produit à supprimer.
     * @return {@code true} si la suppression a été effectuée avec succès, sinon {@code false}.
     */
    public boolean deleteProduit(Produit produit);

    /**
     * Ajoute un nouveau produit au référentiel.
     * @param produit Le produit à ajouter.
     * @return {@code true} si l'ajout a été effectué avec succès, sinon {@code false}.
     */
    public boolean addProduit(Produit produit);
}