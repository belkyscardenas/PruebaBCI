package com.example.prueba.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDTO {

    @NotEmpty
    private String name;
    @NotEmpty
    @Email
    private String email;
    @NotEmpty
    //@Pattern(regexp="^(?=.*[A-Z])(?=.*[a-z])(\\d{2})$", message="Debe tener una mayuscula, letras minusculas y dos numeros")
    private String password;
    private List<PhonesDTO> phones;
}
