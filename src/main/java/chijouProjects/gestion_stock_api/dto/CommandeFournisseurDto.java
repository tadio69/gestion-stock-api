package chijouProjects.gestion_stock_api.dto;

import chijouProjects.gestion_stock_api.model.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Data;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
public class CommandeFournisseurDto {
    private Integer id;
    private String code;
    private Instant datecommande;
    private EtatCommande etatCommande;
    private FournisseurDto  fournisseurdto;
    private Integer identreprise;
    @JsonIgnore
    private List<LigneCdeFournisseurDto> lignecdefournisseursdto;

    public static CommandeFournisseurDto fromEntity(CommandeFournisseur commandefournisseur) {
        if (commandefournisseur == null) return null;

        return CommandeFournisseurDto.builder()
                .id(commandefournisseur.getId())
                .code(commandefournisseur.getCode())
                .datecommande(commandefournisseur.getDatecommande())
                .etatCommande(commandefournisseur.getEtatcommande())
                .fournisseurdto(FournisseurDto.fromEntity(commandefournisseur.getFournisseur()))
                .identreprise(commandefournisseur.getEntreprise().getId())
                .lignecdefournisseursdto(
                        commandefournisseur.getLignecdefournisseurs() != null ?
                                commandefournisseur.getLignecdefournisseurs().stream()
                                        .map(LigneCdeFournisseurDto::fromEntity)
                                        .collect(Collectors.toList()) : null
                )
                .build();
    }

    public static CommandeFournisseur toEntity(CommandeFournisseurDto commandefournisseurdto) {
        if (commandefournisseurdto == null) return null;

        CommandeFournisseur commandefournisseur = new CommandeFournisseur();
        commandefournisseur.setId(commandefournisseurdto.getId());
        commandefournisseur.setCode(commandefournisseurdto.getCode());
        commandefournisseur.setDatecommande(commandefournisseurdto.getDatecommande());
        commandefournisseur.setEtatcommande(commandefournisseurdto.getEtatCommande());
        if(commandefournisseurdto.fournisseurdto != null) {
            Fournisseur fournisseur = new Fournisseur();
            fournisseur.setId(commandefournisseurdto.getFournisseurdto().getId());
            commandefournisseur.setFournisseur(fournisseur);
        }

        if(commandefournisseurdto.getIdentreprise() != null){
            Entreprise entreprise = new Entreprise();
            entreprise.setId(commandefournisseurdto.getIdentreprise());
            commandefournisseur.setEntreprise(entreprise);
        }

        if (commandefournisseurdto.getLignecdefournisseursdto() != null) {
            List<LigneCdeFournisseur> lignes = commandefournisseurdto.getLignecdefournisseursdto().stream()
                    .map(LigneCdeFournisseurDto::toEntity)
                    .collect(Collectors.toList());
            lignes.forEach(l -> l.setCommandefournisseur(commandefournisseur)); // 🔁 liaison bidirectionnelle
            commandefournisseur.setLignecdefournisseurs(lignes);
        }

        return commandefournisseur;
    }

    public boolean isCommandeLivree(){
        return EtatCommande.LIVREE.equals(this.etatCommande);
    }
}
