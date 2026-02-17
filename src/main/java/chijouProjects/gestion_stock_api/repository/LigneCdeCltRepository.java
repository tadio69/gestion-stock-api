package chijouProjects.gestion_stock_api.repository;

import chijouProjects.gestion_stock_api.model.LigneCdeClt;
import chijouProjects.gestion_stock_api.model.LigneCdeFournisseur;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface LigneCdeCltRepository extends JpaRepository<LigneCdeClt, Integer> {
    List<LigneCdeClt> findAllByCommandeclientId(Integer idCommande);
    List<LigneCdeClt> findAllByArticleId(Integer idArticle);
}
