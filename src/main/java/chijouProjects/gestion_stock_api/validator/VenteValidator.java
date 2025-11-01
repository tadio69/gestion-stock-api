package chijouProjects.gestion_stock_api.validator;

import chijouProjects.gestion_stock_api.dto.RoleDto;
import chijouProjects.gestion_stock_api.dto.VenteDto;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class VenteValidator {
    public static List<String> validate(VenteDto ventedto) {
        List<String> errors = new ArrayList<>();
        if(ventedto == null) {

            return errors;
        }

        if(!StringUtils.hasLength(ventedto.getCode())){
            errors.add("Veuillez renseigner le code de vente");
        }

        if(ventedto.getDatevente() == null) {
            errors.add("Veuillez renseigner la date de vente");
        }
        return errors;
    }
}
