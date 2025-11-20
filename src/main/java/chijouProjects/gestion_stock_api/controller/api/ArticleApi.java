package chijouProjects.gestion_stock_api.controller.api;

import chijouProjects.gestion_stock_api.dto.ArticleDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static chijouProjects.gestion_stock_api.utils.Constants.APP_ROOT;

@Tag(name = "Articles", description = "API de gestion des articles")
public interface ArticleApi {

    @PostMapping(
            value = APP_ROOT + "/articles/create",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @Operation(
            summary = "Enregistrer ou modifier un article",
            description = "Cette méthode permet d'enregistrer ou modifier un article"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Article créé ou modifié avec succès"),
            @ApiResponse(responseCode = "400", description = "Article non valide")
    })
    ArticleDto save(@RequestBody ArticleDto articleDto);

    @GetMapping(
            value = APP_ROOT + "/articles/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @Operation(
            summary = "Rechercher un article par ID",
            description = "Cette méthode permet de chercher un article via son identifiant"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Article trouvé avec succès"),
            @ApiResponse(responseCode = "404", description = "Aucun article trouvé pour cet ID")
    })
    ArticleDto findById(@PathVariable("id") Integer id);

    @GetMapping(
            value = APP_ROOT + "/articles/code/{code}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @Operation(
            summary = "Rechercher un article par code",
            description = "Cette méthode permet de chercher un article via son code"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Article trouvé avec succès"),
            @ApiResponse(responseCode = "404", description = "Aucun article trouvé pour ce code")
    })
    ArticleDto findByCode(@PathVariable("code") String codeArticle);

    @GetMapping(
            value = APP_ROOT + "/articles/all",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @Operation(
            summary = "Renvoyer la liste des articles",
            description = "Cette méthode retourne la liste des articles enregistrés"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Liste des articles retournée")
    })
    List<ArticleDto> findAll();

    @DeleteMapping(
            value = APP_ROOT + "/articles/delete/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @Operation(
            summary = "Supprimer un article",
            description = "Cette méthode supprime un article par son identifiant"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Article supprimé")
    })
    void delete(@PathVariable("id") Integer id);
}
