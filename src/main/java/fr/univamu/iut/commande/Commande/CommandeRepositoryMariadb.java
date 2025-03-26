package fr.univamu.iut.commande.Commande;

import java.io.Closeable;
import java.sql.*;
import java.util.ArrayList;
import java.util.Date;

/**
 * Classe permettant d'accèder aux commandes stockés dans une base de données Mariadb
 */
public class CommandeRepositoryMariadb   implements CommandeRepositoryInterface, Closeable {

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
    public CommandeRepositoryMariadb(String infoConnection, String user, String pwd ) throws java.sql.SQLException, java.lang.ClassNotFoundException {
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
    public Commande getCommande(int reference) {

        Commande selectedCommande = null;

        String query = "SELECT * FROM Commande WHERE id=?";

        try ( PreparedStatement ps = dbConnection.prepareStatement(query) ){
            ps.setInt(1, reference);

            ResultSet result = ps.executeQuery();

            if( result.next() )
            {
                int id = result.getInt("id");
                int id_user = result.getInt("id_user");
                int prix = result.getInt("prix");
                boolean valide = result.getBoolean("valide");
                Date date_echeance = result.getDate("date_echeance");
                String point_relai = result.getString("point_relai");

                selectedCommande = new Commande(id, id_user, prix, valide, date_echeance, point_relai);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return selectedCommande;
    }

    @Override
    public ArrayList<Commande> getAllCommande() {
        ArrayList<Commande> listCommande ;

        String query = "SELECT * FROM Commande";

        // construction et exécution d'une requête préparée
        try ( PreparedStatement ps = dbConnection.prepareStatement(query) ){
            // exécution de la requête
            ResultSet result = ps.executeQuery();

            listCommande = new ArrayList<>();

            while ( result.next() )
            {
                int id = result.getInt("id");
                int id_user = result.getInt("id_user");
                int prix = result.getInt("prix");
                boolean valide = result.getBoolean("valide");
                Date date_echeance = result.getDate("date_echeance");
                String point_relai = result.getString("point_relai");


                Commande currentCommande = new Commande(id, id_user, prix, valide, date_echeance, point_relai);

                listCommande.add(currentCommande);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return listCommande;
    }


    @Override
    public boolean updateCommande( int id, int id_user, int prix, boolean valide, Date date_echeance, String point_relai) {
        String query = "UPDATE Commande SET id_user=?, prix=?, valide=?, date_echeance=?, point_relai=?  where id=?";
        int nbRowModified = 0;

        try ( PreparedStatement ps = dbConnection.prepareStatement(query) ){
            ps.setInt(1, id_user);
            ps.setInt(2, prix);
            ps.setBoolean(3, valide);
            ps.setDate(4, (java.sql.Date) date_echeance);
            ps.setString(5, point_relai);


            nbRowModified = ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return ( nbRowModified != 0 );
    }
}