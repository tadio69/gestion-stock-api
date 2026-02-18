package chijouProjects.gestion_stock_api.resolver;

import chijouProjects.gestion_stock_api.exception.EntityNotFoundException;
import chijouProjects.gestion_stock_api.model.ImageOwner;
import chijouProjects.gestion_stock_api.repository.ArticleRepository;

public class EntrepriseResolver implements ImageOwnerResolver {

    private final ArticleRepository repo;

    public EntrepriseResolver(ArticleRepository repo) {
        this.repo = repo;
    }

    @Override
    public String getType() {
        return "Entreprise";
    }

    @Override
    public ImageOwner resolve(Integer id) {
        return repo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Entreprise introuvable id=" + id));
    }
}
