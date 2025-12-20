package chijouProjects.gestion_stock_api.service;

import chijouProjects.gestion_stock_api.dto.ImgLinkDto;
import chijouProjects.gestion_stock_api.model.Entreprise;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ImgLinkService {

    ImgLinkDto uploadImage(MultipartFile file, Entreprise entreprise);

    ImgLinkDto save(ImgLinkDto imgLinkDto);

    ImgLinkDto findById(Integer id);

    List<ImgLinkDto> findAll();

    void delete(Integer id);
}
