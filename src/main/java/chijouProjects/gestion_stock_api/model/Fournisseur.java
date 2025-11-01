package chijouProjects.gestion_stock_api.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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

    @Column(name = "photo")
    private String photo;

    @OneToMany(mappedBy = "fournisseur")
    private List<CommandeFournisseur> commandefournisseurs;

    @Column(name = "identreprise")
    private Integer identreprise;
}
