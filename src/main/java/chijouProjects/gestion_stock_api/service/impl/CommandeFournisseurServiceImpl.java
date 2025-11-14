package chijouProjects.gestion_stock_api.service.impl;

import chijouProjects.gestion_stock_api.dto.CommandeFournisseurDto;
import chijouProjects.gestion_stock_api.dto.LigneCdeFournisseurDto;
import chijouProjects.gestion_stock_api.exception.EntityNotFoundException;
import chijouProjects.gestion_stock_api.exception.ErrorCodes;
import chijouProjects.gestion_stock_api.exception.InvalidEntityException;
import chijouProjects.gestion_stock_api.model.*;
import chijouProjects.gestion_stock_api.repository.ArticleRepository;
import chijouProjects.gestion_stock_api.repository.CommandeFournisseurRepository;
import chijouProjects.gestion_stock_api.repository.FournisseurRepository;
import chijouProjects.gestion_stock_api.repository.LigneCdeFournisseurRepository;
import chijouProjects.gestion_stock_api.service.CommandeFournisseurService;
import chijouProjects.gestion_stock_api.validator.CommandeFournisseurValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class CommandeFournisseurServiceImpl implements CommandeFournisseurService {

    private CommandeFournisseurRepository commandeFournisseurrepository;
    private ArticleRepository articleRepository;
    private LigneCdeFournisseurRepository ligneCdeFournisseurRepository;
    private FournisseurRepository fournisseurRepository;

    @Autowired
    public CommandeFournisseurServiceImpl(CommandeFournisseurRepository commandeFournisseurrepository, ArticleRepository articleRepository, LigneCdeFournisseurRepository ligneCdeFournisseurRepository, FournisseurRepository fournisseurRepository){
        this.articleRepository = articleRepository;
        this.commandeFournisseurrepository = commandeFournisseurrepository;
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
        Optional<Fournisseur> fournisseur = fournisseurRepository.findById(commandeFournisseurDto.getFournisseurdto().getId());

        if(fournisseur.isEmpty()) {
            log.warn("Fournisseur with ID {} was not found in the DBB", commandeFournisseurDto.getFournisseurdto().getId());
            throw new EntityNotFoundException("Aucun Fournisseur avec l'ID " + commandeFournisseurDto.getFournisseurdto().getId() + " n'a été trouvé dans la BDD");
        }

        List<String> articleErrors = new ArrayList<>();

        if(commandeFournisseurDto.getLignecdefournisseursdto() != null){
            commandeFournisseurDto.getLignecdefournisseursdto().forEach(lignecdefournisseur -> {
                if(lignecdefournisseur.getArticledto() != null){
                    Optional<Article> article = articleRepository.findById(lignecdefournisseur.getArticledto().getId());
                    if(article.isEmpty()) {
                        articleErrors.add("L'article avec l'ID " + lignecdefournisseur.getArticledto().getId() + " n'existe pas.");
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

        CommandeFournisseur saveCmdFournisseur = commandeFournisseurrepository.save(CommandeFournisseurDto.toEntity(commandeFournisseurDto));

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
    public CommandeFournisseurDto findById(Integer id) {
        if (id == null) {
            log.error("Commande fournisseur ID is null");
            return null; // ou throw IllegalArgumentException
        }

        CommandeFournisseur commandeFournisseur = commandeFournisseurrepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Aucune commande Fournisseur avec l'ID = " + id + " n'a été trouvée dans la BDD",
                        ErrorCodes.COMMANDE_FOURNISSEUR_NOT_FOUND
                ));
        return CommandeFournisseurDto.fromEntity(commandeFournisseur);
    }

    @Override
    public CommandeFournisseurDto findByCode(String code) {
        if (!StringUtils.hasLength(code)) {
            log.error("Le code de commande Fournisseur est null ou vide");
            throw new IllegalArgumentException("Le code de commande Fournisseur ne peut pas être vide");
        }

        CommandeFournisseur commandeFournisseur = commandeFournisseurrepository.findByCode(code)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Aucune commande Fournisseur avec le code = " + code + " n'a été trouvée dans la BDD",
                        ErrorCodes.COMMANDE_FOURNISSEUR_NOT_FOUND
                ));


        return CommandeFournisseurDto.fromEntity(commandeFournisseur);
    }

    @Override
    public List<CommandeFournisseurDto> findAll() {
        return commandeFournisseurrepository.findAll().stream()
                .map(CommandeFournisseurDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Integer id) {
        if (id == null) {
            log.error("Commande Fournisseur ID is null");
            return;
        }

        if (!commandeFournisseurrepository.existsById(id)) {
            log.warn("Attempted to delete non-existent commande Fournisseur with ID: {}", id);
            return;
        }

        commandeFournisseurrepository.deleteById(id);
        log.info("Commande Fournisseur with ID {} successfully deleted.", id);
    }
}
