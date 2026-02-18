package chijouProjects.gestion_stock_api.resolver;

import chijouProjects.gestion_stock_api.exception.EntityNotFoundException;
import chijouProjects.gestion_stock_api.model.ImageOwner;
import chijouProjects.gestion_stock_api.repository.ArticleRepository;
import chijouProjects.gestion_stock_api.repository.FournisseurRepository;

public class ArticleResolver implements ImageOwnerResolver {

    private final ArticleRepository repo;

    public ArticleResolver(ArticleRepository repo) {
        this.repo = repo;
    }

    @Override
    public String getType() {
        return "Article";
    }

    @Override
    public ImageOwner resolve(Integer id) {
        return repo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Article introuvable id=" + id));
    }
}
