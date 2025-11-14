package chijouProjects.gestion_stock_api.dto;

import chijouProjects.gestion_stock_api.model.ImgLink;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ImgLinkDto {
    private Integer id;
    private String filename; // nom optionnel de l'image
    private String url;      // URL renvoyée par ImgLink
    private Integer identreprise;

    // Mapping vers entité si tu as une entité ImgLink
    public static ImgLinkDto fromEntity(ImgLink img) {
        if (img == null) return null;
        return ImgLinkDto.builder()
                .id(img.getId())
                .filename(img.getFilename())
                .url(img.getUrl())
                .identreprise(img.getIdentreprise())
                .build();
    }

    public static ImgLink toEntity(ImgLinkDto dto) {
        if (dto == null) return null;
        ImgLink img = new ImgLink();
        img.setId(dto.getId());
        img.setFilename(dto.getFilename());
        img.setUrl(dto.getUrl());
        img.setIdentreprise(dto.getIdentreprise());
        return img;
    }
}
