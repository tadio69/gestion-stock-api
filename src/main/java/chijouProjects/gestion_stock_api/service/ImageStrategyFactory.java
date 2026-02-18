package chijouProjects.gestion_stock_api.service;

import chijouProjects.gestion_stock_api.model.ImageOwner;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class ImageStrategyFactory {
    private final Map<Class<? extends ImageOwner>, ImageUploadStrategy<?>> strategies = new HashMap<>();

    public ImageStrategyFactory(List<ImageUploadStrategy<?>> strategyList) {
        for (ImageUploadStrategy<?> strategy : strategyList) {
            strategies.put(strategy.getOwnerClass(), strategy);
        }
    }

    // Nouvelle méthode pour récupérer toutes les stratégies
    public Collection<ImageUploadStrategy<?>> getAllStrategies() {
        return strategies.values();
    }

    // Permet d'enregistrer les stratégies (à faire dans la configuration)
    public <T extends ImageOwner> void registerStrategy(Class<T> clazz, ImageUploadStrategy<T> strategy) {
        strategies.put(clazz, strategy);
    }

    @SuppressWarnings("unchecked")
    public <T extends ImageOwner> Optional<ImageUploadStrategy<T>> getStrategyFor(Class<T> clazz){
        return strategies.values().stream()
                .filter(s -> s.getOwnerClass().isAssignableFrom(clazz))
                .map(s -> (ImageUploadStrategy<T>) s)
                .findFirst();
    }
}
