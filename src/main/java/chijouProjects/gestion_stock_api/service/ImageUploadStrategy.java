package chijouProjects.gestion_stock_api.service;

import chijouProjects.gestion_stock_api.model.ImageOwner;
import chijouProjects.gestion_stock_api.model.ImgLink;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ImageUploadStrategy<T extends ImageOwner> {

    // Upload d'une image
    ImgLink handleUpload(T entity, MultipartFile file);

    // Récupérer toutes les images gérées par cette stratégie
    List<ImgLink> findAll();

    // Supprimer une image par son ID (retourne true si supprimé)
    boolean delete(Integer id);

    Class<T> getOwnerClass();// Permet de savoir quel type d'entité cette stratégie gère
}
