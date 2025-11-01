package chijouProjects.gestion_stock_api.dto;

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

    private ArticleDto article;

    private BigDecimal quantite;

    private TypeMvtStock typemvt;

    public static MvtStockDto fromEntity(MvtStock mvtdtock) {
        if (mvtdtock == null) return null;

        return MvtStockDto.builder()
                .id(mvtdtock.getId())
                .datemvt(mvtdtock.getDatemvt())
                .quantite(mvtdtock.getQuantite())
                .typemvt(mvtdtock.getTypemvt())
                .build();
    }

    public static MvtStock toEntity(MvtStockDto mvtstockdto) {
        if (mvtstockdto == null) return null;
        MvtStock mvtstock = new MvtStock();
        mvtstock.setId(mvtstockdto.getId());
        mvtstock.setDatemvt(mvtstockdto.getDatemvt());
        mvtstock.setQuantite(mvtstockdto.getQuantite());
        mvtstock.setTypemvt(mvtstockdto.getTypemvt());
        return mvtstock;
    }
}
