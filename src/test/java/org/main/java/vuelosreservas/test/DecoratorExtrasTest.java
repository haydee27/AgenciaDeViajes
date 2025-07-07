package org.main.java.vuelosreservas.test;

import org.junit.jupiter.api.Test;
import org.main.java.com.vuelosreservas.builderVuelo.ReservaBuilder;
import org.main.java.com.vuelosreservas.builderVuelo.ReservaVuelo;
import org.main.java.com.vuelosreservas.decorator.*;
import org.main.java.com.vuelosreservas.factory.VueloEconomico;
import org.main.java.com.vuelosreservas.factory.VueloEconomicoFactory;

import java.util.Date;
import static org.junit.jupiter.api.Assertions.*;

public class DecoratorExtrasTest {

    private ReservaExtra crearReservaBase() {
        ReservaVuelo vuelo = new ReservaBuilder()
                .setOrigen("San Salvador")
                .setDestino("Bogotá")
                .setFechaHora(new Date())
                .setTipoVuelo(new VueloEconomicoFactory().crearServicio())
                .build();

        return new ReservaBasica(vuelo);
    }

    @Test
    void testExtraEquipajeSumaCosto() {
        ReservaExtra base = crearReservaBase();
        double costoBase = base.getCosto();

        ReservaExtra conEquipaje = new ExtraEquipaje(base);

        assertEquals(costoBase + 50.0, conEquipaje.getCosto());
    }

    @Test
    void testAsientoPremium() {
        ReservaExtra base = crearReservaBase();
        ReservaExtra asiento = new ExtraAsiento(base, "A1"); // Premium

        assertTrue(asiento.getDescripcion().contains("Premium"));
        assertEquals(base.getCosto() + 35.0, asiento.getCosto());
    }

    @Test
    void testAcompanianteCosto() {
        ReservaExtra base = crearReservaBase();
        ReservaExtra conAcompaniante = new ExtraAcompaniante(base);

        assertEquals(base.getCosto() + 100.0, conAcompaniante.getCosto());
    }
}
