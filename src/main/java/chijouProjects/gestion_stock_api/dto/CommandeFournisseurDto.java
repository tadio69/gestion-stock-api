package chijouProjects.gestion_stock_api.dto;

import chijouProjects.gestion_stock_api.model.CommandeClient;
import chijouProjects.gestion_stock_api.model.CommandeFournisseur;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Data;

import java.time.Instant;
import java.util.List;

@Data
@Builder
public class CommandeFournisseurDto {
    private Integer id;

    private String code;

    private Instant datecommande;

    private FournisseurDto fournisseur;

    private Integer identreprise;

    private List<LigneCdeFournisseurDto> lignecdefournisseurs;

    public static CommandeFournisseurDto fromEntity(CommandeFournisseur commandefournissur) {
        if (commandefournissur == null) return null;

        return CommandeFournisseurDto.builder()
                .id(commandefournissur.getId())
                .code(commandefournissur.getCode())
                .datecommande(commandefournissur.getDatecommande())
                .identreprise(commandefournissur.getIdentreprise())
                .build();
    }

    public static CommandeFournisseur toEntity(CommandeFournisseurDto Commandefournisseurdto) {
        if (Commandefournisseurdto == null) return null;
        CommandeFournisseur commandefournisseur = new CommandeFournisseur();
        commandefournisseur.setId(Commandefournisseurdto.getId());
        commandefournisseur.setCode(Commandefournisseurdto.getCode());
        commandefournisseur.setDatecommande(Commandefournisseurdto.getDatecommande());
        commandefournisseur.setIdentreprise(Commandefournisseurdto.getIdentreprise());
        return commandefournisseur;
    }
}
