package fr.univamu.iut.api_user_produit.user;

import fr.univamu.iut.api_user_produit.user.User;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;
import java.util.ArrayList;

/**
 * Service permettant de gérer les utilisateurs en interagissant avec le dépôt des utilisateurs
 * (UserRepositoryInterface). Ce service expose des méthodes pour récupérer, ajouter, mettre à jour
 * et supprimer des utilisateurs.
 */
@ApplicationScoped
public class UserService {

    /**
     * Dépôt des utilisateurs permettant l'accès aux données des utilisateurs.
     */
    protected UserRepositoryInterface userRepo;

    /**
     * Constructeur de la classe UserService avec injection du dépôt des utilisateurs.
     * @param userRepo Dépôt des utilisateurs.
     */
    @Inject
    public UserService(UserRepositoryInterface userRepo) {
        this.userRepo = userRepo;
    }

    /**
     * Constructeur par défaut de la classe UserService.
     */
    public UserService() {}

    /**
     * Récupère tous les utilisateurs sous forme de chaîne JSON.
     * @return Une chaîne contenant tous les utilisateurs au format JSON.
     */
    public String getAllUserJSON() {
        ArrayList<User> allUser = userRepo.getAllUser();
        try (Jsonb jsonb = JsonbBuilder.create()) {
            return jsonb.toJson(allUser);
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return "{}";
        }
    }

    /**
     * Récupère tous les utilisateurs ayant le rôle "Client" sous forme de chaîne JSON.
     * @return Une chaîne contenant les utilisateurs avec le rôle "Client" au format JSON.
     */
    public String getAllUserClientJSON() {
        ArrayList<User> allUser = userRepo.getAllUserClient();
        try (Jsonb jsonb = JsonbBuilder.create()) {
            return jsonb.toJson(allUser);
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return "{}";
        }
    }

    /**
     * Récupère tous les utilisateurs ayant le rôle "Gestionnaire" sous forme de chaîne JSON.
     * @return Une chaîne contenant les utilisateurs avec le rôle "Gestionnaire" au format JSON.
     */
    public String getAllUserGestionnaireJSON() {
        ArrayList<User> allUser = userRepo.getAllUserGestionnaire();
        try (Jsonb jsonb = JsonbBuilder.create()) {
            return jsonb.toJson(allUser);
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return "{}";
        }
    }

    /**
     * Récupère un utilisateur spécifique par son identifiant sous forme de chaîne JSON.
     * @param id_user L'identifiant de l'utilisateur à récupérer.
     * @return Une chaîne contenant l'utilisateur correspondant au format JSON, ou null si non trouvé.
     */
    public String getUserJSON(int id_user) {
        User myUser = userRepo.getUser(id_user);
        if (myUser == null) {
            return null;
        }
        try (Jsonb jsonb = JsonbBuilder.create()) {
            return jsonb.toJson(myUser);
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return "{}";
        }
    }

    /**
     * Met à jour les informations d'un utilisateur.
     * @param user L'utilisateur avec les nouvelles informations à mettre à jour.
     * @return True si la mise à jour a été effectuée avec succès, sinon false.
     */
    public boolean updateUser( User user) {
        return userRepo.updateUser(user.getId_user(), user.getPassword(), user.getNom(), user.getRole());
    }

    /**
     * Supprime un utilisateur.
     * @param user L'utilisateur à supprimer.
     * @return True si la suppression a été effectuée avec succès, sinon false.
     */
    public boolean deleteUser(User user) {
        return userRepo.deleteUser(user);
    }

    /**
     * Ajoute un nouvel utilisateur.
     * @param user L'utilisateur à ajouter.
     * @return True si l'ajout a été effectué avec succès, sinon false.
     */
    public boolean addUser(User user) {
        return userRepo.addUser(user);
    }
}