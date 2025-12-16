package chijouProjects.gestion_stock_api.controller.api;

import chijouProjects.gestion_stock_api.dto.CommandeFournisseurDto;
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

import static chijouProjects.gestion_stock_api.utils.Constants.COMMANDES_FOURNISSEUR_ENDPOINT;

@Tag(name = "Commandes fournisseur", description = "Gestion des commandes fournisseur")
public interface CommandeFournisseurApi {
    @PostMapping(
            value = COMMANDES_FOURNISSEUR_ENDPOINT + "/create",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @Operation(summary = "Enregistrer ou modifier une commande fournisseur",
            description = "Cette méthode permet de créer ou de modifier une commande fournisseur"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Commande fournisseur créée ou modifiée avec succès"),
            @ApiResponse(responseCode = "400", description = "Commande fournisseur non valide")
    })
    ResponseEntity<CommandeFournisseurDto> save(@RequestBody CommandeFournisseurDto commandeFournisseurDto);

    @GetMapping(
            value = COMMANDES_FOURNISSEUR_ENDPOINT + "/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @Operation(summary = "Rechercher une commande fournisseur par son ID",
            description = "Cette méthode permet de chercher une commande fournisseur par son identifiant"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Commande fournisseur trouvée avec succès"),
            @ApiResponse(responseCode = "404", description = "Aucune commande fournisseur trouvée avec cet identifiant")
    })
    ResponseEntity<CommandeFournisseurDto> findById(@PathVariable("id") Integer id);

    @GetMapping(
            value = COMMANDES_FOURNISSEUR_ENDPOINT + "/code/{code}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @Operation(summary = "Rechercher une commande fournisseur par son code",
            description = "Cette méthode permet de chercher la commande fournisseur par son code",
            parameters = {
                    @Parameter(name= "X-Entreprise-Id", in = ParameterIn.HEADER, description = "ID de l'entreprise", required = false)
            }
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Commande fournisseur trouvée avec succès"),
            @ApiResponse(responseCode = "404", description = "Aucune commande fournisseur trouvée avec ce code")
    })
    ResponseEntity<CommandeFournisseurDto> findByCode(@PathVariable String code);

    @GetMapping(
            value = COMMANDES_FOURNISSEUR_ENDPOINT + "/all",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @Operation(summary = "Renvoyer la liste des commandes fournisseur",
            description = "Cette méthode permet de retourner les commandes fournisseur enregistrées",
            parameters = {
                    @Parameter(name= "X-Entreprise-Id", in = ParameterIn.HEADER, description = "ID de l'entreprise", required = false)
            }
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Liste des commandes fournisseur retournée avec succès")
    })
    ResponseEntity<List<CommandeFournisseurDto>> findAll();

    @DeleteMapping(
            value = COMMANDES_FOURNISSEUR_ENDPOINT + "/delete/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @Operation(summary = "Supprimer une commande fournisseur par son ID", description = "Cette méthode permet de supprimer une commande fournisseur par son identifiant")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Commande fournisseur supprimée avec succès")
    })
    ResponseEntity<Void> delete(@PathVariable Integer id);
}
