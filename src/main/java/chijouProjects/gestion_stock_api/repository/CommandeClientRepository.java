package chijouProjects.gestion_stock_api.repository;

import chijouProjects.gestion_stock_api.model.CommandeClient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommandeClientRepository extends JpaRepository<CommandeClient, Integer> {
}
