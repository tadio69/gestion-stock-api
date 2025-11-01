package chijouProjects.gestion_stock_api.model;

import jakarta.persistence.*;
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
    @JoinColumn(name = "idvente")
    private Vente vente;

    @Column(name = "quantite")
    private BigDecimal quantite;

    @Column(name = "prixunitaire")
    private BigDecimal prixunitaire;

    @Column(name = "identreprise")
    private Integer identreprise;
}
