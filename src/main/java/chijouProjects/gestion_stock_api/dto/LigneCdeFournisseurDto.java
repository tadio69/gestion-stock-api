package chijouProjects.gestion_stock_api.dto;

import chijouProjects.gestion_stock_api.model.LigneCdeClt;
import chijouProjects.gestion_stock_api.model.LigneCdeFournisseur;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class LigneCdeFournisseurDto {
    private Integer id;

    private ArticleDto article;

    private CommandeFournisseurDto commandeFournisseur;

    private BigDecimal quantite;

    private BigDecimal prixunitaire;

    private Integer identreprise;

    public static LigneCdeFournisseurDto fromEntity(LigneCdeFournisseur lignecdefournisseur) {
        if (lignecdefournisseur == null) return null;

        return LigneCdeFournisseurDto.builder()
                .id(lignecdefournisseur.getId())
                .quantite(lignecdefournisseur.getQuantite())
                .prixunitaire(lignecdefournisseur.getPrixunitaire())
                .identreprise(lignecdefournisseur.getIdentreprise())
                .build();
    }

    public static LigneCdeFournisseur toEntity(LigneCdeFournisseurDto lignecdefournisseurdto) {
        if (lignecdefournisseurdto == null) return null;
        LigneCdeFournisseur lignecdefournisseur = new LigneCdeFournisseur();
        lignecdefournisseur.setId(lignecdefournisseurdto.getId());
        lignecdefournisseur.setQuantite(lignecdefournisseurdto.getQuantite());
        lignecdefournisseur.setPrixunitaire(lignecdefournisseurdto.getPrixunitaire());
        lignecdefournisseur.setId(lignecdefournisseurdto.getIdentreprise());
        return lignecdefournisseur;
    }
}
