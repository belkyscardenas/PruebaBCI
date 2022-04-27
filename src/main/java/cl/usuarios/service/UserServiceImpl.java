package cl.usuarios.service;

import cl.usuarios.dto.PhonesDTO;
import cl.usuarios.dto.RegistroResponseDTO;
import cl.usuarios.dto.UserDTO;
import cl.usuarios.exception.ErrorNegocioException;
import cl.usuarios.model.PhoneModel;
import cl.usuarios.model.UserModel;
import cl.usuarios.repository.UserRepository;
import cl.usuarios.security.JwtToken;
import cl.usuarios.util.ValidatorUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtToken jwtToken;

    @Value("${error.email}")
    private String errorEmail;

    @Value("${error.password}")
    private String errorPassword;

    @Value("${error.registro}")
    private String errorRegistro;

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
                    .phones(this.phoneDTOToPhoneModel(user.getPhones()))
                    .build());

            return RegistroResponseDTO.builder()
                    .id(response.getId())
                    .created(response.getCreated())
                    .modified(response.getModified())
                    .lastLogin(response.getLastLogin())
                    .token(response.getToken())
                    .lastLogin(response.getLastLogin())
                    .isActive(response.getIsActive())
                    .build();
        } catch (DataIntegrityViolationException e) {
            throw new ErrorNegocioException(errorRegistro);
        }
    }

    private List<PhoneModel> phoneDTOToPhoneModel(List<PhonesDTO> phones) {
        List<PhoneModel> phonesModel = new ArrayList<>();

        phones.forEach(phone -> {
            PhoneModel phoneModel = PhoneModel.builder()
                    .number(phone.getNumber())
                    .cityCode(phone.getCitycode())
                    .countryCode(phone.getContrycode())
                    .build();
            phonesModel.add(phoneModel);
        });

        return phonesModel;
    }
}
