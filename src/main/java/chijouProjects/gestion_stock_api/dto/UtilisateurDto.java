package chijouProjects.gestion_stock_api.dto;

import chijouProjects.gestion_stock_api.model.Role;
import chijouProjects.gestion_stock_api.model.Utilisateur;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Data;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
public class UtilisateurDto {
    private Integer id;

    private String nom;

    private String prenom;

    private String email;

    private Instant datenaissance;

    private String motdepasse;

    private AdresseDto adresse;

    private String photo;

    @JsonIgnore
    private List<RoleDto> roles;

    public static UtilisateurDto fromEntity(Utilisateur utilisateur) {
        if (utilisateur == null) return null;

        return UtilisateurDto.builder()
                .id(utilisateur.getId())
                .nom(utilisateur.getNom())
                .prenom(utilisateur.getPrenom())
                .email(utilisateur.getEmail())
                .datenaissance(utilisateur.getDatenaissance())
                .motdepasse(utilisateur.getMotdepasse())
                .adresse(AdresseDto.fromEntity(utilisateur.getAdresse()))
                .photo(utilisateur.getPhoto())
                .roles(
                        utilisateur.getRoles() != null?
                                utilisateur.getRoles().stream()
                                        .map(RoleDto::fromEntity)
                                        .collect(Collectors.toList()) : null
                )
                .build();
    }

    public static Utilisateur toEntity(UtilisateurDto utilisateurdto) {
        if (utilisateurdto == null) return null;
        Utilisateur utilisateur = new Utilisateur();
        utilisateur.setId(utilisateurdto.getId());
        utilisateur.setNom(utilisateurdto.getNom());
        utilisateur.setPrenom(utilisateurdto.getPrenom());
        utilisateur.setEmail(utilisateurdto.getEmail());
        utilisateur.setDatenaissance(utilisateurdto.getDatenaissance());
        utilisateur.setMotdepasse(utilisateurdto.getMotdepasse());
        utilisateur.setPhoto(utilisateurdto.getPhoto());
        return utilisateur;
    }
}
