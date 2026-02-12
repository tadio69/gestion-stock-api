package chijouProjects.gestion_stock_api.controller.api;

import chijouProjects.gestion_stock_api.dto.CommandeClientDto;
import chijouProjects.gestion_stock_api.model.EtatCommande;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

import static chijouProjects.gestion_stock_api.utils.Constants.COMMANDES_CLIENT_ENDPOINT;

@Tag(name = "Commandes client", description = "Gestion des commandes client")
public interface CommandeClientApi {
    @PostMapping(
            value = COMMANDES_CLIENT_ENDPOINT + "/create",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @Operation(
            summary = "Enregistrer ou modifier une commande client",
            description = "Cette méthode permet d'enregistrer ou modifier une commande client"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Commande client créée ou modifiée avec succès"),
            @ApiResponse(responseCode = "400", description = "Commande client non valide")
    })
    ResponseEntity<CommandeClientDto> save(@RequestBody CommandeClientDto commandeClientDto);

    @PatchMapping(
            value = COMMANDES_CLIENT_ENDPOINT + "/update/etat/{idCommande}/{etatCommande}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @Operation(
            summary = "Modifier l'état d'une commande client",
            description = "Cette méthode permet de modifier l'état d'une commande client"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "État de la commande modifié avec succès"),
            @ApiResponse(responseCode = "400", description = "Requête invalide ou état non autorisé"),
            @ApiResponse(responseCode = "404", description = "Commande client introuvable"),
            @ApiResponse(responseCode = "409", description = "Conflit : transition d'état impossible")
    })
    ResponseEntity<CommandeClientDto> updateEtatCommande(@PathVariable("idCommande") Integer idCommande, @PathVariable("etatCommande") EtatCommande etatCommande);

    @PatchMapping(
            value = COMMANDES_CLIENT_ENDPOINT + "/update/quantite/{idCommande}/{idLigneCommande}/{quantite}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @Operation(
            summary = "Modifier la quantité d'articles d'une ligne de commande client",
            description = "Cette méthode permet de modifier la quantité d'articles d'une ligne de commande client"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Quantité d'articles d'une ligne de commande client modifiée avec succès"),
            @ApiResponse(responseCode = "400", description = "Requête invalide ou quantité non acceptée"),
            @ApiResponse(responseCode = "404", description = "Commande ou ligne de commande client introuvable")
    })
    ResponseEntity<CommandeClientDto> updateQuantiteCommande(@PathVariable("idCommande") Integer idCommande, @PathVariable("idLigneCommande") Integer idLigneCommande, @PathVariable("quantite") BigDecimal quantite);

    @PatchMapping(
            value = COMMANDES_CLIENT_ENDPOINT + "/update/client/{idCommande}/{idClient}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @Operation(
            summary = "Modifier le client d'une commande",
            description = "Cette méthode permet de modifier le client d'une commande"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Client de la commande modifié avec succès"),
            @ApiResponse(responseCode = "400", description = "Requête invalide ou client inexistant"),
            @ApiResponse(responseCode = "404", description = "Commande ou client introuvable")
    })
    ResponseEntity<CommandeClientDto> updateClient(@PathVariable("idCommande") Integer idCommande, @PathVariable("idClient") Integer idClient);

    @PatchMapping(
            value = COMMANDES_CLIENT_ENDPOINT + "/update/article/{idCommande}/{idLigneCommande}/{idArticle}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @Operation(
            summary = "Modifier l'article d'une ligne de commande",
            description = "Cette méthode permet de modifier l'article d'une ligne de commande"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Article de la ligne de commande modifié avec succès"),
            @ApiResponse(responseCode = "400", description = "Requête invalide ou article inexistant"),
            @ApiResponse(responseCode = "404", description = "Ligne de commande ou article introuvable")
    })
    ResponseEntity<CommandeClientDto> updateArticle(@PathVariable("idCommande") Integer idCommande, @PathVariable("idLigneCommande") Integer idLigneCommande, @PathVariable("idArticle") Integer idArticle);

    @GetMapping(
            value = COMMANDES_CLIENT_ENDPOINT + "/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @Operation(summary = "Rechercher une commande client par son ID",
            description = "Cette méthode permet de chercher une commande client pas son identifiant"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Commande client trouvée avec succès"),
            @ApiResponse(responseCode = "404", description = "Aucune commande client trouvée avec cet identifiant")
    })
    ResponseEntity<CommandeClientDto> findById(@PathVariable("id") Integer id);

    @GetMapping(
            value = COMMANDES_CLIENT_ENDPOINT + "/code/{code}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @Operation(summary = "Rechercher une commande client par son code",
            description = "Cette méthode permet de chercher une commande client par son code",
            parameters = {
                    @Parameter(name= "X-Entreprise-Id", in = ParameterIn.HEADER, description = "ID de l'entreprise", required = false)
            }
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Commande client trouvée avec succès"),
            @ApiResponse(responseCode = "404", description = "Aucune commande client trouvée avec ce code")
    })
    ResponseEntity<CommandeClientDto> findByCode(@PathVariable("code") String code);

    @GetMapping(
            value = COMMANDES_CLIENT_ENDPOINT + "/all",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @Operation(summary = "Renvoyer la liste des commandes client",
            description = "Cette méthode permet de retourner la liste des commandes client enregistrées",
            parameters = {
                    @Parameter(name= "X-Entreprise-Id", in = ParameterIn.HEADER, description = "ID de l'entreprise", required = false)
            }
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Liste des commandes client retournée avec succès")
    })
    ResponseEntity<List<CommandeClientDto>> findAll();

    @DeleteMapping(
            value = COMMANDES_CLIENT_ENDPOINT + "/delete/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @Operation(summary = "Supprimer une commande client par son ID", description = "Cette méthode permet de supprimer une commande client par son identifiant")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Commande client supprimée avec succès")
    })
    ResponseEntity<Void> delete(@PathVariable("id") Integer id);
}
