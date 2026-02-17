package chijouProjects.gestion_stock_api.repository;

import chijouProjects.gestion_stock_api.model.LigneCdeFournisseur;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LigneCdeFournisseurRepository extends JpaRepository<LigneCdeFournisseur, Integer> {
    List<LigneCdeFournisseur> findAllByCommandefournisseurId(Integer idCommande);
    List<LigneCdeFournisseur> findAllByArticleId(Integer idArticle);
}
