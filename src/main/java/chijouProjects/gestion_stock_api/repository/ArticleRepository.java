package chijouProjects.gestion_stock_api.repository;

import chijouProjects.gestion_stock_api.model.Article;
import chijouProjects.gestion_stock_api.model.Categorie;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ArticleRepository extends JpaRepository<Article, Integer> {

    Optional<Article> findByCode(String code);
    Optional<Article> findByDesignation(String designation);
    List<Article> findAllArticlesByCategorieId(Integer idCategorie);
}
