package chijouProjects.gestion_stock_api.repository;

import chijouProjects.gestion_stock_api.model.Categorie;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategorieRepository extends JpaRepository<Categorie, Integer> {
    Optional<Categorie> findByCode(String code);
    Optional<Categorie> findByDesignation(String designation);
    //Optional<Categorie> findByIdAndIdEntreprise(Integer id, Integer idEntreprise); complexe. On doit faire simple
}
