package chijouProjects.gestion_stock_api.repository;

import chijouProjects.gestion_stock_api.model.CommandeClient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CommandeClientRepository extends JpaRepository<CommandeClient, Integer> {
    Optional<CommandeClient> findByCode(String code);
}
