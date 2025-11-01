package chijouProjects.gestion_stock_api.dto;

import chijouProjects.gestion_stock_api.model.Utilisateur;
import chijouProjects.gestion_stock_api.model.Vente;
import lombok.Builder;
import lombok.Data;

import java.time.Instant;

@Data
@Builder
public class VenteDto {
    private Integer id;

    private String code;

    private Instant datevente;

    private String commentaire;

    public static VenteDto fromEntity(Vente vente) {
        if (vente == null) return null;

        return VenteDto.builder()
                .id(vente.getId())
                .code(vente.getCode())
                .datevente(vente.getDatevente())
                .commentaire(vente.getCommentaire())
                .build();
    }

    public static Vente toEntity(VenteDto ventedto) {
        if (ventedto == null) return null;
        Vente vente = new Vente();
        vente.setId(ventedto.getId());
        vente.setCode(ventedto.getCode());
        vente.setDatevente(ventedto.getDatevente());
        vente.setCommentaire(ventedto.getCommentaire());
        return vente;
    }
}
