package chijouProjects.gestion_stock_api.controller.api;

import chijouProjects.gestion_stock_api.dto.ArticleDto;
import chijouProjects.gestion_stock_api.dto.ClientDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static chijouProjects.gestion_stock_api.utils.Constants.APP_ROOT;

@Tag(name = "Clients", description = "Gestion des clients")
public interface ClientApi {
    @PostMapping(value = APP_ROOT + "/clients/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Enregistrer ou modifier un client", description = "Cette méthode permet d'enregistrer ou de modifier un client")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Client créé ou modifié avec succès"),
            @ApiResponse(responseCode = "400", description = "Client non valide")
    })
    ResponseEntity<ClientDto> save(@RequestBody ClientDto articleDto);

    @GetMapping(value = APP_ROOT + "/clients/{id}" , produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Rechercher un client par son ID", description = "Cette méthode permet de chercher un client par son identifiant")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Client trouvé avec succès"),
            @ApiResponse(responseCode = "404", description = "Aucun client trouvé avec cet ID")
    })
    ResponseEntity<ClientDto> findById(@PathVariable("id") Integer id);

    @GetMapping(value = APP_ROOT + "/clients/nom/{nom}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Rechercher un client par son nom", description = "Cette méthode permet de chercher un client par son nom")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Client trouvé avec succès"),
            @ApiResponse(responseCode = "404", description = "Aucun client trouvé avec ce nom")
    })
    ResponseEntity<ClientDto> findByNom(@PathVariable("nom") String nomClient);

    @GetMapping(value = APP_ROOT + "/clients/all", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Renvoyer la liste des clients", description = "Cette méthode permet de renvoyer la liste des clients enregistrés")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Liste des clients retournée avec succès")
    })
    ResponseEntity<List<ClientDto>> findAll();

    @DeleteMapping(value = APP_ROOT + "/clients/delete/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Supprimer un client par son ID", description = "Cette méthode permet de supprimer un client par son identifiant")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Client supprimé avec succès")
    })
    ResponseEntity<Void> delete(@PathVariable("id") Integer id);
}
