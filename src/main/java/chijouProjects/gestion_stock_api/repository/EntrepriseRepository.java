package chijouProjects.gestion_stock_api.repository;

import chijouProjects.gestion_stock_api.model.Entreprise;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EntrepriseRepository extends JpaRepository<Entreprise, Integer> {
    Optional<Entreprise> findByCodeFiscal(String codeFiscal);

    Optional<Entreprise> findByDescription(String description);
}
