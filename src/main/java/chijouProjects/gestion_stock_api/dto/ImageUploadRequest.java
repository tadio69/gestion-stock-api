package chijouProjects.gestion_stock_api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.web.multipart.MultipartFile;

public class ImageUploadRequest {
    @NotNull(message = "Le fichier image est obligatoire")
    private MultipartFile file;

    @NotBlank(message = "Le type d'entité est obligatoire")
    private String entityType;

    @NotNull(message = "L'ID de l'entité est obligatoire")
    private Integer entityId;

    // Getters / Setters
    public MultipartFile getFile() { return file; }
    public void setFile(MultipartFile file) { this.file = file; }

    public String getEntityType() { return entityType; }
    public void setEntityType(String entityType) { this.entityType = entityType; }

    public Integer getEntityId() { return entityId; }
    public void setEntityId(Integer entityId) { this.entityId = entityId; }
}
