package fr.univamu.iut.api_panier.panier;

import java.io.Closeable;
import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PanierRepositoryMariadb implements PanierRepositoryInterface, Closeable {

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
    public Panier createPanier(Panier panier) {
        String query = "INSERT INTO Panier (nom, datemaj, prix, quantite) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = dbConnection.prepareStatement(query)) {
            stmt.setString(1, panier.getNom());
            stmt.setDate(2, new java.sql.Date(panier.getDatemaj().getTime()));
            stmt.setInt(3, panier.getPrix());
            stmt.setInt(4, panier.getQuantite());
            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return panier;
    }

    @Override
    public Panier getPanier(int id) {
        Panier panier = null;
        String query = "SELECT * FROM Panier WHERE id = ?";

        try (PreparedStatement stmt = dbConnection.prepareStatement(query)) {
            stmt.setInt(1, id);
            ResultSet result = stmt.executeQuery();
            if (result.next()) {
                String nom = result.getString("nom");
                Date datemaj = result.getDate("datemaj");
                int prix = result.getInt("prix");
                int quantite = result.getInt("quantite");
                panier = new Panier(nom, datemaj, prix, quantite);

            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return panier;
    }

    @Override
    public List<Panier> getPaniers() {
        ArrayList<Panier> paniers = new ArrayList<>();
        String query = "SELECT * FROM Panier";

        try (PreparedStatement stmt = dbConnection.prepareStatement(query)) {
            ResultSet result = stmt.executeQuery();
            while (result.next()) {
                String nom = result.getString("nom");
                Date datemaj = result.getDate("datemaj");
                int prix = result.getInt("prix");
                int quantite = result.getInt("quantite");
                Panier panier = new Panier(nom, datemaj, prix, quantite);
                paniers.add(panier);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return paniers;
    }

    @Override
    public void deletePanier(int id) {
        String query = "DELETE FROM Panier WHERE id = ?";
        try (PreparedStatement stmt = dbConnection.prepareStatement(query)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void updatePanier(int id, String nom, Date datemaj, int prix, int quantite) {
        String query = "UPDATE Panier SET nom = ?, datemaj = ?, prix = ?, quantite = ? WHERE id = ?";

        try (PreparedStatement stmt = dbConnection.prepareStatement(query)) {
            stmt.setString(1, nom);
            stmt.setDate(2, new java.sql.Date(datemaj.getTime()));
            stmt.setInt(3, prix);
            stmt.setInt(4, quantite);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int getIdPanier(Panier panier) {
        String query = "SELECT id FROM Panier WHERE nom = ? AND datemaj = ? AND prix = ? AND quantite = ?";
        try (PreparedStatement stmt = dbConnection.prepareStatement(query)) {
            stmt.setString(1, panier.getNom());
            stmt.setDate(2, new java.sql.Date(panier.getDatemaj().getTime()));
            stmt.setInt(3, panier.getPrix());
            stmt.setInt(4, panier.getQuantite());

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("id");
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return -1;
    }
}
