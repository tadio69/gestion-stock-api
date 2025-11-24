package chijouProjects.gestion_stock_api.dto;

import chijouProjects.gestion_stock_api.model.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
public class ClientDto {
    private Integer id;
    private String nom;
    private String prenom;
    private String email;
    private String numTel;
    private AdresseDto adressedto;
    private Integer idimglink;
    private Integer identreprise;

    @JsonIgnore
    private List<CommandeClientDto> commandeclientsdto;

    public static ClientDto fromEntity(Client client) {
        if (client == null) return null;

        return ClientDto.builder()
                .id(client.getId())
                .nom(client.getNom())
                .prenom(client.getPrenom())
                .email(client.getEmail())
                .numTel(client.getNumTel())
                .adressedto(AdresseDto.fromEntity(client.getAdresse()))
                .idimglink(client.getImglink().getId())
                .identreprise(client.getIdentreprise())
                .commandeclientsdto(
                        client.getCommandeclients() != null ?
                                client.getCommandeclients().stream()
                                        .map(CommandeClientDto::fromEntity)
                                        .collect(Collectors.toList()) : null
                )
                .build();
    }

    public static Client toEntity(ClientDto clientdto) {
        if (clientdto == null) return null;

        Client client = new Client();
        client.setId(clientdto.getId());
        client.setNom(clientdto.getNom());
        client.setPrenom(clientdto.getPrenom());
        client.setEmail(clientdto.getEmail());
        client.setNumTel(clientdto.getNumTel());
        if (clientdto.getIdimglink() != null) {
            ImgLink imglink = new ImgLink();
            imglink.setId(clientdto.getIdimglink());
            client.setImglink(imglink);
        }

        client.setAdresse(AdresseDto.toEntity(clientdto.getAdressedto()));
        client.setIdentreprise(clientdto.getIdentreprise());

        if (clientdto.getCommandeclientsdto() != null) {
            List<CommandeClient> commandes = clientdto.getCommandeclientsdto().stream()
                    .map(CommandeClientDto::toEntity)
                    .collect(Collectors.toList());
            commandes.forEach(c -> c.setClient(client)); // 🔁 liaison bidirectionnelle
            client.setCommandeclients(commandes);
        }

        return client;
    }
}
