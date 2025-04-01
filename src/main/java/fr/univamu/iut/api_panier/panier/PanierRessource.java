package fr.univamu.iut.api_panier.panier;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;

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
    @Path("{id}")
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
}
