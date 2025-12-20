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

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "role")
public class Role extends AbstractEntity {
    @Column(name = "rolename")
    private String rolename;

    @ManyToOne
    @JoinColumn(name = "idutilisateur", nullable = false)
    @NotNull(message = "L'utilisateur est obligatoire")
    private Utilisateur utilisateur;

    @ManyToOne
    @JoinColumn(name = "identreprise", nullable = false)
    @NotNull(message = "L'entrprise est obligatoire")
    private Entreprise entreprise;
}
