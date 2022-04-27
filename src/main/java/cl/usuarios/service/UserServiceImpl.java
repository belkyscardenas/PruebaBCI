package cl.usuarios.service;

import cl.usuarios.dto.RegistroResponseDTO;
import cl.usuarios.dto.UserDTO;
import cl.usuarios.exception.ErrorNegocioException;
import cl.usuarios.model.PhoneModel;
import cl.usuarios.model.UserModel;
import cl.usuarios.repository.PhoneRepository;
import cl.usuarios.repository.UserRepository;
import cl.usuarios.security.JwtToken;
import cl.usuarios.util.ValidatorUtil;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PhoneRepository phoneRepository;

    @Autowired
    private JwtToken jwtToken;

    @Value("${error.email}")
    private String errorEmail;

    @Value("${error.password}")
    private String errorPassword;

    @Value("${error.registro}")
    private String errorRegistro;

    @Value("${error.generico}")
    private String errorGenerico;

    @Override
    public RegistroResponseDTO registrar(UserDTO user) throws ErrorNegocioException {

        if (!ValidatorUtil.validarEmail(user.getEmail())) {
            throw new ErrorNegocioException(errorEmail);
        }

        if (!ValidatorUtil.validarPassword(user.getPassword())) {
            throw new ErrorNegocioException(errorPassword);
        }

        try {
            UserModel response = userRepository.save(UserModel.builder()
                    .name(user.getName())
                    .email(user.getEmail())
                    .password(user.getPassword())
                    .created(new Date())
                    .modified(new Date())
                    .lastLogin(new Date())
                    .isActive(Boolean.TRUE)
                    .token(jwtToken.generateToken(user))
                    .build());

            user.getPhones().forEach(phone -> phoneRepository.save(PhoneModel.builder()
                    .id(response.getId())
                    .number(phone.getNumber())
                    .cityCode(phone.getCitycode())
                    .countryCode(phone.getContrycode())
                    .build()));

            return RegistroResponseDTO.builder()
                    .id(response.getId())
                    .created(response.getCreated())
                    .modified(response.getModified())
                    .lastLogin(response.getLastLogin())
                    .token(response.getToken())
                    .lastLogin(response.getLastLogin())
                    .isActive(response.getIsActive())
                    .build();
        } catch (Exception e) {
            if(e instanceof DataIntegrityViolationException) {
                throw new ErrorNegocioException(errorRegistro);
            } else {
                throw new ErrorNegocioException(errorGenerico);
            }
        }
    }
}
