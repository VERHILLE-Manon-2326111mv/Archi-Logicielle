package fr.univamu.iut.commande.Commande;

import fr.univamu.iut.commande.Commande_Panier.Commande_Panier;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;

import java.util.Date;

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
    @Path("/{id}")
    @Produces("application/json")
    public String getCommande(@PathParam("id") int id) {
        String result = service.getCommandeJSON(id);
        if (result == null) {
            throw new NotFoundException();
        }
        return result;
    }

    @PUT
    @Path("/update")
    @Consumes("application/json")
    public Response updateCommande(Commande commande) {
        if (!service.updateCommande(commande)) {
            throw new NotFoundException();
        }
        return Response.ok("updated").build();
    }

    @PUT
    @Path("/delete")
    @Consumes("application/json")
    public Response deleteCommande(Commande commande) {
        if (!service.deleteCommande(commande)) {
            throw new NotFoundException();
        }
        return Response.ok("deleted").build();
    }

    @POST
    @Path("/create}")
    @Consumes("application/json")
    public Response createCommande(Commande commande) {
        if (!service.createCommande(commande)) {
            throw new NotFoundException();
        }
        return Response.ok("created").build();
    }

    @POST
    @Path("/valide")
    @Consumes("application/json")
    public Response valideCommande(Commande commande) {
        if (!service.valideCommande(commande)) {
            throw new NotFoundException();
        }
        return Response.ok("valide").build();
    }

    @POST
    @Path("/addCommandePanier")
    @Consumes("application/json")
    public Response addPanier(Commande_Panier commande) {
        if (!service.addPanier(commande)) {
            throw new NotFoundException();
        }
        return Response.ok("added").build();
    }

    @PUT
    @Path("/updateCommandePanier")
    @Consumes("application/json")
    public Response updatePanier(Commande_Panier commande) {
        if (!service.updatePanier(commande)) {
            throw new NotFoundException();
        }
        return Response.ok("updated").build();
    }

    @PUT
    @Path("/deleteCommandePanier")
    @Consumes("application/json")
    public Response deletePanier(Commande_Panier commande) {
        if (!service.deletePanier(commande)) {
            throw new NotFoundException();
        }
        return Response.ok("deleted").build();
    }
}
