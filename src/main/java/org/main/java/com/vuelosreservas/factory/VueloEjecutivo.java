package org.main.java.com.vuelosreservas.factory;

public class VueloEjecutivo implements ServicioVuelo {
    public String getDescripcion() {
        return "Clase Ejecutiva";
    }

    public double getPrecioBase() {
        return 600.0;
    }
}

