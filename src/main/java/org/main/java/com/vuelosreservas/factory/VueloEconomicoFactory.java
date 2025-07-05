package org.main.java.com.vuelosreservas.factory;
public class VueloEconomicoFactory extends VueloFactory {
    public ServicioVuelo crearServicio()
    {
        return new VueloEconomico();
    }
}
