package org.main.java.vuelosreservas.test;

import org.junit.jupiter.api.Test;
import org.main.java.com.vuelosreservas.strategy.PagoTarjeta;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class PagoTarjetaTest {
    @Test
    void testPagoConMontoValido() {
        PagoTarjeta pago = new PagoTarjeta();

        // No lanza excepción si es válido
        assertDoesNotThrow(() -> pago.pagar(100));
    }

    @Test
    void testPagoConMontoInvalido() {
        PagoTarjeta pago = new PagoTarjeta();

        // Simula que si implementaras una validación de monto > 0
        assertDoesNotThrow(() -> pago.pagar(0)); // Aquí puedes poner assertThrows si implementas una excepción
    }
}
