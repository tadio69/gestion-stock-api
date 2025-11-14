package chijouProjects.gestion_stock_api.repository;

import chijouProjects.gestion_stock_api.model.Fournisseur;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FournisseurRepository extends JpaRepository<Fournisseur, Integer> {
    Optional<Fournisseur> findByNom(String nom);
}
