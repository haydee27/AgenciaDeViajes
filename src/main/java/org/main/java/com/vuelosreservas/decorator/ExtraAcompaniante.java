package org.main.java.com.vuelosreservas.decorator;

public class ExtraAcompaniante implements ReservaExtra{

    private final ReservaExtra reserva;

    public ExtraAcompaniante(ReservaExtra reserva) {
        this.reserva = reserva;
    }

    @Override
    public String getDescripcion() {
        return reserva.getDescripcion() + "\n🧓 Acompañante adulto mayor incluido.";
    }

    @Override
    public double getCosto() {
        return reserva.getCosto() + 100.0;
    }
}
