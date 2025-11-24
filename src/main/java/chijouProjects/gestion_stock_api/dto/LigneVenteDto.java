package chijouProjects.gestion_stock_api.dto;

import chijouProjects.gestion_stock_api.model.Article;
import chijouProjects.gestion_stock_api.model.LigneVente;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class LigneVenteDto {
    private Integer id;

    private Integer idarticle;

    private VenteDto ventedto;

    private BigDecimal quantite;

    private BigDecimal prixunitaire;

    private Integer identreprise;

    public static LigneVenteDto fromEntity(LigneVente lignevente) {
        if (lignevente == null) return null;

        return LigneVenteDto.builder()
                .id(lignevente.getId())
                .quantite(lignevente.getQuantite())
                .idarticle(lignevente.getArticle().getId())
                .prixunitaire(lignevente.getPrixunitaire())
                .ventedto(VenteDto.fromEntity(lignevente.getVente()))
                .identreprise(lignevente.getIdentreprise())
                .build();
    }

    public static LigneVente toEntity(LigneVenteDto ligneventedto) {
        if (ligneventedto == null) return null;
        LigneVente lignevente = new LigneVente();
        lignevente.setId(ligneventedto.getId());
        if(ligneventedto.getIdarticle() != null) {
            Article article = new Article();
            article.setId(ligneventedto.getIdarticle());
            lignevente.setArticle(article);
        }

        lignevente.setVente(VenteDto.toEntity(ligneventedto.getVentedto()));
        lignevente.setQuantite(ligneventedto.getQuantite());
        lignevente.setPrixunitaire(ligneventedto.getPrixunitaire());
        lignevente.setIdentreprise(ligneventedto.getIdentreprise());
        return lignevente;
    }
}
