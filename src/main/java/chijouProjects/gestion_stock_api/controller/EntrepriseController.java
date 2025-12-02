package chijouProjects.gestion_stock_api.controller;

import chijouProjects.gestion_stock_api.controller.api.EntrepriseApi;
import chijouProjects.gestion_stock_api.dto.EntrepriseDto;
import chijouProjects.gestion_stock_api.service.EntrepriseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class EntrepriseController implements EntrepriseApi {

    private EntrepriseService entrepriseService;

    @Autowired
    public EntrepriseController(EntrepriseService entrepriseService) {
        this.entrepriseService = entrepriseService;
    }

    @Override
    public ResponseEntity<EntrepriseDto> save(EntrepriseDto articleDto) {
        return ResponseEntity.ok(entrepriseService.save(articleDto));
    }

    @Override
    public ResponseEntity<EntrepriseDto> findById(Integer id) {
        return ResponseEntity.ok(entrepriseService.findById(id));
    }

    @Override
    public ResponseEntity<EntrepriseDto> findByDescription(String description) {
        return ResponseEntity.ok(entrepriseService.findByDescription(description));
    }

    @Override
    public ResponseEntity<EntrepriseDto> findByCodefiscal(String codefiscal) {
        return ResponseEntity.ok(entrepriseService.findByCodefiscal(codefiscal));
    }

    @Override
    public ResponseEntity<List<EntrepriseDto>> findAll() {
        return ResponseEntity.ok(entrepriseService.findAll());
    }

    @Override
    public ResponseEntity<Void> delete(Integer id) {
        entrepriseService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
