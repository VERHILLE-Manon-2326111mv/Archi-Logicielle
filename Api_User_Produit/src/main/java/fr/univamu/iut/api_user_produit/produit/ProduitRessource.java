package fr.univamu.iut.api_user_produit.produit;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;

@Path("/produit")
@ApplicationScoped
public class ProduitRessource {

    @Inject
    private ProduitService service;

    @GET
    @Produces("application/json")
    public String getAllProduit() {
        return service.getAllProduitJSON();
    }

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

    @PUT
    @Path("{id_produit}")
    @Consumes("application/json")
    public Response updateProduit(@PathParam("id_produit") int id_produit, Produit produit) {
        if (!service.updateProduit(id_produit, produit)) {
            throw new NotFoundException();
        }
        return Response.ok("updated").build();
    }
}