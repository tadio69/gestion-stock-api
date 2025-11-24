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
@Table(name = "utilisateur")
public class Utilisateur extends AbstractEntity {
    @Column(name = "nom")
    private String nom;

    @Column(name = "prenom")
    private String prenom;

    @Column(name = "email")
    private String email;

    @Column(name = "datenaissance")
    private Instant datenaissance;

    @Column(name = "motdepasse")
    private String motdepasse;

    @Embedded
    private Adresse adresse;

    @OneToOne
    @JoinColumn(name = "idimglink")
    private ImgLink imglink;

    @OneToMany(mappedBy = "utilisateur")
    private List<Role> roles;

}
