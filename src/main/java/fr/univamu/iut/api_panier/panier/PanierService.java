package fr.univamu.iut.api_panier.panier;

import fr.univamu.iut.api_panier.Panier_Produit.Panier_Produit;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;


@ApplicationScoped
public class PanierService{

    protected PanierRepositoryInterface panierRepo;

    @Inject
    public PanierService(PanierRepositoryInterface panierRepo){this.panierRepo = panierRepo;}

    public PanierService(){}

    public String getPanierJSON(int id) {
        Panier panier = panierRepo.getPanier(id);
        if (panier == null) {
            return "{}";
        }

        Jsonb jsonb = JsonbBuilder.create();
        ArrayList<Produit> produitsDansPanier = new ArrayList<>();

        String EXTERNAL_API_URL_PRODUIT = "http://localhost:8080/Api_User_Produit-1.0-SNAPSHOT/api/produit/";

        // Récupération des produits associés au panier
        List<Panier_Produit> paniersProduits = panierRepo.getPaniersProduit();

        for (Panier_Produit panierProduit : paniersProduits) {
            if (panierProduit.getId_panier() == id) {
                String jsonResponse = callExternalApi(EXTERNAL_API_URL_PRODUIT + panierProduit.getId_produit());

                try {
                    Produit produit = jsonb.fromJson(jsonResponse, Produit.class);
                    produitsDansPanier.add(produit);
                } catch (Exception e) {
                    System.err.println("Erreur de parsing JSON produit : " + e.getMessage());
                }
            }
        }

        // Associer les produits au panier
        panier.setProduits(produitsDansPanier);

        try {
            return jsonb.toJson(panier);
        } catch (Exception e) {
            System.err.println("Erreur de conversion en JSON : " + e.getMessage());
            return "{}";
        }
    }


    public String getPaniersJSON(){
        ArrayList<Panier> paniers = (ArrayList<Panier>) panierRepo.getPaniers();
        try (Jsonb jsonb = JsonbBuilder.create()){
            return jsonb.toJson(paniers);
        }
        catch (Exception e){
            return null;
        }
    }

    public int getIdPanierJSON(Panier panier){
        return panierRepo.getIdPanier(panier);
    }


    public void createPanier(Panier panier){
        panierRepo.createPanier(panier);
    }

    public void deletePanier(int id){
        panierRepo.deletePanier(id);
    }

    public void updatePanier(int id, String nom, java.util.Date datemaj, int prix, int quantite){
        panierRepo.updatePanier(id, nom, datemaj, prix, quantite);
    }

    private static final String EXTERNAL_API_URL = "http://localhost:8080/Api_User_Produit-1.0-SNAPSHOT/api/produit/";

    public String callExternalApi( String apiUrl) {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(apiUrl))
                .GET()
                .build();

        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            return response.body();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            return "Erreur lors de l'appel à l'API";
        }
    }
}
