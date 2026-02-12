package chijouProjects.gestion_stock_api.service.impl;

import chijouProjects.gestion_stock_api.dto.ArticleDto;
import chijouProjects.gestion_stock_api.dto.ClientDto;
import chijouProjects.gestion_stock_api.dto.CommandeClientDto;
import chijouProjects.gestion_stock_api.dto.LigneCdeCltDto;
import chijouProjects.gestion_stock_api.exception.EntityNotFoundException;
import chijouProjects.gestion_stock_api.exception.ErrorCodes;
import chijouProjects.gestion_stock_api.exception.InvalidEntityException;
import chijouProjects.gestion_stock_api.exception.InvalidOperationException;
import chijouProjects.gestion_stock_api.model.*;
import chijouProjects.gestion_stock_api.repository.*;
import chijouProjects.gestion_stock_api.service.CommandeClientService;
import chijouProjects.gestion_stock_api.validator.ArticleValidator;
import chijouProjects.gestion_stock_api.validator.CommandeClientValidator;
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
public class CommandeClientServiceImpl implements CommandeClientService {

    private final LigneCdeFournisseurRepository ligneCdeFournisseurRepository;
    private CommandeClientRepository commandeClientRepository;
    private LigneCdeCltRepository ligneCdeCltRepository;
    private ClientRepository clientRepository;
    private ArticleRepository articleRepository;

    @Autowired
    public CommandeClientServiceImpl(CommandeClientRepository commandeClientRepository, ClientRepository clientRepository, ArticleRepository articleRepository, LigneCdeCltRepository ligneCdeCltRepository, LigneCdeFournisseurRepository ligneCdeFournisseurRepository) {
        this.commandeClientRepository = commandeClientRepository;
        this.clientRepository = clientRepository;
        this.articleRepository = articleRepository;
        this.ligneCdeCltRepository = ligneCdeCltRepository;
        this.ligneCdeFournisseurRepository = ligneCdeFournisseurRepository;
    }

    @Override
    public CommandeClientDto save(CommandeClientDto commandeClientDto) {
        List<String> errors = CommandeClientValidator.validate(commandeClientDto);;
        if(!errors.isEmpty()) {
            log.error("Commande Client is not valid: {}", commandeClientDto);
            throw new InvalidEntityException("Commande Client n'est pas valide", ErrorCodes.COMMANDE_CLIENT_NOT_VALID, errors);
        }

        if(commandeClientDto.getId() != null && commandeClientDto.isCommandeLivree()){
           throw new InvalidOperationException("Impossible de modifier la commande lorsqu'elle est livrée.", ErrorCodes.COMMANDE_CLIENT_NOT_EDITABLE);
        }

        Optional<Client> client = clientRepository.findById(commandeClientDto.getId());

        if(client.isEmpty()) {
            log.warn("Client with ID {} was not found in the DBB", commandeClientDto.getId());
            throw new EntityNotFoundException("Aucun client avec l'ID " + commandeClientDto.getId() + " n'a été trouvé dans la BDD");
        }

        List<String> articleErrors = new ArrayList<>();

        if(commandeClientDto.getLignecdecltsdto() != null){
            commandeClientDto.getLignecdecltsdto().forEach(lignecdecltdto -> {
                if(lignecdecltdto.getIdarticle() != null){
                    Optional<Article> article = articleRepository.findById(lignecdecltdto.getIdarticle());
                    if(article.isEmpty()) {
                        articleErrors.add("L'article avec l'ID " + lignecdecltdto.getIdarticle() + " n'existe pas.");
                    }
                } else {
                    articleErrors.add("Impossible d'enregistrer une commande avec un article NULL.");
                }
            });
        }

        if(!articleErrors.isEmpty()) {
            log.warn("Commande Client is not valid: {}", commandeClientDto);
            throw new InvalidEntityException("Au moins un des articles n'existe pas dans la BDD ", ErrorCodes.ARTICLE_NOT_FOUND, articleErrors);
        }

        CommandeClient saveCmdClt = commandeClientRepository.save(CommandeClientDto.toEntity(commandeClientDto));

        if(commandeClientDto.getLignecdecltsdto() != null){
            commandeClientDto.getLignecdecltsdto().forEach(lignecdeclt -> {
                LigneCdeClt ligneCdeClt = LigneCdeCltDto.toEntity(lignecdeclt);
                ligneCdeClt.setCommandeclient(saveCmdClt);
                ligneCdeCltRepository.save(ligneCdeClt);
            });
        }

        return CommandeClientDto.fromEntity(saveCmdClt);
    }

    @Override
    public CommandeClientDto updateEtatCommande(Integer idCommande, EtatCommande etatCommande) {
        checkIdCommande(idCommande);
        if (!StringUtils.hasLength(String.valueOf(etatCommande))) {
            log.error("L'état de la commande client is null");
            throw new InvalidOperationException("Impossible de modifier l'état d'une commande avec un état null.", ErrorCodes.COMMANDE_CLIENT_NOT_EDITABLE);
        }

        CommandeClientDto commandeClientDto = checkEtatCommande(idCommande);

        commandeClientDto.setEtatCommande(etatCommande);
        return CommandeClientDto.fromEntity(commandeClientRepository.save(CommandeClientDto.toEntity(
           commandeClientDto
        )));
    }

    @Override
    public CommandeClientDto updateQuantiteCommande(Integer idCommande, Integer idLigneCommande, BigDecimal quantite) {
        checkIdCommande(idCommande);
        checkIdLigneCommande(idLigneCommande);

        if (quantite == null || quantite.compareTo(BigDecimal.ZERO) <= 0) {
            log.error("La quantité fournie est invalide");
            throw new InvalidOperationException("Impossible de modifier une commande avec une quantité null ou inférieure ou égale à zéro.", ErrorCodes.COMMANDE_CLIENT_NOT_EDITABLE);
        }

        CommandeClientDto commandeClientDto = checkEtatCommande(idCommande);

        Optional<LigneCdeClt> ligneCdeCltOptional = findLigneCommandeClient(idLigneCommande);
        LigneCdeClt ligneCdeClt = ligneCdeCltOptional.get();
        ligneCdeClt.setQuantite(quantite);
        ligneCdeCltRepository.save(ligneCdeClt);
        return commandeClientDto;
    }

    @Override
    public CommandeClientDto updateClient(Integer idCommande, Integer idClient) {
        checkIdCommande(idCommande);
        if (idClient == null) {
            log.error("L'ID du client is null");
            throw new InvalidOperationException("Impossible de modifier une commande avec un ID client null.", ErrorCodes.COMMANDE_CLIENT_NOT_EDITABLE);
        }

        CommandeClientDto commandeClientDto = checkEtatCommande(idCommande);

        Optional<Client> clientOptional = clientRepository.findById(idClient);
        if(clientOptional.isEmpty()){
            throw new EntityNotFoundException("Aucun client n'a été trouvé avec l'ID " + idClient, ErrorCodes.CLIENT_NOT_FOUND);
        }
        commandeClientDto.setClientdto(ClientDto.fromEntity(clientOptional.get()));
        return CommandeClientDto.fromEntity(
                commandeClientRepository.save(CommandeClientDto.toEntity(commandeClientDto))
        );
    }

    @Override
    public CommandeClientDto updateArticle(Integer idCommande, Integer idLigneCommande, Integer idArticle) {
        checkIdCommande(idCommande);
        checkIdLigneCommande(idLigneCommande);
        checkIdArticle(idArticle, "nouvel");
        CommandeClientDto commandeClientDto = checkEtatCommande(idCommande);

        Optional<LigneCdeClt> ligneCdeClt = findLigneCommandeClient(idLigneCommande);

        Optional<Article> articleOptional = articleRepository.findById(idArticle);

        if (articleOptional.isEmpty()){
            throw new EntityNotFoundException("Aucun article n'a été trouvé avec l'ID " + idArticle, ErrorCodes.ARTICLE_NOT_FOUND);
        }

        List<String> errors = ArticleValidator.validate(ArticleDto.fromEntity(articleOptional.get()));
        if(!errors.isEmpty()){
            throw new InvalidEntityException("Article invalide", ErrorCodes.ARTICLE_NOT_VALID, errors);
        }
        LigneCdeClt ligneCdeCltToSave = ligneCdeClt.get();
        ligneCdeCltToSave.setArticle(articleOptional.get());
        ligneCdeCltRepository.save(ligneCdeCltToSave);

        return commandeClientDto;
    }

    @Override
    @Transactional(readOnly = true)
    public CommandeClientDto findById(Integer id) {
        if (id == null) {
            log.error("Commande client ID is null");
            return null; // ou throw IllegalArgumentException
        }

        CommandeClient commandeClient = commandeClientRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Aucune commande client avec l'ID = " + id + " n'a été trouvée dans la BDD",
                        ErrorCodes.COMMANDE_CLIENT_NOT_FOUND
                ));

        if(commandeClient.getLignecdeclts() != null){
            commandeClient.getLignecdeclts().size();
        }
        return CommandeClientDto.fromEntity(commandeClient);
    }

    @Override
    @Transactional(readOnly = true)
    public CommandeClientDto findByCode(String code) {
        if (!StringUtils.hasLength(code)) {
            log.error("Le code de commande Client est null ou vide");
            throw new IllegalArgumentException("Le code de commande Client ne peut pas être vide");
        }

        CommandeClient commandeClient = commandeClientRepository.findByCode(code)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Aucune commande client avec le code = " + code + " n'a été trouvée dans la BDD",
                        ErrorCodes.COMMANDE_CLIENT_NOT_FOUND
                ));

        if(commandeClient.getLignecdeclts() != null){
            commandeClient.getLignecdeclts().size();
        }
        return CommandeClientDto.fromEntity(commandeClient);
    }

    @Override
    public List<CommandeClientDto> findAll() {
        return commandeClientRepository.findAll().stream()
                .map(CommandeClientDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Integer id) {
        if (id == null) {
            log.error("Commande Client ID is null");
            return;
        }

        if (!commandeClientRepository.existsById(id)) {
            log.warn("Attempted to delete non-existent commande Client with ID: {}", id);
            return;
        }

        commandeClientRepository.deleteById(id);
        log.info("Commande Client with ID {} successfully deleted.", id);
    }

    private void checkIdCommande(Integer idCommande){
        if (idCommande == null) {
            log.error("Commande client ID is null");
            throw new InvalidOperationException("Impossible de modifier l'état d'une commande avec un ID null.", ErrorCodes.COMMANDE_CLIENT_NOT_EDITABLE);
        }
    }

    private void checkIdLigneCommande(Integer idLigneCommande) {
        if (idLigneCommande == null) {
            log.error("L'ID de la ligne de commande client is null");
            throw new InvalidOperationException("Impossible de modifier l'état d'une commande avec une ligne de commande null.", ErrorCodes.COMMANDE_CLIENT_NOT_EDITABLE);
        }
    }

    private void checkIdArticle(Integer idArticle, String msg) {
        if (idArticle == null) {
            log.error("L'ID " + msg + " is null");
            throw new InvalidOperationException("Impossible de modifier la commande client avec un " + msg + "ID article null.", ErrorCodes.COMMANDE_CLIENT_NOT_EDITABLE);
        }
    }

    private CommandeClientDto checkEtatCommande(Integer idCommande) {
        CommandeClientDto commandeClientDto = findById(idCommande);
        if(commandeClientDto.isCommandeLivree()){
            throw new InvalidOperationException("Impossible de modifier la commande lorsqu'elle est livrée.", ErrorCodes.COMMANDE_CLIENT_NOT_EDITABLE);
        }
        return commandeClientDto;
    }

    private Optional<LigneCdeClt> findLigneCommandeClient(Integer idLigneCommande) {
        Optional<LigneCdeClt> ligneCdeCltOptional = ligneCdeCltRepository.findById(idLigneCommande);
        if(ligneCdeCltOptional.isEmpty()){
            throw new EntityNotFoundException("Aucune ligne de commande client n'a été trouvée avec l'ID " + idLigneCommande, ErrorCodes.LIGNE_COMMANDE_CLIENT_NOT_FOUND);
        }
        return ligneCdeCltOptional;
    }
}
