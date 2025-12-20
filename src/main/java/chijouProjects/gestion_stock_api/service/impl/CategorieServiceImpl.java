package chijouProjects.gestion_stock_api.service.impl;

import chijouProjects.gestion_stock_api.dto.CategorieDto;
import chijouProjects.gestion_stock_api.exception.EntityNotFoundException;
import chijouProjects.gestion_stock_api.exception.ErrorCodes;
import chijouProjects.gestion_stock_api.exception.InvalidEntityException;
import chijouProjects.gestion_stock_api.interceptor.Interceptor;
import chijouProjects.gestion_stock_api.model.Categorie;
import chijouProjects.gestion_stock_api.repository.CategorieRepository;
import chijouProjects.gestion_stock_api.service.CategorieService;
import chijouProjects.gestion_stock_api.validator.CategorieValidator;
import jakarta.persistence.EntityManager;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class CategorieServiceImpl implements CategorieService {

    private CategorieRepository categorieRepository;
    private final EntityManager entityManager;

    @Autowired
    public CategorieServiceImpl(CategorieRepository categorieRepository, EntityManager entityManager) {
        this.categorieRepository = categorieRepository;
        this.entityManager = entityManager;
    }
    @Override
    public CategorieDto save(CategorieDto categorieDto) {
        List<String> errors = CategorieValidator.validate(categorieDto);;
        if(!errors.isEmpty()) {
            log.error("Catégorie is not valid: {}", categorieDto);
            throw new InvalidEntityException("la catégorie n'est pas valide", ErrorCodes.CATEGORIE_NOT_VALID, errors);
        }
        return CategorieDto.fromEntity(
                categorieRepository.save(
                        CategorieDto.toEntity(categorieDto)
                )
        );
    }

    @Override
    @Transactional(readOnly = true)
    public CategorieDto findById(Integer id) {
        if (id == null) {
            log.error("Catégorie ID is null");
            return null;
        }

        Categorie categorie = categorieRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Aucune catégorie avec l'ID = " + id + " n'a été trouvée dans la BDD",
                        ErrorCodes.CATEGORIE_NOT_FOUND
                ));


        if (categorie.getArticles() != null) {
            categorie.getArticles().size();
        }

        return CategorieDto.fromEntity(categorie);
    }

    @Override
    @Transactional(readOnly = true)
    public CategorieDto findByCode(String code) {
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

        Categorie categorie = categorieRepository.findByCode(code)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Aucune catégorie avec le code = " + code + " n'a été trouvée dans la BDD concernant l'entreprise d'ID = " + entrepriseId + ".",
                        ErrorCodes.CATEGORIE_NOT_FOUND
                ));

        if (categorie.getEntreprise() == null || !categorie.getEntreprise().getId().equals(entrepriseId)) {
            log.warn("Catégorie de CODE {} trouvée mais l'ID d'entreprise {} ne correspond pas à l'ID de session {}",
                    code, categorie.getEntreprise().getId(), entrepriseId);

            throw new EntityNotFoundException(
                    "Aucune catégorie avec le code = " + code + " n'a été trouvée dans la BDD concernant l'entreprise d'ID = " + entrepriseId + ".",
                    ErrorCodes.CATEGORIE_NOT_FOUND
            );
        }

        if (!StringUtils.hasLength(code)) {
            log.error("Catégorie CODE est null ou vide");
            throw new IllegalArgumentException("Le code de la catégorie ne peut pas être vide");
        }

        if (categorie.getArticles() != null) {
            categorie.getArticles().size();
        }

        return CategorieDto.fromEntity(categorie);
    }

    @Override
    @Transactional(readOnly = true)
    public CategorieDto findByDesignation(String designation) {
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

        Categorie categorie = categorieRepository.findByDesignation(designation)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Aucune catégorie avec la désignation = " + designation + " n'a été trouvée dans la BDD concernant l'entreprise d'ID = " + entrepriseId + ".",
                        ErrorCodes.CATEGORIE_NOT_FOUND
                ));

        if (categorie.getEntreprise() == null || !categorie.getEntreprise().getId().equals(entrepriseId)) {
            log.warn("Catégorie avec la désignation {} trouvée mais l'ID d'entreprise {} ne correspond pas à l'ID de session {}",
                    designation, categorie.getEntreprise().getId(), entrepriseId);

            throw new EntityNotFoundException(
                    "Aucune catégorie avec la désignation = " + designation + " n'a été trouvée dans la BDD concernant l'entreprise d'ID = " + entrepriseId + ".",
                    ErrorCodes.CATEGORIE_NOT_FOUND
            );
        }

        if (!StringUtils.hasLength(designation)) {
            log.error("Désignation de catégorie est null ou vide");
            throw new IllegalArgumentException("La désignation de la catégorie ne peut pas être vide");
        }

        if (categorie.getArticles() != null) {
            categorie.getArticles().size();
        }

        return CategorieDto.fromEntity(categorie);
    }

    @Override
    @Transactional(readOnly = true)
    public List<CategorieDto> findAll() {
        Session session = entityManager.unwrap(Session.class);
        Integer entrepriseId = Interceptor.getCurrentEntrepriseId(); // tu peux ajouter un getter
        if (entrepriseId != null) {
            session.enableFilter("entrepriseFilter")
                    .setParameter("entrepriseId", entrepriseId);
        }
        return categorieRepository.findAll().stream()
                .map(CategorieDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Integer id) {
        if (id == null) {
            log.error("Catégorie ID is null");
            return;
        }

        if (!categorieRepository.existsById(id)) {
            log.warn("Attempted to delete non-existent Catégorie with ID: {}", id);
            return;
        }

        categorieRepository.deleteById(id);
        log.info("Catégorie with ID {} successfully deleted.", id);
    }
}
