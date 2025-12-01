package chijouProjects.gestion_stock_api.controller.api;

import chijouProjects.gestion_stock_api.dto.CategorieDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static chijouProjects.gestion_stock_api.utils.Constants.APP_ROOT;

@Tag(name = "Catégories", description = "Gestion des catégories")
public interface CategorieApi {
    @PostMapping(value = APP_ROOT + "/categories/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Enregistrer ou modifier une catégorie", description = "Cette méthode permet d'enregistrer ou de modifier une catégorie")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Catégorie créée ou modifiée avec succès"),
            @ApiResponse(responseCode = "400", description = "Catégorie non valide")
    })
    ResponseEntity<CategorieDto> save(@RequestBody CategorieDto categorieDto);

    @GetMapping(value = APP_ROOT + "/categories/{id}" , produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Rechercher une catégorie par son ID", description = "Cette méthode permet de chercher une catégorie via son identifiant")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Catégorie trouvée avec succès"),
            @ApiResponse(responseCode = "404", description = "Aucune catégorie trouvée pour cet ID")
    })
    ResponseEntity<CategorieDto> findById(@PathVariable("id") Integer id);

    @GetMapping(value = APP_ROOT + "/categories/designation/{designation}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Rechercher une catégorie par sa désignation", description = "Cette méthode permet de chercher une catégorie par sa désignation")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Catégorie trouvée avec succès"),
            @ApiResponse(responseCode = "404", description = "Aucune catégorie trouvée avec cette désignation")
    })
    ResponseEntity<CategorieDto> findByDesignation(@PathVariable("designation") String designation);

    @GetMapping(value = APP_ROOT + "/categories/code/{code}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Rechercher une catégorie par son code", description = "Cette méthode permet de chercher une catégorie par son code")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Catégorie trouvée avec succès"),
            @ApiResponse(responseCode = "404", description = "Aucune catégorie trouvée avec ce code")
    })
    ResponseEntity<CategorieDto> findByCode(@PathVariable("code") String code);

    @GetMapping(value = APP_ROOT + "/categories/all", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Renvoyer la liste des catégories", description = "Cette méthode retourne la liste des catégories enregistrées")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Liste des catégories retournées avec succès")
    })
    ResponseEntity<List<CategorieDto>> findAll();

    @DeleteMapping(value = APP_ROOT + "/categories/delete/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Supprimer une catégorie par son ID", description = "Cette méthode permet de supprimer une catégorie par son identifiant")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Catégorie supprimée avec succès")
    })
    ResponseEntity delete(@PathVariable("id") Integer id);
}
