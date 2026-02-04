package chijouProjects.gestion_stock_api.service;

import chijouProjects.gestion_stock_api.dto.UtilisateurDto;

import java.util.List;

public interface UtilisateurService {
    UtilisateurDto save(UtilisateurDto UtilisateurDto, boolean creationAuto);

    UtilisateurDto findById(Integer id);

    UtilisateurDto findByNom(String nom);

    List<UtilisateurDto> findAll();

    void delete(Integer id);
}
