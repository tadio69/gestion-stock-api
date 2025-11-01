package chijouProjects.gestion_stock_api.validator;

import chijouProjects.gestion_stock_api.dto.CategorieDto;
import chijouProjects.gestion_stock_api.dto.UtilisateurDto;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class UtilisateurValidator {
    public static List<String> validate(UtilisateurDto utilisateurdto) {
        List<String> errors = new ArrayList<>();

        if(utilisateurdto == null) {
            errors.add("Veuillez renseigner le nom d'utilisateur");
            errors.add("Veuillez renseigner le prénom d'utilisateur");
            errors.add("Veuillez renseigner le mot de passe d'utilisateur");
            errors.add("Veuillez renseigner l'adresse d'utilisateur");
            errors.add("Veuillez renseigner la date de naissance d'utilisateur");
            return errors;
        }

        if(!StringUtils.hasLength(utilisateurdto.getNom())) {
            errors.add("Veuillez renseigner le nom d'utilisateur");
        }

        if(!StringUtils.hasLength(utilisateurdto.getPrenom())) {
            errors.add("Veuillez renseigner le prénom d'utilisateur");
        }

        if(!StringUtils.hasLength(utilisateurdto.getMotdepasse())) {
            errors.add("Veuillez renseigner le mot de passe d'utilisateur");
        }

        if(!StringUtils.hasLength(utilisateurdto.getEmail())) {
            errors.add("Veuillez renseigner l'email' d'utilisateur");
        }

        if(utilisateurdto.getDatenaissance() == null){
            errors.add("Veuillez renseigner la date de naissance d'utilisateur");
        }

        if(utilisateurdto.getAdresse() == null) {
            errors.add("Veuillez renseigner l'adresse d'utilisateur");
        } else {
            if(!StringUtils.hasLength(utilisateurdto.getAdresse().getAdresse1())) {
                errors.add("Le champ 'Adresse 1' est obligatoire");
            }

            if(!StringUtils.hasLength(utilisateurdto.getAdresse().getVille())) {
                errors.add("Le champ 'Ville' est obligatoire");
            }

            if(!StringUtils.hasLength(utilisateurdto.getAdresse().getCodePostal())) {
                errors.add("Le champ 'Code postal' est obligatoire");
            }

            if(!StringUtils.hasLength(utilisateurdto.getAdresse().getPays())) {
                errors.add("Le champ 'Pays' est obligatoire");
            }
        }

        return errors;
    }
}
