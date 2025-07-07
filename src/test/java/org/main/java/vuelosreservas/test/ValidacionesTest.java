package org.main.java.vuelosreservas.test;

import org.junit.jupiter.api.Test;

import java.util.regex.Pattern;

import static org.junit.jupiter.api.Assertions.*;

public class ValidacionesTest {

    @Test
    void testTelefonoInvalido() {
        String telefono = "abc123";
        assertFalse(telefono.matches("\\d{7,15}"));
    }

    @Test
    void testNombreConSimbolos() {
        String nombre = "Haydee#123";
        Pattern soloLetras = Pattern.compile("^[\\p{L} ]+$");
        assertFalse(soloLetras.matcher(nombre).matches());
    }

    @Test
    void testCorreoValido() {
        String correo = "haydee@gmail.com";
        assertTrue(correo.matches("^[a-z0-9._]+@[a-z]+\\.[a-z]{2,6}$"));
    }
}
