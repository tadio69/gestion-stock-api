package chijouProjects.gestion_stock_api.controller.api;

import chijouProjects.gestion_stock_api.dto.ClientDto;
import chijouProjects.gestion_stock_api.dto.FournisseurDto;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static chijouProjects.gestion_stock_api.utils.Constants.APP_ROOT;

public interface FournisseurApi {
    @PostMapping(value = APP_ROOT + "/fournisseurs/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    FournisseurDto save(@RequestBody FournisseurDto fournisseurDto);

    @GetMapping(value = APP_ROOT + "/fournisseurs/{idFournisseur}" , produces = MediaType.APPLICATION_JSON_VALUE)
    FournisseurDto findById(@PathVariable("idFournisseur") Integer id);

    @GetMapping(value = APP_ROOT + "/fournisseurs/{nom}", produces = MediaType.APPLICATION_JSON_VALUE)
    FournisseurDto findByNomFournisseur(@PathVariable("nom") String nom);

    @GetMapping(value = APP_ROOT + "/fournisseurs/all", produces = MediaType.APPLICATION_JSON_VALUE)
    List<FournisseurDto> findAll();

    @DeleteMapping(value = APP_ROOT + "/fournisseurs/delete/{idFournisseur}", produces = MediaType.APPLICATION_JSON_VALUE)
    void delete(@PathVariable("idFournisseur") Integer id);
}
