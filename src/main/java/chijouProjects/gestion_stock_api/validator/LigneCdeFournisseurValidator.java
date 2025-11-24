package chijouProjects.gestion_stock_api.validator;

import chijouProjects.gestion_stock_api.dto.LigneCdeFournisseurDto;

import java.util.ArrayList;
import java.util.List;

public class LigneCdeFournisseurValidator {
    public static List<String> validate(LigneCdeFournisseurDto lignecdefournisseurdto) {
        List<String> errors = new ArrayList<>();
        if(lignecdefournisseurdto == null) {
            errors.add("Veuillez sélectionner l'article");
            errors.add("Veuillez sélectionner la commande");
            errors.add("Veuillez renseigner la quantité");
            errors.add("Veuillez renseigner le prix unitaire");
            return errors;
        }

        if(lignecdefournisseurdto.getIdarticle() == null) {
            errors.add("Veuillez sélectionner l'article");
        }

        if(lignecdefournisseurdto.getIdcommandefournisseur() == null) {
            errors.add("Veuillez sélectionner la commande");
        }

        if(lignecdefournisseurdto.getQuantite() == null) {
            errors.add("Veuillez renseigner la quantité ");
        }

        if(lignecdefournisseurdto.getPrixunitaire() == null) {
            errors.add("Veuillez renseigner le prix unitaire");
        }
        return errors;
    }
}
