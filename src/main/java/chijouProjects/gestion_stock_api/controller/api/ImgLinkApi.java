package chijouProjects.gestion_stock_api.controller.api;

import chijouProjects.gestion_stock_api.dto.ImageUploadRequest;
import chijouProjects.gestion_stock_api.dto.ImgLinkDto;
import chijouProjects.gestion_stock_api.model.Entreprise;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

import static chijouProjects.gestion_stock_api.utils.Constants.PHOTOS_ENDPOINT;

/*@Tag(name = "Imglink", description = "Gestion des Imglinks (données relatives aux photos)")
public interface ImgLinkApi {
    @PostMapping(value = PHOTOS_ENDPOINT + "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Operation(summary = "Téléverser une photo",
            description = "Cette méthode permet de téléverser une photo dans ImgLink"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Photo téléversée avec succès"),
            @ApiResponse(responseCode = "400", description = "Données de photo non valides")
    })
    ResponseEntity<ImgLinkDto> uploadImage(
            @RequestParam("file") MultipartFile file,
            @RequestParam(value = "identreprise") Entreprise entreprise
    );

    @GetMapping(value = PHOTOS_ENDPOINT + "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Rechercher un Imglink par son ID",
            description = "Cette méthode permet de chercher un Imglink par son identifiant"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Imglink trouvé avec succès"),
            @ApiResponse(responseCode = "404", description = "Aucun Imglink trouvé avec cet identifiant")
    })
    ResponseEntity<ImgLinkDto> findById(@PathVariable("id") Integer id);

    @GetMapping(value = PHOTOS_ENDPOINT + "/all", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Lister tous les Imglinks",
            description = "Cette méthode permet de retourner la liste des imglinks",
            parameters = {
                    @Parameter(name= "X-Entreprise-Id", in = ParameterIn.HEADER, description = "ID de l'entreprise", required = false)
            }
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Liste des imglinks retournée avec succès")
    })
    ResponseEntity<List<ImgLinkDto>> findAll();

    @DeleteMapping(value = PHOTOS_ENDPOINT + "/delete/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Supprimer un Imglink par son ID", description = "Cette méthode permet de supprimer un imglink par son identifiant")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Imglink supprimé avec succès")
    })
    ResponseEntity<Void> delete(@PathVariable("id") Integer id);
}*/

@Tag(name = "Imglink", description = "Gestion des Imglinks (données relatives aux photos)")
public interface ImgLinkApi {

    @PostMapping(value = "/api/photos/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Operation(summary = "Téléverser une photo",
            description = "Cette méthode permet de téléverser une photo vers ImgLink")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Photo téléversée avec succès"),
            @ApiResponse(responseCode = "400", description = "Données de photo non valides")
    })
    ResponseEntity<ImgLinkDto> uploadImage(@Valid @ModelAttribute ImageUploadRequest request);

    @GetMapping(value = "/api/photos/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Rechercher un Imglink par son ID",
            description = "Cette méthode permet de chercher un Imglink par son identifiant")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Imglink trouvé avec succès"),
            @ApiResponse(responseCode = "404", description = "Aucun Imglink trouvé avec cet identifiant")
    })
    ResponseEntity<ImgLinkDto> findById(@PathVariable("id") Integer id);

    @GetMapping(value = "/api/photos/all", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Lister tous les Imglinks",
            description = "Cette méthode permet de retourner la liste des imglinks",
            parameters = {
                    @Parameter(name= "X-Entreprise-Id", in = ParameterIn.HEADER, description = "ID de l'entreprise", required = false)
            })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Liste des imglinks retournée avec succès")
    })
    ResponseEntity<List<ImgLinkDto>> findAll();

    @DeleteMapping(value = "/api/photos/delete/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Supprimer un Imglink par son ID",
            description = "Cette méthode permet de supprimer un Imglink par son identifiant")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Imglink supprimé avec succès")
    })
    ResponseEntity<Void> delete(@PathVariable("id") Integer id);
}
