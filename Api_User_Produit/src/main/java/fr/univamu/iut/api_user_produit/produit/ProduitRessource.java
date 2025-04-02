package fr.univamu.iut.api_user_produit.produit;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;

/**
 * Ressource REST pour gérer les produits via des requêtes HTTP.
 */
@Path("/produit")
@ApplicationScoped
public class ProduitRessource {

    /**
     * Service permettant de gérer les produits.
     */
    @Inject
    private ProduitService service;

    /**
     * Récupère tous les produits sous forme de JSON.
     * @return Une chaîne JSON contenant tous les produits.
     */
    @GET
    @Produces("application/json")
    public String getAllProduit() {
        return service.getAllProduitJSON();
    }

    /**
     * Récupère un produit spécifique en fonction de son identifiant.
     * @param id_produit L'identifiant du produit à récupérer.
     * @return Une chaîne JSON contenant le produit.
     * @throws NotFoundException Si le produit n'existe pas.
     */
    @GET
    @Path("{id_produit}")
    @Produces("application/json")
    public String getProduit(@PathParam("id_produit") int id_produit) {
        String result = service.getProduitJSON(id_produit);
        if (result == null) {
            throw new NotFoundException();
        }
        return result;
    }

    /**
     * Met à jour un produit existant dans la base de données.
     * @param produit Le produit contenant les nouvelles valeurs.
     * @return Une réponse HTTP 200 OK si la mise à jour est réussie.
     * @throws NotFoundException Si le produit à mettre à jour n'existe pas.
     */
    @PUT
    @Path("/update")
    @Consumes("application/json")
    public Response updateProduit(Produit produit) {
        if (!service.updateProduit(produit)) {
            throw new NotFoundException();
        }
        return Response.ok("updated").build();
    }

    /**
     * Supprime un produit de la base de données.
     * @param produit Le produit à supprimer.
     * @return Une réponse HTTP 200 OK si la suppression est réussie.
     * @throws NotFoundException Si le produit à supprimer n'existe pas.
     */
    @PUT
    @Path("/delete")
    @Consumes("application/json")
    public Response deleteProduit(Produit produit) {
        if (!service.deleteProduit(produit)) {
            throw new NotFoundException();
        }
        return Response.ok("deleted").build();
    }

    /**
     * Ajoute un nouveau produit dans la base de données.
     * @param produit Le produit à ajouter.
     * @return Une réponse HTTP 200 OK si l'ajout est réussi.
     * @throws NotFoundException Si l'ajout échoue.
     */
    @POST
    @Path("/add")
    @Consumes("application/json")
    public Response addProduit(Produit produit) {
        if (!service.addProduit(produit)) {
            throw new NotFoundException();
        }
        return Response.ok("add").build();
    }
}