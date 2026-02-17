package chijouProjects.gestion_stock_api.service.impl;

import chijouProjects.gestion_stock_api.dto.ImgLinkDto;
import chijouProjects.gestion_stock_api.model.ImageOwner;
import chijouProjects.gestion_stock_api.model.ImgLink;
import chijouProjects.gestion_stock_api.service.ImageUploadStrategy;
import chijouProjects.gestion_stock_api.service.ImgLinkService;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class DefaultImageStrategy implements ImageUploadStrategy<ImageOwner> {
    private final ImgLinkService imgLinkService;

    public DefaultImageStrategy(ImgLinkService imgLinkService) {
        this.imgLinkService = imgLinkService;
    }

    @Override
    public ImgLink handleUpload(ImageOwner entity, MultipartFile file) {
        if (file == null || file.isEmpty()) {
            throw new RuntimeException("Le fichier image est obligatoire pour l'upload.");
        }

        ImgLinkDto dto = imgLinkService.uploadImage(file, entity.getEntreprise());
        ImgLink img = ImgLinkDto.toEntity(dto);
        entity.setImglink(img);

        return img;
    }
}
