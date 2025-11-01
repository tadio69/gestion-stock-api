package chijouProjects.gestion_stock_api.dto;

import chijouProjects.gestion_stock_api.model.Adresse;
import chijouProjects.gestion_stock_api.model.Article;
import chijouProjects.gestion_stock_api.model.Client;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ClientDto {
    private Integer id;

    private String nom;

    private String prenom;

    private String email;

    private String numTel;

    private AdresseDto adresse;

    private String photo;

    @JsonIgnore
    private List<CommandeClientDto> commandeclients;

    public static ClientDto fromEntity(Client client) {
        if (client == null) return null;

        return ClientDto.builder()
                .id(client.getId())
                .nom(client.getNom())
                .prenom(client.getPrenom())
                .email(client.getEmail())
                .numTel(client.getNumTel())
                .photo(client.getPhoto())
                .build();
    }

    public static Client toEntity(ClientDto clientdto) {
        if (clientdto == null) return null;
        Client client = new Client();
        client.setNom(clientdto.getNom());
        client.setPrenom(clientdto.getPrenom());
        client.setEmail(clientdto.getEmail());
        client.setNumTel(clientdto.getNumTel());
        client.setPhoto(clientdto.getPhoto());
        return client;
    }
}
