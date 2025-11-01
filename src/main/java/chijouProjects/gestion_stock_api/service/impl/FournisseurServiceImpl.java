package chijouProjects.gestion_stock_api.service.impl;

import chijouProjects.gestion_stock_api.dto.EntrepriseDto;
import chijouProjects.gestion_stock_api.dto.FournisseurDto;
import chijouProjects.gestion_stock_api.exception.EntityNotFoundException;
import chijouProjects.gestion_stock_api.exception.ErrorCodes;
import chijouProjects.gestion_stock_api.exception.InvalidEntityException;
import chijouProjects.gestion_stock_api.model.Entreprise;
import chijouProjects.gestion_stock_api.model.Fournisseur;
import chijouProjects.gestion_stock_api.repository.FournisseurRepository;
import chijouProjects.gestion_stock_api.service.FournisseurService;
import chijouProjects.gestion_stock_api.validator.EntrepriseValidator;
import chijouProjects.gestion_stock_api.validator.FournisseurValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class FournisseurServiceImpl implements FournisseurService {

    private FournisseurRepository fournisseurRepository;

    @Autowired
    public FournisseurServiceImpl(FournisseurRepository fournisseurRepository) {
        this.fournisseurRepository = fournisseurRepository;
    }
    @Override
    public FournisseurDto save(FournisseurDto fournisseurDto) {
        List<String> errors = FournisseurValidator.validate(fournisseurDto);;
        if(!errors.isEmpty()) {
            log.error("Fournisseur is not valid: {}", fournisseurDto);
            throw new InvalidEntityException("Le Fournisseur n'est pas valide", ErrorCodes.FOURNISSEUR_NOT_VALID, errors);
        }
        return FournisseurDto.fromEntity(
                fournisseurRepository.save(
                        FournisseurDto.toEntity(fournisseurDto)
                )
        );
    }

    @Override
    public FournisseurDto findById(Integer id) {
        if (id == null) {
            log.error("Fournisseur ID is null");
            return null; // ou throw IllegalArgumentException
        }

        Fournisseur fournisseur = fournisseurRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Aucun fournisseur avec l'ID = " + id + " n'a été trouvé dans la BDD",
                        ErrorCodes.FOURNISSEUR_NOT_FOUND
                ));
        return FournisseurDto.fromEntity(fournisseur);
    }

    @Override
    public FournisseurDto findByNomFournisseur(String nom) {
        if (!StringUtils.hasLength(nom)) {
            log.error("Le nom du fournisseur est null ou vide");
            throw new IllegalArgumentException("Le nom du fournisseur ne peut pas être vide");
        }

        Fournisseur fournisseur = fournisseurRepository.findByNomFournisseur(nom)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Aucun fournisseur avec le nom = " + nom + " n'a été trouvé dans la BDD",
                        ErrorCodes.FOURNISSEUR_NOT_FOUND
                ));


        return FournisseurDto.fromEntity(fournisseur);
    }

    @Override
    public List<FournisseurDto> findAll() {
        return fournisseurRepository.findAll().stream()
                .map(FournisseurDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Integer id) {
        if (id == null) {
            log.error("Fournisseur ID is null");
            return;
        }

        if (!fournisseurRepository.existsById(id)) {
            log.warn("Attempted to delete non-existent fournisseur with ID: {}", id);
            return;
        }

        fournisseurRepository.deleteById(id);
        log.info("Fournisseur with ID {} successfully deleted.", id);
    }
}
