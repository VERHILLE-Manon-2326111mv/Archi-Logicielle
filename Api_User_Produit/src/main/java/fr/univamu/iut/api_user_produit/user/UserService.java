package fr.univamu.iut.api_user_produit.user;

import fr.univamu.iut.api_user_produit.user.User;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;
import java.util.ArrayList;

@ApplicationScoped
public class UserService {

    protected UserRepositoryInterface userRepo;

    @Inject
    public UserService(UserRepositoryInterface userRepo) {
        this.userRepo = userRepo;
    }

    public UserService() {}

    public String getAllUserJSON() {
        ArrayList<User> allUser = userRepo.getAllUser();
        try (Jsonb jsonb = JsonbBuilder.create()) {
            return jsonb.toJson(allUser);
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return "{}";
        }
    }

    public String getAllUserClientJSON() {
        ArrayList<User> allUser = userRepo.getAllUserClient();
        try (Jsonb jsonb = JsonbBuilder.create()) {
            return jsonb.toJson(allUser);
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return "{}";
        }
    }

    public String getAllUserGestionnaireJSON() {
        ArrayList<User> allUser = userRepo.getAllUserGestionnaire();
        try (Jsonb jsonb = JsonbBuilder.create()) {
            return jsonb.toJson(allUser);
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return "{}";
        }
    }

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

    public boolean updateUser( User user) {
        return userRepo.updateUser(user.getId_user(), user.getPassword(), user.getNom(), user.getRole());
    }

    public boolean deleteUser(User user) {
        return userRepo.deleteUser(user);
    }

    public boolean addUser(User user) {
        return userRepo.addUser(user);
    }
}