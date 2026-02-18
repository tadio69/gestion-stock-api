package chijouProjects.gestion_stock_api.resolver;

import chijouProjects.gestion_stock_api.exception.EntityNotFoundException;
import chijouProjects.gestion_stock_api.model.ImageOwner;
import chijouProjects.gestion_stock_api.repository.UtilisateurRepository;

public class UtilisateurResolver implements ImageOwnerResolver {
    private final UtilisateurRepository repo;

    public UtilisateurResolver(UtilisateurRepository repo) {
        this.repo = repo;
    }

    @Override
    public String getType() {
        return "Utilisateur";
    }

    @Override
    public ImageOwner resolve(Integer id) {
        return repo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Utilisateur introuvable id=" + id));
    }
}
