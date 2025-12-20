package chijouProjects.gestion_stock_api.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "lignevente")
public class LigneVente extends AbstractEntity {
    @ManyToOne
    @JoinColumn(name = "idvente", nullable = false)
    @NotNull(message = "La vente est obligatoire")
    private Vente vente;

    @Column(name = "quantite")
    private BigDecimal quantite;

    @Column(name = "prixunitaire")
    private BigDecimal prixunitaire;

    @ManyToOne
    @JoinColumn(name = "idarticle", nullable = false)
    @NotNull(message = "L'article est obligatoire")
    private Article article;
}
