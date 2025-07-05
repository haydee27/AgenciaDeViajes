package org.main.java.com.vuelosreservas.factory;

public class VueloEconomico implements ServicioVuelo {
    public String getDescripcion() {
        return "Clase Económica";
    }

    public double getPrecioBase() {
        return 300.0;
    }
}
