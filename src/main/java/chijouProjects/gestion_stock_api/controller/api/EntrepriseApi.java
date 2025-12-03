package chijouProjects.gestion_stock_api.controller.api;

import chijouProjects.gestion_stock_api.dto.EntrepriseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static chijouProjects.gestion_stock_api.utils.Constants.ENTREPRISE_ENDPOINT;

@Tag(name = "Entreprises", description = "Gestion des entreprises")
public interface EntrepriseApi {
    @PostMapping(value = ENTREPRISE_ENDPOINT + "/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Enregistrer ou modifier une entreprise", description = "Cette méthode permet de créer ou de modifier une entreprise")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Entreprise créée ou modifiée avec succès"),
            @ApiResponse(responseCode = "400", description = "Entreprise non valide")
    })
    ResponseEntity<EntrepriseDto> save(@RequestBody EntrepriseDto articleDto);

    @GetMapping(value = ENTREPRISE_ENDPOINT + "/{id}" , produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Rechercher une entreprise par son ID", description = "Cette méthode permet de chercher une entreprise par son identifiant")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Entreprise trouvée avec succès"),
            @ApiResponse(responseCode = "404", description = "Aucune entreprise trouvée avec cet identifiant")
    })
    ResponseEntity<EntrepriseDto> findById(@PathVariable("id") Integer id);

    @GetMapping(value = ENTREPRISE_ENDPOINT + "/description/{description}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Rechercher une entreprise par sa description", description = "Cette méthode permet de chercher uen entreprise par sa description")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Entreprise trouvée avec succès"),
            @ApiResponse(responseCode = "404", description = "Aucune entreprise trouvée avec cette description")
    })
    ResponseEntity<EntrepriseDto> findByDescription(@PathVariable("description") String description);

    @GetMapping(value = ENTREPRISE_ENDPOINT + "/codefiscal/{codefiscal}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Rechercher une entreprise par son code fiscal", description = "Cette méthode permet de chercher une entreprise par son code fiscal")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Entreprise trouvée avec succès"),
            @ApiResponse(responseCode = "404", description = "Aucune entreprise trouvée avec ce code fiscal")
    })
    ResponseEntity<EntrepriseDto> findByCodefiscal(@PathVariable("codefiscal") String codeFiscal);

    @GetMapping(value = ENTREPRISE_ENDPOINT + "/all", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Renvoyer la liste des entreprises", description = "Cette méthode permet de retourner la liste des entreprises enregistrées")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Liste des entreprises retournée avec succès")
    })
    ResponseEntity<List<EntrepriseDto>> findAll();

    @DeleteMapping(value = ENTREPRISE_ENDPOINT + "/delete/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Supprimer une entreprise par son ID", description = "Cette méthode permet de supprimer une entreprise par son identifiant")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Entreprise supprimée avec succès")
    })
    ResponseEntity<Void> delete(@PathVariable("id") Integer id);
}
