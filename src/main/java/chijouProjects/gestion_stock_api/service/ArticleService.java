package chijouProjects.gestion_stock_api.service;

import chijouProjects.gestion_stock_api.dto.ArticleDto;

import java.util.List;

public interface ArticleService {

    ArticleDto save(ArticleDto articleDto);

    ArticleDto findById(Integer id);

    ArticleDto findByCode(String code);

    ArticleDto findByDesignation(String designation);

    List<ArticleDto> findAll();

    void delete(Integer id);
}
