package chijouProjects.gestion_stock_api.controller;

import chijouProjects.gestion_stock_api.controller.api.MvtStockApi;
import chijouProjects.gestion_stock_api.dto.MvtStockDto;
import chijouProjects.gestion_stock_api.exception.EntityNotFoundException;
import chijouProjects.gestion_stock_api.service.MvtStockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<MvtStockDto> save(MvtStockDto mvtStockDto) {
        return ResponseEntity.ok(mvtStockService.save(mvtStockDto));
    }

    @Override
    public ResponseEntity<MvtStockDto> findById(Integer id) {
        return ResponseEntity.ok(mvtStockService.findById(id));
    }

    @Override
    public ResponseEntity<List<MvtStockDto>> findAll() {
        return ResponseEntity.ok(mvtStockService.findAll());
    }

    @Override
    public ResponseEntity<Void> delete(Integer id) {
        try {
            mvtStockService.delete(id);
            return ResponseEntity.noContent().build();
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
