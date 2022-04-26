package com.example.prueba.service;

import com.example.prueba.dto.RegistroResponseDTO;
import com.example.prueba.dto.UserDTO;
import com.example.prueba.model.PhoneModel;
import com.example.prueba.model.UserModel;
import com.example.prueba.repository.PhoneRepository;
import com.example.prueba.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PhoneRepository phoneRepository;

    @Override
    public RegistroResponseDTO registrar(UserDTO user) {
        UserModel response = userRepository.save(UserModel.builder()
                .name(user.getName())
                .email(user.getEmail())
                .password(user.getPassword())
                .created(new Date())
                .modified(new Date())
                .lastLogin(new Date())
                .isActive(Boolean.TRUE)
                .token(UUID.randomUUID().toString())
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
                .build();
    }
}
