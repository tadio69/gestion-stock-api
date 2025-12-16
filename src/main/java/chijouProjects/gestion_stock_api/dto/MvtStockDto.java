package chijouProjects.gestion_stock_api.dto;

import chijouProjects.gestion_stock_api.model.Article;
import chijouProjects.gestion_stock_api.model.LigneVente;
import chijouProjects.gestion_stock_api.model.MvtStock;
import chijouProjects.gestion_stock_api.model.TypeMvtStock;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.Instant;

@Data
@Builder
public class MvtStockDto {
    private Integer id;

    private Instant datemvt;

    private Integer idarticle;

    private BigDecimal quantite;

    private TypeMvtStock typemvt;

    private Integer identreprise;

    public static MvtStockDto fromEntity(MvtStock mvtdtock) {
        if (mvtdtock == null) return null;

        return MvtStockDto.builder()
                .id(mvtdtock.getId())
                .datemvt(mvtdtock.getDatemvt())
                .quantite(mvtdtock.getQuantite())
                .typemvt(mvtdtock.getTypemvt())
                .idarticle(mvtdtock.getArticle().getId())
                .build();
    }

    public static MvtStock toEntity(MvtStockDto mvtstockdto) {
        if (mvtstockdto == null) return null;
        MvtStock mvtstock = new MvtStock();
        mvtstock.setId(mvtstockdto.getId());
        mvtstock.setDatemvt(mvtstockdto.getDatemvt());
        mvtstock.setQuantite(mvtstockdto.getQuantite());
        mvtstock.setTypemvt(mvtstockdto.getTypemvt());
        if (mvtstockdto.getIdarticle() != null) {
            Article article = new Article();
            article.setId(mvtstockdto.getIdarticle());
            mvtstock.setArticle(article);
        }

        return mvtstock;
    }
}
