package fr.univamu.iut.api_panier.panier;

import jakarta.enterprise.context.ApplicationScoped;

import java.util.*;


public interface PanierRepositoryInterface {
    public void close();
    public Panier createPanier(Panier panier);
    public Panier getPanier(int id);
    public List<Panier> getPaniers();
    public void deletePanier(int id);
    public void updatePanier(int id, String nom, Date datemaj, int prix, int quantite);
    public int getIdPanier(Panier panier);

}

