package chijouProjects.gestion_stock_api.controller.api;

import chijouProjects.gestion_stock_api.dto.ClientDto;
import chijouProjects.gestion_stock_api.dto.EntrepriseDto;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static chijouProjects.gestion_stock_api.utils.Constants.APP_ROOT;

public interface EntrepriseApi {
    @PostMapping(value = APP_ROOT + "/entreprises/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    EntrepriseDto save(@RequestBody EntrepriseDto articleDto);

    @GetMapping(value = APP_ROOT + "/entreprises/{idEntreprise}" , produces = MediaType.APPLICATION_JSON_VALUE)
    EntrepriseDto findById(@PathVariable("idEntreprise") Integer id);

    @GetMapping(value = APP_ROOT + "/entreprises/{description}", produces = MediaType.APPLICATION_JSON_VALUE)
    EntrepriseDto findByDescription(@PathVariable("description") String description);

    @GetMapping(value = APP_ROOT + "/entreprises/{codeFiscal}", produces = MediaType.APPLICATION_JSON_VALUE)
    EntrepriseDto findByCodeFiscal(@PathVariable("codeFiscal") String codeFiscal);

    @GetMapping(value = APP_ROOT + "/entreprises/all", produces = MediaType.APPLICATION_JSON_VALUE)
    List<EntrepriseDto> findAll();

    @DeleteMapping(value = APP_ROOT + "/entreprises/delete/{idEntreprise}", produces = MediaType.APPLICATION_JSON_VALUE)
    void delete(@PathVariable("idEntreprise") Integer id);
}
