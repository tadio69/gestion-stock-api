package chijouProjects.gestion_stock_api.repository;

import chijouProjects.gestion_stock_api.model.MvtStock;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MvtStockRepository extends JpaRepository<MvtStock, Integer> {
}
