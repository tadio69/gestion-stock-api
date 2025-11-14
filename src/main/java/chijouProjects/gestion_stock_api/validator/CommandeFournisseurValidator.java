package chijouProjects.gestion_stock_api.validator;

import chijouProjects.gestion_stock_api.dto.CommandeClientDto;
import chijouProjects.gestion_stock_api.dto.CommandeFournisseurDto;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class CommandeFournisseurValidator {
    public static List<String> validate(CommandeFournisseurDto commandefournisseurdto) {
        List<String> errors = new ArrayList<>();
        if(commandefournisseurdto == null) {

            return errors;
        }
        if(!StringUtils.hasLength(commandefournisseurdto.getCode())) {
            errors.add("Veuillez renseigner le code de la commande du fournisseur");
        }

        if(commandefournisseurdto.getDatecommande() == null) {
            errors.add("Veuillez renseigner la date de la commande du fournisseur");
        }

        if(commandefournisseurdto.getFournisseurdto() == null) {
            errors.add("Veuillez sélectionner le fournisseur");
        }

        return errors;
    }
}
