package chijouProjects.gestion_stock_api.validator;

import chijouProjects.gestion_stock_api.dto.CategorieDto;
import chijouProjects.gestion_stock_api.dto.RoleDto;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class RoleValidator {
    public static List<String> validate(RoleDto roledto) {
        List<String> errors = new ArrayList<>();
        if(roledto == null) {
            errors.add("Veuillez renseigner le nom du rôle");
            errors.add("Veuillez sélectionner l'utilisateur");
            return errors;
        }

        if(!StringUtils.hasLength(roledto.getRolename())){
            errors.add("Veuillez renseigner le nom du rôle");
        }

        return errors;
    }
}
