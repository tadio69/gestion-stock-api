package chijouProjects.gestion_stock_api.repository;

import chijouProjects.gestion_stock_api.model.Vente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface VenteRepository extends JpaRepository<Vente, Integer> {
    Optional<Vente> findByCodeVente(String codeVente);
}
