package org.main.java.com.vuelosreservas.reserva;

import org.main.java.com.vuelosreservas.builderVuelo.ReservaVuelo;
import org.main.java.com.vuelosreservas.decorator.ReservaExtra;

import java.util.List;

public class ReservaFinal {

    private final ReservaVuelo vueloIda;
    private final ReservaVuelo vueloRegreso;
    private final String vueloIdaTexto;
    private final String vueloRegresoTexto;
    private final String asientoIda;
    private final String asientoRegreso;
    private final List<String> extrasSeleccionados;
    private final ReservaExtra reservaDecoradaIda;
    private final ReservaExtra reservaDecoradaRegreso;

    private final String nombre;
    private final String apellido;
    private final String nacimiento;
    private final String genero;
    private final String nacionalidad;
    private final String telefono;
    private final boolean asistenciaAeropuerto;

    // ✔️ Constructor
    public ReservaFinal(ReservaVuelo vueloIda,
                        ReservaVuelo vueloRegreso,
                        String vueloIdaTexto,
                        String vueloRegresoTexto,
                        String asientoIda,
                        String asientoRegreso,
                        List<String> extrasSeleccionados,
                        ReservaExtra reservaDecoradaIda,
                        ReservaExtra reservaDecoradaRegreso,
                        String nombre,
                        String apellido,
                        String nacimiento,
                        String genero,
                        String nacionalidad,
                        String telefono,
                        boolean asistenciaAeropuerto) {

        this.vueloIda = vueloIda;
        this.vueloRegreso = vueloRegreso;
        this.vueloIdaTexto = vueloIdaTexto;
        this.vueloRegresoTexto = vueloRegresoTexto;
        this.asientoIda = asientoIda;
        this.asientoRegreso = asientoRegreso;
        this.extrasSeleccionados = extrasSeleccionados;
        this.reservaDecoradaIda = reservaDecoradaIda;
        this.reservaDecoradaRegreso = reservaDecoradaRegreso;
        this.nombre = nombre;
        this.apellido = apellido;
        this.nacimiento = nacimiento;
        this.genero = genero;
        this.nacionalidad = nacionalidad;
        this.telefono = telefono;
        this.asistenciaAeropuerto = asistenciaAeropuerto;
    }

    // ✔️ Resumen completo
    public String generarResumenCompleto() {
        StringBuilder sb = new StringBuilder();

        sb.append("👤 PASAJERO\n");
        sb.append("Nombre: ").append(nombre).append(" ").append(apellido).append("\n");
        sb.append("Nacimiento: ").append(nacimiento).append("\n");
        sb.append("Género: ").append(genero).append("\n");
        sb.append("Nacionalidad: ").append(nacionalidad).append("\n");
        sb.append("Teléfono: ").append(telefono).append("\n");
        sb.append("Asistencia aeropuerto: ").append(asistenciaAeropuerto ? "Sí" : "No").append("\n\n");

        sb.append("✈️ VUELOS\n");
        sb.append("Vuelo Ida: ").append(vueloIdaTexto).append("\n");
        if (vueloIda != null) {
            sb.append(" - Precio Base: $").append(vueloIda.getPrecio()).append("\n");
        }
        sb.append("Asiento Ida: ").append(asientoIda).append("\n");

        if (vueloRegresoTexto != null && vueloRegreso != null) {
            sb.append("\nVuelo Regreso: ").append(vueloRegresoTexto).append("\n");
            sb.append(" - Precio Base: $").append(vueloRegreso.getPrecio()).append("\n");
            sb.append("Asiento Regreso: ").append(asientoRegreso).append("\n");
        }

        sb.append("\n🛠 SERVICIOS EXTRAS SELECCIONADOS\n");
        if (extrasSeleccionados.isEmpty()) {
            sb.append("Ninguno\n");
        } else {
            for (String extra : extrasSeleccionados) {
                sb.append("• ").append(extra).append("\n");
            }
        }

        sb.append("\n💳 DETALLE DEL PAGO\n");
        sb.append("📍 Vuelo de Ida:\n").append(reservaDecoradaIda.getDescripcion()).append("\n");

        if (reservaDecoradaRegreso != null) {
            sb.append("\n📍 Vuelo de Regreso:\n").append(reservaDecoradaRegreso.getDescripcion()).append("\n");
        }

        sb.append("\n-------------------------------------------\n");
        sb.append("💰 TOTAL A PAGAR: $").append(getTotal()).append("\n");

        return sb.toString();
    }

    // ✔️ Cálculo total
    public double getTotal() {
        double total = 0.0;
        if (reservaDecoradaIda != null) total += reservaDecoradaIda.getCosto();
        if (reservaDecoradaRegreso != null) total += reservaDecoradaRegreso.getCosto();
        return total;
    }

    // ✔️ Getters para Adapter
    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public ReservaVuelo getVueloIda() {
        return vueloIda;
    }

    public ReservaVuelo getVueloRegreso() {
        return vueloRegreso;
    }

    public String getAsientoIda() {
        return asientoIda;
    }

    public String getAsientoRegreso() {
        return asientoRegreso;
    }

    public List<String> getExtras() {
        return extrasSeleccionados;
    }

    public ReservaExtra getReservaDecoradaIda() {
        return reservaDecoradaIda;
    }

    public ReservaExtra getReservaDecoradaRegreso() {
        return reservaDecoradaRegreso;
    }
}
