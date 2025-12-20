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

import java.time.Instant;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "commandefournisseur")
public class CommandeFournisseur extends AbstractEntity {
    @Column(name = "code")
    private String code;

    @Column(name = "datecommande")
    private Instant datecommande;

    @ManyToOne
    @JoinColumn(name = "idfournisseur", nullable = false)
    @NotNull(message = "Le fournisseur est obligatoire")
    private Fournisseur fournisseur;

    @OneToMany(mappedBy = "commandefournisseur")
    private List<LigneCdeFournisseur> lignecdefournisseurs;

    @ManyToOne
    @JoinColumn(name = "identreprise", nullable = false)
    @NotNull(message = "L'entreprise est obligatoire")
    private Entreprise entreprise;
}
