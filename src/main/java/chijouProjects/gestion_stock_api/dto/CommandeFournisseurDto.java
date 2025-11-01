package chijouProjects.gestion_stock_api.dto;

import chijouProjects.gestion_stock_api.model.CommandeClient;
import chijouProjects.gestion_stock_api.model.CommandeFournisseur;
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

    private List<LigneCdeFournisseurDto> lignecdefournisseurs;

    public static CommandeFournisseurDto fromEntity(CommandeClient commandeclient) {
        if (commandeclient == null) return null;

        return CommandeFournisseurDto.builder()
                .id(commandeclient.getId())
                .code(commandeclient.getCode())
                .datecommande(commandeclient.getDatecommande())
                .build();
    }

    public static CommandeFournisseur toEntity(CommandeFournisseurDto Commandefournisseurdto) {
        if (Commandefournisseurdto == null) return null;
        CommandeFournisseur commandefournisseur = new CommandeFournisseur();
        commandefournisseur.setId(Commandefournisseurdto.getId());
        commandefournisseur.setCode(Commandefournisseurdto.getCode());
        commandefournisseur.setDatecommande(Commandefournisseurdto.getDatecommande());
        return commandefournisseur;
    }
}
