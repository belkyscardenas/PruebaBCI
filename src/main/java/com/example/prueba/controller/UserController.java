package com.example.prueba.controller;

import com.example.prueba.dto.ErrorDTO;
import com.example.prueba.dto.RegistroResponseDTO;
import com.example.prueba.dto.UserDTO;
import com.example.prueba.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

import javax.validation.Valid;

@RestController
public class UserController {

    @Autowired
    private UserService service;

    @PostMapping(value = "/registrar", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RegistroResponseDTO> registrar(@RequestBody @Valid UserDTO user) {
        return ResponseEntity.ok(service.registrar(user));
    }

    @ExceptionHandler
    public ResponseEntity<Object> handlePatter(
            Exception ex, WebRequest request) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ErrorDTO.builder().mensaje(ex.getMessage()).build());
    }
}
