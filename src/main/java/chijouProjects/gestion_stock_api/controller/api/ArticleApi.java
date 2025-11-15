package chijouProjects.gestion_stock_api.controller.api;

import chijouProjects.gestion_stock_api.dto.ArticleDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static chijouProjects.gestion_stock_api.utils.Constants.APP_ROOT;

@Api(APP_ROOT + "/articles")
public interface ArticleApi {

    @PostMapping(value = APP_ROOT + "/articles/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Enregistrer un article", notes = "Cette méthode permet d'enregistrer ou de modifier un article", response = ArticleDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Article créé/modifié avec succès"),
            @ApiResponse(code = 400, message = "Article non valide")
    })
    ArticleDto save(@RequestBody ArticleDto articleDto);

    @GetMapping(value = APP_ROOT + "/articles/{id}" , produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Rechercher un article", notes = "Cette méthode permet de chercher un article par son id", response = ArticleDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Article trouvé avec succès dans la BDD"),
            @ApiResponse(code = 404, message = "Aucun article n'existe dans la BDD avec l'id fourni")
    })
    ArticleDto findById(@PathVariable("id") Integer id);

    @GetMapping(value = APP_ROOT + "/articles/{code}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Rechercher un article", notes = "Cette méthode permet de chercher un article par son code", response = ArticleDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Article trouvé avec succès dans la BDD"),
            @ApiResponse(code = 404, message = "Aucun article n'existe dans la BDD avec le code fourni")
    })
    ArticleDto findByCode(@PathVariable("code") String codeArticle);

    @GetMapping(value = APP_ROOT + "/articles/all", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Renvoyer la liste des articles", notes = "Cette méthode permet de chercher et renvoyer la liste de tous les articles de la BDD", responseContainer = "List<ArticleDto>")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Liste des articles / liste vide")
    })
    List<ArticleDto> findAll();

    @DeleteMapping(value = APP_ROOT + "/articles/delete/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Supprimer un article", notes = "Cette méthode permet de supprimer un article par son id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Article supprimé avec succès de la BDD"),
    })
    void delete(@PathVariable("id") Integer id);
}
