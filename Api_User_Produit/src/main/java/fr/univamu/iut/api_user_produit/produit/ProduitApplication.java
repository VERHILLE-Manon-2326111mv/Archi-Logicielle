package fr.univamu.iut.api_user_produit.produit;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Produces;
import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.core.Application;

/**
 * Classe représentant l'application JAX-RS pour la gestion des produits.
 * Définit le chemin d'accès de l'API et gère la connexion à la base de données.
 */
@ApplicationPath("/api")
@ApplicationScoped
public class ProduitApplication extends Application {

    /**
     * Produit une instance de {@link ProduitRepositoryInterface} avec une connexion à la base de données MariaDB.
     * Cette méthode est annotée avec {@code @Produces} et {@code @ApplicationScoped} pour être utilisée comme une
     * ressource injectable dans l'application.
     *
     * @return Une instance de {@link ProduitRepositoryMariadb} connectée à la base de données.
     * @throws RuntimeException Si une erreur survient lors de la connexion à la base de données.
     */
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