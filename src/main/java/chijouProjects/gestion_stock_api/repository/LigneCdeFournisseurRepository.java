package chijouProjects.gestion_stock_api.repository;

import chijouProjects.gestion_stock_api.model.LigneCdeFournisseur;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LigneCdeFournisseurRepository extends JpaRepository<LigneCdeFournisseur, Integer> {
}
