package chijouProjects.gestion_stock_api.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "mvtstock")
public class MvtStock extends AbstractEntity {
    @Column(name = "datemvt")
    private Instant datemvt;

    @ManyToOne
    @JoinColumn(name = "idarticle", nullable = false)
    @NotNull(message = "L'article est obligatoire")
    private Article article;

    @Column(name = "quantite")
    private BigDecimal quantite;

    @Column(name = "typemvt")
    private TypeMvtStock typemvt;
}
