package chijouProjects.gestion_stock_api.service.impl;

import chijouProjects.gestion_stock_api.dto.ImgLinkDto;
import chijouProjects.gestion_stock_api.model.ImgLink;
import chijouProjects.gestion_stock_api.repository.ImgLinkRepository;
import chijouProjects.gestion_stock_api.service.ImgLinkService;
import chijouProjects.gestion_stock_api.validator.ImgLinkValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ImgLinkServiceImpl implements ImgLinkService {
    @Value("${imglink.api.key}")
    private String apiKey;

    private final RestTemplate restTemplate = new RestTemplate();

    private ImgLinkRepository imgLinkRepository;

    @Autowired
    public ImgLinkServiceImpl(ImgLinkRepository imgLinkRepository) {
        this.imgLinkRepository = imgLinkRepository;
    }

    @Override
    public String uploadImage(String base64Image, String name) {
        String url = "https://api.imglink.io/v1/upload";

        MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
        body.add("image", base64Image);
        if (name != null) {
            body.add("name", name);
        }

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);
        headers.set("Authorization", "Bearer " + apiKey);

        HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(body, headers);

        ResponseEntity<String> response = restTemplate.postForEntity(url, requestEntity, String.class);

        if (response.getStatusCode() == HttpStatus.OK) {
            return response.getBody();
        } else {
            throw new RuntimeException("Erreur lors de l'upload sur ImgLink: " + response.getStatusCode());
        }
    }

    @Override
    public ImgLinkDto uploadImage(ImgLinkDto imglinkDto) {
        List<String> errors = ImgLinkValidator.validate(imglinkDto);
        if (!errors.isEmpty()) {
            throw new IllegalArgumentException(errors.toString());
        }

        // Upload via API
        String url = uploadImage(imglinkDto.getBase64(), imglinkDto.getFilename());
        imglinkDto.setUrl(url);

        // Sauvegarde en base
        ImgLink saved = imgLinkRepository.save(ImgLinkDto.toEntity(imglinkDto));
        return ImgLinkDto.fromEntity(saved);
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
