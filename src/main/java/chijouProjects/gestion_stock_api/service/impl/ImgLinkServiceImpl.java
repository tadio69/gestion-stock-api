package chijouProjects.gestion_stock_api.service.impl;

import chijouProjects.gestion_stock_api.dto.ImgLinkDto;
import chijouProjects.gestion_stock_api.model.ImgLink;
import chijouProjects.gestion_stock_api.utils.MultipartInputStreamFileResource;
import chijouProjects.gestion_stock_api.repository.ImgLinkRepository;
import chijouProjects.gestion_stock_api.service.ImgLinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
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

        // ⚠️ 1. Définir RestTemplate comme Bean ou champ privé (pour la performance)
        RestTemplate restTemplate = new RestTemplate();

        try {
            String uploadUrl = "https://imglink.io/upload";

            HttpHeaders headers = new HttpHeaders();
            // Laissez Spring/RestTemplate déterminer Content-Type exact (incluant boundary),
            // ou assurez-vous que vous définissez le bon type de média.
            // Pour Multipart, laisser Spring faire le travail est souvent plus sûr.
            headers.setContentType(MediaType.MULTIPART_FORM_DATA);

            MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();

            // CORRECTION 1: Utilisation correcte de MultipartInputStreamFileResource
            // Une IOException peut survenir ici.
            body.add("file", new MultipartInputStreamFileResource(file));
            body.add("title", file.getOriginalFilename());
            body.add("description", "Uploaded via API");

            HttpEntity<MultiValueMap<String, Object>> request = new HttpEntity<>(body, headers);

            // CORRECTION 2: Utilisation d'un type plus précis si possible, sinon Map<String, Object> est OK
            // NOTE: Si imglink.io retourne un JSON complexe, Map<String, Object> est souvent le choix par défaut.
            ResponseEntity<Map> response = restTemplate.postForEntity(uploadUrl, request, Map.class);

            // Vérification de la réponse
            if (response.getStatusCode().isError() || response.getBody() == null) {
                throw new RuntimeException("L'API ImgLink a retourné une erreur ou une réponse vide. Statut: " + response.getStatusCodeValue());
            }

            // CORRECTION 3: Vérification et cast sécurisé des données JSON
            Map<String, Object> responseBody = response.getBody();
            List<Map<String, Object>> images = (List<Map<String, Object>>) responseBody.get("images");

            if (images == null || images.isEmpty()) {
                throw new RuntimeException("La réponse ImgLink ne contient pas de section 'images' valide.");
            }

            Map<String, Object> img = images.get(0);
            String directUrl = (String) img.get("direct_url");

            if (directUrl == null) {
                throw new RuntimeException("La réponse ImgLink ne contient pas 'direct_url'.");
            }

            // 4. Enregistrement dans la base de données locale
            ImgLink entity = new ImgLink();
            entity.setUrl(directUrl);
            entity.setFilename(file.getOriginalFilename());
            entity.setUploadedat(Instant.now());
            entity.setIdentreprise(identreprise);

            ImgLink savedEntity = imgLinkRepository.save(entity);

            return ImgLinkDto.fromEntity(savedEntity);

        } catch (IOException e) {
            // Gérer spécifiquement les erreurs d'entrée/sortie du fichier
            throw new RuntimeException("Erreur de lecture du fichier Multipart: " + e.getMessage(), e);
        } catch (RestClientException e) {
            // Gérer les erreurs de communication HTTP (connexion, timeout, 4xx, 5xx)
            throw new RuntimeException("Erreur de communication avec l'API ImgLink: " + e.getMessage(), e);
        } catch (Exception e) {
            // Gérer toutes les autres erreurs (cast, null pointer, etc.)
            throw new RuntimeException("Erreur lors de l'upload et du traitement de l'image: " + e.getMessage(), e);
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
