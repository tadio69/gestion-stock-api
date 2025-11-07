package chijouProjects.gestion_stock_api.dto;

import chijouProjects.gestion_stock_api.model.Article;
import chijouProjects.gestion_stock_api.model.Categorie;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

/*@Data
@Builder
public class CategorieDto {
    private Integer id;

    private String code;
    
    private String designation;

    private Integer identreprise;

    @JsonIgnore
    private List<ArticleDto> articlesdto;

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
                .identreprise(categorie.getIdentreprise())
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
        categorie.setIdentreprise(categorieDto.getIdentreprise());
        return categorie;
    }
}*/

@Data
@Builder
public class CategorieDto {
    private Integer id;
    private String code;
    private String designation;
    private Integer identreprise;

    @JsonIgnore
    private List<ArticleDto> articlesdto;

    // Mapping de Categorie vers CategorieDto
    public static CategorieDto fromEntity(Categorie categorie) {
        if (categorie == null) return null;

        return CategorieDto.builder()
                .id(categorie.getId())
                .code(categorie.getCode())
                .designation(categorie.getDesignation())
                .identreprise(categorie.getIdentreprise())
                .articlesdto(
                        categorie.getArticles() != null ?
                                categorie.getArticles().stream()
                                        .map(ArticleDto::fromEntity)
                                        .collect(Collectors.toList()) : null
                )
                .build();
    }

    // Mapping de CategorieDto vers Categorie
    public static Categorie toEntity(CategorieDto categorieDto) {
        if (categorieDto == null) return null;

        Categorie categorie = new Categorie();
        categorie.setId(categorieDto.getId());
        categorie.setCode(categorieDto.getCode());
        categorie.setDesignation(categorieDto.getDesignation());
        categorie.setIdentreprise(categorieDto.getIdentreprise());

        if (categorieDto.getArticlesdto() != null) {
            List<Article> articles = categorieDto.getArticlesdto().stream()
                    .map(ArticleDto::toEntity)
                    .collect(Collectors.toList());
            articles.forEach(a -> a.setCategorie(categorie)); // 🔁 liaison bidirectionnelle
            categorie.setArticles(articles);
        }

        return categorie;
    }
}
