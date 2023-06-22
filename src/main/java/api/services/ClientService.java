package api.services;

import api.ClientNotFoundException;
import api.models.ClientModel;
import api.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientService {

    private final ClientRepository clientRepository;

    @Autowired
    public ClientService(ClientRepository clientRepository){
        this.clientRepository = clientRepository;
    }

    public ClientModel saveClient(ClientModel clientModel) {
        return clientRepository.save(clientModel);
    }

    public ClientModel getClient(Long id){
        return clientRepository.findById(id).orElse(null);
    }

    public List<ClientModel> getAllClients(){
        return clientRepository.findAll();
    }

    public ClientModel updateClient(Long id, ClientModel updatedClientModel){
        return clientRepository.findById(id)
                .map(clientModel -> {
                    clientModel.setName(updatedClientModel.getName());
                    clientModel.setBirthday(updatedClientModel.getBirthday());
                    clientModel.setPhone(updatedClientModel.getPhone());
                    clientModel.setMail(updatedClientModel.getMail());
                    return clientRepository.save(clientModel);
                })
                .orElseThrow(() -> new ClientNotFoundException(id));
    }

    public boolean deleteClient(Long id){
        Optional<ClientModel> optionalClient = clientRepository.findById(id);
        if (optionalClient.isPresent()){
            clientRepository.delete(optionalClient.get());
            return true;
        } else {
            return false;
        }
    }

}
