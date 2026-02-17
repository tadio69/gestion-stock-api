package chijouProjects.gestion_stock_api.dto;

import chijouProjects.gestion_stock_api.model.Entreprise;
import chijouProjects.gestion_stock_api.model.Vente;
import chijouProjects.gestion_stock_api.model.LigneVente;
import lombok.Builder;
import lombok.Data;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
public class VenteDto {
    private Integer id;
    private String code;
    private Instant datevente;
    private String commentaire;
    private Integer identreprise;

    private List<LigneVenteDto> ligneventesdto;

    // ====== Conversion Entité → DTO ======
    public static VenteDto fromEntity(Vente vente) {
        if (vente == null) return null;

        return VenteDto.builder()
                .id(vente.getId())
                .code(vente.getCode())
                .datevente(vente.getDatevente())
                .commentaire(vente.getCommentaire())
                .identreprise(vente.getEntreprise().getId())
                .ligneventesdto(
                        vente.getLigneventes() != null
                                ? vente.getLigneventes().stream()
                                .map(LigneVenteDto::fromEntity)
                                .collect(Collectors.toList())
                                : null
                )
                .build();
    }

    // ====== Conversion DTO → Entité ======
    public static Vente toEntity(VenteDto ventedto) {
        if (ventedto == null) return null;

        Vente vente = new Vente();
        vente.setId(ventedto.getId());
        vente.setCode(ventedto.getCode());
        vente.setDatevente(ventedto.getDatevente());
        vente.setCommentaire(ventedto.getCommentaire());
        if(ventedto.getIdentreprise() != null){
            Entreprise entreprise = new Entreprise();
            entreprise.setId(ventedto.getIdentreprise());
            vente.setEntreprise(entreprise);
        }

        if (ventedto.getLigneventesdto() != null) {
            List<LigneVente> lignes = ventedto.getLigneventesdto().stream()
                    .map(LigneVenteDto::toEntity)
                    .collect(Collectors.toList());
            lignes.forEach(ligne -> ligne.setVente(vente)); // lie chaque ligne à la vente
            vente.setLigneventes(lignes);
        }

        return vente;
    }
}
