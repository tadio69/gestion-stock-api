package chijouProjects.gestion_stock_api.service.impl;

import chijouProjects.gestion_stock_api.dto.ImgLinkDto;
import chijouProjects.gestion_stock_api.model.ImgLink;
import chijouProjects.gestion_stock_api.model.MultipartInputStreamFileResource;
import chijouProjects.gestion_stock_api.repository.ImgLinkRepository;
import chijouProjects.gestion_stock_api.service.ImgLinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.time.Instant;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ImgLinkServiceImpl implements ImgLinkService {

    private ImgLinkRepository imgLinkRepository;

    @Autowired
    public ImgLinkServiceImpl(ImgLinkRepository imgLinkRepository) {
        this.imgLinkRepository = imgLinkRepository;
    }

    @Override
    public ImgLinkDto uploadImage(MultipartFile file, Integer identreprise) {
        try {
            String uploadUrl = "https://imglink.io/upload";

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.MULTIPART_FORM_DATA);

            MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
            body.add("file", new MultipartInputStreamFileResource(file));
            body.add("title", file.getOriginalFilename());
            body.add("description", "Uploaded via API");

            HttpEntity<MultiValueMap<String, Object>> request = new HttpEntity<>(body, headers);

            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<Map> response = restTemplate.postForEntity(uploadUrl, request, Map.class);

            List<Map> images = (List<Map>) response.getBody().get("images");
            Map img = images.get(0);

            String directUrl = (String) img.get("direct_url");

            ImgLink entity = new ImgLink();
            entity.setUrl(directUrl);
            entity.setFilename(file.getOriginalFilename());
            entity.setUploadedAt(Instant.now());
            entity.setIdentreprise(identreprise);

            imgLinkRepository.save(entity);

            return ImgLinkDto.fromEntity(entity);

        } catch (Exception e) {
            throw new RuntimeException("Erreur upload : " + e.getMessage());
        }
    }

    @Override
    public ImgLinkDto save(ImgLinkDto imgLinkDto) {
        return ImgLinkDto.fromEntity(imgLinkRepository.save(ImgLinkDto.toEntity(imgLinkDto)));
    }

    @Override
    public ImgLinkDto findById(Integer id) {
        return imgLinkRepository.findById(id)
                .map(ImgLinkDto::fromEntity)
                .orElse(null);
    }

    @Override
    public List<ImgLinkDto> findAll() {
        return imgLinkRepository.findAll().stream()
                .map(ImgLinkDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Integer id) {
        imgLinkRepository.deleteById(id);
    }
}
