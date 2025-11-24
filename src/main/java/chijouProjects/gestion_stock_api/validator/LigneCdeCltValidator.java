package chijouProjects.gestion_stock_api.validator;

import chijouProjects.gestion_stock_api.dto.LigneCdeCltDto;

import java.util.ArrayList;
import java.util.List;

public class LigneCdeCltValidator {
    public static List<String> validate(LigneCdeCltDto lignecdecltdto) {
        List<String> errors = new ArrayList<>();

        if(lignecdecltdto == null) {
            errors.add("Veuillez sélectionner l'article");
            errors.add("Veuillez sélectionner la commande");
            errors.add("Veuillez renseigner la quantité");
            errors.add("Veuillez renseigner le prix unitaire");
            return errors;
        }

        if(lignecdecltdto.getIdarticle() == null) {
            errors.add("Veuillez sélectionner l'article");
        }

        if(lignecdecltdto.getIdcommandeclient() == null) {
            errors.add("Veuillez sélectionner la commande");
        }

        if(lignecdecltdto.getQuantite() == null) {
            errors.add("Veuillez renseigner la quantité ");
        }

        if(lignecdecltdto.getPrixunitaire() == null) {
            errors.add("Veuillez renseigner le prix unitaire");
        }
        return errors;
    }
}
