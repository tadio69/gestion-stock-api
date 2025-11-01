package chijouProjects.gestion_stock_api.dto;
import chijouProjects.gestion_stock_api.model.Article;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class ArticleDto {
    private Integer id;

    private String codeArticle;

    private String designation;

    private BigDecimal prixunitaireht;

    private BigDecimal tauxtva;

    private BigDecimal prixunitairettc;

    private String photo;

    private CategorieDto categoriedto;

    public static ArticleDto fromEntity(Article article) {
        if (article == null) return null;

        return ArticleDto.builder()
                .id(article.getId())
                .codeArticle(article.getCodeArticle())
                .designation(article.getDesignation())
                .prixunitaireht(article.getPrixunitaireht())
                .tauxtva(article.getTauxtva())
                .prixunitairettc(article.getPrixunitairettc())
                .photo(article.getPhoto())
                .build();
    }

    public static Article toEntity(ArticleDto articledto) {
        if (articledto == null) return null;
        Article article = new Article();
        article.setId(articledto.getId());
        article.setCodeArticle(articledto.getCodeArticle());
        article.setDesignation(articledto.getDesignation());
        article.setPrixunitaireht(articledto.getPrixunitaireht());
        article.setTauxtva(articledto.getTauxtva());
        article.setPrixunitairettc(articledto.getPrixunitairettc());
        article.setPhoto(articledto.getPhoto());
        return article;
    }
}
