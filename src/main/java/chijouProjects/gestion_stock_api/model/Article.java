package chijouProjects.gestion_stock_api.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Filter;
import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.ParamDef;

import java.math.BigDecimal;
import java.util.List;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "article")
public class Article extends AbstractEntity {
    @Column(name = "code", nullable = false)
    private String code;

    @Column(name = "designation")
    private String designation;

    @Column(name = "prixunitaireht")
    private BigDecimal prixunitaireht;

    @Column(name = "tauxtva")
    private BigDecimal tauxtva;

    @Column(name = "prixunitairettc")
    private BigDecimal prixunitairettc;

    @OneToOne
    @JoinColumn(name = "idimglink")
    private ImgLink imglink;

    @ManyToOne
    @JoinColumn(name = "idcategorie", nullable = false)
    @NotNull(message = "La catégorie est obligatoire")
    private Categorie categorie;

    @ManyToOne
    @JoinColumn(name = "identreprise", nullable = false)
    @NotNull(message = "L'entreprise est obligatoire")
    private Entreprise entreprise;

    @OneToMany(mappedBy = "article")
    private List<LigneVente> lignevente;

    @OneToMany(mappedBy = "article")
    private List<LigneCdeClt> lignecdeclts;

    @OneToMany(mappedBy = "article")
    private List<LigneCdeFournisseur> lignecdefournisseurs;

    @OneToMany(mappedBy = "article")
    private List<MvtStock> mvtstocks;
}
