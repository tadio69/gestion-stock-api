package chijouProjects.gestion_stock_api.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Filter;
import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.ParamDef;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "client")
public class Client extends AbstractEntity {
    @Column(name = "nom")
    private String nom;

    @Column(name = "prenom")
    private String prenom;

    @Column(name = "email")
    private String email;

    @Column(name = "numtel")
    private String numTel;

    @Embedded
    private Adresse adresse;

    @OneToMany(mappedBy = "client")
    private List<CommandeClient> commandeclients;

    @ManyToOne
    @JoinColumn(name = "identreprise", nullable = false)
    @NotNull(message = "L'entreprise est obligatoire")
    private Entreprise entreprise;

    @OneToOne
    @JoinColumn(name = "idimglink")
    private ImgLink imglink;
}
