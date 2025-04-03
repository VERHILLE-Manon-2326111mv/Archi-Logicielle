package fr.univamu.iut.api_panier.panier;

import fr.univamu.iut.api_panier.Panier_Produit.Panier_Produit;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;

import java.util.List;

/**
 * Classe PanierRessource
 * Fournit des endpoints REST pour gérer les opérations sur les paniers et les produits associés.
 */
@Path("/panier")
@ApplicationScoped
public class PanierRessource {

    @Inject
    private PanierService panierService;

    /**
     * Récupère tous les paniers.
     *
     * @return Une chaîne JSON représentant tous les paniers.
     */
    @GET
    @Produces("application/json")
    public String getAllPaniers() {
        return panierService.getPaniersJSON();
    }

    /**
     * Récupère un panier par son ID.
     *
     * @param id L'ID du panier à récupérer.
     * @return Une chaîne JSON représentant le panier.
     */
    @GET
    @Path("/{id}")
    @Produces("application/json")
    public String getPanier(@PathParam("id") int id) {
        String result = panierService.getPanierJSON(id);
        if (result == null || result.equals("{}")) {
            throw new NotFoundException();
        }
        return result;
    }

    /**
     * Crée un nouveau panier.
     *
     * @param panier Le panier à créer.
     * @return Une réponse HTTP avec le panier créé.
     */
    @POST
    @Path("/create")
    @Produces("application/json")
    public Response createPanier(Panier panier) {
        Panier createdPanier = panierService.createPanier(panier);
        if (createdPanier == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        return Response.ok(createdPanier).build();
    }

    /**
     * Met à jour un panier existant.
     *
     * @param id L'ID du panier à mettre à jour.
     * @param panier Le panier avec les nouvelles informations.
     * @return Une réponse HTTP indiquant le succès de l'opération.
     */
    @PUT
    @Path("/update/{id}")
    @Consumes("application/json")
    public Response updatePanier(@PathParam("id") int id, Panier panier) {
        panierService.updatePanier(id, panier.getNom(), panier.getDatemaj(), panier.getPrix(), panier.getQuantite());
        return Response.ok("updated").build();
    }

    /**
     * Supprime un panier par son ID.
     *
     * @param id L'ID du panier à supprimer.
     * @return Une réponse HTTP indiquant le succès de l'opération.
     */
    @DELETE
    @Path("/delete/{id}")
    public Response deletePanier(@PathParam("id") int id) {
        panierService.deletePanier(id);
        return Response.ok("deleted").build();
    }

    /**
     * Ajoute un produit dans un panier.
     *
     * @param panier_produit L'association panier-produit à ajouter.
     * @return Une réponse HTTP indiquant le succès de l'opération.
     */
    @POST
    @Path("/panierProduit/add")
    @Consumes("application/json")
    public Response addProduitPanier(Panier_Produit panier_produit) {
        panierService.addProduitPanier(panier_produit);
        return Response.ok("Produit ajouté au panier").build();
    }

    /**
     * Met à jour la quantité d'un produit dans un panier.
     *
     * @param panier_produit L'association panier-produit à mettre à jour.
     * @return Une réponse HTTP indiquant le succès de l'opération.
     */
    @PUT
    @Path("/panierProduit/update")
    @Consumes("application/json")
    public Response updateProduitPanier(Panier_Produit panier_produit) {
        panierService.panierRepo.updateProduitPanier(panier_produit);
        return Response.ok("Quantité mise à jour").build();
    }

    /**
     * Supprime un produit d'un panier.
     *
     * @param panier_produit L'association panier-produit à supprimer.
     * @return Une réponse HTTP indiquant le succès de l'opération.
     */
    @DELETE
    @Path("/panierProduit/delete")
    @Consumes("application/json")
    public Response deleteProduitPanier(Panier_Produit panier_produit) {
        panierService.deleteProduitPanier(panier_produit);
        return Response.ok("Produit supprimé du panier").build();
    }

    /**
     * Récupère une association panier-produit par l'ID du produit.
     *
     * @param id_produit L'ID du produit.
     * @return Une réponse HTTP avec l'association panier-produit.
     */
    @GET
    @Path("/panierProduit/{id_produit}")
    @Produces("application/json")
    public Response getProduitPanier(@PathParam("id_produit") int id_produit) {
        Panier_Produit panierProduit = panierService.getPaniersProduit(id_produit);
        if (panierProduit == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(panierProduit).build();
    }

    /**
     * Récupère tous les produits des paniers.
     *
     * @return Une réponse HTTP avec la liste de toutes les associations panier-produit.
     */
    @GET
    @Path("/panierProduit")
    @Produces("application/json")
    public Response getAllProduitsPanier() {
        List<Panier_Produit> produits = panierService.getPaniersProduit();
        return Response.ok(produits).build();
    }

    /**
     * Récupère la réponse d'une API externe.
     *
     * @return Une chaîne JSON représentant la réponse de l'API externe.
     */
    @GET
    @Path("/external")
    @Produces("application/json")
    public String getExternalApiResponse() {
        return panierService.callExternalApi("http://localhost:8080/Api_User_Produit-1.0-SNAPSHOT/api/produit/");
    }

}