package fr.univamu.iut.api_user_produit.produit;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Produces;
import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.core.Application;

@ApplicationPath("/api")
@ApplicationScoped
public class ProduitApplication extends Application {

    @Produces
    @ApplicationScoped
    public ProduitRepositoryInterface openDbConnection() {
        try {
            return new ProduitRepositoryMariadb("jdbc:mariadb://mysql-vente-agricole-mrec.alwaysdata.net/vente-agricole-mrec_bdd", "406088_evan", "Banane23!");
        } catch (Exception e) {
            throw new RuntimeException("Erreur lors de la connexion à la base de données", e);
        }
    }
}