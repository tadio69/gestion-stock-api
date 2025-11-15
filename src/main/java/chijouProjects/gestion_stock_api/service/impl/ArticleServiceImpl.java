package chijouProjects.gestion_stock_api.service.impl;

import chijouProjects.gestion_stock_api.dto.ArticleDto;
import chijouProjects.gestion_stock_api.exception.EntityNotFoundException;
import chijouProjects.gestion_stock_api.exception.ErrorCodes;
import chijouProjects.gestion_stock_api.exception.InvalidEntityException;
import chijouProjects.gestion_stock_api.model.Article;
import chijouProjects.gestion_stock_api.repository.ArticleRepository;
import chijouProjects.gestion_stock_api.service.ArticleService;
import chijouProjects.gestion_stock_api.validator.ArticleValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ArticleServiceImpl implements ArticleService {

    private ArticleRepository articleRepository;

    @Autowired
    public ArticleServiceImpl(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    @Override
    public ArticleDto save(ArticleDto articleDto) {
        List<String> errors = ArticleValidator.validate(articleDto);;
        if(!errors.isEmpty()) {
            log.error("Article is not valid: {}", articleDto);
            throw new InvalidEntityException("l'article n'est pas valide", ErrorCodes.ARTICLE_NOT_VALID, errors);
        }
        return ArticleDto.fromEntity(
                articleRepository.save(
                        ArticleDto.toEntity(articleDto)
                )
        );
    }

    @Override
    public ArticleDto findById(Integer id) {
        if (id == null) {
            log.error("Article ID is null");
            return null;
        }

        Article article = articleRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(
                "Aucun article avec l'ID = " + id + " n'a été trouvé dans la BDD",
                ErrorCodes.ARTICLE_NOT_FOUND
        ));

        return ArticleDto.fromEntity(article);
    }

    @Override
    public ArticleDto findByCode(String code) {
        if (!StringUtils.hasLength(code)) {
            log.error("Article CODE est null ou vide");
            throw new IllegalArgumentException("Le code de l'article ne peut pas être vide");
        }

        Article article = articleRepository.findByCode(code)
                .orElseThrow(() -> new EntityNotFoundException(
                "Aucun article avec le code = " + code + " n'a été trouvé dans la BDD",
                ErrorCodes.ARTICLE_NOT_FOUND
        ));

        return ArticleDto.fromEntity(article);
    }

    @Override
    public List<ArticleDto> findAll() {
        return articleRepository.findAll().stream()
                .map(ArticleDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Integer id) {
        if (id == null) {
            log.error("Article ID is null");
            return;
        }

        if (!articleRepository.existsById(id)) {
            log.warn("Attempted to delete non-existent Article with ID: {}", id);
            return;
        }

        articleRepository.deleteById(id);
        log.info("Article with ID {} successfully deleted.", id);
    }
}
