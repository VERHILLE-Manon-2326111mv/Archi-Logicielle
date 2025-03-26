package fr.univamu.iut.commande.Commande;

import fr.univamu.iut.commande.Commande_Panier.Commande_Panier;

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
    public boolean updateCommande( Commande commande ) {
        String query = "UPDATE Commande SET id_user=?, prix=?, valide=?, date_echeance=?, point_relai=?  where id=?";
        int nbRowModified = 0;

        try ( PreparedStatement ps = dbConnection.prepareStatement(query) ){
            ps.setInt(1, commande.getId_user());
            ps.setInt(2, commande.getPrix());
            ps.setBoolean(3, commande.isValide());
            java.util.Date utilDate = commande.getDate_echeance();
            java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
            ps.setDate(4, (java.sql.Date) sqlDate);
            ps.setString(5, commande.getPoint_relai());
            ps.setInt(6, commande.getId());


            nbRowModified = ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return ( nbRowModified != 0 );
    }

    @Override
    public boolean deleteCommande( Commande commande ) {
        String query = "DELETE FROM Commande WHERE id=?";
        int nbRowModified = 0;

        try ( PreparedStatement ps = dbConnection.prepareStatement(query) ){
            ps.setInt(1, commande.getId());

            nbRowModified = ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return ( nbRowModified != 0 );
    }

    @Override
    public boolean createCommande( Commande commande ) {
        String query = "INSERT INTO Commande (id_user, prix,valide, date_echeance, point_relai) VALUES (?, ?, false, null, null)";
        int nbRowModified = 0;

        try ( PreparedStatement ps = dbConnection.prepareStatement(query) ){
            ps.setInt(1, commande.getId_user());
            ps.setInt(2, commande.getPrix());

            nbRowModified = ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return ( nbRowModified != 0 );
    }

    @Override
    public boolean valideCommande(Commande commande) {
        String query = "UPDATE Commande SET valide=true, date_echeance=?, point_relai=? where id=?";
        int nbRowModified = 0;

        try ( PreparedStatement ps = dbConnection.prepareStatement(query) ){
            ps.setInt(3, commande.getId());
            java.util.Date utilDate = commande.getDate_echeance();
            java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
            ps.setDate(1, sqlDate);
            ps.setString(2, commande.getPoint_relai());

            nbRowModified = ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return ( nbRowModified != 0 );
    }

    @Override
    public boolean addPanier(Commande_Panier commande) {
        String query = "INSERT INTO Commande_Panier (id_commande, id_panier, quantite) VALUES (?, ?, ?)";
        int nbRowModified = 0;

        try ( PreparedStatement ps = dbConnection.prepareStatement(query) ){
            ps.setInt(1, commande.getId_commande());
            ps.setInt(2, commande.getId_panier());
            ps.setInt(3, commande.getQuantite());

            nbRowModified = ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return ( nbRowModified != 0 );
    }

    @Override
    public boolean updatePanier(Commande_Panier commande) {
        String query = "UPDATE Commande_Panier SET quantite=? where id_commande=? and id_panier=?";
        int nbRowModified = 0;

        try ( PreparedStatement ps = dbConnection.prepareStatement(query) ){
            ps.setInt(1, commande.getQuantite());
            ps.setInt(2, commande.getId_commande());
            ps.setInt(3, commande.getId_panier());

            nbRowModified = ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return ( nbRowModified != 0 );
    }

    @Override
    public boolean deletePanier(Commande_Panier commande) {
        String query = "DELETE FROM Commande_Panier WHERE id_commande=? and id_panier=?";
        int nbRowModified = 0;

        try ( PreparedStatement ps = dbConnection.prepareStatement(query) ){
            ps.setInt(1, commande.getId_commande());
            ps.setInt(2, commande.getId_panier());

            nbRowModified = ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return ( nbRowModified != 0 );
    }
}