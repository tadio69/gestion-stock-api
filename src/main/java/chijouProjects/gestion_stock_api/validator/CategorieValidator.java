package chijouProjects.gestion_stock_api.validator;

import chijouProjects.gestion_stock_api.dto.CategorieDto;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class CategorieValidator {
    public static List<String> validate(CategorieDto categoriedto) {
        List<String> errors = new ArrayList<>();
        if(categoriedto == null || !StringUtils.hasLength(categoriedto.getCode())) {
            errors.add("Veuillez renseigner le code de la catégorie");
        }
        return errors;
    }
}
