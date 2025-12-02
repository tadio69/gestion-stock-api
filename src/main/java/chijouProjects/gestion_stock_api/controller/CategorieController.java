package chijouProjects.gestion_stock_api.controller;

import chijouProjects.gestion_stock_api.controller.api.CategorieApi;
import chijouProjects.gestion_stock_api.dto.CategorieDto;
import chijouProjects.gestion_stock_api.service.CategorieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CategorieController implements CategorieApi {

    private CategorieService categorieService;

    @Autowired
    public CategorieController(CategorieService categorieService) {
        this.categorieService = categorieService;
    }

    @Override
    public ResponseEntity<CategorieDto> save(CategorieDto categorieDto) {
        return ResponseEntity.ok(categorieService.save(categorieDto));
    }

    @Override
    public ResponseEntity<CategorieDto> findById(Integer id) {
        return ResponseEntity.ok(categorieService.findById(id));
    }

    @Override
    public ResponseEntity<CategorieDto> findByDesignation(String designation) {
        return ResponseEntity.ok(categorieService.findByDesignation(designation));
    }

    @Override
    public ResponseEntity<CategorieDto> findByCode(String code) {
        return ResponseEntity.ok(categorieService.findByCode(code));
    }

    @Override
    public ResponseEntity<List<CategorieDto>> findAll() {
        return ResponseEntity.ok(categorieService.findAll());
    }

    @Override
    public ResponseEntity<Void> delete(Integer id) {
        categorieService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
