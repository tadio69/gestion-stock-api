package chijouProjects.gestion_stock_api.controller.api;

import chijouProjects.gestion_stock_api.dto.MvtStockDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static chijouProjects.gestion_stock_api.utils.Constants.MVTSTOCK_ENDPOINT;

@Tag(name = "MvtStocks", description = "Gestion des mouvements de stock")
public interface MvtStockApi {
    @PostMapping(
            value = MVTSTOCK_ENDPOINT + "/create",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @Operation(summary = "Enregistrer ou modifier un mvtstock", description = "Cette méthode permet de créer ou de modifier un mouvement de stock")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Mouvement de stock créé ou modifié avec succès"),
            @ApiResponse(responseCode = "400", description = "Mouvement de stock non valide")
    })
    ResponseEntity<MvtStockDto> save(@RequestBody MvtStockDto mvtStockDto);

    @GetMapping(
            value = MVTSTOCK_ENDPOINT + "/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @Operation(summary = "Rechercher un mvtstock par son ID", description = "Cette méthode permet de chercher un mouvement de stock par identifiant")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Mouvement de stock trouvé avec succès"),
            @ApiResponse(responseCode = "404", description = "Aucun mouvement de stock trouvé avec cet identifiant")
    })
    ResponseEntity<MvtStockDto> findById(@PathVariable("id") Integer id);

    @GetMapping(
            value = MVTSTOCK_ENDPOINT + "/all",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @Operation(summary = "Renvoyer la liste des mvtstocks", description = "Cette méthode permet de retourner la liste des mouvements de stock")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Liste des mouvements de stock retournée avec succès")
    })
    ResponseEntity<List<MvtStockDto>> findAll();

    @DeleteMapping(
            value = MVTSTOCK_ENDPOINT + "/delete/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @Operation(summary = "Supprimer un mvtstock par son ID", description = "Cette méthode permet de supprimer un mouvement de stock")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Mouvement de stock supprimé avec succès")
    })
    ResponseEntity<Void> delete(@PathVariable("id") Integer id);
}
