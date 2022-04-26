package com.example.prueba.controller;

import com.example.prueba.dto.PhonesDTO;
import com.example.prueba.dto.RegistroResponseDTO;
import com.example.prueba.dto.UserDTO;
import com.example.prueba.service.UserService;
import com.example.prueba.service.UserServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
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
    public void registrarTest() {
        UserDTO user = UserDTO.builder()
                .name("Belkys")
                .email("belkys@gmail.com")
                .password("Abcd12")
                .phones(Collections.singletonList(PhonesDTO.builder().number("123456").build()))
                .build();

        RegistroResponseDTO response = RegistroResponseDTO.builder().id(1L)
                .created(new Date())
                .modified()
                .build();

        Mockito.when(service.registrar(Mockito.any(UserDTO.class))).thenReturn()
    }
}
