package chijouProjects.gestion_stock_api.dto;

import chijouProjects.gestion_stock_api.model.LigneCdeClt;
import chijouProjects.gestion_stock_api.model.CommandeClient;

import lombok.Builder;
import lombok.Data;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

/*@Data
@Builder
public class CommandeClientDto {
    private Integer id;

    private String code;

    private Instant datecommande;

    private ClientDto clientdto;

    private Integer identreprise;

    private List<LigneCdeCltDto> lignecdecltsdto;

    public static CommandeClientDto fromEntity(CommandeClient commandeclient) {
        if (commandeclient == null) return null;

        return CommandeClientDto.builder()
                .id(commandeclient.getId())
                .code(commandeclient.getCode())
                .datecommande(commandeclient.getDatecommande())
                .identreprise(commandeclient.getIdentreprise())
                .clientdto(ClientDto.fromEntity(commandeclient.getClient()))
                .build();
    }

    public static CommandeClient toEntity(CommandeClientDto commandeclientdto) {
        if (commandeclientdto == null) return null;
        CommandeClient commandeclient = new CommandeClient();
        commandeclient.setId(commandeclientdto.getId());
        commandeclient.setCode(commandeclientdto.getCode());
        commandeclient.setDatecommande(commandeclientdto.getDatecommande());
        commandeclient.setClient(ClientDto.toEntity(commandeclientdto.getClientdto()));
        commandeclient.setIdentreprise(commandeclientdto.getIdentreprise());
        return commandeclient;
    }
}*/

@Data
@Builder
public class CommandeClientDto {
    private Integer id;
    private String code;
    private Instant datecommande;
    private ClientDto clientdto;
    private Integer identreprise;
    private List<LigneCdeCltDto> lignecdecltsdto;

    public static CommandeClientDto fromEntity(CommandeClient commandeclient) {
        if (commandeclient == null) return null;

        return CommandeClientDto.builder()
                .id(commandeclient.getId())
                .code(commandeclient.getCode())
                .datecommande(commandeclient.getDatecommande())
                .identreprise(commandeclient.getIdentreprise())
                .clientdto(ClientDto.fromEntity(commandeclient.getClient()))
                .lignecdecltsdto(
                        commandeclient.getLignecdeclts() != null ?
                                commandeclient.getLignecdeclts().stream()
                                        .map(LigneCdeCltDto::fromEntity)
                                        .collect(Collectors.toList()) : null
                )
                .build();
    }

    public static CommandeClient toEntity(CommandeClientDto commandeclientdto) {
        if (commandeclientdto == null) return null;

        CommandeClient commandeclient = new CommandeClient();
        commandeclient.setId(commandeclientdto.getId());
        commandeclient.setCode(commandeclientdto.getCode());
        commandeclient.setDatecommande(commandeclientdto.getDatecommande());
        commandeclient.setClient(ClientDto.toEntity(commandeclientdto.getClientdto()));
        commandeclient.setIdentreprise(commandeclientdto.getIdentreprise());

        if (commandeclientdto.getLignecdecltsdto() != null) {
            List<LigneCdeClt> lignes = commandeclientdto.getLignecdecltsdto().stream()
                    .map(LigneCdeCltDto::toEntity)
                    .collect(Collectors.toList());
            lignes.forEach(l -> l.setCommandeclient(commandeclient)); // 🔁 liaison bidirectionnelle
            commandeclient.setLignecdeclts(lignes);
        }

        return commandeclient;
    }
}
