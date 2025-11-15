package chijouProjects.gestion_stock_api.repository;

import chijouProjects.gestion_stock_api.model.Article;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ArticleRepository extends JpaRepository<Article, Integer> {

    //Article findArticleByCodeArticle(String codeArticle);
    Optional<Article> findByCode(String code);
}
