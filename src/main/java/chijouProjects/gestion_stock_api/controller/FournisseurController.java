package chijouProjects.gestion_stock_api.controller;

import chijouProjects.gestion_stock_api.controller.api.FournisseurApi;
import chijouProjects.gestion_stock_api.dto.FournisseurDto;
import chijouProjects.gestion_stock_api.exception.EntityNotFoundException;
import chijouProjects.gestion_stock_api.service.FournisseurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class FournisseurController implements FournisseurApi {

    private FournisseurService fournisseurService;

    @Autowired
    public FournisseurController(FournisseurService fournisseurService) {
        this.fournisseurService = fournisseurService;
    }

    @Override
    public ResponseEntity<FournisseurDto> save(FournisseurDto fournisseurDto) {
        return ResponseEntity.ok(fournisseurService.save(fournisseurDto));
    }

    @Override
    public ResponseEntity<FournisseurDto> findById(Integer id) {
        return ResponseEntity.ok(fournisseurService.findById(id));
    }

    @Override
    public ResponseEntity<FournisseurDto> findByNom(String nom) {
        return ResponseEntity.ok(fournisseurService.findByNom(nom));
    }

    @Override
    public ResponseEntity<List<FournisseurDto>> findAll() {
        return ResponseEntity.ok(fournisseurService.findAll());
    }

    @Override
    public ResponseEntity<Void> delete(Integer id) {
        try {
            fournisseurService.delete(id);
            return ResponseEntity.noContent().build();
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
