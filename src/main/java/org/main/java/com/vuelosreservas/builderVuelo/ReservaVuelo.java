package org.main.java.com.vuelosreservas.builderVuelo;

import org.main.java.com.vuelosreservas.factory.ServicioVuelo;

import java.util.Date;

public class ReservaVuelo {
    public final String origen;
    public final String destino;
    public final Date fechaHora;
    public final double precio;
    private final ServicioVuelo tipoVuelo;

    public ReservaVuelo(String origen, String destino, Date fechaHora, ServicioVuelo tipoVuelo) {
        this.origen = origen;
        this.destino = destino;
        this.fechaHora = fechaHora;
        this.tipoVuelo = tipoVuelo;
        this.precio = tipoVuelo.getPrecioBase();
    }
    public String getResumen() {
        return "Reserva: " + origen + " ➡ " + destino +
                "\nFecha: " + fechaHora +
                "\nTipo: " + tipoVuelo.getDescripcion() +
                "\nPrecio: $" + precio;
    }

    public double getPrecio() {
        return precio;
    }

    public ServicioVuelo getTipoVuelo() {
        return tipoVuelo;
    }
}
