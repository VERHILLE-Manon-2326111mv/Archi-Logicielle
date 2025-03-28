package fr.univamu.iut.api_user_produit.user;

import fr.univamu.iut.api_user_produit.user.User;

import java.io.Closeable;
import java.sql.*;
import java.util.ArrayList;

/**
 * Classe permettant d'accèder aux users stockés dans une base de données Mariadb
 */
public class UserRepositoryMariadb implements UserRepositoryInterface, Closeable {

    /**
     * Accès à la base de données (session)
     */
    protected Connection dbConnection ;

    /**
     * Constructeur de la classe
     * @param infoConnection chaîne de caractères avec les informations de connexion
     *                       (p.ex. jdbc:mariadb://mysql-[compte].alwaysdata.net/[compte]_library_db
     * @param user chaîne de caractères contenant l'identifiant de connexion à la base de données
     * @param pwd chaîne de caractères contenant le mot de passe à utiliser
     */
    public UserRepositoryMariadb(String infoConnection, String user, String pwd ) throws java.sql.SQLException, java.lang.ClassNotFoundException {
        Class.forName("org.mariadb.jdbc.Driver");
        dbConnection = DriverManager.getConnection( infoConnection, user, pwd ) ;
    }

    @Override
    public void close() {
        try{
            dbConnection.close();
        }
        catch(SQLException e){
            System.err.println(e.getMessage());
        }
    }

    @Override
    public User getUser(int id_user) {
        User selectedUser = null;

        String query = "SELECT * FROM User WHERE id_user=?";

        try ( PreparedStatement ps = dbConnection.prepareStatement(query) ){
            ps.setInt(1, id_user);

            ResultSet result = ps.executeQuery();

            if( result.next() )
            {
                String password = result.getString("password");
                String nom = result.getString("nom");
                String role = result.getString("role");

                selectedUser = new User(id_user, password, nom, role);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return selectedUser;
    }

    @Override
    public ArrayList<User> getAllUser() {
        ArrayList<User> listUser ;

        String query = "SELECT * FROM User";

        // construction et exécution d'une requête préparée
        try ( PreparedStatement ps = dbConnection.prepareStatement(query) ){
            // exécution de la requête
            ResultSet result = ps.executeQuery();

            listUser = new ArrayList<>();

            while ( result.next() )
            {
                int id_user = result.getInt("id_user");
                String password = result.getString("password");
                String nom = result.getString("nom");
                String role = result.getString("role");


                User currentUser = new User(id_user, password, nom, role);

                listUser.add(currentUser);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return listUser;
    }

    @Override
    public ArrayList<User> getAllUserClient() {
        ArrayList<User> listUser ;

        String query = "SELECT * FROM User WHERE role='client'";

        // construction et exécution d'une requête préparée
        try ( PreparedStatement ps = dbConnection.prepareStatement(query) ){
            // exécution de la requête
            ResultSet result = ps.executeQuery();

            listUser = new ArrayList<>();

            while ( result.next() )
            {
                int id_user = result.getInt("id_user");
                String password = result.getString("password");
                String nom = result.getString("nom");
                String role = result.getString("role");


                User currentUser = new User(id_user, password, nom, role);

                listUser.add(currentUser);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return listUser;
    }

    @Override
    public ArrayList<User> getAllUserGestionnaire() {
        ArrayList<User> listUser ;

        String query = "SELECT * FROM User WHERE role='gestionnaire'";

        // construction et exécution d'une requête préparée
        try ( PreparedStatement ps = dbConnection.prepareStatement(query) ){
            // exécution de la requête
            ResultSet result = ps.executeQuery();

            listUser = new ArrayList<>();

            while ( result.next() )
            {
                int id_user = result.getInt("id_user");
                String password = result.getString("password");
                String nom = result.getString("nom");
                String role = result.getString("role");


                User currentUser = new User(id_user, password, nom, role);

                listUser.add(currentUser);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return listUser;
    }



    @Override
    public boolean updateUser(int id_user, String password, String nom, String role) {
        String query = "UPDATE User SET password=?, nom=?, role=? WHERE id_user=?";
        int nbRowModified = 0;

        try ( PreparedStatement ps = dbConnection.prepareStatement(query) ){
            ps.setString(1, password);
            ps.setString(2, nom);
            ps.setString(3, role);
            ps.setInt(4, id_user);

            nbRowModified = ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return ( nbRowModified != 0 );
    }

    @Override
    public boolean deleteUser(User user) {
        String query = "DELETE FROM User WHERE id_user=?";
        int nbRowModified = 0;

        try ( PreparedStatement ps = dbConnection.prepareStatement(query) ){
            ps.setInt(1, user.getId_user());

            nbRowModified = ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return ( nbRowModified != 0 );
    }

    @Override
    public boolean addUser( User user ) {
        String query = "INSERT INTO User (id_user , password, nom, role) VALUES (?, ?, ?, ?)";
        int nbRowModified = 0;

        try ( PreparedStatement ps = dbConnection.prepareStatement(query) ){
            ps.setInt(1, user.getId_user());
            ps.setString(2, user.getPassword());
            ps.setString(3, user.getNom());
            ps.setString(4, user.getRole());

            nbRowModified = ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return ( nbRowModified != 0 );
    }
}