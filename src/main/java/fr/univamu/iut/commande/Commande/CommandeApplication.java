package fr.univamu.iut.commande.Commande;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Produces;
import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.core.Application;

@ApplicationPath("/api")
@ApplicationScoped
public class CommandeApplication extends Application {

    @Produces
    @ApplicationScoped
    public CommandeRepositoryInterface openDbConnection() {
        try {
            return new CommandeRepositoryMariadb("jdbc:mariadb://mysql-vente-agricole-mrec.alwaysdata.net/vente-agricole-mrec_bdd", "406088_evan", "Banane23!");
        } catch (Exception e) {
            throw new RuntimeException("Erreur lors de la connexion à la base de données", e);
        }
    }
}
