package chijouProjects.gestion_stock_api.model;

import chijouProjects.gestion_stock_api.dto.UtilisateurDto;
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
@Builder
@Table(name = "entreprise")
public class Entreprise extends AbstractEntity implements ImageOwner {
    @Column(name = "nom")
    private String nom;

    @Column(name = "description")
    private String description;

    @Embedded
    private Adresse adresse;

    @Column(name = "codefiscal")
    private String codefiscal;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "idimglink")
    private ImgLink imglink;

    @Column(name = "email")
    private String email;

    @Column(name = "numtel")
    private String numtel;

    @Column(name = "siteweb")
    private String siteweb;

    @OneToMany(mappedBy = "entreprise")
    private List<Utilisateur> utilisateurs;

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
        return this;
    }
}
