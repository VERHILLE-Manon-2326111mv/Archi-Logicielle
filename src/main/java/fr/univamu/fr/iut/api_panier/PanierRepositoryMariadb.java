package fr.univamu.fr.iut.api_panier;

import java.io.Closeable;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

public class PanierRepositoryMariadb implements PanierRepositoryInterface, Closeable {

    protected Connection connection;

    public PanierRepositoryMariadb(String infoConnexion, String user, String password) throws java.sql.SQLException, java.lang.ClassNotFoundException {
        Class.forName("org.mariadb.jdbc.Driver");
        connection = java.sql.DriverManager.getConnection(infoConnexion, user, password);
    }

    @Override
    public Panier createPanier(String nom, Date datemaj, int prix, int quantite) {
        // TODO
    }

    @Override
    public Panier getPanier(int id) {
        // TODO
    }

    @Override
    public List<Panier> getPaniers() {
        // TODO
    }

    @Override
    public void deletePanier(int id) {
        // TODO
    }

    @Override
    public void updatePanier(int id, String nom, Date datemaj, int prix, int quantite) {
        // TODO
    }

    @Override
    public void close() {

        try {
            connection.close();
        } catch (SQLException e) {
            System.out.println("e.getMessage() = " + e.getMessage());
        }

    }
}
