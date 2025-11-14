package chijouProjects.gestion_stock_api.service;

import chijouProjects.gestion_stock_api.dto.CategorieDto;
import chijouProjects.gestion_stock_api.dto.ClientDto;

import java.util.List;

public interface ClientService {
    ClientDto save(ClientDto clientDto);

    ClientDto findById(Integer id);

    ClientDto findByNom(String nom);

    List<ClientDto> findAll();

    void delete(Integer id);
}
