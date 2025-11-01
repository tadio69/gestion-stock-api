package chijouProjects.gestion_stock_api.controller;

import chijouProjects.gestion_stock_api.controller.api.CategorieApi;
import chijouProjects.gestion_stock_api.dto.CategorieDto;
import chijouProjects.gestion_stock_api.service.CategorieService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public CategorieDto save(CategorieDto categorieDto) {
        return categorieService.save(categorieDto);
    }

    @Override
    public CategorieDto findById(Integer id) {
        return categorieService.findById(id);
    }

    @Override
    public CategorieDto findByDesignation(String designation) {
        return categorieService.findByDesignation(designation);
    }

    @Override
    public CategorieDto findByCodeCategorie(String codeCategorie) {
        return categorieService.findByCodeCategorie(codeCategorie);
    }

    @Override
    public List<CategorieDto> findAll() {
        return categorieService.findAll();
    }

    @Override
    public void delete(Integer id) {
        categorieService.delete(id);
    }
}
