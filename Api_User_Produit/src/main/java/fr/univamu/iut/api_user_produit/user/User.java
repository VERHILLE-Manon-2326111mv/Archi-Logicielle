package fr.univamu.iut.api_user_produit.user;

public class User {
    protected int id_user;

    protected String password;

    protected String nom;

    protected String role;

    public User() {}

    public User(int id_user, String password, String nom, String role) {
        this.id_user = id_user;
        this.nom = nom;
        this.password = password;
        this.role = role;
    }

    public int getId_user() {
        return id_user;
    }
    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public String getNom() {
        return nom;
    }
    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }
    public void setRole(String role) {
        this.role = role;
    }
}