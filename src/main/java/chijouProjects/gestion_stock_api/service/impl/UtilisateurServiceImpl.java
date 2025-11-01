package chijouProjects.gestion_stock_api.service.impl;

import chijouProjects.gestion_stock_api.dto.FournisseurDto;
import chijouProjects.gestion_stock_api.dto.UtilisateurDto;
import chijouProjects.gestion_stock_api.exception.EntityNotFoundException;
import chijouProjects.gestion_stock_api.exception.ErrorCodes;
import chijouProjects.gestion_stock_api.exception.InvalidEntityException;
import chijouProjects.gestion_stock_api.model.Fournisseur;
import chijouProjects.gestion_stock_api.model.Utilisateur;
import chijouProjects.gestion_stock_api.repository.UtilisateurRepository;
import chijouProjects.gestion_stock_api.service.UtilisateurService;
import chijouProjects.gestion_stock_api.validator.UtilisateurValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class UtilisateurServiceImpl implements UtilisateurService {

    private UtilisateurRepository utilisateurRepository;

    @Autowired
    public UtilisateurServiceImpl(UtilisateurRepository utilisateurRepository) {
        this.utilisateurRepository = utilisateurRepository;
    }
    @Override
    public UtilisateurDto save(UtilisateurDto utilisateurDto) {
        List<String> errors = UtilisateurValidator.validate(utilisateurDto);;
        if(!errors.isEmpty()) {
            log.error("Utilisateur is not valid: {}", utilisateurDto);
            throw new InvalidEntityException("L'utilisateur n'est pas valide", ErrorCodes.UTILISATEUR_NOT_VALID, errors);
        }
        return UtilisateurDto.fromEntity(
                utilisateurRepository.save(
                        UtilisateurDto.toEntity(utilisateurDto)
                )
        );
    }

    @Override
    public UtilisateurDto findById(Integer id) {
        if (id == null) {
            log.error("Utilisateur ID is null");
            return null; // ou throw IllegalArgumentException
        }

        Utilisateur utilisateur = utilisateurRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Aucun utilisateur avec l'ID = " + id + " n'a été trouvé dans la BDD",
                        ErrorCodes.UTILISATEUR_NOT_FOUND
                ));
        return UtilisateurDto.fromEntity(utilisateur);
    }

    @Override
    public UtilisateurDto findByNomUtilisateur(String nom) {
        if (!StringUtils.hasLength(nom)) {
            log.error("Nom de l'utilisateur est null ou vide");
            throw new IllegalArgumentException("Le nom de l'utilisateur ne peut pas être vide");
        }

        Utilisateur utilisateur = utilisateurRepository.findByNomUtilisateur(nom)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Aucun utilisateur avec le nom = " + nom + " n'a été trouvé dans la BDD",
                        ErrorCodes.UTILISATEUR_NOT_FOUND
                ));


        return UtilisateurDto.fromEntity(utilisateur);
    }

    @Override
    public List<UtilisateurDto> findAll() {
        return utilisateurRepository.findAll().stream()
                .map(UtilisateurDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Integer id) {
        if (id == null) {
            log.error("Utilisateur ID is null");
            return;
        }

        if (!utilisateurRepository.existsById(id)) {
            log.warn("Attempted to delete non-existent utilisateur with ID: {}", id);
            return;
        }

        utilisateurRepository.deleteById(id);
        log.info("Utilisateur with ID {} successfully deleted.", id);
    }
}
