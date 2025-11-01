package chijouProjects.gestion_stock_api.repository;

import chijouProjects.gestion_stock_api.model.CommandeFournisseur;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommandeFournisseurRepository extends JpaRepository<CommandeFournisseur, Integer> {
}
