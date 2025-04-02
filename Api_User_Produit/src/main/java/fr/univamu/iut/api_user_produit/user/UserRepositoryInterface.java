package fr.univamu.iut.api_user_produit.user;
import java.util.*;

/**
 * Interface définissant les opérations de gestion des utilisateurs dans la base de données.
 * Elle permet d'effectuer des actions telles que la récupération, l'ajout, la mise à jour
 * et la suppression des utilisateurs.
 */
public interface UserRepositoryInterface {

    /**
     * Ferme la connexion à la base de données.
     */
    public void close();

    /**
     * Récupère un utilisateur par son identifiant.
     * @param id L'identifiant de l'utilisateur à récupérer.
     * @return L'utilisateur correspondant à l'identifiant, ou {@code null} si aucun utilisateur n'est trouvé.
     */
    public User getUser( int id );

    /**
     * Récupère tous les utilisateurs de la base de données.
     * @return Une liste de tous les utilisateurs.
     */
    public ArrayList<User> getAllUser() ;

    /**
     * Récupère tous les utilisateurs ayant le rôle "Client".
     * @return Une liste des utilisateurs ayant le rôle "Client".
     */
    public ArrayList<User> getAllUserClient() ;

    /**
     * Récupère tous les utilisateurs ayant le rôle "Gestionnaire".
     * @return Une liste des utilisateurs ayant le rôle "Gestionnaire".
     */
    public ArrayList<User> getAllUserGestionnaire() ;

    /**
     * Met à jour les informations d'un utilisateur.
     * @param id_user  L'identifiant de l'utilisateur à mettre à jour.
     * @param password Le nouveau mot de passe de l'utilisateur.
     * @param nom      Le nouveau nom de l'utilisateur.
     * @param role     Le nouveau rôle de l'utilisateur.
     * @return {@code true} si l'utilisateur a été mis à jour avec succès, sinon {@code false}.
     */
    public boolean updateUser( int id_user, String password, String nom, String role);

    /**
     * Supprime un utilisateur de la base de données.
     * @param user L'utilisateur à supprimer.
     * @return {@code true} si l'utilisateur a été supprimé avec succès, sinon {@code false}.
     */
    public boolean deleteUser( User user);


    /**
     * Ajoute un nouvel utilisateur dans la base de données.
     * @param user L'utilisateur à ajouter.
     * @return {@code true} si l'utilisateur a été ajouté avec succès, sinon {@code false}.
     */
    public boolean addUser( User user );

}