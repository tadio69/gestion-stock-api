package chijouProjects.gestion_stock_api.service.impl;

import chijouProjects.gestion_stock_api.dto.ClientDto;
import chijouProjects.gestion_stock_api.dto.EntrepriseDto;
import chijouProjects.gestion_stock_api.dto.RoleDto;
import chijouProjects.gestion_stock_api.dto.UtilisateurDto;
import chijouProjects.gestion_stock_api.exception.EntityNotFoundException;
import chijouProjects.gestion_stock_api.exception.ErrorCodes;
import chijouProjects.gestion_stock_api.exception.InvalidEntityException;
import chijouProjects.gestion_stock_api.model.Client;
import chijouProjects.gestion_stock_api.model.Entreprise;
import chijouProjects.gestion_stock_api.model.Utilisateur;
import chijouProjects.gestion_stock_api.repository.EntrepriseRepository;
import chijouProjects.gestion_stock_api.repository.RoleRepository;
import chijouProjects.gestion_stock_api.service.EntrepriseService;
import chijouProjects.gestion_stock_api.service.UtilisateurService;
import chijouProjects.gestion_stock_api.validator.EntrepriseValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class EntrepriseServiceImpl implements EntrepriseService {

    private EntrepriseRepository entrepriseRepository;
    private UtilisateurService utilisateurService;
    private RoleRepository roleRepository;

    @Autowired
    public EntrepriseServiceImpl(EntrepriseRepository entrepriseRepository, RoleRepository roleRepository, UtilisateurService utilisateurService) {
        this.entrepriseRepository = entrepriseRepository;
        this.roleRepository = roleRepository;
        this.utilisateurService = utilisateurService;
    }

    @Override
    public EntrepriseDto save(EntrepriseDto entrepriseDto) {
        List<String> errors = EntrepriseValidator.validate(entrepriseDto);;
        if(!errors.isEmpty()) {
            log.error("Entreprise is not valid: {}", entrepriseDto);
            throw new InvalidEntityException("L'Entreprise n'est pas valide", ErrorCodes.ENTREPRISE_NOT_VALID, errors);
        }

        EntrepriseDto savedEntreprise = EntrepriseDto.fromEntity(
                entrepriseRepository.save(
                        EntrepriseDto.toEntity(entrepriseDto)
                )
        );

        UtilisateurDto utilisateurDto = fromEntreprise(savedEntreprise);

        UtilisateurDto savedUser = utilisateurService.save(utilisateurDto, true);

        RoleDto roleDto = RoleDto.builder()
                .rolename("ADMIN")
                .idutilisateur(savedUser.getId())
                .identreprise(savedEntreprise.getId())
                .build();
        roleRepository.save(RoleDto.toEntity(roleDto));

        return savedEntreprise;
    }

    private UtilisateurDto fromEntreprise(EntrepriseDto entrepriseDto){
        //permet de créer automatiquement un utilisateur lié à une entreprise au
        //moment de la création de cette entreprise
        return UtilisateurDto.builder()
                .adressedto(entrepriseDto.getAdressedto())
                .nom(entrepriseDto.getNom())
                .prenom(entrepriseDto.getCodefiscal())
                .email(entrepriseDto.getEmail())
                .motdepasse(generateRandomPassword())
                .identreprise(entrepriseDto.getId())
                .datenaissance(Instant.now())
                .idimglink(entrepriseDto.getIdimglink())
                .build();
    }

    private String generateRandomPassword(){
        return "mdp_à_générer_automatiquement_fonction_à_définir";//pour l'instant on le fixe à cette chaîne en attendant créer le générateur de mots de passe
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
    public EntrepriseDto findByCodefiscal(String codefiscal) {
        if (!StringUtils.hasLength(codefiscal)) {
            log.error("Le code fiscal est null ou vide");
            throw new IllegalArgumentException("Le code fiscal ne peut pas être vide");
        }

        Entreprise entreprise = entrepriseRepository.findByCodefiscal(codefiscal)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Aucune entreprise avec le codeFiscal = " + codefiscal + " n'a été trouvée dans la BDD",
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
