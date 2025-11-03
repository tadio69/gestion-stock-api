package chijouProjects.gestion_stock_api.service;

import chijouProjects.gestion_stock_api.dto.ClientDto;
import chijouProjects.gestion_stock_api.dto.CommandeClientDto;

import java.util.List;

public interface CommandeClientService {

    CommandeClientDto save(CommandeClientDto commandeClientDto);

    CommandeClientDto findById(Integer id);

    CommandeClientDto findByCode(String code);

    List<CommandeClientDto> findAll();

    void delete(Integer id);
}
