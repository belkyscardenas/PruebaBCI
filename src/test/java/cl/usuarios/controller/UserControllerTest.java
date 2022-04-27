package cl.usuarios.controller;

import cl.usuarios.exception.ErrorNegocioException;
import cl.usuarios.service.UserService;
import cl.usuarios.dto.PhonesDTO;
import cl.usuarios.dto.RegistroResponseDTO;
import cl.usuarios.dto.UserDTO;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collections;
import java.util.Date;

@RunWith(SpringRunner.class)
public class UserControllerTest {

    @InjectMocks
    private UserController controller;

    @Mock
    private UserService service;

    @Test
    public void registrarTest() throws ErrorNegocioException {
        UserDTO user = UserDTO.builder()
                .name("Belkys")
                .email("belkys@gmail.com")
                .password("Abcdddd12")
                .phones(Collections.singletonList(PhonesDTO.builder().number(123456L).build()))
                .build();

        RegistroResponseDTO expected = RegistroResponseDTO.builder().id(1L)
                .created(new Date())
                .modified(new Date())
                .build();

        Mockito.when(service.registrar(Mockito.any(UserDTO.class))).thenReturn(expected);

        ResponseEntity<RegistroResponseDTO> response = controller.registrar(user);

        Assert.assertEquals(response.getBody(), expected);
    }
}
