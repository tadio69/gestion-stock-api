package chijouProjects.gestion_stock_api.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    @JoinColumn(name = "idfournisseur")
    private Fournisseur fournisseur;

    @OneToMany(mappedBy = "commandefournisseur")
    private List<LigneCdeFournisseur> lignecdefournisseurs;

    @Column(name = "identreprise")
    private Integer identreprise;
}
