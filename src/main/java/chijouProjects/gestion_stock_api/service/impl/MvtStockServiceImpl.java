package chijouProjects.gestion_stock_api.service.impl;

import chijouProjects.gestion_stock_api.dto.MvtStockDto;
import chijouProjects.gestion_stock_api.model.Article;
import chijouProjects.gestion_stock_api.model.MvtStock;
import chijouProjects.gestion_stock_api.repository.ArticleRepository;
import chijouProjects.gestion_stock_api.repository.MvtStockRepository;
import chijouProjects.gestion_stock_api.service.MvtStockService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class MvtStockServiceImpl implements MvtStockService {

    private final MvtStockRepository mvtStockRepository;
    private final ArticleRepository articleRepository;

    @Autowired
    public MvtStockServiceImpl(MvtStockRepository mvtStockRepository,
                               ArticleRepository articleRepository) {
        this.mvtStockRepository = mvtStockRepository;
        this.articleRepository = articleRepository;
    }

    @Override
    public MvtStockDto save(MvtStockDto mvtStockDto) {
        if (mvtStockDto == null) {
            log.error("MvtStockDto est null");
            throw new IllegalArgumentException("Le mouvement de stock ne peut pas être null");
        }

        // Vérifie la présence de l’article lié
        if (mvtStockDto.getArticledto() == null || mvtStockDto.getArticledto().getId() == null) {
            log.error("Article du mouvement de stock est null");
            throw new IllegalArgumentException("L'article associé est obligatoire pour un mouvement de stock");
        }

        Optional<Article> articleOpt = articleRepository.findById(mvtStockDto.getArticledto().getId());
        if (articleOpt.isEmpty()) {
            log.error("Aucun article trouvé avec l'id {}", mvtStockDto.getArticledto().getId());
            throw new IllegalArgumentException("Article introuvable");
        }

        // Conversion DTO → entité
        MvtStock entity = MvtStockDto.toEntity(mvtStockDto);
        entity.setArticle(articleOpt.get());

        // Sauvegarde et reconversion en DTO
        MvtStock saved = mvtStockRepository.save(entity);
        return MvtStockDto.fromEntity(saved);
    }

    @Override
    public MvtStockDto findById(Integer id) {
        if (id == null) {
            log.error("ID du mouvement de stock est null");
            return null;
        }

        Optional<MvtStock> mvtStock = mvtStockRepository.findById(id);
        return mvtStock.map(MvtStockDto::fromEntity).orElse(null);
    }

    @Override
    public List<MvtStockDto> findAll() {
        return mvtStockRepository.findAll().stream()
                .map(MvtStockDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Integer id) {
        if (id == null) {
            log.error("ID du mouvement de stock est null");
            return;
        }
        mvtStockRepository.deleteById(id);
    }
}
