package fr.univamu.iut.commande.Commande;

import fr.univamu.iut.commande.Commande_Panier.Commande_Panier;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;
import java.util.ArrayList;
import java.util.Date;

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
}
