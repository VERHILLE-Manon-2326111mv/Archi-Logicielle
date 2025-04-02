package fr.univamu.iut.api_user_produit.produit;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;
import java.util.ArrayList;

/**
 * Service permettant de gérer les produits en effectuant des opérations CRUD
 * et en convertissant les données en JSON.
 */
@ApplicationScoped
public class ProduitService {

    /**
     * Référentiel des produits permettant d'effectuer les opérations sur la base de données.
     */
    protected ProduitRepositoryInterface produitRepo;

    /**
     * Constructeur permettant d'injecter le repository des produits.
     * @param produitRepo L'interface du repository permettant l'accès aux données des produits.
     */
    @Inject
    public ProduitService(ProduitRepositoryInterface produitRepo) {
        this.produitRepo = produitRepo;
    }

    /**
     * Constructeur par défaut.
     */
    public ProduitService() {}

    /**
     * Récupère tous les produits sous forme de JSON.
     * @return Une chaîne JSON contenant la liste de tous les produits.
     */
    public String getAllProduitJSON() {
        ArrayList<Produit> allProduit = produitRepo.getAllProduit();
        try (Jsonb jsonb = JsonbBuilder.create()) {
            return jsonb.toJson(allProduit);
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return "{}";
        }
    }

    /**
     * Récupère un produit spécifique sous forme de JSON.
     * @param id_produit L'identifiant du produit à récupérer.
     * @return Une chaîne JSON contenant le produit ou null s'il n'existe pas.
     */
    public String getProduitJSON(int id_produit) {
        Produit myProduit = produitRepo.getProduit(id_produit);
        if (myProduit == null) {
            return null;
        }
        try (Jsonb jsonb = JsonbBuilder.create()) {
            return jsonb.toJson(myProduit);
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return "{}";
        }
    }

    /**
     * Met à jour un produit existant dans la base de données.
     * @param produit Le produit contenant les nouvelles informations.
     * @return true si la mise à jour a réussi, false sinon.
     */
    public boolean updateProduit( Produit produit) {
        return produitRepo.updateProduit(produit.getId_produit(), produit.getNom(), produit.getQuantite(), produit.getPrix(), produit.getUnite());
    }

    /**
     * Supprime un produit de la base de données.
     * @param produit Le produit à supprimer.
     * @return true si la suppression a réussi, false sinon.
     */
    public boolean deleteProduit(Produit produit) {
        return produitRepo.deleteProduit(produit);
    }

    /**
     * Ajoute un nouveau produit dans la base de données.
     * @param produit Le produit à ajouter.
     * @return true si l'ajout a réussi, false sinon.
     */
    public boolean addProduit(Produit produit) {
        return produitRepo.addProduit(produit);
    }
}