package chijouProjects.gestion_stock_api.controller.api;

import chijouProjects.gestion_stock_api.dto.FournisseurDto;
import chijouProjects.gestion_stock_api.dto.UtilisateurDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static chijouProjects.gestion_stock_api.utils.Constants.APP_ROOT;

@Tag(name = "Utilisateurs", description = "Gestion des utilisateurs")
public interface UtilisateurApi {
    @PostMapping(value = APP_ROOT + "/utilisateurs/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Enregistrer ou modifier un utilisateur", description = "Cette méthode permet de créer ou modifier un utilisateur")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Utilisateur créé ou modifié avec succès"),
            @ApiResponse(responseCode = "400", description = "Utilisateur non valide")
    })
    ResponseEntity<UtilisateurDto> save(@RequestBody UtilisateurDto fournisseurDto);

    @GetMapping(value = APP_ROOT + "/utilisateurs/{id}" , produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Rechercher un utilisateur par son ID", description = "Cette méthode permet de chercher un utilisateur par son identifiant")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Utilisateur trouvé avec succès"),
            @ApiResponse(responseCode = "404", description = "Aucun utilisateur trouvé avec cet identifiant")
    })
    ResponseEntity<UtilisateurDto> findById(@PathVariable("id") Integer id);

    @GetMapping(value = APP_ROOT + "/utilisateurs/nom/{nom}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Rechercher un utilisateur par son nom", description = "Cette méthode permet de chercher un utilisateur par son nom")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Utilisateur trouvé avec succès"),
            @ApiResponse(responseCode = "404", description = "Aucun utilisateur trouvé avec ce nom")
    })
    ResponseEntity<UtilisateurDto> findByNom(@PathVariable("nom") String nom);

    @GetMapping(value = APP_ROOT + "/utilisateurs/all", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Renvoyer la liste des utilisateurs", description = "Cette méthode permet de retourner la liste des utilisateurs enregistrés")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Liste des utilisateurs retournée avec succès")
    })
    ResponseEntity<List<UtilisateurDto>> findAll();

    @DeleteMapping(value = APP_ROOT + "/utilisateurs/delete/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Supprimer un utilisateur par son ID", description = "Cette permet de supprimer un utilisateur par son identifiant")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Utilisateur supprimé avec succès")
    })
    ResponseEntity delete(@PathVariable("id") Integer id);
}
