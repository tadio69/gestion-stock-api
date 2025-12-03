package chijouProjects.gestion_stock_api.controller;

import chijouProjects.gestion_stock_api.controller.api.VenteApi;
import chijouProjects.gestion_stock_api.dto.VenteDto;
import chijouProjects.gestion_stock_api.exception.EntityNotFoundException;
import chijouProjects.gestion_stock_api.service.VenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class VenteController implements VenteApi {
    private VenteService venteService;

    @Autowired
    public VenteController(VenteService venteService) {
        this.venteService = venteService;
    }

    @Override
    public ResponseEntity<VenteDto> save(VenteDto venteDto) {
        return ResponseEntity.ok(venteService.save(venteDto));
    }

    @Override
    public ResponseEntity<VenteDto> findById(Integer id) {
        return ResponseEntity.ok(venteService.findById(id));
    }

    @Override
    public ResponseEntity<VenteDto> findByCode(String code) {
        return ResponseEntity.ok(venteService.findByCode(code));
    }

    @Override
    public ResponseEntity<List<VenteDto>> findAll() {
        return ResponseEntity.ok(venteService.findAll());
    }

    @Override
    public ResponseEntity<Void> delete(Integer id) {
        try {
            venteService.delete(id);
            return ResponseEntity.noContent().build();
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
