package chijouProjects.gestion_stock_api.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "vente")
public class Vente extends AbstractEntity {
    @Column(name = "code")
    private String code;

    @Column(name = "datevente")
    private Instant datevente;

    @Column(name = "commentaire")
    private String commentaire;

    @Column(name = "identreprise")
    private Integer identreprise;
}
