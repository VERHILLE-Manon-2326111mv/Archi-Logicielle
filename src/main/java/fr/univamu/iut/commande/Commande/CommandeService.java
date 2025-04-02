package fr.univamu.iut.commande.Commande;

import fr.univamu.iut.commande.Commande_Panier.Commande_Panier;
import fr.univamu.iut.commande.Panier.Panier;
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

    /**
     * Constructeur de la classe CommandeService
     * @param commandeRepo
     */
    @Inject
    public CommandeService(CommandeRepositoryInterface commandeRepo) {
        this.commandeRepo = commandeRepo;
    }

    /**
     * Constructeur de la classe CommandeService
     */
    public CommandeService() {}

    /**
     * Récupérer toutes les commandes
     * @return
     */
    public String getAllCommandeJSON() {
        ArrayList<Commande> allCommande = commandeRepo.getAllCommande();
        Jsonb jsonb = JsonbBuilder.create();

        // On parcourt chaque commande pour récupérer ses paniers
        for (Commande commande : allCommande) {
            ArrayList<Commande_Panier> paniers = commandeRepo.getAllPanierCommande(commande.getId());
            ArrayList<Panier> paniersProduit = new ArrayList<>();

            for (Commande_Panier panier : paniers) {
                String EXTERNAL_API_URL_PANIER = "http://localhost:7150/API_Panier-1.0-SNAPSHOT/api/panier/";

                // Appel de l'API pour chaque panier
                String jsonResponse = callExternalApi(EXTERNAL_API_URL_PANIER + panier.getId_panier());

                System.out.println("Réponse de l'API : " + jsonResponse);

                // Désérialisation du panier
                Panier panierr = jsonb.fromJson(jsonResponse, Panier.class);
                paniersProduit.add(panierr);
            }

            // Associer les paniers à la commande
            commande.setPanier(paniersProduit);
        }

        // Sérialisation de la liste de commandes
        try {
            return jsonb.toJson(allCommande);
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return "{}";
        }
    }

    /**
     * Récupérer une commande
     * @param id
     * @return
     */
    public String getCommandeJSON(int id) {
        ArrayList<Commande_Panier> paniers = commandeRepo.getAllPanierCommande(id);
        ArrayList<Panier> paniersProduit = new ArrayList<>();

        Jsonb jsonb = JsonbBuilder.create();

        for (Commande_Panier panier : paniers) {
            String EXTERNAL_API_URL_PANIER = "http://localhost:7150/API_Panier-1.0-SNAPSHOT/api/panier/";

            String jsonResponse = callExternalApi(EXTERNAL_API_URL_PANIER + panier.getId_panier());

            System.out.println("Réponse de l'API : " + jsonResponse);

            Panier panierr = jsonb.fromJson(jsonResponse, Panier.class);
            paniersProduit.add(panierr);
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

    /**
     * Met à jour une commande
     * @param commande
     * @return
     */
    public boolean updateCommande(Commande commande) {
        return commandeRepo.updateCommande(commande);
    }

    /**
     * Supprime une commande
     * @param commande
     * @return
     */
    public boolean deleteCommande(Commande commande) {
        return commandeRepo.deleteCommande(commande);
    }

    /**
     * Crée une commande
     * @param commande
     * @return
     */
    public boolean createCommande(Commande commande) {
        return commandeRepo.createCommande(commande);
    }

    /**
     * Valide une commande
     * @param commande
     * @return
     */
    public boolean valideCommande(Commande commande) {
        return commandeRepo.valideCommande(commande);
    }

    /**
     * Récupère toutes les commandes d'un utilisateur
     * @param commande
     * @return
     */
    public boolean addPanier(Commande_Panier commande) {
        return commandeRepo.addPanier(commande);
    }

    /**
     * Ajoute un panier à une commande
     * @param commande
     * @return
     */
    public boolean updatePanier(Commande_Panier commande) {
        return commandeRepo.updatePanier(commande);
    }

    /**
     * Met à jour un panier
     * @param commande
     * @return
     */
    public boolean deletePanier(Commande_Panier commande) {
        return commandeRepo.deletePanier(commande);
    }

    private static final String EXTERNAL_API_URL = "http://localhost:8080/Api_User_Produit-1.0-SNAPSHOT/api/produit/";

    /**
     * Appel à une API externe
     * @param apiUrl
     * @return
     */
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
