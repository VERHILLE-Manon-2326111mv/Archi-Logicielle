package fr.univamu.iut.api_panier.panier;

import fr.univamu.iut.api_panier.Panier_Produit.Panier_Produit;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.*;

/**
 * Interface PanierRepositoryInterface
 * Définit les méthodes pour gérer les opérations CRUD sur les paniers et les produits associés.
 */
public interface PanierRepositoryInterface {

    /**
     * Ferme la connexion au dépôt.
     */
    public void close();

    /**
     * Crée un nouveau panier.
     *
     * @param panier Le panier à créer.
     * @return Le panier créé.
     */
    public Panier createPanier(Panier panier);

    /**
     * Récupère un panier par son ID.
     *
     * @param id L'ID du panier à récupérer.
     * @return Le panier correspondant à l'ID.
     */
    public Panier getPanier(int id);

    /**
     * Récupère tous les paniers.
     *
     * @return Une liste de tous les paniers.
     */
    public List<Panier> getPaniers();

    /**
     * Supprime un panier par son ID.
     *
     * @param id L'ID du panier à supprimer.
     */
    public void deletePanier(int id);

    /**
     * Met à jour un panier existant.
     *
     * @param id L'ID du panier à mettre à jour.
     * @param nom Le nouveau nom du panier.
     * @param datemaj La nouvelle date de mise à jour du panier.
     * @param prix Le nouveau prix du panier.
     * @param quantite La nouvelle quantité de produits dans le panier.
     */
    public void updatePanier(int id, String nom, Date datemaj, int prix, int quantite);

    /**
     * Récupère l'ID d'un panier.
     *
     * @param panier Le panier dont l'ID est à récupérer.
     * @return L'ID du panier.
     */
    public int getIdPanier(Panier panier);

    /**
     * Ajoute un produit à un panier.
     *
     * @param panier_produit L'association panier-produit à ajouter.
     */
    public void addProduitPanier(Panier_Produit panier_produit);

    /**
     * Supprime un produit d'un panier.
     *
     * @param panier_produit L'association panier-produit à supprimer.
     */
    public void deleteProduitPanier(Panier_Produit panier_produit);

    /**
     * Met à jour un produit dans un panier.
     *
     * @param panier_produit L'association panier-produit à mettre à jour.
     */
    public void updateProduitPanier(Panier_Produit panier_produit);

    /**
     * Récupère toutes les associations panier-produit.
     *
     * @return Une liste de toutes les associations panier-produit.
     */
    public List<Panier_Produit> getPaniersProduit();

    /**
     * Récupère une association panier-produit par l'ID du produit.
     *
     * @param id_produit L'ID du produit.
     * @return L'association panier-produit correspondant à l'ID du produit.
     */
    public Panier_Produit getPaniersProduit(int id_produit);
}