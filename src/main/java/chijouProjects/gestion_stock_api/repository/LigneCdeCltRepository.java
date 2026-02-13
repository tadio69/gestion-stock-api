package chijouProjects.gestion_stock_api.repository;

import chijouProjects.gestion_stock_api.dto.LigneCdeCltDto;
import chijouProjects.gestion_stock_api.model.LigneCdeClt;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface LigneCdeCltRepository extends JpaRepository<LigneCdeClt, Integer> {
    List<LigneCdeClt> findAllByCommandeclientId(Integer idCommande);
}
