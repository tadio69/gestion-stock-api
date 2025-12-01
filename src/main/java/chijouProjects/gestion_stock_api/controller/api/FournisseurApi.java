package chijouProjects.gestion_stock_api.controller.api;

import chijouProjects.gestion_stock_api.dto.ClientDto;
import chijouProjects.gestion_stock_api.dto.FournisseurDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import static chijouProjects.gestion_stock_api.utils.Constants.APP_ROOT;

@Tag(name = "Fournisseur", description = "Gestion des fournissuers")
public interface FournisseurApi {
    @PostMapping(value = APP_ROOT + "/fournisseurs/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Enregistrer ou modifier un fournisseur", description = "Cette méthode permet de créer ou de modifier un fournisseur")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Entreprise créée ou modifiée avec succès"),
            @ApiResponse(responseCode = "400", description = "Fournisseur non valide")
    })
    ResponseEntity<FournisseurDto> save(@RequestBody FournisseurDto fournisseurDto);

    @GetMapping(value = APP_ROOT + "/fournisseurs/{id}" , produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Rechercher un fourniseur par ID", description = "Cette méthode permet de chercher un fournisseur par son identifiant")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Fournisseur trouvé avec succès"),
            @ApiResponse(responseCode = "404", description = "Aucun fournisseur trouvé avec cet identifiant")
    })
    ResponseEntity<FournisseurDto> findById(@PathVariable("id") Integer id);

    @GetMapping(value = APP_ROOT + "/fournisseurs/nom/{nom}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Rechercher un fournisseur par son nom", description = "Cette méthode permet de chercher un fournisseur par son nom")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Fournisseur trouvé avec succès"),
            @ApiResponse(responseCode = "404", description = "Aucun fournisseur trouvé avec ce nom")
    })
    ResponseEntity<FournisseurDto> findByNom(@PathVariable("nom") String nom);

    @GetMapping(value = APP_ROOT + "/fournisseurs/all", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Renvoyer la liste des fournisseurs", description = "Cette méthode permet de retourner la liste des fournisseurs enregistrés")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Liste des fournisseurs retournée avec succès")
    })
    ResponseEntity<List<FournisseurDto>> findAll();

    @DeleteMapping(value = APP_ROOT + "/fournisseurs/delete/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Supprimer un fournisseur par son ID", description = "Cette méthode permet de supprimer un fournisseur par son identifiant")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Fournisseur supprimé avec succès")
    })
    ResponseEntity delete(@PathVariable("id") Integer id);
}
