package fr.univamu.iut.api_panier.panier;

import fr.univamu.iut.api_panier.Panier_Produit.Panier_Produit;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/panier")
@ApplicationScoped
public class PanierRessource {

    @Inject
    private PanierService panierService;

    @GET
    @Produces("application/json")
    public String getAllPaniers() {
        return panierService.getPaniersJSON();
    }

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

    @POST
    @Path("/create")
    @Produces("application/json")
    public Response createPanier(Panier panier) {
        Panier createdPanier = panierService.panierRepo.createPanier(panier);
        if (createdPanier == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        return Response.ok(createdPanier).build();
    }

    @PUT
    @Path("/update/{id}")
    @Consumes("application/json")
    public Response updatePanier(@PathParam("id") int id, Panier panier) {
        panierService.panierRepo.updatePanier(id, panier.getNom(), panier.getDatemaj(), panier.getPrix(), panier.getQuantite());
        return Response.ok("updated").build();
    }

    @DELETE
    @Path("/delete/{id}")
    public Response deletePanier(@PathParam("id") int id) {
        panierService.panierRepo.deletePanier(id);
        return Response.ok("deleted").build();
    }

    // Ajouter un produit dans un panier
    @POST
    @Path("/panierProduit/add")
    @Consumes("application/json")
    public Response addProduitPanier(Panier_Produit panier_produit) {
        panierService.panierRepo.addProduitPanier(panier_produit);
        return Response.ok("Produit ajouté au panier").build();
    }

    // Modifier la quantité d’un produit dans un panier
    @PUT
    @Path("/panierProduit/update/{id}")
    @Consumes("application/json")
    public Response updateProduitPanier(@PathParam("id") Panier_Produit panier_produit) {
        panierService.panierRepo.updateProduitPanier(panier_produit);
        return Response.ok("Quantité mise à jour").build();
    }

    @DELETE
    @Path("/panierProduit/delete/{id}")
    @Consumes("application/json")
    public Response deleteProduitPanier(@PathParam("id") Panier_Produit panier_produit) {
        panierService.panierRepo.deleteProduitPanier(panier_produit);
        return Response.ok("Produit supprimé du panier").build();
    }

    @GET
    @Path("/panierProduit/{id_produit}")
    @Produces("application/json")
    public Response getProduitPanier(@PathParam("id_produit") int id_produit) {
        Panier_Produit panierProduit = panierService.panierRepo.getPaniersProduit(id_produit);
        if (panierProduit == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(panierProduit).build();
    }

    // Récupérer tous les produits des paniers
    @GET
    @Path("/panierProduit")
    @Produces("application/json")
    public Response getAllProduitsPanier() {
        List<Panier_Produit> produits = panierService.panierRepo.getPaniersProduit();
        return Response.ok(produits).build();
    }

    @GET
    @Path("/external")
    @Produces("application/json")
    public String getExternalApiResponse() {
        return panierService.callExternalApi("http://localhost:8080/Api_User_Produit-1.0-SNAPSHOT/api/produit/");
    }

}
