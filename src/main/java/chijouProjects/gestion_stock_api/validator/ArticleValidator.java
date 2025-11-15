package chijouProjects.gestion_stock_api.validator;

import chijouProjects.gestion_stock_api.dto.ArticleDto;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class ArticleValidator {
    public static List<String> validate(ArticleDto articledto) {
        List<String> errors = new ArrayList<>();

        if(articledto == null) {
            errors.add("Veuillez renseigner le code de l'article");
            errors.add("Veuillez renseigner la désignation de l'article");
            errors.add("Veuillez renseigner le prix unitaire HT de l'article");
            errors.add("Veuillez renseigner le prix unitaire TTC de l'article");
            errors.add("Veuillez sélectionner la catégorie de l'article");
            return errors;
        }
        if(!StringUtils.hasLength(articledto.getCode())) {
            errors.add("Veuillez renseigner le code de l'article");
        }

        if(!StringUtils.hasLength(articledto.getDesignation())) {
            errors.add("Veuillez renseigner la désignation de l'article");
        }

        if(articledto.getPrixunitaireht() == null) {
            errors.add("Veuillez renseigner le prix unitaire HT de l'article");
        }

        if(articledto.getPrixunitairettc() == null) {
            errors.add("Veuillez renseigner le prix unitaire TTC de l'article");
        }

        if(articledto.getCategoriedto() == null) {
            errors.add("Veuillez sélectionner la catégorie de l'article");
        }

        return errors;
    }
}
