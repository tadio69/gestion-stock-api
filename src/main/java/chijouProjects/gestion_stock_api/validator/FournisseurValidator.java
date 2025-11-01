package chijouProjects.gestion_stock_api.validator;

import chijouProjects.gestion_stock_api.dto.FournisseurDto;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class FournisseurValidator {
    public static List<String> validate(FournisseurDto fournisseurdto) {
        List<String> errors = new ArrayList<>();
        if(fournisseurdto == null){
            errors.add("Veuillez renseigner le nom du fournisseur");
            errors.add("Veuillez renseigner le prénom du fournisseur");
            errors.add("Veuillez renseigner l'email du fournisseur");
            errors.add("Veuillez renseigner luméro de téléphone du fournisseur");
            return errors;
        }
        if(!StringUtils.hasLength(fournisseurdto.getNom())){
            errors.add("Veuillez renseigner le nom du fournisseur");
        }

        if(!StringUtils.hasLength(fournisseurdto.getPrenom())){
            errors.add("Veuillez renseigner le prénom du fournisseur");
        }

        if(!StringUtils.hasLength(fournisseurdto.getEmail())){
            errors.add("Veuillez renseigner l'email du fournisseur");
        }

        if(!StringUtils.hasLength(fournisseurdto.getNumtel())){
            errors.add("Veuillez renseigner luméro de téléphone du fournisseur");
        }
        return errors;
    }
}
