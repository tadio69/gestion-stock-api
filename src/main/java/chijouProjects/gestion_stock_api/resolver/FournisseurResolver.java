package chijouProjects.gestion_stock_api.resolver;

import chijouProjects.gestion_stock_api.exception.EntityNotFoundException;
import chijouProjects.gestion_stock_api.model.ImageOwner;
import chijouProjects.gestion_stock_api.repository.ClientRepository;
import chijouProjects.gestion_stock_api.repository.FournisseurRepository;

public class FournisseurResolver implements ImageOwnerResolver {

    private final FournisseurRepository repo;

    public FournisseurResolver(FournisseurRepository repo) {
        this.repo = repo;
    }

    @Override
    public String getType() {
        return "Fournisseur";
    }

    @Override
    public ImageOwner resolve(Integer id) {
        return repo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Fournisseur introuvable id=" + id));
    }
}
