package chijouProjects.gestion_stock_api.service.impl;

import chijouProjects.gestion_stock_api.dto.*;
import chijouProjects.gestion_stock_api.exception.EntityNotFoundException;
import chijouProjects.gestion_stock_api.exception.ErrorCodes;
import chijouProjects.gestion_stock_api.exception.InvalidEntityException;
import chijouProjects.gestion_stock_api.exception.InvalidOperationException;
import chijouProjects.gestion_stock_api.model.*;
import chijouProjects.gestion_stock_api.repository.ArticleRepository;
import chijouProjects.gestion_stock_api.repository.CommandeFournisseurRepository;
import chijouProjects.gestion_stock_api.repository.FournisseurRepository;
import chijouProjects.gestion_stock_api.repository.LigneCdeFournisseurRepository;
import chijouProjects.gestion_stock_api.service.CommandeFournisseurService;
import chijouProjects.gestion_stock_api.validator.ArticleValidator;
import chijouProjects.gestion_stock_api.validator.CommandeFournisseurValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class CommandeFournisseurServiceImpl implements CommandeFournisseurService {

    private final CommandeFournisseurRepository commandeFournisseurRepository;
    private final ArticleRepository articleRepository;
    private final LigneCdeFournisseurRepository ligneCdeFournisseurRepository;
    private final FournisseurRepository fournisseurRepository;

    @Autowired
    public CommandeFournisseurServiceImpl(CommandeFournisseurRepository commandeFournisseurRepository, ArticleRepository articleRepository, LigneCdeFournisseurRepository ligneCdeFournisseurRepository, FournisseurRepository fournisseurRepository){
        this.articleRepository = articleRepository;
        this.commandeFournisseurRepository = commandeFournisseurRepository;
        this.ligneCdeFournisseurRepository = ligneCdeFournisseurRepository;
        this.fournisseurRepository = fournisseurRepository;
    }
    @Override
    public CommandeFournisseurDto save(CommandeFournisseurDto commandeFournisseurDto) {
        List<String> errors = CommandeFournisseurValidator.validate(commandeFournisseurDto);;
        if(!errors.isEmpty()) {
            log.error("Commande fournisseur is not valid: {}", commandeFournisseurDto);
            throw new InvalidEntityException("Commande fournisseur n'est pas valide", ErrorCodes.COMMANDE_FOURNISSEUR_NOT_FOUND, errors);
        }

        if(commandeFournisseurDto.getId() != null && commandeFournisseurDto.isCommandeLivree()){
            throw new InvalidOperationException("Impossible de modifier la commande lorsqu'elle est livrée.", ErrorCodes.COMMANDE_FOURNISSEUR_NOT_EDITABLE);
        }

        Optional<Fournisseur> fournisseur = fournisseurRepository.findById(commandeFournisseurDto.getFournisseurdto().getId());

        if(fournisseur.isEmpty()) {
            log.warn("Fournisseur with ID {} was not found in the DBB", commandeFournisseurDto.getId());
            throw new EntityNotFoundException("Aucun Fournisseur avec l'ID " + commandeFournisseurDto.getId() + " n'a été trouvé dans la BDD");
        }

        List<String> articleErrors = new ArrayList<>();

        if(commandeFournisseurDto.getLignecdefournisseursdto() != null){
            commandeFournisseurDto.getLignecdefournisseursdto().forEach(lignecdefournisseur -> {
                if(lignecdefournisseur.getIdarticle() != null){
                    Optional<Article> article = articleRepository.findById(lignecdefournisseur.getIdarticle());
                    if(article.isEmpty()) {
                        articleErrors.add("L'article avec l'ID " + lignecdefournisseur.getIdarticle() + " n'existe pas.");
                    }
                } else {
                    articleErrors.add("Impossible d'enregistrer une commande avec un article NULL.");
                }
            });
        }

        if(!articleErrors.isEmpty()) {
            log.warn("Commande fournisseur is not valid: {}", commandeFournisseurDto);
            throw new InvalidEntityException("Au moins un des articles n'existe pas dans la BDD ", ErrorCodes.ARTICLE_NOT_FOUND, articleErrors);
        }

        CommandeFournisseur saveCmdFournisseur = commandeFournisseurRepository.save(CommandeFournisseurDto.toEntity(commandeFournisseurDto));

        if(commandeFournisseurDto.getLignecdefournisseursdto() != null){
            commandeFournisseurDto.getLignecdefournisseursdto().forEach(lignecdefournisseur -> {
                LigneCdeFournisseur ligneCdeFournisseur = LigneCdeFournisseurDto.toEntity(lignecdefournisseur);
                ligneCdeFournisseur.setCommandefournisseur(saveCmdFournisseur);
                ligneCdeFournisseurRepository.save(ligneCdeFournisseur);
            });
        }

        return CommandeFournisseurDto.fromEntity(saveCmdFournisseur);
    }

    @Override
    public CommandeFournisseurDto updateEtatCommande(Integer idCommande, EtatCommande etatCommande) {
        checkIdCommande(idCommande);
        if (!StringUtils.hasLength(String.valueOf(etatCommande))) {
            log.error("L'état de la commande fournisseur is null");
            throw new InvalidOperationException("Impossible de modifier l'état d'une commande avec un état null.", ErrorCodes.COMMANDE_FOURNISSEUR_NOT_EDITABLE);
        }

        CommandeFournisseurDto commandeFournisseurDto = checkEtatCommande(idCommande);

        commandeFournisseurDto.setEtatCommande(etatCommande);
        return CommandeFournisseurDto.fromEntity(commandeFournisseurRepository.save(CommandeFournisseurDto.toEntity(
                commandeFournisseurDto
        )));
    }

    @Override
    public CommandeFournisseurDto updateQuantiteCommande(Integer idCommande, Integer idLigneCommande, BigDecimal quantite) {
        checkIdCommande(idCommande);
        checkIdLigneCommande(idLigneCommande);

        if (quantite == null || quantite.compareTo(BigDecimal.ZERO) <= 0) {
            log.error("La quantité fournie est invalide");
            throw new InvalidOperationException("Impossible de modifier une commande avec une quantité null ou inférieure ou égale à zéro.", ErrorCodes.COMMANDE_FOURNISSEUR_NOT_EDITABLE);
        }

        CommandeFournisseurDto commandeFournisseurDto = checkEtatCommande(idCommande);

        Optional<LigneCdeFournisseur> ligneCdeFournisseurOptional = findLigneCommandeFournisseur(idLigneCommande);
        LigneCdeFournisseur ligneCdeFournisseur;
        if (ligneCdeFournisseurOptional.isPresent()) {
            ligneCdeFournisseur = ligneCdeFournisseurOptional.get();
        } else {
            throw new EntityNotFoundException("Ligne de commande non trouvée");
        }
        ligneCdeFournisseur.setQuantite(quantite);
        ligneCdeFournisseurRepository.save(ligneCdeFournisseur);
        return commandeFournisseurDto;
    }

    @Override
    public CommandeFournisseurDto updateFournisseur(Integer idCommande, Integer idFournisseur) {
        checkIdCommande(idCommande);
        if (idFournisseur == null) {
            log.error("L'ID du fournisseur is null");
            throw new InvalidOperationException("Impossible de modifier une commande avec un ID fournisseur null.", ErrorCodes.COMMANDE_FOURNISSEUR_NOT_EDITABLE);
        }

        CommandeFournisseurDto commandeFournisseurDto = checkEtatCommande(idCommande);

        Optional<Fournisseur> fournisseurOptional = fournisseurRepository.findById(idFournisseur);
        if(fournisseurOptional.isEmpty()){
            throw new EntityNotFoundException("Aucun fournisseur n'a été trouvé avec l'ID " + idFournisseur, ErrorCodes.FOURNISSEUR_NOT_FOUND);
        }
        commandeFournisseurDto.setFournisseurdto(FournisseurDto.fromEntity(fournisseurOptional.get()));
        return CommandeFournisseurDto.fromEntity(
                commandeFournisseurRepository.save(CommandeFournisseurDto.toEntity(commandeFournisseurDto))
        );
    }

    @Override
    public CommandeFournisseurDto updateArticle(Integer idCommande, Integer idLigneCommande, Integer idArticle) {
        checkIdCommande(idCommande);
        checkIdLigneCommande(idLigneCommande);
        checkIdArticle(idArticle);
        CommandeFournisseurDto commandeFournisseurDto = checkEtatCommande(idCommande);

        Optional<LigneCdeFournisseur> ligneCdeFournisseurOptional = findLigneCommandeFournisseur(idLigneCommande);

        Optional<Article> articleOptional = articleRepository.findById(idArticle);

        if (articleOptional.isEmpty()){
            throw new EntityNotFoundException("Aucun article n'a été trouvé avec l'ID " + idArticle, ErrorCodes.ARTICLE_NOT_FOUND);
        }

        List<String> errors = ArticleValidator.validate(ArticleDto.fromEntity(articleOptional.get()));
        if(!errors.isEmpty()){
            throw new InvalidEntityException("Article invalide", ErrorCodes.ARTICLE_NOT_VALID, errors);
        }
        LigneCdeFournisseur ligneCdeFournisseurToSave;

        if (ligneCdeFournisseurOptional.isPresent()) {
            ligneCdeFournisseurToSave = ligneCdeFournisseurOptional.get();
        } else {
            throw new EntityNotFoundException("Ligne de commande non trouvée");
        }

        ligneCdeFournisseurToSave.setArticle(articleOptional.get());
        ligneCdeFournisseurRepository.save(ligneCdeFournisseurToSave);

        return commandeFournisseurDto;
    }

    @Override
    public CommandeFournisseurDto deleteArticle(Integer idCommande, Integer idLigneCommande) {
        checkIdCommande(idCommande);
        checkIdLigneCommande(idLigneCommande);
        CommandeFournisseurDto commandeFournisseurDto = checkEtatCommande(idCommande);
        // Juste pour vérifier l'existence de ligne commande et informer le fournisseur en cas d'absence
        findLigneCommandeFournisseur(idLigneCommande);
        ligneCdeFournisseurRepository.deleteById(idLigneCommande);
        return commandeFournisseurDto;
    }

    @Override
    @Transactional(readOnly = true)
    public CommandeFournisseurDto findById(Integer id) {
        if (id == null) {
            log.error("Commande fournisseur ID is null");
            return null; // ou throw IllegalArgumentException
        }

        CommandeFournisseur commandeFournisseur = commandeFournisseurRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Aucune commande Fournisseur avec l'ID = " + id + " n'a été trouvée dans la BDD",
                        ErrorCodes.COMMANDE_FOURNISSEUR_NOT_FOUND
                ));

        return CommandeFournisseurDto.fromEntity(commandeFournisseur);
    }

    @Override
    @Transactional(readOnly = true)
    public CommandeFournisseurDto findByCode(String code) {
        if (!StringUtils.hasLength(code)) {
            log.error("Le code de commande Fournisseur est null ou vide");
            throw new IllegalArgumentException("Le code de commande Fournisseur ne peut pas être vide");
        }

        CommandeFournisseur commandeFournisseur = commandeFournisseurRepository.findByCode(code)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Aucune commande Fournisseur avec le code = " + code + " n'a été trouvée dans la BDD",
                        ErrorCodes.COMMANDE_FOURNISSEUR_NOT_FOUND
                ));

        return CommandeFournisseurDto.fromEntity(commandeFournisseur);
    }

    @Override
    public List<CommandeFournisseurDto> findAll() {
        return commandeFournisseurRepository.findAll().stream()
                .map(CommandeFournisseurDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public List<LigneCdeFournisseurDto> findAllLignesCommandesFournisseursByCommandeFournisseur(Integer idCommande) {
        return ligneCdeFournisseurRepository.findAllByCommandefournisseurId(idCommande).stream()
                .map(LigneCdeFournisseurDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Integer id) {
        if (id == null) {
            log.error("Commande Fournisseur ID is null");
            return;
        }

        if (!commandeFournisseurRepository.existsById(id)) {
            log.warn("Attempted to delete non-existent commande Fournisseur with ID: {}", id);
            return;
        }

        commandeFournisseurRepository.deleteById(id);
        log.info("Commande Fournisseur with ID {} successfully deleted.", id);
    }

    private void checkIdCommande(Integer idCommande){
        if (idCommande == null) {
            log.error("Commande fournisseur ID is null");
            throw new InvalidOperationException("Impossible de modifier l'état d'une commande avec un ID null.", ErrorCodes.COMMANDE_FOURNISSEUR_NOT_EDITABLE);
        }
    }

    private void checkIdLigneCommande(Integer idLigneCommande) {
        if (idLigneCommande == null) {
            log.error("L'ID de la ligne de commande fournisseur is null");
            throw new InvalidOperationException("Impossible de modifier l'état d'une commande avec une ligne de commande null.", ErrorCodes.COMMANDE_FOURNISSEUR_NOT_EDITABLE);
        }
    }

    private void checkIdArticle(Integer idArticle) {
        if (idArticle == null) {
            log.error("L'ID article is null");
            throw new InvalidOperationException("Impossible de modifier la commande fournisseur avec un ID article null.", ErrorCodes.COMMANDE_FOURNISSEUR_NOT_EDITABLE);
        }
    }

    private CommandeFournisseurDto checkEtatCommande(Integer idCommande) {
        CommandeFournisseurDto commandeFournisseurDto = findById(idCommande);
        if(commandeFournisseurDto.isCommandeLivree()){
            throw new InvalidOperationException("Impossible de modifier la commande lorsqu'elle est livrée.", ErrorCodes.COMMANDE_FOURNISSEUR_NOT_EDITABLE);
        }
        return commandeFournisseurDto;
    }

    private Optional<LigneCdeFournisseur> findLigneCommandeFournisseur(Integer idLigneCommande) {
        Optional<LigneCdeFournisseur> ligneCdeFournisseurOptional = ligneCdeFournisseurRepository.findById(idLigneCommande);
        if(ligneCdeFournisseurOptional.isEmpty()){
            throw new EntityNotFoundException("Aucune ligne de commande fournisseur n'a été trouvée avec l'ID " + idLigneCommande, ErrorCodes.LIGNE_COMMANDE_FOURNISSEUR_NOT_FOUND);
        }
        return ligneCdeFournisseurOptional;
    }
}
