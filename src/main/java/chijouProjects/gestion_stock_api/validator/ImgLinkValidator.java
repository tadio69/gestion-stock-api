package chijouProjects.gestion_stock_api.validator;

import chijouProjects.gestion_stock_api.dto.ImgLinkDto;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class ImgLinkValidator {
    public static List<String> validate(ImgLinkDto imgLinkDto) {
        List<String> errors = new ArrayList<>();

        if (imgLinkDto == null) {
            errors.add("Veuillez fournir les informations de la photo");
            errors.add("Le nom du fichier est obligatoire");
            return errors;
        }

        if (!StringUtils.hasLength(imgLinkDto.getFilename())) {
            errors.add("Le nom du fichier est obligatoire");
        }

        return errors;
    }
}
/*
public class ImgLinkValidator {
    public static List<String> validate(MultipartFile file, Integer identreprise) {
        List<String> errors = new ArrayList<>();

        if (file == null || file.isEmpty()) {
            errors.add("Veuillez choisir une image.");
        }

        if (identreprise == null) {
            errors.add("L'identreprise est obligatoire.");
        }

        return errors;
    }
}*/
