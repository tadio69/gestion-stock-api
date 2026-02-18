package chijouProjects.gestion_stock_api.service;

import chijouProjects.gestion_stock_api.model.ImageOwner;
import chijouProjects.gestion_stock_api.model.ImgLink;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ImageUploadStrategy<T extends ImageOwner> {
    ImgLink handleUpload(T entity, MultipartFile file);
    Class<T> getOwnerClass();
}
