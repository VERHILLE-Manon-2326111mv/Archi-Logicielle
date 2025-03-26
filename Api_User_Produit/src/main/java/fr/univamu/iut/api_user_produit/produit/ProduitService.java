package fr.univamu.iut.api_user_produit.produit;


import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;
import java.util.ArrayList;

@ApplicationScoped
public class ProduitService {

    protected ProduitRepositoryInterface produitRepo;

    @Inject
    public ProduitService(ProduitRepositoryInterface produitRepo) {
        this.produitRepo = produitRepo;
    }

    public ProduitService() {}

    public String getAllProduitJSON() {
        ArrayList<Produit> allProduit = produitRepo.getAllProduit();
        try (Jsonb jsonb = JsonbBuilder.create()) {
            return jsonb.toJson(allProduit);
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return "{}";
        }
    }

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

    public boolean updateProduit(int id_produit, Produit produit) {
        return produitRepo.updateProduit(id_produit, produit.getNom(), produit.getQuantite(), produit.getPrix(), produit.getUnite());
    }

}
