package chijouProjects.gestion_stock_api.resolver;

import chijouProjects.gestion_stock_api.model.ImageOwner;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class ImageOwnerResolverRegistry {
    private final Map<String, ImageOwnerResolver> resolvers = new HashMap<>();

    public ImageOwnerResolverRegistry(List<ImageOwnerResolver> resolverList) {
        resolverList.forEach(r -> resolvers.put(r.getType().toLowerCase(), r));
    }

    public ImageOwner resolve(String type, Integer id) {
        ImageOwnerResolver resolver = resolvers.get(type.toLowerCase());
        if (resolver == null) {
            throw new RuntimeException("Type d'entité inconnu : " + type);
        }
        return resolver.resolve(id);
    }
}
