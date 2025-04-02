package fr.univamu.iut.api_panier.panier;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.ApplicationPath;
import jakarta.enterprise.inject.Produces;
import jakarta.ws.rs.core.Application;

/**
 * Classe PanierApplication
 * Configure l'application JAX-RS et produit une instance de PanierRepositoryInterface.
 */
@ApplicationPath("/api")
@ApplicationScoped
public class PanierApplication extends Application {

    /**
     * Produit une instance de PanierRepositoryInterface.
     *
     * @return Une instance de PanierRepositoryMariadb connectée à la base de données.
     * @throws RuntimeException si une erreur survient lors de la connexion à la base de données.
     */
    @Produces
    @ApplicationScoped
    public PanierRepositoryInterface openDbConnection() {
        try {
            return new PanierRepositoryMariadb("jdbc:mariadb://mysql-vente-agricole-mrec.alwaysdata.net/vente-agricole-mrec_bdd", "406088_ronan", "Banane23!");
        }
        catch (Exception e) {
            throw new RuntimeException("Erreur lors de la connexion à la base de données", e);
        }
    }

}