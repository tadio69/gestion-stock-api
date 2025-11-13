package chijouProjects.gestion_stock_api.controller;

import chijouProjects.gestion_stock_api.controller.api.ImgLinkApi;
import chijouProjects.gestion_stock_api.dto.ImgLinkDto;
import chijouProjects.gestion_stock_api.service.ImgLinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ImgLinkController implements ImgLinkApi {
    private final ImgLinkService imglinkService;

    @Autowired
    public ImgLinkController(ImgLinkService imglinkService) {
        this.imglinkService = imglinkService;
    }

    @Override
    public ImgLinkDto uploadImage(ImgLinkDto imglinkDto) {
        return imglinkService.uploadImage(imglinkDto);
    }

    @Override
    public ImgLinkDto findById(Integer id) {
        return imglinkService.findById(id);
    }

    @Override
    public List<ImgLinkDto> findAll() {
        return imglinkService.findAll();
    }

    @Override
    public void delete(Integer id) {
        imglinkService.delete(id);
    }
}
