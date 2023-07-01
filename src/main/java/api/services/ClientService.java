package api.services;

import api.controllers.ClientController;
import api.dtos.ClientRecordDto;
import api.models.ClientModel;
import api.repositories.ClientRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.Optional;
import java.util.UUID;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
public class ClientService {

    private final ClientRepository clientRepository;

    @Autowired
    public ClientService(ClientRepository clientRepository){
        this.clientRepository = clientRepository;
    }

    public ClientModel saveClient(ClientRecordDto clientRecordDto) {
        var clientModel = new ClientModel();
        BeanUtils.copyProperties(clientRecordDto, clientModel);
        return clientRepository.save(clientModel);
    }

    public Optional<ClientModel> getOneClient(UUID id){
        Optional<ClientModel> client = clientRepository.findById(id);
        return client;
    }

    public Page<ClientModel> getAllClients(Pageable pageable){
        Page<ClientModel> clientsPage = clientRepository.findAll(pageable);
        //If you have elements, you must go through each one of them and build the links (HATEOS)
        if(!clientsPage.isEmpty()){
            for (ClientModel client: clientsPage) {
                UUID id = client.getClientId();
                client.add(linkTo(methodOn(ClientController.class).getOneClient(id)).withSelfRel());
            }
        }

        return clientsPage;
    }

    public Optional<ClientModel> updateClient(ClientModel clientModel){
        return Optional.of(clientRepository.save(clientModel));
    }

    public void deleteClient(Optional<ClientModel> optionalClient){
        clientRepository.delete(optionalClient.get());
    }

}
