package chijouProjects.gestion_stock_api.controller;

import chijouProjects.gestion_stock_api.controller.api.ImgLinkApi;
import chijouProjects.gestion_stock_api.dto.ImgLinkDto;
import chijouProjects.gestion_stock_api.exception.EntityNotFoundException;
import chijouProjects.gestion_stock_api.model.Entreprise;
import chijouProjects.gestion_stock_api.service.ImgLinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
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
}
