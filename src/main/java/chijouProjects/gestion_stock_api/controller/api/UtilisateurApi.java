package chijouProjects.gestion_stock_api.controller.api;

import chijouProjects.gestion_stock_api.dto.FournisseurDto;
import chijouProjects.gestion_stock_api.dto.UtilisateurDto;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static chijouProjects.gestion_stock_api.utils.Constants.APP_ROOT;

public interface UtilisateurApi {
    @PostMapping(value = APP_ROOT + "/utilisateurs/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    UtilisateurDto save(@RequestBody UtilisateurDto fournisseurDto);

    @GetMapping(value = APP_ROOT + "/utilisateurs/{idUtilisateur}" , produces = MediaType.APPLICATION_JSON_VALUE)
    UtilisateurDto findById(@PathVariable("idUtilisateur") Integer id);

    @GetMapping(value = APP_ROOT + "/utilisateurs/{nom}", produces = MediaType.APPLICATION_JSON_VALUE)
    UtilisateurDto findByNomUtilisateur(@PathVariable("nom") String nom);

    @GetMapping(value = APP_ROOT + "/utilisateurs/all", produces = MediaType.APPLICATION_JSON_VALUE)
    List<UtilisateurDto> findAll();

    @DeleteMapping(value = APP_ROOT + "/utilisateurs/delete/{idUtilisateur}", produces = MediaType.APPLICATION_JSON_VALUE)
    void delete(@PathVariable("idUtilisateur") Integer id);
}
