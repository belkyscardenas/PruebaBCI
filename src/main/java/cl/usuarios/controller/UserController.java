package cl.usuarios.controller;

import cl.usuarios.exception.ErrorNegocioException;
import cl.usuarios.service.UserService;
import cl.usuarios.dto.RegistroResponseDTO;
import cl.usuarios.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class UserController {

    @Autowired
    private UserService service;

    @PostMapping(value = "/registrar", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RegistroResponseDTO> registrar(@RequestBody @Valid UserDTO user) throws ErrorNegocioException {
        return ResponseEntity.ok(service.registrar(user));
    }

}
