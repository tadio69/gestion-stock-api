package chijouProjects.gestion_stock_api.service;

import chijouProjects.gestion_stock_api.dto.ArticleDto;
import chijouProjects.gestion_stock_api.dto.LigneCdeCltDto;
import chijouProjects.gestion_stock_api.dto.LigneCdeFournisseurDto;
import chijouProjects.gestion_stock_api.dto.LigneVenteDto;

import java.util.List;

public interface ArticleService {

    ArticleDto save(ArticleDto articleDto);

    ArticleDto findById(Integer id);

    ArticleDto findByCode(String code);

    ArticleDto findByDesignation(String designation);

    List<ArticleDto> findAll();

    List<LigneVenteDto> findHistoriqueVentes(Integer idArticle);

    List<LigneCdeCltDto> findHistoriqueCommandeClient(Integer idArticle);

    List<LigneCdeFournisseurDto> findHistoriqueCommandeFournisseur(Integer idArticle);

    List<ArticleDto> findAllArticlesByCategorieId(Integer idCategorie);

    void delete(Integer id);
}
