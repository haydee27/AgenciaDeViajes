package org.main.java.com.vuelosreservas.adapterVueloExterno;

import org.main.java.com.vuelosreservas.reserva.ReservaFinal;

public class VueloAdapter implements Servicio {

    private final ProveedorExterno proveedor = new ProveedorExterno();

    @Override
    public void reservar(ReservaFinal reserva) {
        proveedor.reservarVuelo(
                reserva.getNombre() + " " + reserva.getApellido(),
                reserva.getVueloIda(),
                reserva.getVueloRegreso(),
                reserva.getTotal(),
                reserva.getAsientoIda(),
                reserva.getAsientoRegreso(),
                reserva.getExtras()
        );
    }
}
