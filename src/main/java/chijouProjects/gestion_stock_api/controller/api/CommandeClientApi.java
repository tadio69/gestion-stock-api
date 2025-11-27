package chijouProjects.gestion_stock_api.controller.api;

import chijouProjects.gestion_stock_api.dto.CommandeClientDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static chijouProjects.gestion_stock_api.utils.Constants.APP_ROOT;

@Tag(name = "Commande client", description = "Gestion des commandes clients")
public interface CommandeClientApi {
    @PostMapping(
            value = APP_ROOT + "/commandes-client/create",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @Operation(
            summary = "Enregistrer ou modifier une commande client",
            description = "Cette méthode permet d'enregistrer ou modifier une commande client"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Commande client créée ou modifiée avec succès"),
            @ApiResponse(responseCode = "400", description = "Commande client non valide")
    })
    CommandeClientDto save(@RequestBody CommandeClientDto commandeClientDto);

    @GetMapping(
            value = APP_ROOT + "/commandes-client/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @Operation(summary = "Rechercher une commande client par son ID", description = "Cette méthode permet de chercher une commande client pas son identifiant")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Commande client trouvée avec succès"),
            @ApiResponse(responseCode = "404", description = "Aucune commande client trouvée avec cet identifiant")
    })
    CommandeClientDto findById(@PathVariable("id") Integer id);

    @GetMapping(
            value = APP_ROOT + "/commandes-client/code/{code}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @Operation(summary = "Rechercher une commande client par son code", description = "Cette méthode permet de chercher une commande client par son code")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Commande client trouvée avec succès"),
            @ApiResponse(responseCode = "404", description = "Aucune commande client trouvée avec ce code")
    })
    CommandeClientDto findByCode(@PathVariable("code") String code);

    @GetMapping(
            value = APP_ROOT + "/commandes-client/all",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @Operation(summary = "Renvoyer la liste des commandes client", description = "Cette méthode permet de retourner la liste des commandes client enregistrées")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Liste des commandes client retournée avec succès")
    })
    List<CommandeClientDto> findAll();

    @DeleteMapping(
            value = APP_ROOT + "/commandes-client/delete/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @Operation(summary = "Supprimer une commande client par son ID", description = "Cette méthode permet de supprimer une commande client par son identifiant")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Commande client supprimée avec succès")
    })
    void delete(@PathVariable("id") Integer id);
}
