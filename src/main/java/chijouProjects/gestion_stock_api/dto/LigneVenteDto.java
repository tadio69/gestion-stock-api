package chijouProjects.gestion_stock_api.dto;

import chijouProjects.gestion_stock_api.model.LigneCdeFournisseur;
import chijouProjects.gestion_stock_api.model.LigneVente;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class LigneVenteDto {
    private Integer id;

    private VenteDto vente;

    private BigDecimal quantite;

    private BigDecimal prixunitaire;

    public static LigneVenteDto fromEntity(LigneVente lignevente) {
        if (lignevente == null) return null;

        return LigneVenteDto.builder()
                .id(lignevente.getId())
                .quantite(lignevente.getQuantite())
                .prixunitaire(lignevente.getPrixunitaire())
                .build();
    }

    public static LigneVente toEntity(LigneVenteDto ligneventedto) {
        if (ligneventedto == null) return null;
        LigneVente lignevente = new LigneVente();
        lignevente.setId(ligneventedto.getId());
        lignevente.setQuantite(ligneventedto.getQuantite());
        lignevente.setPrixunitaire(ligneventedto.getPrixunitaire());
        return lignevente;
    }
}
