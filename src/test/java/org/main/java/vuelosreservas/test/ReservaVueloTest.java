package org.main.java.vuelosreservas.test;

import org.junit.jupiter.api.Test;
import org.main.java.com.vuelosreservas.builderVuelo.ReservaBuilder;
import org.main.java.com.vuelosreservas.builderVuelo.ReservaVuelo;
import org.main.java.com.vuelosreservas.factory.ServicioVuelo;
import org.main.java.com.vuelosreservas.factory.VueloEconomico;
import org.main.java.com.vuelosreservas.factory.VueloEconomicoFactory;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ReservaVueloTest {
    @Test
    void testCreacionReservaVuelo() {
        ServicioVuelo tipo = new VueloEconomicoFactory().crearServicio();

        ReservaVuelo reserva = new ReservaBuilder()
                .setOrigen("San Salvador")
                .setDestino("Guatemala")
                .setFechaHora(new Date())
                .setTipoVuelo(tipo)
                .build();

        assertNotNull(reserva);
        assertEquals("San Salvador", reserva.origen);
        assertEquals("Guatemala", reserva.destino);
        assertEquals("Clase Económica", reserva.getTipoVuelo().getDescripcion());
        assertEquals(300.0, reserva.getPrecio());
    }

}
