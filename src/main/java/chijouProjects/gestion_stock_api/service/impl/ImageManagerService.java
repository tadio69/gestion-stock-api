package chijouProjects.gestion_stock_api.service.impl;

import chijouProjects.gestion_stock_api.dto.ImgLinkDto;
import chijouProjects.gestion_stock_api.model.ImageOwner;
import chijouProjects.gestion_stock_api.model.ImgLink;
import chijouProjects.gestion_stock_api.service.ImageStrategyFactory;
import chijouProjects.gestion_stock_api.service.ImageUploadStrategy;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ImageManagerService {

    private final ImageStrategyFactory factory;

    public ImageManagerService(ImageStrategyFactory factory) {
        this.factory = factory;
    }

    // Upload
    @SuppressWarnings("unchecked")
    public <T extends ImageOwner> ImgLink uploadImage(T entity, MultipartFile file) {
        ImageUploadStrategy<T> strategy = (ImageUploadStrategy<T>)
                factory.getStrategy((Class<T>) entity.getClass())
                        .orElseThrow(() -> new RuntimeException(
                                "Stratégie introuvable pour " + entity.getClass().getSimpleName()));
        return strategy.handleUpload(entity, file);
    }


    // Récupérer un ImgLink par son ID
    public ImgLinkDto findById(Integer id) {
        // On peut parcourir toutes les stratégies et tenter de récupérer le fichier
        return factory.getAllStrategies().stream()
                .map(ImageUploadStrategy::findAll) // toutes les images de cette stratégie
                .flatMap(List::stream)
                .filter(img -> img.getId().equals(id))
                .findFirst()
                .map(ImgLinkDto::fromEntity)
                .orElseThrow(() -> new RuntimeException("Imglink introuvable avec id=" + id));
    }

    // Récupérer toutes les images
    public List<ImgLinkDto> findAll() {
        return factory.getAllStrategies().stream()
                .map(ImageUploadStrategy::findAll) // toutes les images de chaque stratégie
                .flatMap(List::stream)
                .map(ImgLinkDto::fromEntity)
                .collect(Collectors.toList());
    }

    // Supprimer une image par son ID
    public void delete(Integer id) {
        boolean deleted = factory.getAllStrategies().stream()
                .anyMatch(strategy -> strategy.delete(id)); // delete retourne true si supprimé

        if (!deleted) {
            throw new RuntimeException("Impossible de supprimer l'Imglink avec id=" + id);
        }
    }
}
