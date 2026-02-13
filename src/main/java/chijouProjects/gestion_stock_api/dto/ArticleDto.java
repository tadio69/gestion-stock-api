package chijouProjects.gestion_stock_api.dto;
import chijouProjects.gestion_stock_api.model.Article;
import chijouProjects.gestion_stock_api.model.Categorie;
import chijouProjects.gestion_stock_api.model.Entreprise;
import chijouProjects.gestion_stock_api.model.ImgLink;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class ArticleDto {
    private Integer id;

    private String code;

    private String designation;

    private BigDecimal prixunitaireht;

    private BigDecimal tauxtva;

    private BigDecimal prixunitairettc;

    private Integer idimglink;

    private Integer  idcategorie;

    private Integer identreprise;

    public static ArticleDto fromEntity(Article article) {
        if (article == null) return null;

        return ArticleDto.builder()
                .id(article.getId())
                .code(article.getCode())
                .idcategorie(article.getCategorie().getId())
                .designation(article.getDesignation())
                .prixunitaireht(article.getPrixunitaireht())
                .tauxtva(article.getTauxtva())
                .prixunitairettc(article.getPrixunitairettc())
                .idimglink(article.getImglink().getId())
                .identreprise(article.getEntreprise().getId())
                .build();
    }

    public static Article toEntity(ArticleDto articledto) {
        if (articledto == null) return null;
        Article article = new Article();
        article.setId(articledto.getId());
        article.setCode(articledto.getCode());
        article.setDesignation(articledto.getDesignation());
        article.setPrixunitaireht(articledto.getPrixunitaireht());
        article.setTauxtva(articledto.getTauxtva());
        article.setPrixunitairettc(articledto.getPrixunitairettc());
        if (articledto.getIdimglink() != null) {
            ImgLink imglink = new ImgLink();
            imglink.setId(articledto.getIdimglink());
            article.setImglink(imglink);
        }
        if (articledto.getIdentreprise() != null) {
            Entreprise entreprise = new Entreprise();
            entreprise.setId(articledto.getIdentreprise());
            article.setEntreprise(entreprise);
        }
        if (articledto.getIdcategorie() != null) {
            Categorie categorie = new Categorie();
            categorie.setId(articledto.getIdcategorie());
            article.setCategorie(categorie);
        }
        return article;
    }
}
