package chijouProjects.gestion_stock_api.dto;

import chijouProjects.gestion_stock_api.model.*;

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
    private EtatCommande etatCommande;
    private ClientDto  clientdto;
    private Integer identreprise;
    private List<LigneCdeCltDto> lignecdecltsdto;

    public static CommandeClientDto fromEntity(CommandeClient commandeclient) {
        if (commandeclient == null) return null;

        return CommandeClientDto.builder()
                .id(commandeclient.getId())
                .code(commandeclient.getCode())
                .datecommande(commandeclient.getDatecommande())
                .etatCommande(commandeclient.getEtatcommande())
                .identreprise(commandeclient.getEntreprise().getId())
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
        commandeclient.setEtatcommande(commandeclientdto.getEtatCommande());
        commandeclient.setDatecommande(commandeclientdto.getDatecommande());

        if(commandeclientdto.getIdentreprise() != null){
            Entreprise entreprise = new Entreprise();
            entreprise.setId(commandeclientdto.getIdentreprise());
            commandeclient.setEntreprise(entreprise);
        }

        if (commandeclientdto.getLignecdecltsdto() != null) {
            List<LigneCdeClt> lignes = commandeclientdto.getLignecdecltsdto().stream()
                    .map(LigneCdeCltDto::toEntity)
                    .collect(Collectors.toList());
            lignes.forEach(l -> l.setCommandeclient(commandeclient)); // 🔁 liaison bidirectionnelle
            commandeclient.setLignecdeclts(lignes);
        }

        if(commandeclientdto.clientdto != null) {
            Client client = new Client();
            client.setId(commandeclientdto.getClientdto().getId());
            commandeclient.setClient(client);
        }

        return commandeclient;
    }

    public boolean isCommandeLivree(){
        return EtatCommande.LIVREE.equals(this.etatCommande);
    }
}
