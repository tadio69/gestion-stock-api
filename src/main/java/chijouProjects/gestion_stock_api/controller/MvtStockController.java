package chijouProjects.gestion_stock_api.controller;

import chijouProjects.gestion_stock_api.controller.api.MvtStockApi;
import chijouProjects.gestion_stock_api.dto.MvtStockDto;
import chijouProjects.gestion_stock_api.service.MvtStockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MvtStockController implements MvtStockApi {
    private MvtStockService mvtStockService;

    @Autowired
    public MvtStockController(MvtStockService mvtStockService) {
        this.mvtStockService = mvtStockService;
    }

    @Override
    public MvtStockDto save(MvtStockDto mvtStockDto) {
        return mvtStockService.save(mvtStockDto);
    }

    @Override
    public MvtStockDto findById(Integer id) {
        return mvtStockService.findById(id);
    }

    @Override
    public List<MvtStockDto> findAll() {
        return mvtStockService.findAll();
    }

    @Override
    public void delete(Integer id) {
        mvtStockService.delete(id);
    }
}
