package chijouProjects.gestion_stock_api.dto;

import chijouProjects.gestion_stock_api.model.Entreprise;
import chijouProjects.gestion_stock_api.model.Fournisseur;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class FournisseurDto {
    private Integer id;

    private String nom;

    private String prenom;

    private String email;

    private String numtel;

    private AdresseDto adresse;

    private String photo;

    private Integer identreprise;

    @JsonIgnore
    private List<CommandeFournisseurDto> commandefournisseurs;

    public static FournisseurDto fromEntity(Fournisseur fournisseur) {
        if (fournisseur == null) return null;

        return FournisseurDto.builder()
                .id(fournisseur.getId())
                .nom(fournisseur.getNom())
                .prenom(fournisseur.getPrenom())
                .email(fournisseur.getEmail())
                .numtel(fournisseur.getNumtel())
                .photo(fournisseur.getPhoto())
                .identreprise(fournisseur.getIdentreprise())
                .build();
    }

    public static Fournisseur toEntity(FournisseurDto fournisseurdto) {
        if (fournisseurdto == null) return null;
        Fournisseur fournisseur = new Fournisseur();
        fournisseur.setId(fournisseurdto.getId());
        fournisseur.setNom(fournisseurdto.getNom());
        fournisseur.setPrenom(fournisseurdto.getNom());
        fournisseur.setEmail(fournisseurdto.getEmail());
        fournisseur.setNumtel(fournisseurdto.getNumtel());
        fournisseur.setPhoto(fournisseurdto.getPhoto());
        fournisseur.setIdentreprise(fournisseurdto.getIdentreprise());
        return fournisseur;
    }
}
