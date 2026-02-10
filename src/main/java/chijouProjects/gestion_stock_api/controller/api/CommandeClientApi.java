package chijouProjects.gestion_stock_api.controller.api;

import chijouProjects.gestion_stock_api.dto.CommandeClientDto;
import chijouProjects.gestion_stock_api.model.EtatCommande;
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

import static chijouProjects.gestion_stock_api.utils.Constants.COMMANDES_CLIENT_ENDPOINT;

@Tag(name = "Commandes client", description = "Gestion des commandes client")
public interface CommandeClientApi {
    @PostMapping(
            value = COMMANDES_CLIENT_ENDPOINT + "/create",
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
    ResponseEntity<CommandeClientDto> save(@RequestBody CommandeClientDto commandeClientDto);

    @PatchMapping(
            value = COMMANDES_CLIENT_ENDPOINT + "/update/{idCommande}/{etatCommande}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @Operation(
            summary = "Modifier l'état d'une commande client",
            description = "Cette méthode permet de modifier l'état d'une commande client"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "État de la commande modifié avec succès"),
            @ApiResponse(responseCode = "400", description = "Requête invalide ou état non autorisé"),
            @ApiResponse(responseCode = "404", description = "Commande client introuvable"),
            @ApiResponse(responseCode = "409", description = "Conflit : transition d'état impossible")
    })
    ResponseEntity<CommandeClientDto> updateEtatCommande(@PathVariable("idCommande") Integer idCommande, @PathVariable("etatCommande") EtatCommande etatCommande);

    @GetMapping(
            value = COMMANDES_CLIENT_ENDPOINT + "/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @Operation(summary = "Rechercher une commande client par son ID",
            description = "Cette méthode permet de chercher une commande client pas son identifiant"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Commande client trouvée avec succès"),
            @ApiResponse(responseCode = "404", description = "Aucune commande client trouvée avec cet identifiant")
    })
    ResponseEntity<CommandeClientDto> findById(@PathVariable("id") Integer id);

    @GetMapping(
            value = COMMANDES_CLIENT_ENDPOINT + "/code/{code}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @Operation(summary = "Rechercher une commande client par son code",
            description = "Cette méthode permet de chercher une commande client par son code",
            parameters = {
                    @Parameter(name= "X-Entreprise-Id", in = ParameterIn.HEADER, description = "ID de l'entreprise", required = false)
            }
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Commande client trouvée avec succès"),
            @ApiResponse(responseCode = "404", description = "Aucune commande client trouvée avec ce code")
    })
    ResponseEntity<CommandeClientDto> findByCode(@PathVariable("code") String code);

    @GetMapping(
            value = COMMANDES_CLIENT_ENDPOINT + "/all",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @Operation(summary = "Renvoyer la liste des commandes client",
            description = "Cette méthode permet de retourner la liste des commandes client enregistrées",
            parameters = {
                    @Parameter(name= "X-Entreprise-Id", in = ParameterIn.HEADER, description = "ID de l'entreprise", required = false)
            }
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Liste des commandes client retournée avec succès")
    })
    ResponseEntity<List<CommandeClientDto>> findAll();

    @DeleteMapping(
            value = COMMANDES_CLIENT_ENDPOINT + "/delete/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @Operation(summary = "Supprimer une commande client par son ID", description = "Cette méthode permet de supprimer une commande client par son identifiant")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Commande client supprimée avec succès")
    })
    ResponseEntity<Void> delete(@PathVariable("id") Integer id);
}
