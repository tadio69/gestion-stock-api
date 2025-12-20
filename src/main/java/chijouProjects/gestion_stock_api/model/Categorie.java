package chijouProjects.gestion_stock_api.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.hibernate.annotations.Filter;
import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.ParamDef;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "categorie")
public class Categorie extends AbstractEntity {
    @Column(name = "code")
    private String code;

    @Column(name = "designation")
    private String designation;

    @OneToMany(mappedBy = "categorie")
    private List<Article> articles;

    @ManyToOne
    @JoinColumn(name = "identreprise", nullable = false)
    @NotNull(message = "L'entreprise est obligatoire")
    private Entreprise entreprise;
}
