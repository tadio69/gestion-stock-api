package chijouProjects.gestion_stock_api.service.impl;

import chijouProjects.gestion_stock_api.dto.CommandeClientDto;
import chijouProjects.gestion_stock_api.dto.EntrepriseDto;
import chijouProjects.gestion_stock_api.dto.LigneCdeCltDto;
import chijouProjects.gestion_stock_api.exception.EntityNotFoundException;
import chijouProjects.gestion_stock_api.exception.ErrorCodes;
import chijouProjects.gestion_stock_api.exception.InvalidEntityException;
import chijouProjects.gestion_stock_api.model.*;
import chijouProjects.gestion_stock_api.repository.ArticleRepository;
import chijouProjects.gestion_stock_api.repository.ClientRepository;
import chijouProjects.gestion_stock_api.repository.CommandeClientRepository;
import chijouProjects.gestion_stock_api.repository.LigneCdeCltRepository;
import chijouProjects.gestion_stock_api.service.CommandeClientService;
import chijouProjects.gestion_stock_api.validator.CommandeClientValidator;
import chijouProjects.gestion_stock_api.validator.EntrepriseValidator;
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
public class CommandeClientServiceImpl implements CommandeClientService {

    private CommandeClientRepository commandeClientRepository;
    private LigneCdeCltRepository ligneCdeCltRepository;
    private ClientRepository clientRepository;
    private ArticleRepository articleRepository;

    @Autowired
    public CommandeClientServiceImpl(CommandeClientRepository commandeClientRepository, ClientRepository clientRepository, ArticleRepository articleRepository, LigneCdeCltRepository ligneCdeCltRepository) {
        this.commandeClientRepository = commandeClientRepository;
        this.clientRepository = clientRepository;
        this.articleRepository = articleRepository;
        this.ligneCdeCltRepository = ligneCdeCltRepository;
    }

    @Override
    public CommandeClientDto save(CommandeClientDto commandeClientDto) {
        List<String> errors = CommandeClientValidator.validate(commandeClientDto);;
        if(!errors.isEmpty()) {
            log.error("Commande Client is not valid: {}", commandeClientDto);
            throw new InvalidEntityException("Commande Client n'est pas valide", ErrorCodes.LIGNE_COMMANE_CLIENT_NOT_VALID, errors);
        }
        Optional<Client> client = clientRepository.findById(commandeClientDto.getClient().getId());

        if(client.isEmpty()) {
            log.warn("Client with ID {} was not found in the DBB", commandeClientDto.getClient().getId());
            throw new EntityNotFoundException("Aucun client avec l'ID " + commandeClientDto.getClient().getId() + " n'a été trouvé dans la BDD");
        }

        List<String> articleErrors = new ArrayList<>();

        if(commandeClientDto.getLignecdeclts() != null){
            commandeClientDto.getLignecdeclts().forEach(lignecdeclt -> {
                if(lignecdeclt.getArticle() != null){
                    Optional<Article> article = articleRepository.findById(lignecdeclt.getArticle().getId());
                    if(article.isEmpty()) {
                        articleErrors.add("L'article avec l'ID " + lignecdeclt.getArticle().getId() + " n'existe pas.");
                    }
                } else {
                    articleErrors.add("Impossible d'enregistrer une commande avec un article NULL.");
                }
            });
        }

        if(!articleErrors.isEmpty()) {
            log.warn("CommandeClient is not valid: {}", commandeClientDto);
            throw new InvalidEntityException("Au moins un des articles n'existe pas dans la BDD ", ErrorCodes.ARTICLE_NOT_FOUND, articleErrors);
        }

        CommandeClient saveCmdClt = commandeClientRepository.save(CommandeClientDto.toEntity(commandeClientDto));

        if(commandeClientDto.getLignecdeclts() != null){
            commandeClientDto.getLignecdeclts().forEach(lignecdeclt -> {
                LigneCdeClt ligneCdeClt = LigneCdeCltDto.toEntity(lignecdeclt);
                ligneCdeClt.setCommandeclient(saveCmdClt);
                ligneCdeCltRepository.save(ligneCdeClt);
            });
        }

        return CommandeClientDto.fromEntity(saveCmdClt);
    }

    @Override
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
        return CommandeClientDto.fromEntity(commandeClient);
    }

    @Override
    public CommandeClientDto findByCode(String code) {
        if (!StringUtils.hasLength(code)) {
            log.error("Le code de commandeClient est null ou vide");
            throw new IllegalArgumentException("Le code de commandeClient ne peut pas être vide");
        }

        CommandeClient commandeClient = commandeClientRepository.findByCode(code)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Aucune commande client avec le code = " + code + " n'a été trouvée dans la BDD",
                        ErrorCodes.COMMANDE_CLIENT_NOT_FOUND
                ));


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
            log.error("CommandeClient ID is null");
            return;
        }

        if (!commandeClientRepository.existsById(id)) {
            log.warn("Attempted to delete non-existent commandeClient with ID: {}", id);
            return;
        }

        commandeClientRepository.deleteById(id);
        log.info("CommandeClient with ID {} successfully deleted.", id);
    }
}
