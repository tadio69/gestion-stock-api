package chijouProjects.gestion_stock_api.model;

import jakarta.persistence.*;
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
@Table(name = "categorie")
@FilterDef(name = "entrepriseFilter", parameters = @ParamDef(name = "entrepriseId", type = Integer.class))
@Filter(name = "entrepriseFilter", condition = "identreprise = :entrepriseId")
public class Categorie extends AbstractEntity {
    @Column(name = "code")
    private String code;

    @Column(name = "designation")
    private String designation;

    @OneToMany(mappedBy = "categorie")
    private List<Article> articles;

    @Column(name = "identreprise")
    private Integer identreprise;
}
