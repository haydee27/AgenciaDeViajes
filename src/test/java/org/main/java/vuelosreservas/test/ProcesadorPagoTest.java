package org.main.java.vuelosreservas.test;

import org.junit.jupiter.api.Test;
import org.main.java.com.vuelosreservas.strategy.MetodoPago;
import org.main.java.com.vuelosreservas.strategy.PagoPaypal;
import org.main.java.com.vuelosreservas.strategy.PagoTarjeta;
import org.main.java.com.vuelosreservas.strategy.ProcesadorPago;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ProcesadorPagoTest {
    @Test
    void testCambioMetodoPago() {
        ProcesadorPago procesador = new ProcesadorPago();
        MetodoPago tarjeta = new PagoTarjeta();
        MetodoPago paypal = new PagoPaypal();

        procesador.setMetodo(tarjeta);
        assertNotNull(procesador);

        procesador.setMetodo(paypal);
        assertNotNull(procesador);
    }

    @Test
    void testProcesoPagoNoNulo() {
        ProcesadorPago procesador = new ProcesadorPago();
        procesador.setMetodo(new PagoPaypal());
        assertDoesNotThrow(() -> procesador.procesar(500));
    }
}
