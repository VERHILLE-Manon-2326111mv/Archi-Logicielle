package fr.univamu.iut.api_panier.panier_produit;

import fr.univamu.iut.api_panier.panier.PanierRepositoryMariadb;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.Produces;

@ApplicationPath("/api")
@ApplicationScoped
public class Panier_ProduitApplication {

    @Produces
    @ApplicationScoped
    public Panier_ProduitService panier_produitService() {
        try {
            return new PanierRepositoryMariadb("jdbc:mariadb://mysql-vente-agricole-mrec.alwaysdata.net/vente-agricole-mrec_bdd", "406088_ronan", "Banane23!");
        }
        catch (Exception e) {
            throw new RuntimeException("Erreur lors de la connexion à la base de données", e);
        }
    }

}
