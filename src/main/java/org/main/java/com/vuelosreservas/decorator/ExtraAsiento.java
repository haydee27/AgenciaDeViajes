package org.main.java.com.vuelosreservas.decorator;

public class ExtraAsiento implements ReservaExtra {
    private final ReservaExtra reserva;
    private final String codigoAsiento;
    private final boolean esAsientoPremium;

    public ExtraAsiento(ReservaExtra reserva, String codigoAsiento) {
        this.reserva = reserva;
        this.codigoAsiento = codigoAsiento;
        this.esAsientoPremium = codigoAsiento.matches("A1|B1|C1|D1|E1|F1");
    }

    @Override
    public String getDescripcion() {
        double extra = esAsientoPremium ? 35.0 : 15.0;
        String descripcion = reserva.getDescripcion() +
                "\n💺 Asiento seleccionado: " + codigoAsiento +
                (esAsientoPremium ? " (Premium)" : "") +
                " (+$" + extra + ")";
        return descripcion;
    }

    @Override
    public double getCosto() {
        double extra = esAsientoPremium ? 35.0 : 15.0;
        return reserva.getCosto() + extra;
    }
}
