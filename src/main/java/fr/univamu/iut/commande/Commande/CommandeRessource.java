package fr.univamu.iut.commande.Commande;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;

@Path("/commande")
@ApplicationScoped
public class CommandeRessource {

    @Inject
    private CommandeService service;

    @GET
    @Produces("application/json")
    public String getAllCommande() {
        return service.getAllCommandeJSON();
    }

    @GET
    @Path("{id}")
    @Produces("application/json")
    public String getCommande(@PathParam("id") int id) {
        String result = service.getCommandeJSON(id);
        if (result == null) {
            throw new NotFoundException();
        }
        return result;
    }

    @PUT
    @Path("{id}")
    @Consumes("application/json")
    public Response updateCommande(@PathParam("id") int id, Commande commande) {
        if (!service.updateCommande(id, commande)) {
            throw new NotFoundException();
        }
        return Response.ok("updated").build();
    }
}
