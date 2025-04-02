package fr.univamu.iut.api_panier.panier;

import fr.univamu.iut.api_panier.Panier_Produit.Panier_Produit;
import jakarta.annotation.Priority;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Alternative;
import jakarta.inject.Inject;

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

    @Override
    public void addProduitPanier(Panier_Produit panier_produit) {
        String query = "INSERT INTO Panier_Produit (id_panier, id_produit, quantite_produit) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = dbConnection.prepareStatement(query)) {
            stmt.setInt(1, panier_produit.getId_panier());
            stmt.setInt(2, panier_produit.getId_produit());
            stmt.setInt(3, panier_produit.getQuantite());
            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteProduitPanier(Panier_Produit panier_produit) {
        String query = "DELETE FROM Panier_Produit WHERE id_panier = ? AND id_produit = ?";
        try (PreparedStatement stmt = dbConnection.prepareStatement(query)) {
            stmt.setInt(1, panier_produit.getId_panier());
            stmt.setInt(2, panier_produit.getId_produit());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void updateProduitPanier(Panier_Produit panier_produit) {
        String query = "UPDATE Panier_Produit SET quantite_produit = ? WHERE id_panier = ? AND id_produit = ?";

        try (PreparedStatement stmt = dbConnection.prepareStatement(query)) {
            stmt.setInt(1, panier_produit.getQuantite());
            stmt.setInt(2, panier_produit.getId_panier());
            stmt.setInt(3, panier_produit.getId_produit());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Panier_Produit> getPaniersProduit() {
        ArrayList<Panier_Produit> paniers = new ArrayList<>();
        String query = "SELECT * FROM Panier_Produit";

        try (PreparedStatement stmt = dbConnection.prepareStatement(query)) {
            ResultSet result = stmt.executeQuery();
            while (result.next()) {
                int id_panier = result.getInt("id_panier");
                int id_produit = result.getInt("id_produit");
                int quantite_produit = result.getInt("quantite_produit");
                Panier_Produit panier_produit = new Panier_Produit(id_panier, id_produit, quantite_produit);
                paniers.add(panier_produit);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return paniers;
    }

    @Override
    public Panier_Produit getPaniersProduit(int id_produit) {
        Panier_Produit panier_produit = null;
        String query = "SELECT * FROM Panier_Produit WHERE id_produit = ?";

        try (PreparedStatement stmt = dbConnection.prepareStatement(query)) {
            stmt.setInt(1, id_produit);
            ResultSet result = stmt.executeQuery();
            if (result.next()) {
                int id_panier = result.getInt("id_panier");
                int quantite_produit = result.getInt("quantite_produit");
                panier_produit = new Panier_Produit(id_panier, id_produit, quantite_produit);

            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return panier_produit;
    }
}
