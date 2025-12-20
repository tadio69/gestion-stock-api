package chijouProjects.gestion_stock_api.service.impl;

import chijouProjects.gestion_stock_api.dto.ClientDto;
import chijouProjects.gestion_stock_api.exception.EntityNotFoundException;
import chijouProjects.gestion_stock_api.exception.ErrorCodes;
import chijouProjects.gestion_stock_api.exception.InvalidEntityException;
import chijouProjects.gestion_stock_api.model.Client;
import chijouProjects.gestion_stock_api.repository.ClientRepository;
import chijouProjects.gestion_stock_api.service.ClientService;
import chijouProjects.gestion_stock_api.validator.ClientValidator;
import jakarta.persistence.EntityManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ClientSericeImpl implements ClientService {

    private ClientRepository clientRepository;
    private final EntityManager entityManager;

    @Autowired
    public ClientSericeImpl(ClientRepository clientRepository, EntityManager entityManager) {
        this.clientRepository = clientRepository;
        this.entityManager = entityManager;
    }

    @Override
    public ClientDto save(ClientDto clientDto) {
        List<String> errors = ClientValidator.validate(clientDto);;
        if(!errors.isEmpty()) {
            log.error("Client is not valid: {}", clientDto);
            throw new InvalidEntityException("le client n'est pas valide", ErrorCodes.CLIENT_NOT_VALID, errors);
        }
        return ClientDto.fromEntity(
                clientRepository.save(
                        ClientDto.toEntity(clientDto)
                )
        );
    }

    @Override
    @Transactional(readOnly = true)
    public ClientDto findById(Integer id) {
        if (id == null) {
            log.error("Client ID is null");
            return null; // ou throw IllegalArgumentException
        }
        /*
        return clientRepository.findById(id)
                .map(ClientDto::fromEntity) // transforme Client -> ClientDto si présent
                .orElseThrow(() -> new EntityNotFoundException(
                        "Aucun client avec l'ID = " + id + " n'a été trouvé dans la BDD",
                        ErrorCodes.CLIENT_NOT_FOUND
                ));
         */
        Client client = clientRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Aucun client avec l'ID = " + id + " n'a été trouvé dans la BDD",
                        ErrorCodes.CLIENT_NOT_FOUND
                ));

        if(client.getCommandeclients() != null) {
            client.getCommandeclients().size();
        }

        return ClientDto.fromEntity(client);
    }

    @Override
    @Transactional(readOnly = true)
    public ClientDto findByNom(String nom) {
        if (!StringUtils.hasLength(nom)) {
            log.error("Nom client est null ou vide");
            throw new IllegalArgumentException("Le nom de client ne peut pas être vide");
        }

        Client client = clientRepository.findByNom(nom)
                .orElseThrow(() -> new EntityNotFoundException(
                "Aucun client avec le nom = " + nom + " n'a été trouvé dans la BDD",
                ErrorCodes.CLIENT_NOT_FOUND
        ));

        if(client.getCommandeclients() != null) {
            client.getCommandeclients().size();
        }

        return ClientDto.fromEntity(client);
    }

    @Override
    public List<ClientDto> findAll() {
        return clientRepository.findAll().stream()
                .map(ClientDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Integer id) {
        if (id == null) {
            log.error("Client ID is null");
            return;
        }

        if (!clientRepository.existsById(id)) {
            log.warn("Attempted to delete non-existent client with ID: {}", id);
            return;
        }

        clientRepository.deleteById(id);
        log.info("Client with ID {} successfully deleted.", id);
    }
}
