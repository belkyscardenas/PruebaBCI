package com.example.prueba.service;

import com.example.prueba.dto.RegistroResponseDTO;
import com.example.prueba.dto.UserDTO;

public interface UserService {
    public RegistroResponseDTO registrar(UserDTO user);
}
