package cl.usuarios.service;

import cl.usuarios.dto.RegistroResponseDTO;
import cl.usuarios.dto.UserDTO;
import cl.usuarios.exception.ErrorNegocioException;

public interface UserService {
    public RegistroResponseDTO registrar(UserDTO user) throws ErrorNegocioException;
}
