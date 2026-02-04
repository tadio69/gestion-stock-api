package chijouProjects.gestion_stock_api.controller;

import chijouProjects.gestion_stock_api.controller.api.UtilisateurApi;
import chijouProjects.gestion_stock_api.dto.UtilisateurDto;
import chijouProjects.gestion_stock_api.exception.EntityNotFoundException;
import chijouProjects.gestion_stock_api.service.UtilisateurService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UtilisateurController implements UtilisateurApi {

    private UtilisateurService utilisateurService;

    @Autowired
    public UtilisateurController(UtilisateurService utilisateurService) {
        this.utilisateurService = utilisateurService;
    }

    @Override
    public ResponseEntity<UtilisateurDto> save(UtilisateurDto fournisseurDto, boolean creationAuto) {
        return ResponseEntity.ok(utilisateurService.save(fournisseurDto, creationAuto));
    }

    @Override
    public ResponseEntity<UtilisateurDto> findById(Integer id) {
        return ResponseEntity.ok(utilisateurService.findById(id));
    }

    @Override
    public ResponseEntity<UtilisateurDto> findByNom(String nom) {
        return ResponseEntity.ok(utilisateurService.findByNom(nom));
    }

    @Override
    public ResponseEntity<List<UtilisateurDto>> findAll() {
        return ResponseEntity.ok(utilisateurService.findAll());
    }

    @Override
    public ResponseEntity<Void> delete(Integer id) {
        try {
            utilisateurService.delete(id);
            return ResponseEntity.noContent().build();
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
