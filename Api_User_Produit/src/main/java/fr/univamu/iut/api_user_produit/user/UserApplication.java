package fr.univamu.iut.api_user_produit.user;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Produces;
import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.core.Application;

/**
 * Classe principale de l'application REST pour la gestion des utilisateurs.
 * Elle définit le chemin de base de l'API et gère la connexion à la base de données.
 */
@ApplicationPath("/api")
@ApplicationScoped
public class UserApplication extends Application {

    /**
     * Initialise et produit une instance du repository utilisateur pour gérer les interactions
     * avec la base de données MariaDB.
     * @return Une instance de {@link UserRepositoryInterface} connectée à la base de données.
     * @throws RuntimeException si une erreur survient lors de la connexion.
     */
    @Produces
    @ApplicationScoped
    public UserRepositoryInterface openDbConnection() {
        try {
            return new UserRepositoryMariadb("jdbc:mariadb://mysql-vente-agricole-mrec.alwaysdata.net/vente-agricole-mrec_bdd", "406088_evan", "Banane23!");
        } catch (Exception e) {
            throw new RuntimeException("Erreur lors de la connexion à la base de données", e);
        }
    }
}