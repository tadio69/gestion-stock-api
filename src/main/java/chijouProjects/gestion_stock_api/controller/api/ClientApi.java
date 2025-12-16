package chijouProjects.gestion_stock_api.controller.api;


import chijouProjects.gestion_stock_api.dto.ClientDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


import static chijouProjects.gestion_stock_api.utils.Constants.CLIENT_ENDPOINT;

@Tag(name = "Clients", description = "Gestion des clients")
public interface ClientApi {
    @PostMapping(value = CLIENT_ENDPOINT + "/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Enregistrer ou modifier un client", description = "Cette méthode permet d'enregistrer ou de modifier un client")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Client créé ou modifié avec succès"),
            @ApiResponse(responseCode = "400", description = "Client non valide")
    })
    ResponseEntity<ClientDto> save(@RequestBody ClientDto articleDto);

    @GetMapping(value = CLIENT_ENDPOINT + "/{id}" , produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Rechercher un client par son ID",
            description = "Cette méthode permet de chercher un client par son identifiant"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Client trouvé avec succès"),
            @ApiResponse(responseCode = "404", description = "Aucun client trouvé avec cet ID")
    })
    ResponseEntity<ClientDto> findById(@PathVariable("id") Integer id);

    @GetMapping(value = CLIENT_ENDPOINT + "/nom/{nom}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Rechercher un client par son nom",
                description = "Cette méthode permet de chercher un client par son nom",
            parameters = {
                    @Parameter(name= "X-Entreprise-Id", in = ParameterIn.HEADER, description = "ID de l'entreprise", required = false)
            }
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Client trouvé avec succès"),
            @ApiResponse(responseCode = "404", description = "Aucun client trouvé avec ce nom")
    })
    ResponseEntity<ClientDto> findByNom(@PathVariable("nom") String nomClient);

    @GetMapping(value = CLIENT_ENDPOINT + "/all", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Renvoyer la liste des clients",
            description = "Cette méthode permet de renvoyer la liste des clients enregistrés",
            parameters = {
                    @Parameter(name= "X-Entreprise-Id", in = ParameterIn.HEADER, description = "ID de l'entreprise", required = false)
            }
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Liste des clients retournée avec succès")
    })
    ResponseEntity<List<ClientDto>> findAll();

    @DeleteMapping(value = CLIENT_ENDPOINT + "/delete/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Supprimer un client par son ID", description = "Cette méthode permet de supprimer un client par son identifiant")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Client supprimé avec succès")
    })
    ResponseEntity<Void> delete(@PathVariable("id") Integer id);
}
