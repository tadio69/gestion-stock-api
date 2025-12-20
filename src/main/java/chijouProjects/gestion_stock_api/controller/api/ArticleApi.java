package chijouProjects.gestion_stock_api.controller.api;

import chijouProjects.gestion_stock_api.dto.ArticleDto;
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

import static chijouProjects.gestion_stock_api.utils.Constants.ARTICLE_ENDPOINT;
import static chijouProjects.gestion_stock_api.utils.Constants.CATEGORIE_ENDPOINT;

@Tag(name = "Articles", description = "Gestion des articles")
public interface ArticleApi {
    @PostMapping(
            value = ARTICLE_ENDPOINT + "/create",
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
    ResponseEntity<ArticleDto> save(@RequestBody ArticleDto articleDto);

    @GetMapping(
            value = ARTICLE_ENDPOINT + "/{id}",
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
    ResponseEntity<ArticleDto> findById(@PathVariable("id") Integer id);

    @GetMapping(
            value = ARTICLE_ENDPOINT + "/code/{code}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @Operation(
            summary = "Rechercher un article par son code",
            description = "Cette méthode permet de chercher un article par son code",
            parameters = {
                    @Parameter(name= "X-Entreprise-Id", in = ParameterIn.HEADER, description = "ID de l'entreprise", required = false)
            }
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Article trouvé avec succès"),
            @ApiResponse(responseCode = "404", description = "Aucun article trouvé pour ce code")
    })
    ResponseEntity<ArticleDto> findByCode(@PathVariable("code") String codeArticle);

    @GetMapping(value = ARTICLE_ENDPOINT + "/designation/{designation}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Rechercher un article par sa désignation",
            description = "Cette méthode permet de chercher un article par sa désignation",
            parameters = {
                    @Parameter(name= "X-Entreprise-Id", in = ParameterIn.HEADER, description = "ID de l'entreprise", required = false)
            }
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Article trouvé avec succès"),
            @ApiResponse(responseCode = "404", description = "Aucun article trouvé avec cette désignation")
    })
    ResponseEntity<ArticleDto> findByDesignation(@PathVariable("designation") String designation);

    @GetMapping(
            value = ARTICLE_ENDPOINT + "/all",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @Operation(
            summary = "Renvoyer la liste des articles",
            description = "Cette méthode retourne la liste des articles enregistrés",
            parameters = {
                    @Parameter(name= "X-Entreprise-Id", in = ParameterIn.HEADER, description = "ID de l'entreprise", required = false)
            }
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Liste des articles retournée avec succès")
    })
    ResponseEntity<List<ArticleDto>> findAll();

    @DeleteMapping(
            value = ARTICLE_ENDPOINT + "/delete/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @Operation(
            summary = "Supprimer un article par son ID",
            description = "Cette méthode permet de supprimer un article par son identifiant"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Article supprimé avec succès")
    })
    ResponseEntity<Void> delete(@PathVariable("id") Integer id);
}
