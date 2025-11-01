package chijouProjects.gestion_stock_api.service;

import chijouProjects.gestion_stock_api.dto.ClientDto;
import chijouProjects.gestion_stock_api.dto.EntrepriseDto;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

import static chijouProjects.gestion_stock_api.utils.Constants.APP_ROOT;

public interface EntrepriseService {
    EntrepriseDto save(EntrepriseDto entrepriseDto);

    EntrepriseDto findById(Integer id);

    EntrepriseDto findByDescription(String description);

    EntrepriseDto findByCodeFiscal(String codeFiscal);

    List<EntrepriseDto> findAll();

    void delete(Integer id);
}
