package chijouProjects.gestion_stock_api.service;

import chijouProjects.gestion_stock_api.dto.ArticleDto;
import chijouProjects.gestion_stock_api.dto.ImgLinkDto;

import java.util.List;

public interface ImgLinkService {
    /**
     * Upload une image en base64 sur ImgLink et retourne la réponse JSON.
     *
     * @param base64Image l'image encodée en base64
     * @param name nom optionnel de l'image
     * @return la réponse JSON de l'API ImgLink
     */

    // Upload via base64 et nom
    String uploadImage(String base64Image, String name);

    // Upload via DTO (retourne le DTO mis à jour avec URL)
    ImgLinkDto uploadImage(ImgLinkDto imglinkDto);

    ImgLinkDto save(ImgLinkDto imgLinkDto);

    ImgLinkDto findById(Integer id);

    List<ImgLinkDto> findAll();

    void delete(Integer id);
}
