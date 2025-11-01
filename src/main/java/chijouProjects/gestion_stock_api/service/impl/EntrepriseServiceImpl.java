package chijouProjects.gestion_stock_api.service.impl;

import chijouProjects.gestion_stock_api.dto.ClientDto;
import chijouProjects.gestion_stock_api.dto.EntrepriseDto;
import chijouProjects.gestion_stock_api.exception.EntityNotFoundException;
import chijouProjects.gestion_stock_api.exception.ErrorCodes;
import chijouProjects.gestion_stock_api.exception.InvalidEntityException;
import chijouProjects.gestion_stock_api.model.Client;
import chijouProjects.gestion_stock_api.model.Entreprise;
import chijouProjects.gestion_stock_api.repository.EntrepriseRepository;
import chijouProjects.gestion_stock_api.service.EntrepriseService;
import chijouProjects.gestion_stock_api.validator.EntrepriseValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class EntrepriseServiceImpl implements EntrepriseService {

    private EntrepriseRepository entrepriseRepository;

    @Autowired
    public EntrepriseServiceImpl(EntrepriseRepository entrepriseRepository) {
        this.entrepriseRepository = entrepriseRepository;
    }

    @Override
    public EntrepriseDto save(EntrepriseDto entrepriseDto) {
        List<String> errors = EntrepriseValidator.validate(entrepriseDto);;
        if(!errors.isEmpty()) {
            log.error("Entreprise is not valid: {}", entrepriseDto);
            throw new InvalidEntityException("L'Entreprise n'est pas valide", ErrorCodes.ENTREPRISE_NOT_VALID, errors);
        }
        return EntrepriseDto.fromEntity(
                entrepriseRepository.save(
                        EntrepriseDto.toEntity(entrepriseDto)
                )
        );
    }

    @Override
    public EntrepriseDto findById(Integer id) {
        if (id == null) {
            log.error("Entreprise ID is null");
            return null; // ou throw IllegalArgumentException
        }

        Entreprise entreprise = entrepriseRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Aucune entreprise avec l'ID = " + id + " n'a été trouvée dans la BDD",
                        ErrorCodes.ENTREPRISE_NOT_FOUND
                ));
        return EntrepriseDto.fromEntity(entreprise);
    }

    @Override
    public EntrepriseDto findByDescription(String description) {
        if (!StringUtils.hasLength(description)) {
            log.error("La description de l'entreprise est null ou vide");
            throw new IllegalArgumentException("La description de l'entreprise ne peut pas être vide");
        }

        Entreprise entreprise = entrepriseRepository.findByDescription(description)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Aucune entreprise avec la description = " + description + " n'a été trouvée dans la BDD",
                        ErrorCodes.ENTREPRISE_NOT_FOUND
                ));


        return EntrepriseDto.fromEntity(entreprise);
    }

    @Override
    public EntrepriseDto findByCodeFiscal(String codeFiscal) {
        if (!StringUtils.hasLength(codeFiscal)) {
            log.error("Le code fiscal est null ou vide");
            throw new IllegalArgumentException("Le code fiscal ne peut pas être vide");
        }

        Entreprise entreprise = entrepriseRepository.findByCodeFiscal(codeFiscal)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Aucune entreprise avec le codeFiscal = " + codeFiscal + " n'a été trouvée dans la BDD",
                        ErrorCodes.ENTREPRISE_NOT_FOUND
                ));

        return EntrepriseDto.fromEntity(entreprise);
    }

    @Override
    public List<EntrepriseDto> findAll() {
        return entrepriseRepository.findAll().stream()
                .map(EntrepriseDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Integer id) {
        if (id == null) {
            log.error("Entreprise ID is null");
            return;
        }

        if (!entrepriseRepository.existsById(id)) {
            log.warn("Attempted to delete non-existent entreprise with ID: {}", id);
            return;
        }

        entrepriseRepository.deleteById(id);
        log.info("Entreprise with ID {} successfully deleted.", id);
    }
}
