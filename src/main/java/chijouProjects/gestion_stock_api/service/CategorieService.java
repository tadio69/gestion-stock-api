package chijouProjects.gestion_stock_api.service;

import chijouProjects.gestion_stock_api.dto.ArticleDto;
import chijouProjects.gestion_stock_api.dto.CategorieDto;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface CategorieService {
    CategorieDto save(CategorieDto categorieDto);

    CategorieDto findById(Integer id);

    CategorieDto findByCodeCategorie(String codeCategorie);

    CategorieDto findByDesignation(String designation);

    List<CategorieDto> findAll();

    void delete(Integer id);
}
