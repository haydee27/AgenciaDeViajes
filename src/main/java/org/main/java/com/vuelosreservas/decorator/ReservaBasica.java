package org.main.java.com.vuelosreservas.decorator;

import org.main.java.com.vuelosreservas.builderVuelo.ReservaVuelo;

public class ReservaBasica implements  ReservaExtra{
    private final ReservaVuelo reserva;

    public ReservaBasica(ReservaVuelo reserva) {
        this.reserva = reserva;
    }

    @Override
    public String getDescripcion() {
        return reserva.getResumen();
    }

    @Override
    public double getCosto() {
        return reserva.getPrecio();
    }
}
