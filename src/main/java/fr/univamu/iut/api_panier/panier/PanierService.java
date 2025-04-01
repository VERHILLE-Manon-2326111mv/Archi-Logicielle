package fr.univamu.iut.api_panier.panier;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;

import java.util.ArrayList;


@ApplicationScoped
public class PanierService{

    protected PanierRepositoryInterface panierRepo;

    @Inject
    public PanierService(PanierRepositoryInterface panierRepo){this.panierRepo = panierRepo;}

    public PanierService(){}

    public String getPanierJSON(int id){
        Panier panier = panierRepo.getPanier(id);
        if (panier == null){
            return null;
        }
        try (Jsonb jsonb = JsonbBuilder.create()){
            return jsonb.toJson(panier);
        }
        catch (Exception e){
            return null;
        }
    }

    public String getPaniersJSON(){
        ArrayList<Panier> paniers = (ArrayList<Panier>) panierRepo.getPaniers();
        try (Jsonb jsonb = JsonbBuilder.create()){
            return jsonb.toJson(paniers);
        }
        catch (Exception e){
            return null;
        }
    }

    public int getIdPanierJSON(Panier panier){
        return panierRepo.getIdPanier(panier);
    }


    public void createPanier(Panier panier){
        panierRepo.createPanier(panier);
    }

    public void deletePanier(int id){
        panierRepo.deletePanier(id);
    }

    public void updatePanier(int id, String nom, java.util.Date datemaj, int prix, int quantite){
        panierRepo.updatePanier(id, nom, datemaj, prix, quantite);
    }

}
