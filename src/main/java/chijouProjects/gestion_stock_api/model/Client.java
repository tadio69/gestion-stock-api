package chijouProjects.gestion_stock_api.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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

    @Column(name = "photo")
    private String photo;

    @OneToMany(mappedBy = "client")
    private List<CommandeClient> commandeclients;

    @Column(name = "identreprise")
    private Integer identreprise;
}
