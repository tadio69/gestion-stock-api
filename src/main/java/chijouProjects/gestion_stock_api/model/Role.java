package chijouProjects.gestion_stock_api.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "role")
public class Role extends AbstractEntity {
    @Column(name = "rolename")
    private String rolename;

    @ManyToOne
    @JoinColumn(name = "idutilisateur")
    private Utilisateur utilisateur;

    @Column(name = "identreprise")
    private Integer identreprise;
}
