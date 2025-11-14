package chijouProjects.gestion_stock_api.validator;

import chijouProjects.gestion_stock_api.dto.ArticleDto;
import chijouProjects.gestion_stock_api.dto.MvtStockDto;

import java.util.ArrayList;
import java.util.List;

public class MvtStockValidator {
    public static List<String> validate(MvtStockDto mvtstockdto) {
        List<String> errors = new ArrayList<>();

        if(mvtstockdto == null) {
            errors.add("Veuillez sélectionner le article");
            errors.add("Veuillez renseigner le quantite");
            errors.add("Veuillez sélectionner le type de mouvement");
            return errors;
        }

        if(mvtstockdto.getArticledto() == null) {
            errors.add("Veuillez sélectionner le article");
        }

        if(mvtstockdto.getQuantite() == null) {
            errors.add("Veuillez renseigner le quantite");
        }

        if(mvtstockdto.getTypemvt() == null) {
            errors.add("Veuillez sélectionner le type de mouvement");
        }
        return errors;
    }
}
