package chijouProjects.gestion_stock_api.controller;

import chijouProjects.gestion_stock_api.controller.api.UtilisateurApi;
import chijouProjects.gestion_stock_api.dto.UtilisateurDto;
import chijouProjects.gestion_stock_api.service.UtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public ResponseEntity<UtilisateurDto> save(UtilisateurDto fournisseurDto) {
        return ResponseEntity.ok(utilisateurService.save(fournisseurDto));
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
        utilisateurService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
