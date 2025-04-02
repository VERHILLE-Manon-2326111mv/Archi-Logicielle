package fr.univamu.iut.api_user_produit.user;

/**
 * Classe représentant un utilisateur du système.
 */
public class User {

    /**
     * Identifiant unique de l'utilisateur.
     */
    protected int id_user;

    /**
     * Mot de passe de l'utilisateur.
     */
    protected String password;

    /**
     * Nom de l'utilisateur.
     */
    protected String nom;

    /**
     * Rôle de l'utilisateur (ex: administrateur, client, etc.).
     */
    protected String role;

    /**
     * Constructeur par défaut.
     */
    public User() {}

    /**
     * Constructeur avec paramètres.
     *
     * @param id_user Identifiant unique de l'utilisateur.
     * @param password Mot de passe de l'utilisateur.
     * @param nom Nom de l'utilisateur.
     * @param role Rôle de l'utilisateur.
     */
    public User(int id_user, String password, String nom, String role) {
        this.id_user = id_user;
        this.nom = nom;
        this.password = password;
        this.role = role;
    }

    /**
     * Récupère l'identifiant de l'utilisateur.
     * @return L'identifiant de l'utilisateur.
     */
    public int getId_user() {
        return id_user;
    }

    /**
     * Définit l'identifiant de l'utilisateur.
     * @param id_user L'identifiant à attribuer.
     */
    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    /**
     * Récupère le nom de l'utilisateur.
     * @return Le nom de l'utilisateur.
     */
    public String getNom() {
        return nom;
    }

    /**
     * Définit le nom de l'utilisateur.
     * @param nom Le nom à attribuer.
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     * Récupère le mot de passe de l'utilisateur.
     * @return Le mot de passe de l'utilisateur.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Définit le mot de passe de l'utilisateur.
     * @param password Le mot de passe à attribuer.
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Récupère le rôle de l'utilisateur.
     * @return Le rôle de l'utilisateur.
     */
    public String getRole() {
        return role;
    }

    /**
     * Définit le rôle de l'utilisateur.
     * @param role Le rôle à attribuer.
     */
    public void setRole(String role) {
        this.role = role;
    }
}