package api;

import java.util.UUID;

public class ClientNotFoundException extends RuntimeException {
    public ClientNotFoundException(UUID id){
        super("ClientModel not found, id: " + id);
    }
}
