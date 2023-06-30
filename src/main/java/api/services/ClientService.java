package api.services;

import api.dtos.ClientRecordDto;
import api.models.ClientModel;
import api.repositories.ClientRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

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

    public List<ClientModel> getAllClients(){
        return clientRepository.findAll();
    }

    public Optional<ClientModel> updateClient(ClientModel clientModel){
        return Optional.of(clientRepository.save(clientModel));
    }

    public void deleteClient(Optional<ClientModel> optionalClient){
        clientRepository.delete(optionalClient.get());
    }

}
