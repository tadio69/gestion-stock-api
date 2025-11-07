package chijouProjects.gestion_stock_api.dto;

import chijouProjects.gestion_stock_api.model.CommandeClient;
import chijouProjects.gestion_stock_api.model.CommandeFournisseur;
import chijouProjects.gestion_stock_api.model.Entreprise;
import chijouProjects.gestion_stock_api.model.Utilisateur;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

/*@Data
@Builder
public class EntrepriseDto {
    private Integer id;

    private String nom;

    private String description;

    private AdresseDto adressedto;

    private String codefiscal;

    private String photo;

    private String email;

    private String numtel;

    private String siteweb;

    @JsonIgnore
    private List<UtilisateurDto> utilisateursdto;

    public static EntrepriseDto fromEntity(Entreprise entreprise) {
        if (entreprise == null) return null;

        return EntrepriseDto.builder()
                .id(entreprise.getId())
                .nom(entreprise.getNom())
                .description(entreprise.getDescription())
                .codefiscal(entreprise.getCodefiscal())
                .photo(entreprise.getPhoto())
                .email(entreprise.getEmail())
                .adressedto(AdresseDto.fromEntity(entreprise.getAdresse()))
                .numtel(entreprise.getNumtel())
                .siteweb(entreprise.getSiteweb())
                .build();
    }

    public static Entreprise toEntity(EntrepriseDto entreprisedto) {
        if (entreprisedto == null) return null;
        Entreprise entreprise = new Entreprise();
        entreprise.setId(entreprisedto.getId());
        entreprise.setNom(entreprisedto.getNom());
        entreprise.setDescription(entreprisedto.getDescription());
        entreprise.setCodefiscal(entreprisedto.getCodefiscal());
        entreprise.setPhoto(entreprisedto.getPhoto());
        entreprise.setEmail(entreprisedto.getEmail());
        entreprise.setAdresse(AdresseDto.toEntity(entreprisedto.getAdressedto()));
        entreprise.setNumtel(entreprisedto.getNumtel());
        entreprise.setSiteweb(entreprisedto.getSiteweb());
        return entreprise;
    }
}*/

@Data
@Builder
public class EntrepriseDto {
    private Integer id;
    private String nom;
    private String description;
    private AdresseDto adressedto;
    private String codefiscal;
    private String photo;
    private String email;
    private String numtel;
    private String siteweb;

    @JsonIgnore
    private List<UtilisateurDto> utilisateursdto;

    public static EntrepriseDto fromEntity(Entreprise entreprise) {
        if (entreprise == null) return null;

        return EntrepriseDto.builder()
                .id(entreprise.getId())
                .nom(entreprise.getNom())
                .description(entreprise.getDescription())
                .codefiscal(entreprise.getCodefiscal())
                .photo(entreprise.getPhoto())
                .email(entreprise.getEmail())
                .adressedto(AdresseDto.fromEntity(entreprise.getAdresse()))
                .numtel(entreprise.getNumtel())
                .siteweb(entreprise.getSiteweb())
                .utilisateursdto(
                        entreprise.getUtilisateurs() != null ?
                                entreprise.getUtilisateurs().stream()
                                        .map(UtilisateurDto::fromEntity)
                                        .collect(Collectors.toList())
                                : null
                )
                .build();
    }

    public static Entreprise toEntity(EntrepriseDto entreprisedto) {
        if (entreprisedto == null) return null;

        Entreprise entreprise = new Entreprise();
        entreprise.setId(entreprisedto.getId());
        entreprise.setNom(entreprisedto.getNom());
        entreprise.setDescription(entreprisedto.getDescription());
        entreprise.setCodefiscal(entreprisedto.getCodefiscal());
        entreprise.setPhoto(entreprisedto.getPhoto());
        entreprise.setEmail(entreprisedto.getEmail());
        entreprise.setAdresse(AdresseDto.toEntity(entreprisedto.getAdressedto()));
        entreprise.setNumtel(entreprisedto.getNumtel());
        entreprise.setSiteweb(entreprisedto.getSiteweb());

        if (entreprisedto.getUtilisateursdto() != null) {
            List<Utilisateur> utilisateurs = entreprisedto.getUtilisateursdto().stream()
                    .map(UtilisateurDto::toEntity)
                    .collect(Collectors.toList());
            entreprise.setUtilisateurs(utilisateurs);
        }

        return entreprise;
    }
}
