package org.main.java.com.vuelosreservas.decorator;

public class ExtraEquipaje implements ReservaExtra {
    private final ReservaExtra reserva;

    public ExtraEquipaje(ReservaExtra reserva) {
        this.reserva = reserva;
    }

    @Override
    public String getDescripcion() {
        return reserva.getDescripcion() + "\n🧳 + Equipaje adicional (+$50)";
    }

    @Override
    public double getCosto() {
        return reserva.getCosto() + 50.0;
    }
}
