package chijouProjects.gestion_stock_api.repository;

import chijouProjects.gestion_stock_api.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClientRepository extends JpaRepository<Client, Integer> {
    Optional<Client> findByNom(String nom);
}
