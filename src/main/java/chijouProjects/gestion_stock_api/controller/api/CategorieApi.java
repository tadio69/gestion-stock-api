package chijouProjects.gestion_stock_api.controller.api;

import chijouProjects.gestion_stock_api.dto.CategorieDto;
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

import static chijouProjects.gestion_stock_api.utils.Constants.CATEGORIE_ENDPOINT;

@Tag(name = "Catégories", description = "Gestion des catégories")
public interface CategorieApi {
    @PostMapping(value = CATEGORIE_ENDPOINT + "/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Enregistrer ou modifier une catégorie", description = "Cette méthode permet d'enregistrer ou de modifier une catégorie")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Catégorie créée ou modifiée avec succès"),
            @ApiResponse(responseCode = "400", description = "Catégorie non valide")
    })
    ResponseEntity<CategorieDto> save(@RequestBody CategorieDto categorieDto);

    @GetMapping(value = CATEGORIE_ENDPOINT + "/{id}" , produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Rechercher une catégorie par son ID", description = "Cette méthode permet de chercher une catégorie via son identifiant")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Catégorie trouvée avec succès"),
            @ApiResponse(responseCode = "404", description = "Aucune catégorie trouvée pour cet ID")
    })
    ResponseEntity<CategorieDto> findById(@PathVariable("id") Integer id);

    @GetMapping(value = CATEGORIE_ENDPOINT + "/designation/{designation}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Rechercher une catégorie par sa désignation", description = "Cette méthode permet de chercher une catégorie par sa désignation")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Catégorie trouvée avec succès"),
            @ApiResponse(responseCode = "404", description = "Aucune catégorie trouvée avec cette désignation")
    })
    ResponseEntity<CategorieDto> findByDesignation(@PathVariable("designation") String designation);

    @GetMapping(value = CATEGORIE_ENDPOINT + "/code/{code}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Rechercher une catégorie par son code", description = "Cette méthode permet de chercher une catégorie par son code")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Catégorie trouvée avec succès"),
            @ApiResponse(responseCode = "404", description = "Aucune catégorie trouvée avec ce code")
    })
    ResponseEntity<CategorieDto> findByCode(@PathVariable("code") String code);

    @GetMapping(value = CATEGORIE_ENDPOINT + "/all", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Renvoyer la liste des catégories",
                description = "Cette méthode retourne la liste des catégories enregistrées",
                parameters = {
                    @Parameter(name= "X-Entreprise-Id", in = ParameterIn.HEADER, description = "ID de l'entreprise", required = false)
                }
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Liste des catégories retournées avec succès")
    })
    ResponseEntity<List<CategorieDto>> findAll();

    @DeleteMapping(value = CATEGORIE_ENDPOINT + "/delete/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Supprimer une catégorie par son ID", description = "Cette méthode permet de supprimer une catégorie par son identifiant")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Catégorie supprimée avec succès")
    })
    ResponseEntity<Void> delete(@PathVariable("id") Integer id);
}
