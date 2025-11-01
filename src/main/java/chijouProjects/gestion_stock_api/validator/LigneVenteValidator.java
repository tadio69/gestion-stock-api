package chijouProjects.gestion_stock_api.validator;

import chijouProjects.gestion_stock_api.dto.ClientDto;
import chijouProjects.gestion_stock_api.dto.LigneVenteDto;

import java.util.ArrayList;
import java.util.List;

public class LigneVenteValidator {
    public static List<String> validate(LigneVenteDto ligneventedto) {
        List<String> errors = new ArrayList<>();
        if(ligneventedto == null) {
            errors.add("Veuillez sélectionner la vente");
            errors.add("Veuillez renseigner la quantite");
            errors.add("Veuillez renseigner la prixunitaire");
            return errors;
        }

        if(ligneventedto.getVente() == null) {
            errors.add("Veuillez sélectionner la vente");
        }

        if(ligneventedto.getQuantite() == null) {
            errors.add("Veuillez renseigner la quantite");
        }

        if(ligneventedto.getPrixunitaire() == null) {
            errors.add("Veuillez renseigner la prixunitaire");
        }

        return errors;
    }
}
