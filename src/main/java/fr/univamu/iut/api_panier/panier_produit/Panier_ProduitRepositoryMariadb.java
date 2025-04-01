package fr.univamu.iut.api_panier.panier_produit;

import java.io.Closeable;
import java.sql.*;

public class Panier_ProduitRepositoryMariadb implements Panier_ProduitRepositoryInterface, Closeable {

    private final Connection dbConnection;

    public PanierRepositoryMariadb(String infoConnection, String user, String password) throws SQLException, ClassNotFoundException {
        Class.forName("org.mariadb.jdbc.Driver");
        dbConnection = DriverManager.getConnection(infoConnection, user, password);
    }

    @Override
    public void close(){
        try {
            dbConnection.close();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    @Override
    public Panier_Produit createPanier_Produit(Panier_Produit panier_produit) {
        String query = "INSERT INTO Panier_Produit (id_panier, id_produit, quantite) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = dbConnection.prepareStatement(query)) {
            stmt.setInt(1, panier_produit.getId_panier());
            stmt.setInt(2, panier_produit.getId_produit());
            stmt.setInt(3, panier_produit.getQuantite());
            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return panier_produit;
    }

    @Override
    public void updatePanier_Produit(int id, int id_panier, int id_produit, int quantite) {
        String query = "UPDATE Panier_Produit SET id_panier = ?, id_produit = ?, quantite = ? WHERE id = ?";
        try (PreparedStatement stmt = dbConnection.prepareStatement(query)) {
            stmt.setInt(1, id_panier);
            stmt.setInt(2, id_produit);
            stmt.setInt(3, quantite);
            stmt.setInt(4, id);
            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deletePanier_Produit(int id) {
        String query = "DELETE FROM Panier_Produit WHERE id = ?";
        try (PreparedStatement stmt = dbConnection.prepareStatement(query)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int getIdPanier_Produit(Panier_Produit panier_produit) {
        int id = 0;
        String query = "SELECT id FROM Panier_Produit WHERE id_panier = ? AND id_produit = ? AND quantite = ?";
        try (PreparedStatement stmt = dbConnection.prepareStatement(query)) {
            stmt.setInt(1, panier_produit.getId_panier());
            stmt.setInt(2, panier_produit.getId_produit());
            stmt.setInt(3, panier_produit.getQuantite());
            ResultSet result = stmt.executeQuery();
            if (result.next()) {
                id = result.getInt("id");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return id;
    }

    @Override
    public Panier_Produit getPanier_Produit(int id) {
        Panier_Produit panier_produit = null;
        String query = "SELECT id_panier, id_produit, quantite FROM Panier_Produit WHERE id = ?";
        try (PreparedStatement stmt = dbConnection.prepareStatement(query)) {
            stmt.setInt(1, id);
            ResultSet result = stmt.executeQuery();
            if (result.next()) {
                int id_panier = result.getInt("id_panier");
                int id_produit = result.getInt("id_produit");
                int quantite = result.getInt("quantite");
                panier_produit = new Panier_Produit(id, id_panier, id_produit, quantite);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return panier_produit;
    }

    @Override
    public List<Panier_Produit> getPanier_Produits() {
        ArrayList<Panier_Produit> panier_produits = new ArrayList<>();
        String query = "SELECT id, id_panier, id_produit, quantite FROM Panier_Produit";
        try (PreparedStatement stmt = dbConnection.prepareStatement(query)) {
            ResultSet result = stmt.executeQuery();
            while (result.next()) {
                int id = result.getInt("id");
                int id_panier = result.getInt("id_panier");
                int id_produit = result.getInt("id_produit");
                int quantite = result.getInt("quantite");
                Panier_Produit panier_produit = new Panier_Produit(id, id_panier, id_produit, quantite);
                panier_produits.add(panier_produit);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return panier_produits;
    }


}
