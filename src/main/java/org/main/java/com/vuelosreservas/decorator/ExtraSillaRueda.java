package org.main.java.com.vuelosreservas.decorator;

public class ExtraSillaRueda implements ReservaExtra{

    private final ReservaExtra reserva;
    public ExtraSillaRueda(ReservaExtra reserva) {
        this.reserva = reserva;
    }

    @Override
    public String getDescripcion() {
        return reserva.getDescripcion() + "\n♿ + Asistencia con silla de ruedas";
    }

    @Override
    public double getCosto() {
        return reserva.getCosto() + 0.0; // gratuito en Avianca
    }
}
