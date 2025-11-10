package chijouProjects.gestion_stock_api.service;

import chijouProjects.gestion_stock_api.dto.MvtStockDto;

import java.util.List;

public interface MvtStockService {
    MvtStockDto save(MvtStockDto mvtStockDto);

    MvtStockDto findById(Integer id);

    List<MvtStockDto> findAll();

    void delete(Integer id);
}
