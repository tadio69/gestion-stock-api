package chijouProjects.gestion_stock_api.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "lignecdefournisseur")
public class LigneCdeFournisseur extends AbstractEntity {
    @ManyToOne
    @JoinColumn(name = "idarticle")
    private Article article;

    @ManyToOne
    @JoinColumn(name = "idcommandefournisseur")
    private CommandeFournisseur commandefournisseur;

    @Column(name = "quantite")
    private BigDecimal quantite;

    @Column(name = "prixunitaire")
    private BigDecimal prixunitaire;

    @Column(name = "identreprise")
    private Integer identreprise;
}
