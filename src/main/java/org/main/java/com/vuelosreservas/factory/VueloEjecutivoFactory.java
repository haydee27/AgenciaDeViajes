package org.main.java.com.vuelosreservas.factory;

public class VueloEjecutivoFactory extends VueloFactory {
    public ServicioVuelo crearServicio() {
        return new VueloEjecutivo();
    }
}
