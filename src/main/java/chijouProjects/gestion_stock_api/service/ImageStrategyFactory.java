package chijouProjects.gestion_stock_api.service;

import chijouProjects.gestion_stock_api.model.ImageOwner;
import chijouProjects.gestion_stock_api.service.impl.DefaultImageStrategy;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Component
public class ImageStrategyFactory {
    private final Map<Class<? extends ImageOwner>, ImageUploadStrategy<?>> strategies = new HashMap<>();

    // Méthode existante
    public <T extends ImageOwner> Optional<ImageUploadStrategy<T>> getStrategy(Class<T> clazz) {
        ImageUploadStrategy<?> strategy = strategies.get(clazz);
        if (strategy == null) {
            return Optional.empty();
        }
        return Optional.of((ImageUploadStrategy<T>) strategy); // toujours un cast, mais optionnel
    }

    // Nouvelle méthode pour récupérer toutes les stratégies
    public Collection<ImageUploadStrategy<?>> getAllStrategies() {
        return strategies.values();
    }

    // Permet d'enregistrer les stratégies (à faire dans la configuration)
    public <T extends ImageOwner> void registerStrategy(Class<T> clazz, ImageUploadStrategy<T> strategy) {
        strategies.put(clazz, strategy);
    }
}
