package chijouProjects.gestion_stock_api.controller;

import chijouProjects.gestion_stock_api.controller.api.ImgLinkApi;
import chijouProjects.gestion_stock_api.dto.ImageUploadRequest;
import chijouProjects.gestion_stock_api.dto.ImgLinkDto;
import chijouProjects.gestion_stock_api.exception.EntityNotFoundException;
import chijouProjects.gestion_stock_api.model.Entreprise;
import chijouProjects.gestion_stock_api.model.ImageOwner;
import chijouProjects.gestion_stock_api.model.ImgLink;
import chijouProjects.gestion_stock_api.repository.*;
import chijouProjects.gestion_stock_api.service.ImgLinkService;
import chijouProjects.gestion_stock_api.service.impl.ImageManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/*@RestController
public class ImgLinkController implements ImgLinkApi {
    private final ImgLinkService imglinkService;

    @Autowired
    public ImgLinkController(ImgLinkService imglinkService) {
        this.imglinkService = imglinkService;
    }

    @Override
    public ResponseEntity<ImgLinkDto> uploadImage(MultipartFile file, Entreprise entreprise) {
        if (file == null || file.isEmpty()) {
            throw new RuntimeException("Le fichier image est obligatoire pour l'upload.");
        }

        return ResponseEntity.ok(imglinkService.uploadImage(file, entreprise));
    }

    @Override
    public ResponseEntity<ImgLinkDto> findById(Integer id) {
        return ResponseEntity.ok(imglinkService.findById(id));
    }

    @Override
    public ResponseEntity<List<ImgLinkDto>> findAll() {
        return ResponseEntity.ok(imglinkService.findAll());
    }

    @Override
    public ResponseEntity<Void> delete(Integer id) {
        try {
            imglinkService.delete(id);
            return ResponseEntity.noContent().build();
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}*/

@RestController
@Validated
public class ImgLinkController implements ImgLinkApi {

    private final ImageManagerService imageManagerService;
    private final UtilisateurRepository utilisateurRepository;
    private final ClientRepository clientRepository;
    private final FournisseurRepository fournisseurRepository;
    private final ArticleRepository articleRepository;
    private final EntrepriseRepository entrepriseRepository;

    @Autowired
    public ImgLinkController(ImageManagerService imageManagerService,
                             UtilisateurRepository utilisateurRepository,
                             ClientRepository clientRepository,
                             FournisseurRepository fournisseurRepository,
                             ArticleRepository articleRepository,
                             EntrepriseRepository entrepriseRepository) {
        this.imageManagerService = imageManagerService;
        this.utilisateurRepository = utilisateurRepository;
        this.clientRepository = clientRepository;
        this.fournisseurRepository = fournisseurRepository;
        this.articleRepository = articleRepository;
        this.entrepriseRepository = entrepriseRepository;
    }

    @Override
    public ResponseEntity<ImgLinkDto> uploadImage(ImageUploadRequest request) {
        ImageOwner owner = getEntityByTypeAndId(request.getEntityType(), request.getEntityId());
        ImgLink img = imageManagerService.uploadImage(owner, request.getFile());
        return ResponseEntity.ok(ImgLinkDto.fromEntity(img));
    }

    @Override
    public ResponseEntity<ImgLinkDto> findById(Integer id) {
        return ResponseEntity.ok(imageManagerService.findById(id));
    }

    @Override
    public ResponseEntity<List<ImgLinkDto>> findAll() {
        return ResponseEntity.ok(imageManagerService.findAll());
    }

    @Override
    public ResponseEntity<Void> delete(Integer id) {
        try {
            imageManagerService.delete(id);
            return ResponseEntity.noContent().build();
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e){
            return ResponseEntity.status(500).build();
        }
    }

    // Méthode générique pour retrouver l’entité cible
    private ImageOwner getEntityByTypeAndId(String type, Integer id) {
        switch (type.toLowerCase()) {
            case "utilisateur": return utilisateurRepository.findById(id).orElseThrow();
            case "client": return clientRepository.findById(id).orElseThrow();
            case "fournisseur": return fournisseurRepository.findById(id).orElseThrow();
            case "article": return articleRepository.findById(id).orElseThrow();
            case "entreprise": return entrepriseRepository.findById(id).orElseThrow();
            default: throw new RuntimeException("Type d'entité inconnu : " + type);
        }
    }
}
