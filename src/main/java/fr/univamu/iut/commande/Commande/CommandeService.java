package fr.univamu.iut.commande.Commande;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;
import java.util.ArrayList;

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
        Commande myCommande = commandeRepo.getCommande(id);
        if (myCommande == null) {
            return null;
        }
        try (Jsonb jsonb = JsonbBuilder.create()) {
            return jsonb.toJson(myCommande);
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return "{}";
        }
    }

    public boolean updateCommande(int id, Commande commande) {
        return commandeRepo.updateCommande(id, commande.getId_user(), commande.getPrix(), commande.isValide(), commande.getDate_echeance(), commande.getPoint_relai());
    }
}
