package chijouProjects.gestion_stock_api.dto;

import chijouProjects.gestion_stock_api.model.CommandeFournisseur;
import chijouProjects.gestion_stock_api.model.Entreprise;
import chijouProjects.gestion_stock_api.model.Fournisseur;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

/*
@Data
@Builder

public class FournisseurDto {
    private Integer id;

    private String nom;

    private String prenom;

    private String email;

    private String numtel;

    private AdresseDto adressedto;

    private String photo;

    private Integer identreprise;

    @JsonIgnore
    private List<CommandeFournisseurDto> commandefournisseursdto;

    public static FournisseurDto fromEntity(Fournisseur fournisseur) {
        if (fournisseur == null) return null;

        return FournisseurDto.builder()
                .id(fournisseur.getId())
                .nom(fournisseur.getNom())
                .prenom(fournisseur.getPrenom())
                .email(fournisseur.getEmail())
                .numtel(fournisseur.getNumtel())
                .photo(fournisseur.getPhoto())
                .adressedto(AdresseDto.fromEntity(fournisseur.getAdresse()))
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
        fournisseur.setAdresse(AdresseDto.toEntity(fournisseurdto.getAdressedto()));
        fournisseur.setIdentreprise(fournisseurdto.getIdentreprise());
        return fournisseur;
    }
}
*/

@Data
@Builder
public class FournisseurDto {
    private Integer id;
    private String nom;
    private String prenom;
    private String email;
    private String numtel;
    private AdresseDto adressedto;
    private String photo;
    private Integer identreprise;

    @JsonIgnore
    private List<CommandeFournisseurDto> commandefournisseursdto;

    public static FournisseurDto fromEntity(Fournisseur fournisseur) {
        if (fournisseur == null) return null;

        return FournisseurDto.builder()
                .id(fournisseur.getId())
                .nom(fournisseur.getNom())
                .prenom(fournisseur.getPrenom())
                .email(fournisseur.getEmail())
                .numtel(fournisseur.getNumtel())
                .photo(fournisseur.getPhoto())
                .adressedto(AdresseDto.fromEntity(fournisseur.getAdresse()))
                .identreprise(fournisseur.getIdentreprise())
                .commandefournisseursdto(
                        fournisseur.getCommandefournisseurs() != null ?
                                fournisseur.getCommandefournisseurs().stream()
                                        .map(CommandeFournisseurDto::fromEntity)
                                        .collect(Collectors.toList()) : null
                )
                .build();
    }

    public static Fournisseur toEntity(FournisseurDto fournisseurdto) {
        if (fournisseurdto == null) return null;

        Fournisseur fournisseur = new Fournisseur();
        fournisseur.setId(fournisseurdto.getId());
        fournisseur.setNom(fournisseurdto.getNom());
        fournisseur.setPrenom(fournisseurdto.getPrenom()); // ✅ correction ici
        fournisseur.setEmail(fournisseurdto.getEmail());
        fournisseur.setNumtel(fournisseurdto.getNumtel());
        fournisseur.setPhoto(fournisseurdto.getPhoto());
        fournisseur.setAdresse(AdresseDto.toEntity(fournisseurdto.getAdressedto()));
        fournisseur.setIdentreprise(fournisseurdto.getIdentreprise());

        if (fournisseurdto.getCommandefournisseursdto() != null) {
            List<CommandeFournisseur> commandes = fournisseurdto.getCommandefournisseursdto().stream()
                    .map(CommandeFournisseurDto::toEntity)
                    .collect(Collectors.toList());
            commandes.forEach(cmd -> cmd.setFournisseur(fournisseur)); // 🔁 liaison bidirectionnelle
            fournisseur.setCommandefournisseurs(commandes);
        }

        return fournisseur;
    }
}

