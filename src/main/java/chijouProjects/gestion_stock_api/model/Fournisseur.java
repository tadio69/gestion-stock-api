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

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "fournisseur")
public class Fournisseur extends AbstractEntity {
    @Column(name = "nom")
    private String nom;

    @Column(name = "prenom")
    private String prenom;

    @Column(name = "email")
    private String email;

    @Column(name = "numtel")
    private String numtel;

    @Embedded
    private Adresse adresse;

    @OneToMany(mappedBy = "fournisseur")
    private List<CommandeFournisseur> commandefournisseurs;

    @ManyToOne
    @JoinColumn(name = "identreprise", nullable = false)
    @NotNull(message = "L'entrprise est obligatoire")
    private Entreprise entreprise;

    @OneToOne
    @JoinColumn(name = "idimglink")
    private ImgLink imglink;
}
