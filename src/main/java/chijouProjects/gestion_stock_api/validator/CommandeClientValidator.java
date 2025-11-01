package chijouProjects.gestion_stock_api.validator;

import chijouProjects.gestion_stock_api.dto.ClientDto;
import chijouProjects.gestion_stock_api.dto.CommandeClientDto;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class CommandeClientValidator {
    public static List<String> validate(CommandeClientDto commandeclientdto) {
        List<String> errors = new ArrayList<>();
        if(commandeclientdto == null) {
            errors.add("Veuillez renseigner le code de la commande du client");
            errors.add("Veuillez renseigner la date de la commande du client");
            errors.add("Veuillez sélectionner le client");
            return errors;
        }
        if(!StringUtils.hasLength(commandeclientdto.getCode())) {
            errors.add("Veuillez renseigner le code de la commande du client");
        }

        if(commandeclientdto.getDatecommande() == null) {
            errors.add("Veuillez renseigner la date de la commande du client");
        }

        if(commandeclientdto.getClient() == null) {
            errors.add("Veuillez sélectionner le client");
        }

        return errors;
    }
}
