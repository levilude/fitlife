package api.controllers;

import api.dtos.ClientRecordDto;
import api.models.ClientModel;
import api.services.ClientService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/clients")
public class ClientController {
    private final ClientService clientService;

    @Autowired
    public ClientController(ClientService clientService){
        this.clientService = clientService;
    }

    @PostMapping
    @ResponseBody
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<ClientModel> saveClient(@RequestBody @Validated ClientRecordDto clientRecordDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(clientService.saveClient(clientRecordDto));
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<Object> getOneClient(@PathVariable(value="id") UUID id) {
        //Logic to get clientModel by id
        Optional<ClientModel> clientModel = clientService.getOneClient(id);

        if (clientModel.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Client not found.");
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(clientModel.get());
        }
    }

    @GetMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Page<ClientModel>> getAllClients(@PageableDefault(page = 0, size = 10, sort = "clientId", direction = Sort.Direction.ASC) Pageable pageable){
        return ResponseEntity.status(HttpStatus.OK).body(clientService.getAllClients(pageable));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Object> updateClient(@PathVariable(value="id") UUID id, @RequestBody @Validated ClientRecordDto clientRecordDto){

        Optional<ClientModel> client0 = clientService.getOneClient(id);
        if(client0.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Client not found.");
        }
        var clientModel = client0.get();
        BeanUtils.copyProperties(clientRecordDto, clientModel);
        return ResponseEntity.status(HttpStatus.OK).body(clientService.updateClient(clientModel));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Object> deleteClient(@PathVariable(value="id") UUID id) {
        Optional<ClientModel> clientModel = clientService.getOneClient(id);
        if (clientModel.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Client not found.");
        }
        clientService.deleteClient(clientModel);
        return ResponseEntity.status(HttpStatus.OK).body("Client delected successfully.");
    }
}
