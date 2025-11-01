package chijouProjects.gestion_stock_api.validator;

import chijouProjects.gestion_stock_api.dto.EntrepriseDto;
import chijouProjects.gestion_stock_api.model.Entreprise;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class EntrepriseValidator {
    public static List<String> validate(EntrepriseDto entreprisedto) {
        List<String> errors = new ArrayList<>();
        if(entreprisedto == null) {
            errors.add("Veuillez renseigner le nom de l'entreprise");
            errors.add("Veuillez renseigner la description de l'entreprise");
            errors.add("Veuillez renseigner le code fiscal de l'entreprise");
            errors.add("Veuillez renseigner l'email de l'entreprise");
            errors.add("Veuillez renseigner le numéro de téléphone de l'entreprise");
            return errors;
        }

        if(!StringUtils.hasLength(entreprisedto.getNom())){
            errors.add("Veuillez renseigner le nom de l'entreprise");
        }

        if(!StringUtils.hasLength(entreprisedto.getDescription())){
            errors.add("Veuillez renseigner la description de l'entreprise");
        }

        if(!StringUtils.hasLength(entreprisedto.getCodefiscal())){
            errors.add("Veuillez renseigner le code fiscal de l'entreprise");
        }

        if(!StringUtils.hasLength(entreprisedto.getEmail())){
            errors.add("Veuillez renseigner l'email de l'entreprise");
        }

        if(!StringUtils.hasLength(entreprisedto.getNumtel())){
            errors.add("Veuillez renseigner le numéro de téléphone de l'entreprise");
        }

        return errors;
    }
}
