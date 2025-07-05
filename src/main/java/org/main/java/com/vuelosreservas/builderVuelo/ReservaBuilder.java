package org.main.java.com.vuelosreservas.builderVuelo;

import org.main.java.com.vuelosreservas.factory.ServicioVuelo;

import java.util.Date;

public class ReservaBuilder {
    private String origen;
    private String destino;
    private Date fechaHora;
    private ServicioVuelo tipoVuelo;

    public ReservaBuilder setOrigen(String origen) {
        this.origen = origen;
        return this;
    }

    public ReservaBuilder setDestino(String destino) {
        this.destino = destino;
        return this;
    }

    public ReservaBuilder setFechaHora(Date fechaHora) {
        this.fechaHora = fechaHora;
        return this;
    }

    public ReservaBuilder setTipoVuelo(ServicioVuelo tipoVuelo) {
        this.tipoVuelo = tipoVuelo;
        return this;
    }

    public ReservaVuelo build() {
        return new ReservaVuelo(origen, destino, fechaHora, tipoVuelo);
    }
}
