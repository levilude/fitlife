package api.controllers;

import api.models.ClientModel;
import api.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public ClientModel saveClient(@RequestBody ClientModel clientModel){
        return clientService.saveClient(clientModel);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<ClientModel> getClient(@PathVariable Long id) {
        //Logic to get clientModel by id
        ClientModel clientModel = clientService.getClient(id);

        if (clientModel != null) {
            return ResponseEntity.ok(clientModel);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public List<ClientModel> getAllClients(){
        return clientService.getAllClients();
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ClientModel updateClient(@PathVariable Long id, @RequestBody ClientModel updatedClientModel){
        return clientService.updateClient(id, updatedClientModel);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Void> deleteClient(@PathVariable Long id){
        boolean delected = clientService.deleteClient(id);
        if (delected){
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
