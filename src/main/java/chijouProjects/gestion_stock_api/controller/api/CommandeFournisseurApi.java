package chijouProjects.gestion_stock_api.controller.api;

import chijouProjects.gestion_stock_api.dto.CommandeClientDto;
import chijouProjects.gestion_stock_api.dto.CommandeFournisseurDto;
import chijouProjects.gestion_stock_api.dto.LigneCdeCltDto;
import chijouProjects.gestion_stock_api.dto.LigneCdeFournisseurDto;
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
import static chijouProjects.gestion_stock_api.utils.Constants.COMMANDES_FOURNISSEUR_ENDPOINT;

@Tag(name = "Commandes fournisseur", description = "Gestion des commandes fournisseur")
public interface CommandeFournisseurApi {
    @PostMapping(
            value = COMMANDES_FOURNISSEUR_ENDPOINT + "/create",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @Operation(summary = "Enregistrer ou modifier une commande fournisseur",
            description = "Cette méthode permet de créer ou de modifier une commande fournisseur"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Commande fournisseur créée ou modifiée avec succès"),
            @ApiResponse(responseCode = "400", description = "Commande fournisseur non valide")
    })
    ResponseEntity<CommandeFournisseurDto> save(@RequestBody CommandeFournisseurDto commandeFournisseurDto);

    @PatchMapping(
            value = COMMANDES_FOURNISSEUR_ENDPOINT + "/update/etat/{idCommande}/{etatCommande}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @Operation(
            summary = "Modifier l'état d'une commande fournisseur",
            description = "Cette méthode permet de modifier l'état d'une commande fournisseur"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "État de la commande modifié avec succès"),
            @ApiResponse(responseCode = "400", description = "Requête invalide ou état non autorisé"),
            @ApiResponse(responseCode = "404", description = "Commande fournisseur introuvable"),
            @ApiResponse(responseCode = "409", description = "Conflit : transition d'état impossible")
    })
    ResponseEntity<CommandeFournisseurDto> updateEtatCommande(@PathVariable("idCommande") Integer idCommande, @PathVariable("etatCommande") EtatCommande etatCommande);

    @PatchMapping(
            value = COMMANDES_FOURNISSEUR_ENDPOINT + "/update/quantite/{idCommande}/{idLigneCommande}/{quantite}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @Operation(
            summary = "Modifier la quantité d'articles d'une ligne de commande fournisseur",
            description = "Cette méthode permet de modifier la quantité d'articles d'une ligne de commande fournisseur"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Quantité d'articles d'une ligne de commande fournisseur modifiée avec succès"),
            @ApiResponse(responseCode = "400", description = "Requête invalide ou quantité non acceptée"),
            @ApiResponse(responseCode = "404", description = "Commande ou ligne de commande fournisseur introuvable")
    })
    ResponseEntity<CommandeFournisseurDto> updateQuantiteCommande(@PathVariable("idCommande") Integer idCommande, @PathVariable("idLigneCommande") Integer idLigneCommande, @PathVariable("quantite") BigDecimal quantite);

    @PatchMapping(
            value = COMMANDES_FOURNISSEUR_ENDPOINT + "/update/fournisseur/{idCommande}/{idFournisseur}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @Operation(
            summary = "Modifier le fournisseur d'une commande",
            description = "Cette méthode permet de modifier le fournisseur d'une commande"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Fournisseur de la commande modifié avec succès"),
            @ApiResponse(responseCode = "400", description = "Requête invalide ou fournisseur inexistant"),
            @ApiResponse(responseCode = "404", description = "Commande ou fournisseur introuvable")
    })
    ResponseEntity<CommandeFournisseurDto> updateFournisseur(@PathVariable("idCommande") Integer idCommande, @PathVariable("idFournisseur") Integer idFournisseur);

    @PatchMapping(
            value = COMMANDES_FOURNISSEUR_ENDPOINT + "/update/article/{idCommande}/{idLigneCommande}/{idArticle}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @Operation(
            summary = "Modifier l'article d'une ligne de commande fournisseur",
            description = "Cette méthode permet de modifier l'article d'une ligne de commande fournisseur"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Article de la ligne de commande modifié avec succès"),
            @ApiResponse(responseCode = "400", description = "Requête invalide ou article inexistant"),
            @ApiResponse(responseCode = "404", description = "Ligne de commande ou article introuvable")
    })
    ResponseEntity<CommandeFournisseurDto> updateArticle(@PathVariable("idCommande") Integer idCommande, @PathVariable("idLigneCommande") Integer idLigneCommande, @PathVariable("idArticle") Integer idArticle);

    @DeleteMapping(
            value = COMMANDES_FOURNISSEUR_ENDPOINT + "/delete/article/{idCommande}/{idLigneCommande}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @Operation(summary = "Supprimer une ligne de commande fournisseur", description = "Cette méthode permet de supprimer une ligne de commande fournisseur")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ligne de commande fournisseur supprimée avec succès")
    })
    ResponseEntity<CommandeFournisseurDto> deleteArticle(@PathVariable("idCommande") Integer idCommande, @PathVariable("idLigneCommande") Integer idLigneCommande);

    @GetMapping(
            value = COMMANDES_FOURNISSEUR_ENDPOINT + "/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @Operation(summary = "Rechercher une commande fournisseur par son ID",
            description = "Cette méthode permet de chercher une commande fournisseur par son identifiant"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Commande fournisseur trouvée avec succès"),
            @ApiResponse(responseCode = "404", description = "Aucune commande fournisseur trouvée avec cet identifiant")
    })
    ResponseEntity<CommandeFournisseurDto> findById(@PathVariable("id") Integer id);

    @GetMapping(
            value = COMMANDES_FOURNISSEUR_ENDPOINT + "/code/{code}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @Operation(summary = "Rechercher une commande fournisseur par son code",
            description = "Cette méthode permet de chercher la commande fournisseur par son code",
            parameters = {
                    @Parameter(name= "X-Entreprise-Id", in = ParameterIn.HEADER, description = "ID de l'entreprise", required = false)
            }
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Commande fournisseur trouvée avec succès"),
            @ApiResponse(responseCode = "404", description = "Aucune commande fournisseur trouvée avec ce code")
    })
    ResponseEntity<CommandeFournisseurDto> findByCode(@PathVariable String code);

    @GetMapping(
            value = COMMANDES_FOURNISSEUR_ENDPOINT + "/all",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @Operation(summary = "Renvoyer la liste des commandes fournisseur",
            description = "Cette méthode permet de retourner les commandes fournisseur enregistrées",
            parameters = {
                    @Parameter(name= "X-Entreprise-Id", in = ParameterIn.HEADER, description = "ID de l'entreprise", required = false)
            }
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Liste des commandes fournisseur retournée avec succès")
    })
    ResponseEntity<List<CommandeFournisseurDto>> findAll();

    @GetMapping(
            value = COMMANDES_FOURNISSEUR_ENDPOINT + "/lignesCommande/{idCommande}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @Operation(summary = "Renvoyer les lignes d'une commande fournisseur",
            description = "Cette méthode permet de retourner les lignes d'une commande fournisseur enregistrées",
            parameters = {
                    @Parameter(name= "X-Entreprise-Id", in = ParameterIn.HEADER, description = "ID de l'entreprise", required = false)
            }
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lignes de commande fournisseur retournées avec succès")
    })
    ResponseEntity<List<LigneCdeFournisseurDto>> findAllLignesCommandesFournisseursByCommandeFournisseur(@PathVariable("idCommande") Integer idCommande);

    @DeleteMapping(
            value = COMMANDES_FOURNISSEUR_ENDPOINT + "/delete/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @Operation(summary = "Supprimer une commande fournisseur par son ID", description = "Cette méthode permet de supprimer une commande fournisseur par son identifiant")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Commande fournisseur supprimée avec succès")
    })
    ResponseEntity<Void> delete(@PathVariable Integer id);
}
