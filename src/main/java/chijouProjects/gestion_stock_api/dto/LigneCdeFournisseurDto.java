package chijouProjects.gestion_stock_api.dto;

import chijouProjects.gestion_stock_api.model.Article;
import chijouProjects.gestion_stock_api.model.CommandeFournisseur;
import chijouProjects.gestion_stock_api.model.LigneCdeClt;
import chijouProjects.gestion_stock_api.model.LigneCdeFournisseur;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class LigneCdeFournisseurDto {
    private Integer id;

    private Integer idarticle;

    private Integer idcommandefournisseur;

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
                .idarticle(lignecdefournisseur.getArticle().getId())
                .idcommandefournisseur(lignecdefournisseur.getCommandefournisseur().getId())
                .build();
    }

    public static LigneCdeFournisseur toEntity(LigneCdeFournisseurDto lignecdefournisseurdto) {
        if (lignecdefournisseurdto == null) return null;
        LigneCdeFournisseur lignecdefournisseur = new LigneCdeFournisseur();
        lignecdefournisseur.setId(lignecdefournisseurdto.getId());
        lignecdefournisseur.setQuantite(lignecdefournisseurdto.getQuantite());
        lignecdefournisseur.setPrixunitaire(lignecdefournisseurdto.getPrixunitaire());
        lignecdefournisseur.setId(lignecdefournisseurdto.getIdentreprise());
        if (lignecdefournisseurdto.getIdarticle() != null) {
            Article article = new Article();
            article.setId(lignecdefournisseurdto.getIdarticle());
            lignecdefournisseur.setArticle(article);
        }
        if (lignecdefournisseurdto.getIdcommandefournisseur() != null) {
            CommandeFournisseur commandefournisseur = new CommandeFournisseur();
            commandefournisseur.setId(lignecdefournisseurdto.getIdcommandefournisseur());
            lignecdefournisseur.setCommandefournisseur(commandefournisseur);
        }

        return lignecdefournisseur;
    }
}
