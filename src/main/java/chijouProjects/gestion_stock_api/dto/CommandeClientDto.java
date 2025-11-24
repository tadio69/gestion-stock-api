package chijouProjects.gestion_stock_api.dto;

import chijouProjects.gestion_stock_api.model.Client;
import chijouProjects.gestion_stock_api.model.LigneCdeClt;
import chijouProjects.gestion_stock_api.model.CommandeClient;

import lombok.Builder;
import lombok.Data;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
public class CommandeClientDto {
    private Integer id;
    private String code;
    private Instant datecommande;
    private Integer  idclient;
    private Integer identreprise;
    private List<LigneCdeCltDto> lignecdecltsdto;

    public static CommandeClientDto fromEntity(CommandeClient commandeclient) {
        if (commandeclient == null) return null;

        return CommandeClientDto.builder()
                .id(commandeclient.getId())
                .code(commandeclient.getCode())
                .datecommande(commandeclient.getDatecommande())
                .identreprise(commandeclient.getIdentreprise())
                .idclient(commandeclient.getClient().getId())
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
        commandeclient.setIdentreprise(commandeclientdto.getIdentreprise());

        if (commandeclientdto.getLignecdecltsdto() != null) {
            List<LigneCdeClt> lignes = commandeclientdto.getLignecdecltsdto().stream()
                    .map(LigneCdeCltDto::toEntity)
                    .collect(Collectors.toList());
            lignes.forEach(l -> l.setCommandeclient(commandeclient)); // 🔁 liaison bidirectionnelle
            commandeclient.setLignecdeclts(lignes);
        }

        if(commandeclientdto.getIdclient() != null) {
            Client client = new Client();
            client.setId(commandeclientdto.getIdclient());
            commandeclient.setClient(client);
        }

        return commandeclient;
    }
}
