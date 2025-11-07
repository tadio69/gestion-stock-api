package chijouProjects.gestion_stock_api.service.impl;

import chijouProjects.gestion_stock_api.dto.CommandeClientDto;
import chijouProjects.gestion_stock_api.dto.LigneVenteDto;
import chijouProjects.gestion_stock_api.dto.VenteDto;
import chijouProjects.gestion_stock_api.exception.EntityNotFoundException;
import chijouProjects.gestion_stock_api.exception.ErrorCodes;
import chijouProjects.gestion_stock_api.exception.InvalidEntityException;
import chijouProjects.gestion_stock_api.model.Article;
import chijouProjects.gestion_stock_api.model.CommandeClient;
import chijouProjects.gestion_stock_api.model.LigneVente;
import chijouProjects.gestion_stock_api.model.Vente;
import chijouProjects.gestion_stock_api.repository.ArticleRepository;
import chijouProjects.gestion_stock_api.repository.LIgneVenteRepository;
import chijouProjects.gestion_stock_api.repository.VenteRepository;
import chijouProjects.gestion_stock_api.service.VenteService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class VenteServiceImpl implements VenteService {
    private ArticleRepository articleRepository;
    private VenteRepository venteRepository;
    private LIgneVenteRepository ligneVenteRepository;

    @Autowired
    public VenteServiceImpl(ArticleRepository articleRepository, LIgneVenteRepository lIgneVenteRepository, VenteRepository venteRepository) {
        this.venteRepository = venteRepository;
        this.articleRepository = articleRepository;
        this.ligneVenteRepository = lIgneVenteRepository;
    }

    @Override
    public VenteDto save(VenteDto venteDto) {
        List<String> errors = new ArrayList<>();
        if(!errors.isEmpty()) {
            log.error("vente n'est pas valide");
            throw new InvalidEntityException("L'objet vente n'est pas valide", ErrorCodes.VENTE_NOT_VALID, errors);
        }
        List<String> articleErrors = new ArrayList<>();
        venteDto.getLigneventesdto().forEach(ligneventeDto -> {
            Optional<Article> article = articleRepository.findById(ligneventeDto.getArticledto().getId());
            if(article.isEmpty()) {
                articleErrors.add("Aucun article avec l'ID " + ligneventeDto.getArticledto().getId() + "n'est pas été trouvé dans la BDD");
            }
        });

        if(!articleErrors.isEmpty()) {
            log.error("One or more articles were not found in the DB, {}", errors);
            throw new InvalidEntityException("Un ou plusieurs articles n'ont pas été trouvés dans la BDD", ErrorCodes.VENTE_NOT_VALID, errors);
        }

        Vente savedVente = venteRepository.save(VenteDto.toEntity(venteDto));
        venteDto.getLigneventesdto().forEach(ligneventeDto -> {
            LigneVente ligneVente = LigneVenteDto.toEntity(ligneventeDto);
            ligneVente.setVente(savedVente);
            ligneVenteRepository.save(ligneVente);
        });

        return VenteDto.fromEntity(savedVente);
    }

    @Override
    public VenteDto findById(Integer id) {
        if (id == null) {
            log.error("Vente ID is null");
            return null; // ou throw IllegalArgumentException
        }

        Vente vente = venteRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Aucune vente avec l'ID = " + id + " n'a été trouvée dans la BDD",
                        ErrorCodes.VENTE_NOT_FOUND
                ));
        return VenteDto.fromEntity(vente);
    }

    @Override
    public VenteDto findByCodeVente(String codeVente) {
        if (!StringUtils.hasLength(codeVente)) {
            log.error("Le code de vente est null ou vide");
            throw new IllegalArgumentException("Le code de vente ne peut pas être vide");
        }

        Vente vente = venteRepository.findByCodeVente(codeVente)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Aucune vente avec le code = " + codeVente + " n'a été trouvée dans la BDD",
                        ErrorCodes.VENTE_NOT_FOUND
                ));


        return VenteDto.fromEntity(vente);
    }

    @Override
    public List<VenteDto> findAll() {
        return venteRepository.findAll().stream()
                .map(VenteDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Integer id) {
        if (id == null) {
            log.error("Vente ID is null");
            return;
        }

        if (!venteRepository.existsById(id)) {
            log.warn("Attempted to delete non-existent vente with ID: {}", id);
            return;
        }

        venteRepository.deleteById(id);
        log.info("Vente with ID {} successfully deleted.", id);
    }
}
