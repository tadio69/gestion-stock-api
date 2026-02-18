package chijouProjects.gestion_stock_api.resolver;

import chijouProjects.gestion_stock_api.model.ImageOwner;

public interface ImageOwnerResolver {
    String getType();
    ImageOwner resolve(Integer id);
}
