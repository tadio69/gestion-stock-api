package chijouProjects.gestion_stock_api.validator;

import chijouProjects.gestion_stock_api.dto.ClientDto;
import chijouProjects.gestion_stock_api.dto.UtilisateurDto;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class ClientValidator {
    public static List<String> validate(ClientDto clientdto) {
        List<String> errors = new ArrayList<>();
        if(clientdto == null) {
            errors.add("Veuillez renseigner le nom d'client");
            errors.add("Veuillez renseigner le prénom du client");
            errors.add("Veuillez renseigner le email du client");
            errors.add("Veuillez renseigner le numéro de téléphone du client");
            return errors;
        }
        if(!StringUtils.hasLength(clientdto.getNom())) {
            errors.add("Veuillez renseigner le nom du client");
        }

        if(!StringUtils.hasLength(clientdto.getPrenom())) {
            errors.add("Veuillez renseigner le prénom du client");
        }
        if(!StringUtils.hasLength(clientdto.getEmail())) {
            errors.add("Veuillez renseigner le email du client");
        }

        if(!StringUtils.hasLength(clientdto.getNumTel())){
            errors.add("Veuillez renseigner le numéro de téléphone du client");
        }

        return errors;
    }
}
