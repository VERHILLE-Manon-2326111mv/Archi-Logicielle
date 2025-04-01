package fr.univamu.iut.commande.Commande;

import fr.univamu.iut.commande.Commande_Panier.Commande_Panier;
import fr.univamu.iut.commande.Produit.Produit;
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
import java.util.Date;
import java.util.List;

@ApplicationScoped
public class CommandeService {

    protected CommandeRepositoryInterface commandeRepo;

    @Inject
    public CommandeService(CommandeRepositoryInterface commandeRepo) {
        this.commandeRepo = commandeRepo;
    }

    public CommandeService() {}

    public String getAllCommandeJSON() {
        ArrayList<Commande> allCommande = commandeRepo.getAllCommande();
        try (Jsonb jsonb = JsonbBuilder.create()) {
            return jsonb.toJson(allCommande);
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return "{}";
        }
    }

    public String getCommandeJSON(int id) {
        ArrayList<Commande_Panier> paniers = commandeRepo.getAllPanierCommande(id);
        ArrayList<Produit> paniersProduit = new ArrayList<>();

        Jsonb jsonb = JsonbBuilder.create();

        for (Commande_Panier panier : paniers) {
            String EXTERNAL_API_URL_PRODUIT = "http://localhost:8080/Api_User_Produit-1.0-SNAPSHOT/api/produit/";
            String jsonResponse = callExternalApi(EXTERNAL_API_URL_PRODUIT + panier.getId_panier());

            Produit produit = jsonb.fromJson(jsonResponse, Produit.class);
            paniersProduit.add(produit);
        }

        Commande myCommande = commandeRepo.getCommande(id);
        myCommande.setPanier(paniersProduit);

        try {
            return jsonb.toJson(myCommande);
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return "{}";
        }
    }

    public boolean updateCommande(Commande commande) {
        return commandeRepo.updateCommande(commande);
    }

    public boolean deleteCommande(Commande commande) {
        return commandeRepo.deleteCommande(commande);
    }

    public boolean createCommande(Commande commande) {
        return commandeRepo.createCommande(commande);
    }

    public boolean valideCommande(Commande commande) {
        return commandeRepo.valideCommande(commande);
    }

    public boolean addPanier(Commande_Panier commande) {
        return commandeRepo.addPanier(commande);
    }

    public boolean updatePanier(Commande_Panier commande) {
        return commandeRepo.updatePanier(commande);
    }

    public boolean deletePanier(Commande_Panier commande) {
        return commandeRepo.deletePanier(commande);
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
