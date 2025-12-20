package chijouProjects.gestion_stock_api.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "lignecdeclt")
public class LigneCdeClt extends AbstractEntity {
    @ManyToOne
    @JoinColumn(name = "idarticle", nullable = false)
    @NotNull(message = "L'article est obligatoire")
    private Article article;

    @ManyToOne
    @JoinColumn(name = "idcommandeclient", nullable = false)
    @NotNull(message = "La commande est obligatoire")
    private CommandeClient commandeclient;

    @Column(name = "quantite")
    private BigDecimal quantite;

    @Column(name = "prixunitaire")
    private BigDecimal prixunitaire;
}
