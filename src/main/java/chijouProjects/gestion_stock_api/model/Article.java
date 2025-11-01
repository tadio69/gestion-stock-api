package chijouProjects.gestion_stock_api.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "article")
public class Article extends AbstractEntity {
    @Column(name = "codearticle", nullable = false)
    private String codeArticle;

    @Column(name = "designation")
    private String designation;

    @Column(name = "prixunitaireht")
    private BigDecimal prixunitaireht;

    @Column(name = "tauxtva")
    private BigDecimal tauxtva;

    @Column(name = "prixunitairettc")
    private BigDecimal prixunitairettc;

    @Column(name = "photo")
    private String photo;

    @ManyToOne
    @JoinColumn(name = "idcategorie")
    private Categorie categorie;

    @Column(name = "identreprise")
    private Integer identreprise;
}
