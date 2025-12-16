package chijouProjects.gestion_stock_api.dto;

import chijouProjects.gestion_stock_api.model.Article;
import chijouProjects.gestion_stock_api.model.CommandeClient;
import chijouProjects.gestion_stock_api.model.Fournisseur;
import chijouProjects.gestion_stock_api.model.LigneCdeClt;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class LigneCdeCltDto {
    private Integer id;

    private Integer idarticle;

    private Integer idcommandeclient;

    private BigDecimal quantite;

    private BigDecimal prixunitaire;

    private Integer identreprise;

    public static LigneCdeCltDto fromEntity(LigneCdeClt lignecdeclt) {
        if (lignecdeclt == null) return null;

        return LigneCdeCltDto.builder()
                .id(lignecdeclt.getId())
                .quantite(lignecdeclt.getQuantite())
                .prixunitaire(lignecdeclt.getPrixunitaire())
                .idarticle(lignecdeclt.getArticle().getId())
                .idcommandeclient(lignecdeclt.getCommandeclient().getId())
                .build();
    }

    public static LigneCdeClt toEntity(LigneCdeCltDto lignecdecltdto) {
        if (lignecdecltdto == null) return null;
        LigneCdeClt lignecdeclt = new LigneCdeClt();
        lignecdeclt.setId(lignecdecltdto.getId());
        lignecdeclt.setQuantite(lignecdecltdto.getQuantite());
        lignecdeclt.setPrixunitaire(lignecdecltdto.getPrixunitaire());
        if (lignecdecltdto.getIdarticle() != null) {
            Article article = new Article();
            article.setId(lignecdecltdto.getIdarticle());
            lignecdeclt.setArticle(article);
        }
        if (lignecdecltdto.getIdcommandeclient() != null) {
            CommandeClient commandeclient = new CommandeClient();
            commandeclient.setId(lignecdecltdto.getIdcommandeclient());
            lignecdeclt.setCommandeclient(commandeclient);
        }
        return lignecdeclt;
    }
}
