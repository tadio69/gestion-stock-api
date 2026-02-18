package chijouProjects.gestion_stock_api.service.impl;

import chijouProjects.gestion_stock_api.dto.ImgLinkDto;
import chijouProjects.gestion_stock_api.model.ImageOwner;
import chijouProjects.gestion_stock_api.model.ImgLink;
import chijouProjects.gestion_stock_api.service.ImageStrategyFactory;
import chijouProjects.gestion_stock_api.service.ImageUploadStrategy;
import chijouProjects.gestion_stock_api.service.ImgLinkService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;
import java.util.Optional;


@Service
public class ImageManagerService {

    private final ImageStrategyFactory factory;
    private final ImgLinkService imgLinkService;

    public ImageManagerService(ImageStrategyFactory factory,
                               ImgLinkService imgLinkService) {
        this.factory = factory;
        this.imgLinkService = imgLinkService;
    }

    // Upload
    @SuppressWarnings("unchecked")
    public <T extends ImageOwner> ImgLink uploadImage(T entity, MultipartFile file) {

        Class<?> clazz = org.hibernate.Hibernate.getClass(entity);

        ImageUploadStrategy<T> strategy =
                factory.getStrategyFor((Class<T>) clazz)
                        .orElseThrow(() -> new RuntimeException(
                                "Stratégie introuvable pour " + clazz.getSimpleName()));

        return strategy.handleUpload(entity, file);
    }

    // Récupérer un ImgLink par son ID
    public ImgLinkDto findById(Integer id){
        return imgLinkService.findById(id);
    }

    // Récupérer toutes les images
    public List<ImgLinkDto> findAll(){
        return imgLinkService.findAll();
    }

    // Supprimer une image par son ID
    public void delete(Integer id){
        imgLinkService.delete(id);
    }
}
