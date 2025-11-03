package chijouProjects.gestion_stock_api.dto;

import chijouProjects.gestion_stock_api.model.Client;
import chijouProjects.gestion_stock_api.model.CommandeClient;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Data;

import java.time.Instant;
import java.util.List;

@Data
@Builder
public class CommandeClientDto {
    private Integer id;

    private String code;

    private Instant datecommande;

    private ClientDto client;

    private List<LigneCdeCltDto> lignecdeclts;

    public static CommandeClientDto fromEntity(CommandeClient commandeclient) {
        if (commandeclient == null) return null;

        return CommandeClientDto.builder()
                .id(commandeclient.getId())
                .code(commandeclient.getCode())
                .datecommande(commandeclient.getDatecommande())
                .build();
    }

    public static CommandeClient toEntity(CommandeClientDto commandeclientdto) {
        if (commandeclientdto == null) return null;
        CommandeClient commandeclient = new CommandeClient();
        commandeclient.setId(commandeclientdto.getId());
        commandeclient.setCode(commandeclientdto.getCode());
        commandeclient.setDatecommande(commandeclientdto.getDatecommande());
        return commandeclient;
    }
}
