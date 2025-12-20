package chijouProjects.gestion_stock_api.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Filter;
import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.ParamDef;

import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "imglink")
public class ImgLink extends AbstractEntity {
    @Column(name = "url", nullable = false)
    private String url;

    @Column(name = "filename")
    private String filename;

    @Column(name = "uploaded_at")
    private Instant uploadedat;

    @ManyToOne
    @JoinColumn(name = "identreprise", nullable = false)
    @NotNull(message = "L'entreprise est obligatoire")
    private Entreprise entreprise;
}
