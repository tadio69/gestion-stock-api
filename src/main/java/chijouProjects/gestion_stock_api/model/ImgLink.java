package chijouProjects.gestion_stock_api.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    private Instant createdAt;

    @Column(name = "identreprise")
    private Integer identreprise;
}
