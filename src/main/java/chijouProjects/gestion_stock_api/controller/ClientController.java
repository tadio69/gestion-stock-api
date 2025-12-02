package chijouProjects.gestion_stock_api.controller;

import chijouProjects.gestion_stock_api.controller.api.ClientApi;
import chijouProjects.gestion_stock_api.dto.ClientDto;
import chijouProjects.gestion_stock_api.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ClientController implements ClientApi {

    private ClientService clientService;

    @Autowired
    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }
    @Override
    public ResponseEntity<ClientDto> save(ClientDto articleDto) {
        return ResponseEntity.ok(clientService.save(articleDto));
    }

    @Override
    public ResponseEntity<ClientDto> findById(Integer id) {
        return ResponseEntity.ok(clientService.findById(id));
    }

    @Override
    public ResponseEntity<ClientDto> findByNom(String nom) {
        return ResponseEntity.ok(clientService.findByNom(nom));
    }

    @Override
    public ResponseEntity<List<ClientDto>> findAll() {
        return ResponseEntity.ok(clientService.findAll());
    }

    @Override
    public ResponseEntity<Void> delete(Integer id) {
        clientService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
