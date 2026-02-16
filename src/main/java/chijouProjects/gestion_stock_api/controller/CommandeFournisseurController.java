package chijouProjects.gestion_stock_api.controller;

import chijouProjects.gestion_stock_api.controller.api.CommandeFournisseurApi;
import chijouProjects.gestion_stock_api.dto.CommandeFournisseurDto;
import chijouProjects.gestion_stock_api.dto.LigneCdeFournisseurDto;
import chijouProjects.gestion_stock_api.exception.EntityNotFoundException;
import chijouProjects.gestion_stock_api.model.EtatCommande;
import chijouProjects.gestion_stock_api.service.CommandeFournisseurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;

@RestController
public class CommandeFournisseurController implements CommandeFournisseurApi {
    private final CommandeFournisseurService commandeFournisseurService;

    @Autowired
    public CommandeFournisseurController(CommandeFournisseurService commandeFournisseurService){
        this.commandeFournisseurService = commandeFournisseurService;
    }

    @Override
    public ResponseEntity<CommandeFournisseurDto> save(CommandeFournisseurDto commandeFournisseurDto) {
        return ResponseEntity.ok(commandeFournisseurService.save(commandeFournisseurDto));
    }

    @Override
    public ResponseEntity<CommandeFournisseurDto> updateEtatCommande(Integer idCommande, EtatCommande etatCommande) {
        return ResponseEntity.ok(commandeFournisseurService.updateEtatCommande(idCommande,etatCommande));
    }

    @Override
    public ResponseEntity<CommandeFournisseurDto> updateQuantiteCommande(Integer idCommande, Integer idLigneCommande, BigDecimal quantite) {
        return ResponseEntity.ok(commandeFournisseurService.updateQuantiteCommande(idCommande, idLigneCommande, quantite));
    }

    @Override
    public ResponseEntity<CommandeFournisseurDto> updateFournisseur(Integer idCommande, Integer idFournisseur) {
        return ResponseEntity.ok(commandeFournisseurService.updateFournisseur(idCommande, idFournisseur));
    }

    @Override
    public ResponseEntity<CommandeFournisseurDto> updateArticle(Integer idCommande, Integer idLigneCommande, Integer idArticle) {
        return ResponseEntity.ok(commandeFournisseurService.updateArticle(idCommande, idLigneCommande, idArticle));
    }

    @Override
    public ResponseEntity<CommandeFournisseurDto> deleteArticle(Integer idCommande, Integer idLigneCommande) {
        return ResponseEntity.ok(commandeFournisseurService.deleteArticle(idCommande, idLigneCommande));
    }

    @Override
    public ResponseEntity<CommandeFournisseurDto> findById(Integer id) {
        return ResponseEntity.ok(commandeFournisseurService.findById(id));
    }

    @Override
    public ResponseEntity<CommandeFournisseurDto> findByCode(String code) {
        return ResponseEntity.ok(commandeFournisseurService.findByCode(code));
    }

    @Override
    public ResponseEntity<List<CommandeFournisseurDto>> findAll() {
        return ResponseEntity.ok(commandeFournisseurService.findAll());
    }

    @Override
    public ResponseEntity<List<LigneCdeFournisseurDto>> findAllLignesCommandesFournisseursByCommandeFournisseur(Integer idCommande) {
        return ResponseEntity.ok(commandeFournisseurService.findAllLignesCommandesFournisseursByCommandeFournisseur(idCommande));
    }

    @Override
    public ResponseEntity<Void> delete(Integer id) {
        try {
            commandeFournisseurService.delete(id);
            return ResponseEntity.noContent().build();
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
