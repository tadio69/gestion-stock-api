package chijouProjects.gestion_stock_api.controller;

import chijouProjects.gestion_stock_api.controller.api.CommandeFournisseurApi;
import chijouProjects.gestion_stock_api.dto.CommandeFournisseurDto;
import chijouProjects.gestion_stock_api.exception.EntityNotFoundException;
import chijouProjects.gestion_stock_api.service.CommandeFournisseurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CommandeFournisseurController implements CommandeFournisseurApi {
    private CommandeFournisseurService commandeFournisseurService;

    @Autowired
    public CommandeFournisseurController(CommandeFournisseurService commandeFournisseurService){
        this.commandeFournisseurService = commandeFournisseurService;
    }

    @Override
    public ResponseEntity<CommandeFournisseurDto> save(CommandeFournisseurDto commandeFournisseurDto) {
        return ResponseEntity.ok(commandeFournisseurService.save(commandeFournisseurDto));
    }

    @Override
    public ResponseEntity<CommandeFournisseurDto> findById(Integer id) {
        return ResponseEntity.ok(commandeFournisseurService.findById(id));
    }

    @Override
    public ResponseEntity<CommandeFournisseurDto> findByCode(String code) {
        return ResponseEntity.ok(commandeFournisseurService.findByCode(code));
    }

    @Override
    public ResponseEntity<List<CommandeFournisseurDto>> findAll() {
        return ResponseEntity.ok(commandeFournisseurService.findAll());
    }

    @Override
    public ResponseEntity<Void> delete(Integer id) {
        try {
            commandeFournisseurService.delete(id);
            return ResponseEntity.noContent().build();
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
