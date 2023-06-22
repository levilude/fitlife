package api;

public class ClientNotFoundException extends RuntimeException {
    public ClientNotFoundException(Long id){
        super("ClientModel not found, id: " + id);
    }
}
