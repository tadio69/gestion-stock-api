package chijouProjects.gestion_stock_api.controller;

import chijouProjects.gestion_stock_api.controller.api.UtilisateurApi;
import chijouProjects.gestion_stock_api.dto.UtilisateurDto;
import chijouProjects.gestion_stock_api.service.UtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public UtilisateurDto save(UtilisateurDto fournisseurDto) {
        return utilisateurService.save(fournisseurDto);
    }

    @Override
    public UtilisateurDto findById(Integer id) {
        return utilisateurService.findById(id);
    }

    @Override
    public UtilisateurDto findByNomUtilisateur(String nom) {
        return utilisateurService.findByNomUtilisateur(nom);
    }

    @Override
    public List<UtilisateurDto> findAll() {
        return utilisateurService.findAll();
    }

    @Override
    public void delete(Integer id) {
        utilisateurService.delete(id);
    }
}
