package fr.univamu.iut.api_user_produit.user;

import fr.univamu.iut.api_user_produit.user.User;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;

/**
 * Ressource REST permettant de gérer les utilisateurs via les opérations HTTP courantes.
 * Elle permet d'effectuer des actions telles que la récupération, l'ajout, la mise à jour et
 * la suppression des utilisateurs.
 */
@Path("/user")
@ApplicationScoped
public class UserRessource {

    /**
     * Service utilisé pour la gestion des utilisateurs.
     */
    @Inject
    private UserService service;

    /**
     * Récupère tous les utilisateurs sous forme de JSON.
     * @return Une chaîne de caractères contenant tous les utilisateurs au format JSON.
     */
    @GET
    @Produces("application/json")
    public String getAllUser() {
        return service.getAllUserJSON();
    }

    /**
     * Récupère tous les utilisateurs ayant le rôle "Client" sous forme de JSON.
     * @return Une chaîne de caractères contenant les utilisateurs ayant le rôle "Client" au format JSON.
     */
    @GET
    @Path("/client")
    @Produces("application/json")
    public String getAllUserClient() {
        return service.getAllUserClientJSON();
    }

    /**
     * Récupère tous les utilisateurs ayant le rôle "Gestionnaire" sous forme de JSON.
     *
     * @return Une chaîne de caractères contenant les utilisateurs ayant le rôle "Gestionnaire" au format JSON.
     */
    @GET
    @Path("/gestionnaire")
    @Produces("application/json")
    public String getAllUserGestionnaire() {
        return service.getAllUserGestionnaireJSON();
    }

    /**
     * Récupère un utilisateur spécifique par son identifiant sous forme de JSON.
     * @param id_user L'identifiant de l'utilisateur à récupérer.
     * @return Une chaîne de caractères contenant l'utilisateur correspondant au format JSON.
     * @throws NotFoundException Si l'utilisateur avec l'identifiant fourni n'est pas trouvé.
     */
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

    /**
     * Met à jour les informations d'un utilisateur.
     * @param user L'utilisateur avec les nouvelles informations.
     * @return Une réponse HTTP indiquant si la mise à jour a été effectuée avec succès.
     * @throws NotFoundException Si l'utilisateur n'a pas pu être trouvé ou mis à jour.
     */
    @PUT
    @Path("/update")
    @Consumes("application/json")
    public Response updateUser(User user) {
        if (!service.updateUser(user)) {
            throw new NotFoundException();
        }
        return Response.ok("updated").build();
    }

    /**
     * Supprime un utilisateur.
     * @param user L'utilisateur à supprimer.
     * @return Une réponse HTTP indiquant si la suppression a été effectuée avec succès.
     * @throws NotFoundException Si l'utilisateur n'a pas pu être trouvé ou supprimé.
     */
    @PUT
    @Path("/delete")
    @Consumes("application/json")
    public Response deleteUser(User user) {
        if (!service.deleteUser(user)) {
            throw new NotFoundException();
        }
        return Response.ok("deleted").build();
    }

    /**
     * Ajoute un nouvel utilisateur.
     * @param user L'utilisateur à ajouter.
     * @return Une réponse HTTP indiquant si l'ajout a été effectué avec succès.
     * @throws NotFoundException Si l'utilisateur n'a pas pu être ajouté.
     */
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