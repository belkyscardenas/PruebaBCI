package cl.usuarios.service;

import cl.usuarios.dto.PhonesDTO;
import cl.usuarios.dto.RegistroResponseDTO;
import cl.usuarios.dto.UserDTO;
import cl.usuarios.exception.ErrorNegocioException;
import cl.usuarios.model.UserModel;
import cl.usuarios.repository.UserRepository;
import cl.usuarios.security.JwtToken;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.Collections;
import java.util.Date;

@RunWith(SpringRunner.class)
public class UserServiceTest {

    @InjectMocks
    private UserServiceImpl service;

    @Mock
    private UserRepository userRepository;

    @Mock
    private JwtToken jwtToken;

    @Test
    public void registrarTestCasoOk() throws ErrorNegocioException {
        UserDTO user = UserDTO.builder()
                .name("Belkys")
                .email("belkys@gmail.com")
                .password("Abcdddd12")
                .phones(Collections.singletonList(PhonesDTO.builder().number(123456L).build()))
                .build();

        UserModel userCreated = UserModel.builder()
                .email(user.getEmail())
                .created(new Date())
                .modified(new Date())
                .build();

        Mockito.when(userRepository.save(Mockito.any(UserModel.class))).thenReturn(userCreated);
        Mockito.when(jwtToken.generateToken(Mockito.any(UserDTO.class))).thenReturn("token");

        RegistroResponseDTO response = service.registrar(user);

        Assert.assertEquals(response.getCreated(), userCreated.getCreated());
        Assert.assertEquals(response.getModified(), userCreated.getModified());
    }

    @Test(expected = ErrorNegocioException.class)
    public void registrarCasoEmailInvalido() throws ErrorNegocioException {
        ReflectionTestUtils.setField(service, "errorEmail", "Email no valido");

        UserDTO user = UserDTO.builder()
                .name("Belkys")
                .email("belkys.cardenas")
                .password("Abcdddd12")
                .phones(Collections.singletonList(PhonesDTO.builder().number(123456L).build()))
                .build();

        service.registrar(user);
    }

    @Test(expected = ErrorNegocioException.class)
    public void registrarCasoPasswordInvalido() throws ErrorNegocioException {
        ReflectionTestUtils.setField(service, "errorPassword", "Password no valido");

        UserDTO user = UserDTO.builder()
                .name("Belkys")
                .email("belkys@gmail.com")
                .password("aaa")
                .phones(Collections.singletonList(PhonesDTO.builder().number(123456L).build()))
                .build();

        service.registrar(user);
    }

    @Test(expected = ErrorNegocioException.class)
    public void registrarTestCasoEmailDuplicado() throws ErrorNegocioException {
        UserDTO user = UserDTO.builder()
                .name("Belkys")
                .email("belkys@gmail.com")
                .password("Abcdddd12")
                .phones(Collections.singletonList(PhonesDTO.builder().number(123456L).build()))
                .build();

        Mockito.when(userRepository.save(Mockito.any(UserModel.class))).thenThrow(DataIntegrityViolationException.class);
        Mockito.when(jwtToken.generateToken(Mockito.any(UserDTO.class))).thenReturn("token");

        service.registrar(user);
    }
}
