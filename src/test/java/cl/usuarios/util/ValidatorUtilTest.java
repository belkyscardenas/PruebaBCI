package cl.usuarios.util;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class)
public class ValidatorUtilTest {
    @Test
    public void validarEmailCasoEmailValido() {
        assertEquals(true, ValidatorUtil.validarEmail("belkys@gmail.com"));
    }

    @Test
    public void validarEmailCasoEmailNoValido() {
        assertEquals(false, ValidatorUtil.validarEmail("belkys.cardenas"));
    }

    @Test
    public void validarPasswordCasoPasswordValido() {
        assertEquals(true, ValidatorUtil.validarPassword("Admin123"));
    }

    @Test
    public void validarPasswordCasoPasswordNoValido() {
        assertEquals(false, ValidatorUtil.validarPassword("a123"));
    }

}
