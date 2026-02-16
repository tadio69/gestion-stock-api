package chijouProjects.gestion_stock_api.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
@Table(name = "commandeclient")
public class CommandeClient extends AbstractEntity {
    @Column(name = "code")
    private String code;

    @Column(name = "datecommande")
    private Instant datecommande;

    @Column(name = "etatcommande")
    private EtatCommande etatcommande;

    @ManyToOne
    @JoinColumn(name = "idclient", nullable = false)
    @NotNull(message = "Le client est obligatoire")
    private Client client;

    @JsonIgnore
    @OneToMany(mappedBy = "commandeclient")
    private List<LigneCdeClt> lignecdeclts;

    @ManyToOne
    @JoinColumn(name = "identreprise", nullable = false)
    @NotNull(message = "L'entreprise est obligatoire")
    private Entreprise entreprise;
}
