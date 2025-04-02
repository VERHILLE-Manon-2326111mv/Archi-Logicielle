package fr.univamu.iut.api_user_produit.produit;

import java.io.Closeable;
import java.sql.*;
import java.util.ArrayList;

/**
 * Classe permettant d'accèder aux produits stockés dans une base de données Mariadb
 * Implémente l'interface {@link ProduitRepositoryInterface} et l'interface {@link Closeable}.
 */
public class ProduitRepositoryMariadb implements ProduitRepositoryInterface, Closeable {

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
     * @throws SQLException             En cas de problème de connexion à la base de données.
     * @throws ClassNotFoundException   Si le driver JDBC de MariaDB n'est pas trouvé.
     */
    public ProduitRepositoryMariadb(String infoConnection, String user, String pwd ) throws java.sql.SQLException, java.lang.ClassNotFoundException {
        Class.forName("org.mariadb.jdbc.Driver");
        dbConnection = DriverManager.getConnection( infoConnection, user, pwd ) ;
    }

    /**
     * Ferme la connexion à la base de données.
     */
    @Override
    public void close() {
        try{
            dbConnection.close();
        }
        catch(SQLException e){
            System.err.println(e.getMessage());
        }
    }

    /**
     * Récupère un produit à partir de son identifiant.
     * @param id_produit Identifiant du produit.
     * @return L'objet {@link Produit} correspondant ou {@code null} si non trouvé.
     */
    @Override
    public Produit getProduit(int id_produit) {
        Produit selectedProduit = null;

        String query = "SELECT * FROM Produit WHERE id_produit=?";

        try ( PreparedStatement ps = dbConnection.prepareStatement(query) ){
            ps.setInt(1, id_produit);

            ResultSet result = ps.executeQuery();

            if( result.next() )
            {
                String nom = result.getString("nom");
                int quantite = result.getInt("quantite");
                int prix = result.getInt("prix");
                String unite = result.getString("unite");

                selectedProduit = new Produit(id_produit, nom, quantite, prix, unite);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return selectedProduit;
    }

    /**
     * Récupère la liste de tous les produits.
     * @return Une liste d'objets {@link Produit}.
     */
    @Override
    public ArrayList<Produit> getAllProduit() {
        ArrayList<Produit> listProduit ;

        String query = "SELECT * FROM Produit";

        // construction et exécution d'une requête préparée
        try ( PreparedStatement ps = dbConnection.prepareStatement(query) ){
            // exécution de la requête
            ResultSet result = ps.executeQuery();

            listProduit = new ArrayList<>();

            while ( result.next() )
            {
                int id_produit = result.getInt("id_produit");
                String nom = result.getString("nom");
                int quantite = result.getInt("quantite");
                int prix = result.getInt("prix");
                String unite = result.getString("unite");


                Produit currentProduit = new Produit(id_produit, nom, quantite,prix, unite);

                listProduit.add(currentProduit);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return listProduit;
    }


    /**
     * Met à jour les informations d'un produit.
     * @param id_produit L'identifiant du produit à mettre à jour.
     * @param nom        Nouveau nom du produit.
     * @param quantite   Nouvelle quantité disponible du produit.
     * @param prix       Nouveau prix du produit.
     * @param unite      Nouvelle unité du produit.
     * @return {@code true} si la mise à jour a été effectuée, sinon {@code false}.
     */
    @Override
    public boolean updateProduit(int id_produit, String nom, int quantite, int prix, String unite) {
        String query = "UPDATE Produit SET nom=?, quantite=?, prix=?, unite=? WHERE id_produit=?";
        int nbRowModified = 0;

        try ( PreparedStatement ps = dbConnection.prepareStatement(query) ){
            ps.setString(1, nom);
            ps.setInt(2, quantite);
            ps.setInt(3, prix);
            ps.setString(4, unite);
            ps.setInt(5, id_produit);

            nbRowModified = ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return ( nbRowModified != 0 );
    }

    /**
     * Supprime un produit de la base de données.
     * @param produit Le produit à supprimer.
     * @return {@code true} si la suppression a été effectuée, sinon {@code false}.
     */
    @Override
    public boolean deleteProduit(Produit produit) {
        String query = "DELETE FROM Produit WHERE id_produit=?";
        int nbRowModified = 0;

        try ( PreparedStatement ps = dbConnection.prepareStatement(query) ){
            ps.setInt(1, produit.getId_produit());

            nbRowModified = ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return ( nbRowModified != 0 );
    }

    /**
     * Ajoute un produit à la base de données.
     *
     * @param produit Le produit à ajouter.
     * @return {@code true} si l'ajout a été effectué, sinon {@code false}.
     */
    @Override
    public boolean addProduit( Produit produit ) {
        String query = "INSERT INTO Produit (nom, quantite, prix, unite) VALUES ( ?, ?, ?, ?)";
        int nbRowModified = 0;

        try ( PreparedStatement ps = dbConnection.prepareStatement(query) ){
            ps.setString(1, produit.getNom());
            ps.setInt(2, produit.getQuantite());
            ps.setInt(3, produit.getPrix());
            ps.setString(4, produit.getUnite());

            nbRowModified = ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return ( nbRowModified != 0 );
    }

}