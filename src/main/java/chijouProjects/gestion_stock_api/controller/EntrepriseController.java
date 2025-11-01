package chijouProjects.gestion_stock_api.controller;

import chijouProjects.gestion_stock_api.controller.api.EntrepriseApi;
import chijouProjects.gestion_stock_api.dto.EntrepriseDto;
import chijouProjects.gestion_stock_api.service.EntrepriseService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public EntrepriseDto save(EntrepriseDto articleDto) {
        return entrepriseService.save(articleDto);
    }

    @Override
    public EntrepriseDto findById(Integer id) {
        return entrepriseService.findById(id);
    }

    @Override
    public EntrepriseDto findByDescription(String description) {
        return entrepriseService.findByDescription(description);
    }

    @Override
    public EntrepriseDto findByCodeFiscal(String codeFiscal) {
        return entrepriseService.findByCodeFiscal(codeFiscal);
    }

    @Override
    public List<EntrepriseDto> findAll() {
        return entrepriseService.findAll();
    }

    @Override
    public void delete(Integer id) {
        entrepriseService.delete(id);
    }
}
