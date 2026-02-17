package chijouProjects.gestion_stock_api.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
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
public class Utilisateur extends AbstractEntity implements ImageOwner {
    @Column(name = "nom")
    private String nom;

    @Column(name = "prenom")
    private String prenom;

    @Column(name = "email")
    private String email;

    @Column(name = "datenaissance")
    private Instant datenaissance;

    @ManyToOne
    @JoinColumn(name = "identreprise", nullable = false)
    @NotNull(message = "L'entreprise est obligatoire")
    private Entreprise entreprise;

    @Column(name = "motdepasse")
    private String motdepasse;

    @Embedded
    private Adresse adresse;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "idimglink")
    private ImgLink imglink;

    @OneToMany(mappedBy = "utilisateur")
    private List<Role> roles;

    @Override
    public void setImglink(ImgLink img) {
        this.imglink = img;
    }

    @Override
    public ImgLink getImglink() {
        return this.imglink;
    }

    @Override
    public Entreprise getEntreprise() {
        return this.entreprise;
    }
}
