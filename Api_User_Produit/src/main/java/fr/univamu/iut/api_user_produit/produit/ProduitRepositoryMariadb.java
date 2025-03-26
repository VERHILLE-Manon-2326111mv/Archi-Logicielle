package fr.univamu.iut.api_user_produit.produit;
import java.io.Closeable;
import java.sql.*;
import java.util.ArrayList;
import java.util.Date;

/**
 * Classe permettant d'accèder aux produits stockés dans une base de données Mariadb
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
     */
    public ProduitRepositoryMariadb(String infoConnection, String user, String pwd ) throws java.sql.SQLException, java.lang.ClassNotFoundException {
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


    @Override
    public boolean updateProduit( int id_produit, String nom, int quantite, int prix, String unite) {
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

}