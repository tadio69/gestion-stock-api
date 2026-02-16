package chijouProjects.gestion_stock_api.service;

import chijouProjects.gestion_stock_api.dto.CommandeClientDto;
import chijouProjects.gestion_stock_api.dto.CommandeFournisseurDto;
import chijouProjects.gestion_stock_api.dto.LigneCdeCltDto;
import chijouProjects.gestion_stock_api.dto.LigneCdeFournisseurDto;
import chijouProjects.gestion_stock_api.model.EtatCommande;

import java.math.BigDecimal;
import java.util.List;

public interface CommandeFournisseurService {
    CommandeFournisseurDto save(CommandeFournisseurDto commandeFournisseurDto);

    CommandeFournisseurDto updateEtatCommande (Integer idCommande, EtatCommande etatCommande);

    CommandeFournisseurDto updateQuantiteCommande(Integer idCommande, Integer idLigneCommande, BigDecimal quantite);

    CommandeFournisseurDto updateFournisseur(Integer idCommande, Integer idFournisseur);

    CommandeFournisseurDto updateArticle(Integer idCommande, Integer idLigneCommande, Integer idArticle);

    // delete Article ===> delete ligneCommandeFournisseur contenant ledit article
    CommandeFournisseurDto deleteArticle(Integer idCommande, Integer idLigneCommande);

    CommandeFournisseurDto findById(Integer id);

    CommandeFournisseurDto findByCode(String code);

    List<CommandeFournisseurDto> findAll();

    List<LigneCdeFournisseurDto> findAllLignesCommandesFournisseursByCommandeFournisseur(Integer idCommande);

    void delete(Integer id);
}
