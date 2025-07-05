package org.main.java.com.vuelosreservas.strategy;

public class ProcesadorPago {
    private MetodoPago metodo;

    public void setMetodo(MetodoPago metodo) {
        this.metodo = metodo;
    }

    public void procesar(double monto) {
        if (metodo != null) {
            metodo.pagar(monto);
        }
    }
}
