package chijouProjects.gestion_stock_api.service.impl;

import chijouProjects.gestion_stock_api.dto.ArticleDto;
import chijouProjects.gestion_stock_api.exception.EntityNotFoundException;
import chijouProjects.gestion_stock_api.exception.ErrorCodes;
import chijouProjects.gestion_stock_api.exception.InvalidEntityException;
import chijouProjects.gestion_stock_api.interceptor.Interceptor;
import chijouProjects.gestion_stock_api.model.Article;
import chijouProjects.gestion_stock_api.model.Categorie;
import chijouProjects.gestion_stock_api.repository.ArticleRepository;
import chijouProjects.gestion_stock_api.service.ArticleService;
import chijouProjects.gestion_stock_api.validator.ArticleValidator;
import jakarta.persistence.EntityManager;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ArticleServiceImpl implements ArticleService {

    private ArticleRepository articleRepository;
    private final EntityManager entityManager;

    @Autowired
    public ArticleServiceImpl(ArticleRepository articleRepository, EntityManager entityManager) {
        this.articleRepository = articleRepository;
        this.entityManager = entityManager;
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
    @Transactional(readOnly = true)
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

        if (article.getLignecdeclts() != null) {
            article.getLignecdeclts().size();
        }
        if (article.getLignecdefournisseurs() != null) {
            article.getLignecdefournisseurs().size();
        }
        if (article.getMvtstocks() != null) {
            article.getMvtstocks().size();
        }
        return ArticleDto.fromEntity(article);
    }

    @Override
    @Transactional(readOnly = true)
    public ArticleDto findByCode(String code) {

        Object currentId = Interceptor.getCurrentEntrepriseId();
        Integer entrepriseId;

        try {
            if (currentId == null) { throw new NullPointerException(); }
            entrepriseId = Integer.parseInt(String.valueOf(currentId));

        } catch (NumberFormatException | NullPointerException e) {
            throw new InvalidEntityException(
                    "Le filtre d'entreprise (X-Entreprise-Id) est obligatoire et doit être un nombre valide.",
                    ErrorCodes.ENTREPRISE_ID_REQUIRED
            );
        }

        Session session = entityManager.unwrap(Session.class);
        session.enableFilter("entrepriseFilter")
                .setParameter("entrepriseId", entrepriseId);

        Article article = articleRepository.findByCode(code)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Aucun article avec le code = " + code + " n'a été trouvée dans la BDD concernant l'entreprise d'ID = " + entrepriseId + ".",
                        ErrorCodes.CATEGORIE_NOT_FOUND
                ));

        if (article.getIdentreprise() == null || !article.getIdentreprise().equals(entrepriseId)) {
            log.warn("Article de CODE {} trouvée mais l'ID d'entreprise {} ne correspond pas à l'ID de session {}",
                    code, article.getIdentreprise(), entrepriseId);

            throw new EntityNotFoundException(
                    "Aucun article avec le code = " + code + " n'a été trouvée dans la BDD concernant l'entreprise d'ID = " + entrepriseId + ".",
                    ErrorCodes.CATEGORIE_NOT_FOUND
            );
        }

        if (!StringUtils.hasLength(code)) {
            log.error("Article CODE est null ou vide");
            throw new IllegalArgumentException("Le code de l'article ne peut pas être vide");
        }

        if (article.getLignecdeclts() != null) {
            article.getLignecdeclts().size();
        }
        if (article.getLignecdefournisseurs() != null) {
            article.getLignecdefournisseurs().size();
        }
        if (article.getMvtstocks() != null) {
            article.getMvtstocks().size();
        }
        return ArticleDto.fromEntity(article);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ArticleDto> findAll() {
        Session session = entityManager.unwrap(Session.class);
        Integer entrepriseId = Interceptor.getCurrentEntrepriseId();
        if (entrepriseId != null) {
            session.enableFilter("entrepriseFilter")
                    .setParameter("entrepriseId", entrepriseId);
        }
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
