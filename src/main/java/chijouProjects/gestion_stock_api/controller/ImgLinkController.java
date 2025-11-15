package chijouProjects.gestion_stock_api.controller;

import chijouProjects.gestion_stock_api.controller.api.ImgLinkApi;
import chijouProjects.gestion_stock_api.dto.ImgLinkDto;
import chijouProjects.gestion_stock_api.service.ImgLinkService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public ImgLinkDto uploadImage(MultipartFile file, Integer identreprise) {
        if (file == null || file.isEmpty()) {
            throw new RuntimeException("Le fichier image est obligatoire pour l'upload.");
        }

        return imglinkService.uploadImage(file, identreprise);
    }

    @Override
    public ImgLinkDto findById(Integer id) {
        return imglinkService.findById(id);
    }

    @Override
    public List<ImgLinkDto> findAll() {
        return imglinkService.findAll();
    }

    @Override
    public void delete(Integer id) {
        imglinkService.delete(id);
    }
}
