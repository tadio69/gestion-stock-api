package chijouProjects.gestion_stock_api.resolver;

import chijouProjects.gestion_stock_api.exception.EntityNotFoundException;
import chijouProjects.gestion_stock_api.model.ImageOwner;
import chijouProjects.gestion_stock_api.repository.ClientRepository;

public class ClientResolver implements ImageOwnerResolver {

    private final ClientRepository repo;

    public ClientResolver(ClientRepository repo) {
        this.repo = repo;
    }

    @Override
    public String getType() {
        return "Client";
    }

    @Override
    public ImageOwner resolve(Integer id) {
        return repo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Client introuvable id=" + id));
    }
}
