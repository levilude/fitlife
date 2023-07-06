package api.controllers;

import api.dtos.ClientRecordDto;
import api.models.ClientModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/login")
public class LoginController {
    @PostMapping()
    public void login(@RequestParam("username") String username, @RequestParam("password") String password) {
        // Lógica de autenticação aqui
        System.out.println("Usuário: " + username);
        System.out.println("Senha: " + password);
    }

}
