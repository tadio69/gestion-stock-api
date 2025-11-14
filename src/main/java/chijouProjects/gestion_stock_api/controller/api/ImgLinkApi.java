package chijouProjects.gestion_stock_api.controller.api;

import chijouProjects.gestion_stock_api.dto.ImgLinkDto;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

import static chijouProjects.gestion_stock_api.utils.Constants.APP_ROOT;

public interface ImgLinkApi {
    @PostMapping(value = APP_ROOT + "/photos/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    ImgLinkDto uploadImage(
            @RequestParam("file") MultipartFile file,
            @RequestParam(value = "identreprise") Integer identreprise
    );

    @GetMapping(value = APP_ROOT + "/photos/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    ImgLinkDto findById(@PathVariable("id") Integer id);

    @GetMapping(value = APP_ROOT + "/photos/all", produces = MediaType.APPLICATION_JSON_VALUE)
    List<ImgLinkDto> findAll();

    @DeleteMapping(value = APP_ROOT + "/photos/delete/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    void delete(@PathVariable("id") Integer id);
}
