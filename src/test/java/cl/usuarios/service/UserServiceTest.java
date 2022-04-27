package cl.usuarios.service;

import cl.usuarios.dto.PhonesDTO;
import cl.usuarios.dto.RegistroResponseDTO;
import cl.usuarios.dto.UserDTO;
import cl.usuarios.exception.ErrorNegocioException;
import cl.usuarios.model.PhoneModel;
import cl.usuarios.model.UserModel;
import cl.usuarios.repository.PhoneRepository;
import cl.usuarios.repository.UserRepository;
import cl.usuarios.security.JwtToken;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collections;
import java.util.Date;

@RunWith(SpringRunner.class)
public class UserServiceTest {

    @InjectMocks
    private UserServiceImpl service;

    @Mock
    private UserRepository userRepository;

    @Mock
    private PhoneRepository phoneRepository;

    @Mock
    private JwtToken jwtToken;

    @Test
    public void registrarTest() throws ErrorNegocioException {
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

        PhoneModel phoneCreated = PhoneModel.builder()
                .number(user.getPhones().get(0).getNumber())
                .build();

        Mockito.when(userRepository.save(Mockito.any(UserModel.class))).thenReturn(userCreated);
        Mockito.when(phoneRepository.save(Mockito.any(PhoneModel.class))).thenReturn(phoneCreated);
        Mockito.when(jwtToken.generateToken(Mockito.any(UserDTO.class))).thenReturn("token");
        
        RegistroResponseDTO response = service.registrar(user);

        Assert.assertEquals(response.getCreated(), userCreated.getCreated());
        Assert.assertEquals(response.getModified(), userCreated.getModified());
    }
}
