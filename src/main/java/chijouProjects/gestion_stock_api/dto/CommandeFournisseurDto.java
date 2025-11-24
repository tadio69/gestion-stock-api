package chijouProjects.gestion_stock_api.dto;

import chijouProjects.gestion_stock_api.model.CommandeClient;
import chijouProjects.gestion_stock_api.model.CommandeFournisseur;
import chijouProjects.gestion_stock_api.model.Fournisseur;
import chijouProjects.gestion_stock_api.model.LigneCdeFournisseur;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Data;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

/*@Data
@Builder
public class CommandeFournisseurDto {
    private Integer id;

    private String code;

    private Instant datecommande;

    private FournisseurDto fournisseurdto;

    private Integer identreprise;

    private List<LigneCdeFournisseurDto> lignecdefournisseursdto;

    public static CommandeFournisseurDto fromEntity(CommandeFournisseur commandefournissur) {
        if (commandefournissur == null) return null;

        return CommandeFournisseurDto.builder()
                .id(commandefournissur.getId())
                .code(commandefournissur.getCode())
                .datecommande(commandefournissur.getDatecommande())
                .fournisseurdto(FournisseurDto.fromEntity(commandefournissur.getFournisseur()))
                .identreprise(commandefournissur.getIdentreprise())
                .build();
    }

    public static CommandeFournisseur toEntity(CommandeFournisseurDto Commandefournisseurdto) {
        if (Commandefournisseurdto == null) return null;
        CommandeFournisseur commandefournisseur = new CommandeFournisseur();
        commandefournisseur.setId(Commandefournisseurdto.getId());
        commandefournisseur.setCode(Commandefournisseurdto.getCode());
        commandefournisseur.setDatecommande(Commandefournisseurdto.getDatecommande());
        commandefournisseur.setFournisseur(FournisseurDto.toEntity(Commandefournisseurdto.getFournisseurdto()));
        commandefournisseur.setIdentreprise(Commandefournisseurdto.getIdentreprise());
        return commandefournisseur;
    }
}*/

@Data
@Builder
public class CommandeFournisseurDto {
    private Integer id;
    private String code;
    private Instant datecommande;
    private Integer idfournisseur;
    private Integer identreprise;
    private List<LigneCdeFournisseurDto> lignecdefournisseursdto;

    public static CommandeFournisseurDto fromEntity(CommandeFournisseur commandefournisseur) {
        if (commandefournisseur == null) return null;

        return CommandeFournisseurDto.builder()
                .id(commandefournisseur.getId())
                .code(commandefournisseur.getCode())
                .datecommande(commandefournisseur.getDatecommande())
                .idfournisseur(commandefournisseur.getFournisseur().getId())
                .identreprise(commandefournisseur.getIdentreprise())
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
        if(commandefournisseurdto.getIdfournisseur() != null) {
            Fournisseur fournisseur = new Fournisseur();
            fournisseur.setId(commandefournisseurdto.getIdfournisseur());
            commandefournisseur.setFournisseur(fournisseur);
        }
        commandefournisseur.setIdentreprise(commandefournisseurdto.getIdentreprise());

        if (commandefournisseurdto.getLignecdefournisseursdto() != null) {
            List<LigneCdeFournisseur> lignes = commandefournisseurdto.getLignecdefournisseursdto().stream()
                    .map(LigneCdeFournisseurDto::toEntity)
                    .collect(Collectors.toList());
            lignes.forEach(l -> l.setCommandefournisseur(commandefournisseur)); // 🔁 liaison bidirectionnelle
            commandefournisseur.setLignecdefournisseurs(lignes);
        }

        return commandefournisseur;
    }
}
