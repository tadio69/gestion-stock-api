package chijouProjects.gestion_stock_api.dto;

import chijouProjects.gestion_stock_api.model.Fournisseur;
import chijouProjects.gestion_stock_api.model.LigneCdeClt;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class LigneCdeCltDto {
    private Integer id;

    private ArticleDto article;

    private CommandeClientDto commandeclient;

    private BigDecimal quantite;

    private BigDecimal prixunitaire;

    public static LigneCdeCltDto fromEntity(LigneCdeClt lignecdeclt) {
        if (lignecdeclt == null) return null;

        return LigneCdeCltDto.builder()
                .id(lignecdeclt.getId())
                .quantite(lignecdeclt.getQuantite())
                .prixunitaire(lignecdeclt.getPrixunitaire())
                .build();
    }

    public static LigneCdeClt toEntity(LigneCdeCltDto lignecdecltdto) {
        if (lignecdecltdto == null) return null;
        LigneCdeClt lignecdeclt = new LigneCdeClt();
        lignecdeclt.setId(lignecdecltdto.getId());
        lignecdeclt.setQuantite(lignecdecltdto.getQuantite());
        lignecdeclt.setPrixunitaire(lignecdecltdto.getPrixunitaire());
        return lignecdeclt;
    }
}
