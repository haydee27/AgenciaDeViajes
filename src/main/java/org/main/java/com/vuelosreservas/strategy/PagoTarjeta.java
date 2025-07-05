package org.main.java.com.vuelosreservas.strategy;

import javax.swing.*;

public class PagoTarjeta implements MetodoPago{
    @Override
    public void pagar(double monto) {
        JOptionPane.showMessageDialog(null, "💳 Pago de $" + monto + " realizado con tarjeta.");
    }
}
