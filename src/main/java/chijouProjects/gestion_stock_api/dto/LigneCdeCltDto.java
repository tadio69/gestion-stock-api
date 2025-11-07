package chijouProjects.gestion_stock_api.dto;

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

    private ArticleDto articledto;

    private CommandeClientDto commandeclientdto;

    private BigDecimal quantite;

    private BigDecimal prixunitaire;

    private Integer identreprise;

    public static LigneCdeCltDto fromEntity(LigneCdeClt lignecdeclt) {
        if (lignecdeclt == null) return null;

        return LigneCdeCltDto.builder()
                .id(lignecdeclt.getId())
                .quantite(lignecdeclt.getQuantite())
                .prixunitaire(lignecdeclt.getPrixunitaire())
                .identreprise(lignecdeclt.getIdentreprise())
                .articledto(ArticleDto.fromEntity(lignecdeclt.getArticle()))
                .commandeclientdto(CommandeClientDto.fromEntity(lignecdeclt.getCommandeclient()))
                .build();
    }

    public static LigneCdeClt toEntity(LigneCdeCltDto lignecdecltdto) {
        if (lignecdecltdto == null) return null;
        LigneCdeClt lignecdeclt = new LigneCdeClt();
        lignecdeclt.setId(lignecdecltdto.getId());
        lignecdeclt.setQuantite(lignecdecltdto.getQuantite());
        lignecdeclt.setPrixunitaire(lignecdecltdto.getPrixunitaire());
        lignecdeclt.setIdentreprise(lignecdecltdto.getIdentreprise());
        lignecdeclt.setArticle(ArticleDto.toEntity(lignecdecltdto.getArticledto()));
        lignecdeclt.setCommandeclient(CommandeClientDto.toEntity(lignecdecltdto.getCommandeclientdto()));
        return lignecdeclt;
    }
}
