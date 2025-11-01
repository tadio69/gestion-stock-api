package chijouProjects.gestion_stock_api.repository;

import chijouProjects.gestion_stock_api.model.Vente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VenteRepository extends JpaRepository<Vente, Integer> {
}
