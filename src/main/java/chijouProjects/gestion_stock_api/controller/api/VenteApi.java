package chijouProjects.gestion_stock_api.controller.api;

import chijouProjects.gestion_stock_api.dto.VenteDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static chijouProjects.gestion_stock_api.utils.Constants.APP_ROOT;

@Tag(name = "Ventes", description = "Gestion des ventes")
public interface VenteApi {
    @PostMapping(
            value = APP_ROOT + "/ventes/create",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @Operation(summary = "Enregistrer ou modifier une vente", description = "Cette méthode permet de créer ou modifier une vente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Vente créée ou modifiée avec succès"),
            @ApiResponse(responseCode = "400", description = "Vente non valide")
    })
    ResponseEntity<VenteDto> save(@RequestBody VenteDto venteDto);

    @GetMapping(
            value = APP_ROOT + "/ventes/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @Operation(summary = "Rechercher une vente par son ID", description = "Cette méthode permet de chercher une vente par son identifiant")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Vente trouvée avec succès"),
            @ApiResponse(responseCode = "404", description = "Aucune vente trouvée avec cet identifiant")
    })
    ResponseEntity<VenteDto> findById(@PathVariable("id") Integer id);

    @GetMapping(
            value = APP_ROOT + "/ventes/code/{code}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @Operation(summary = "Rechercher une vente par son code", description = "Cette méthode permet de chercher une vente par son code")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Vente trouvée avec succès"),
            @ApiResponse(responseCode = "404", description = "Aucune vente trouvée avec ce code")
    })
    ResponseEntity<VenteDto> findByCode(@PathVariable("code") String code);

    @GetMapping(
            value = APP_ROOT + "/ventes/all",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @Operation(summary = "Renvoyer la liste des ventes", description = "Cette méthode permet de retourner la liste des ventes enregistrées")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Liste des ventes retournée avec succès")
    })
    ResponseEntity<List<VenteDto>> findAll();

    @DeleteMapping(
            value = APP_ROOT + "/ventes/delete/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @Operation(summary = "Supprimer une vente par son ID", description = "Cette méthode permet de supprimer une vente par identifiant")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Vente supprimée avec succès")
    })
    ResponseEntity<Void> delete(@PathVariable("id") Integer id);
}
