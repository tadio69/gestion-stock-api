package chijouProjects.gestion_stock_api.dto;

import chijouProjects.gestion_stock_api.model.Categorie;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class CategorieDto {
    private Integer id;

    private String code;
    
    private String designation;

    @JsonIgnore
    private List<ArticleDto> articles;

    //mapping de Categorie vers CategorieDto
    public static CategorieDto fromEntity(Categorie categorie) {
        if (categorie == null) {
            return null;
            //TODO throw an exception
        }

        return CategorieDto.builder()
                .id(categorie.getId())
                .code(categorie.getCode())
                .designation(categorie.getDesignation())
                .build();

    }

    //mapping de CategorieDto vers Categorie
    public static Categorie toEntity(CategorieDto categorieDto) {
        if (categorieDto == null) {
            return null;
        }
        Categorie categorie = new Categorie();
        categorie.setId(categorieDto.getId());
        categorie.setCode(categorieDto.getCode());
        categorie.setDesignation(categorieDto.getDesignation());
        return categorie;
    }
}
