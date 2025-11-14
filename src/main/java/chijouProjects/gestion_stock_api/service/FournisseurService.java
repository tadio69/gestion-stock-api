package chijouProjects.gestion_stock_api.service;

import chijouProjects.gestion_stock_api.dto.ClientDto;
import chijouProjects.gestion_stock_api.dto.FournisseurDto;

import java.util.List;

public interface FournisseurService {
    FournisseurDto save(FournisseurDto fournisseurDto);

    FournisseurDto findById(Integer id);

    FournisseurDto findByNom(String nom);

    List<FournisseurDto> findAll();

    void delete(Integer id);
}
