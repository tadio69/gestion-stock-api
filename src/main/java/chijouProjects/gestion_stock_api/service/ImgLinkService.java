package chijouProjects.gestion_stock_api.service;

import chijouProjects.gestion_stock_api.dto.ArticleDto;
import chijouProjects.gestion_stock_api.dto.ImgLinkDto;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ImgLinkService {

    ImgLinkDto uploadImage(MultipartFile file, Integer identreprise);

    ImgLinkDto save(ImgLinkDto imgLinkDto);

    ImgLinkDto findById(Integer id);

    List<ImgLinkDto> findAll();

    void delete(Integer id);
}
