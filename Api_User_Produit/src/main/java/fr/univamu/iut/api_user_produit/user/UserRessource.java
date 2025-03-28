package fr.univamu.iut.api_user_produit.user;

import fr.univamu.iut.api_user_produit.user.User;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;

@Path("/user")
@ApplicationScoped
public class UserRessource {

    @Inject
    private UserService service;

    @GET
    @Produces("application/json")
    public String getAllUser() {
        return service.getAllUserJSON();
    }

    @GET
    @Path("/client")
    @Produces("application/json")
    public String getAllUserClient() {
        return service.getAllUserClientJSON();
    }

    @GET
    @Path("/gestionnaire")
    @Produces("application/json")
    public String getAllUserGestionnaire() {
        return service.getAllUserGestionnaireJSON();
    }

    @GET
    @Path("{id_user}")
    @Produces("application/json")
    public String getUser(@PathParam("id_user") int id_user) {
        String result = service.getUserJSON(id_user);
        if (result == null) {
            throw new NotFoundException();
        }
        return result;
    }

    @PUT
    @Path("/update")
    @Consumes("application/json")
    public Response updateUser(User user) {
        if (!service.updateUser(user)) {
            throw new NotFoundException();
        }
        return Response.ok("updated").build();
    }

    @PUT
    @Path("/delete")
    @Consumes("application/json")
    public Response deleteUser(User user) {
        if (!service.deleteUser(user)) {
            throw new NotFoundException();
        }
        return Response.ok("deleted").build();
    }

    @POST
    @Path("/add")
    @Consumes("application/json")
    public Response addUser(User user) {
        if (!service.addUser(user)) {
            throw new NotFoundException();
        }
        return Response.ok("add").build();
    }

}