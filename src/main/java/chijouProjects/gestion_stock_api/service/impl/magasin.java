package chijouProjects.gestion_stock_api.service.impl;

import chijouProjects.gestion_stock_api.dto.CategorieDto;
import chijouProjects.gestion_stock_api.exception.EntityNotFoundException;
import chijouProjects.gestion_stock_api.exception.ErrorCodes;
import chijouProjects.gestion_stock_api.exception.InvalidEntityException;
import chijouProjects.gestion_stock_api.interceptor.Interceptor;
import chijouProjects.gestion_stock_api.model.Categorie;
import chijouProjects.gestion_stock_api.repository.CategorieRepository;
import chijouProjects.gestion_stock_api.validator.CategorieValidator;
import jakarta.persistence.EntityManager;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.stream.Collectors;


/*public class magasin {
    private CategorieRepository categorieRepository;
    private final EntityManager entityManager;

    *//*@Autowired*//*
    public CategorieServiceImpl(CategorieRepository categorieRepository, EntityManager entityManager) {
        this.categorieRepository = categorieRepository;
        this.entityManager = entityManager;
    }
    *//*@Autowired*//*
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

    *//*@Autowired*//*
    @Transactional(readOnly = true)
    public CategorieDto findById(Integer id) {
        if (id == null) {
            log.error("Catégorie ID is null");
            return null;
        }

        // 1. Récupération de l'ID d'entreprise (obligatoire)
        Object currentId = Interceptor.getCurrentEntrepriseId();
        Integer entrepriseId;

        try {
            // Logique de conversion sécurisée (Integer ou String -> Integer)
            if (currentId == null) { throw new NullPointerException(); }
            entrepriseId = Integer.parseInt(String.valueOf(currentId));

        } catch (NumberFormatException | NullPointerException e) {
            throw new InvalidEntityException(
                    "Le filtre d'entreprise (X-Entreprise-Id) est obligatoire et doit être un nombre valide.",
                    ErrorCodes.ENTREPRISE_ID_REQUIRED
            );
        }

        // 2. Activation du filtre (Maintenu, car il fonctionne pour findByCode/findAll)
        Session session = entityManager.unwrap(Session.class);
        session.enableFilter("entrepriseFilter")
                .setParameter("entrepriseId", entrepriseId);

        // 3. Appel du JpaRepository (celui qui bypass le filtre)
        Categorie categorie = categorieRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Aucune catégorie avec l'ID = " + id + " n'a été trouvée dans la BDD",
                        ErrorCodes.CATEGORIE_NOT_FOUND
                ));

        // ====================================================================
        // 🎯 ÉTAPE CRUCIALE : CONTRÔLE DE SÉCURITÉ
        // ====================================================================
        if (categorie.getIdentreprise() == null || !categorie.getIdentreprise().equals(entrepriseId)) {
            log.warn("Catégorie ID {} trouvée mais l'ID d'entreprise {} ne correspond pas à l'ID de session {}",
                    id, categorie.getIdentreprise(), entrepriseId);

            // Simuler un 404/NOT_FOUND pour l'utilisateur non autorisé
            throw new EntityNotFoundException(
                    "Aucune catégorie avec l'ID = " + id + " n'a été trouvée dans la BDD pour cette entreprise.",
                    ErrorCodes.CATEGORIE_NOT_FOUND // Retourne le même code d'erreur
            );
        }
        // ====================================================================


        if (categorie.getArticles() != null) {
            categorie.getArticles().size();
        }

        return CategorieDto.fromEntity(categorie);
    }

    *//*@Autowired*//*
    @Transactional(readOnly = true)
    public CategorieDto findByCode(String code) {
        Session session = entityManager.unwrap(Session.class);
        Integer entrepriseId = (Integer) Interceptor.getCurrentEntrepriseId(); // tu peux ajouter un getter
        if (entrepriseId != null) {
            session.enableFilter("entrepriseFilter")
                    .setParameter("entrepriseId", entrepriseId);
        }
        if (!StringUtils.hasLength(code)) {
            log.error("Catégorie CODE est null ou vide");
            throw new IllegalArgumentException("Le code de la catégorie ne peut pas être vide");
        }

        Categorie categorie = categorieRepository.findByCode(code)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Aucune catégorie avec le CODE = " + code + " n'a été trouvé dans la BDD",
                        ErrorCodes.CATEGORIE_NOT_FOUND
                ));

        if (categorie.getArticles() != null) {
            categorie.getArticles().size();
        }

        return CategorieDto.fromEntity(categorie);
    }

    *//*@Autowired*//*
    @Transactional(readOnly = true)
    public CategorieDto findByDesignation(String designation) {
        Session session = entityManager.unwrap(Session.class);
        Integer entrepriseId = Interceptor.getCurrentEntrepriseId(); // tu peux ajouter un getter
        if (entrepriseId != null) {
            session.enableFilter("entrepriseFilter")
                    .setParameter("entrepriseId", entrepriseId);
        }
        if (!StringUtils.hasLength(designation)) {
            log.error("Désignation de catégorie est null ou vide");
            throw new IllegalArgumentException("La désignation de la catégorie ne peut pas être vide");
        }

        Categorie categorie = categorieRepository.findByDesignation(designation)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Aucune catégorie avec la désignation = " + designation + " n'a été trouvé dans la BDD",
                        ErrorCodes.CATEGORIE_NOT_FOUND
                ));

        if (categorie.getArticles() != null) {
            categorie.getArticles().size();
        }

        return CategorieDto.fromEntity(categorie);
    }

    *//*@Autowired*//*
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

    *//*@Autowired*//*
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
}*/
