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
    @Path("/update")
    @Consumes("application/json")
    public Response updateProduit(Produit produit) {
        if (!service.updateProduit(produit)) {
            throw new NotFoundException();
        }
        return Response.ok("updated").build();
    }

    @PUT
    @Path("/delete")
    @Consumes("application/json")
    public Response deleteProduit(Produit produit) {
        if (!service.deleteProduit(produit)) {
            throw new NotFoundException();
        }
        return Response.ok("deleted").build();
    }

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