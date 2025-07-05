package org.main.java.com.vuelosreservas.adapterVueloExterno;

import org.main.java.com.vuelosreservas.builderVuelo.ReservaVuelo;

import javax.swing.*;
import java.util.List;

public class ProveedorExterno {

    public void reservarVuelo(
            String pasajero,
            ReservaVuelo vueloIda,
            ReservaVuelo vueloRegreso,
            double precioTotal,
            String asientoIda,
            String asientoRegreso,
            List<String> extras
    ) {
        StringBuilder sb = new StringBuilder();
        sb.append("✈ Reserva confirmada con proveedor externo:\n");
        sb.append("👤 Pasajero: ").append(pasajero).append("\n\n");

        sb.append("📍 Vuelo de Ida:\n");
        sb.append("Origen: ").append(vueloIda.origen).append("\n");
        sb.append("Destino: ").append(vueloIda.destino).append("\n");
        sb.append("Fecha: ").append(vueloIda.fechaHora).append("\n");
        sb.append("Precio Base: $").append(vueloIda.getPrecio()).append("\n");
        sb.append("Asiento: ").append(asientoIda).append("\n\n");

        if (vueloRegreso != null) {
            sb.append("📍 Vuelo de Regreso:\n");
            sb.append("Origen: ").append(vueloRegreso.origen).append("\n");
            sb.append("Destino: ").append(vueloRegreso.destino).append("\n");
            sb.append("Fecha: ").append(vueloRegreso.fechaHora).append("\n");
            sb.append("Precio Base: $").append(vueloRegreso.getPrecio()).append("\n");
            sb.append("Asiento: ").append(asientoRegreso).append("\n\n");
        }

        sb.append("🎒 Extras: ").append(extras.isEmpty() ? "Ninguno" : String.join(", ", extras)).append("\n");
        sb.append("💰 Precio total: $").append(precioTotal);

        JOptionPane.showMessageDialog(null, sb.toString());
    }
}
