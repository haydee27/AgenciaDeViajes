package org.main.java.com.vuelosreservas.ui;

import com.github.lgooddatepicker.components.DatePicker;
import com.github.lgooddatepicker.components.DatePickerSettings;
import org.main.java.com.vuelosreservas.builderVuelo.ReservaVuelo;
import org.main.java.com.vuelosreservas.decorator.ReservaExtra;
import org.main.java.com.vuelosreservas.reserva.ReservaFinal;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.regex.Pattern;

public class DatosPasajeroVentana extends JFrame {

    private JTextField campoNombre;
    private JTextField campoApellido;
    private DatePicker campoNacimiento;
    private JComboBox<String> comboGenero;
    private JComboBox<String> comboNacionalidad;
    private JTextField campoTelefono;

    private JCheckBox checkAsistenciaEspecial;

    // Datos requeridos para construir la reserva final
    private final ReservaVuelo vueloIda;
    private final ReservaVuelo vueloRegreso;
    private final String vueloIdaTexto;
    private final String vueloRegresoTexto;
    private final String asientoIda;
    private final String asientoRegreso;
    private final ArrayList<String> extras;
    private final ReservaExtra reservaDecoradaIda;
    private final ReservaExtra reservaDecoradaRegreso;

    public DatosPasajeroVentana(
            ReservaVuelo vueloIda,
            ReservaVuelo vueloRegreso,
            String vueloIdaTexto,
            String vueloRegresoTexto,
            String asientoIda,
            String asientoRegreso,
            ArrayList<String> extras,
            ReservaExtra reservaDecoradaIda,
            ReservaExtra reservaDecoradaRegreso
    ) {
        this.vueloIda = vueloIda;
        this.vueloRegreso = vueloRegreso;
        this.vueloIdaTexto = vueloIdaTexto;
        this.vueloRegresoTexto = vueloRegresoTexto;
        this.asientoIda = asientoIda;
        this.asientoRegreso = asientoRegreso;
        this.extras = extras;
        this.reservaDecoradaIda = reservaDecoradaIda;
        this.reservaDecoradaRegreso = reservaDecoradaRegreso;

        setTitle("✍️ Datos del Pasajero");
        setSize(500, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        initComponents();
    }

    private void initComponents() {
        setLayout(new BorderLayout(10, 10));
        JPanel panel = new JPanel(new GridLayout(8, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        campoNombre = new JTextField();
        campoApellido = new JTextField();
        campoTelefono = new JTextField();

        DatePickerSettings settings = new DatePickerSettings();
        settings.setAllowEmptyDates(false);

// Cambiar el texto del campo seleccionado a negro
        settings.setColor(DatePickerSettings.DateArea.DatePickerTextValidDate, Color.BLACK);
        campoNacimiento = new DatePicker(settings);

        comboGenero = new JComboBox<>(new String[]{"Masculino", "Femenino", "Otro"});

        String[] nacionalidades = {"Salvadoreña", "Guatemalteca", "Hondureña", "Mexicana", "Colombiana", "Estadounidense"};
        comboNacionalidad = new JComboBox<>(nacionalidades);

        checkAsistenciaEspecial = new JCheckBox("Deseo asistencia especial en el aeropuerto");

        panel.add(new JLabel("Nombre(s):"));
        panel.add(campoNombre);
        panel.add(new JLabel("Apellido(s):"));
        panel.add(campoApellido);
        panel.add(new JLabel("Fecha de nacimiento:"));
        panel.add(campoNacimiento);
        panel.add(new JLabel("Género:"));
        panel.add(comboGenero);
        panel.add(new JLabel("Nacionalidad:"));
        panel.add(comboNacionalidad);
        panel.add(new JLabel("Teléfono:"));
        panel.add(campoTelefono);
        panel.add(new JLabel("¿Asistencia en aeropuerto?"));
        panel.add(checkAsistenciaEspecial);

        // ✅ BOTÓN CONTINUAR
        JButton continuar = new JButton("➡️ Ir a Pago");
        continuar.addActionListener(e -> {
            if (!validarCampos()) return;

            String nombre = campoNombre.getText().trim();
            String apellido = campoApellido.getText().trim();
            String nacimiento = campoNacimiento.getDate().toString();
            String genero = comboGenero.getSelectedItem().toString();
            String nacionalidad = comboNacionalidad.getSelectedItem().toString();
            String telefono = campoTelefono.getText().trim();
            boolean asistencia = checkAsistenciaEspecial.isSelected();

            ReservaFinal resumen = new ReservaFinal(
                    vueloIda,
                    vueloRegreso,
                    vueloIdaTexto,
                    vueloRegresoTexto,
                    asientoIda,
                    asientoRegreso,
                    extras,
                    reservaDecoradaIda,
                    reservaDecoradaRegreso,
                    nombre,
                    apellido,
                    nacimiento,
                    genero,
                    nacionalidad,
                    telefono,
                    asistencia
            );

            dispose();
            new PagoVentana(resumen, this).setVisible(true);
            this.setVisible(false);

        });

        // ✅ BOTÓN VOLVER
        JButton volver = new JButton("⬅️ Volver");
        volver.addActionListener(e -> {
            dispose();
            new SeleccionExtrasVentana(vueloIdaTexto, vueloRegresoTexto, vueloRegreso != null, vueloIda.getTipoVuelo()).setVisible(true);
        });

        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.RIGHT, 15, 10));
        panelBotones.setBackground(getBackground());
        panelBotones.add(volver);
        panelBotones.add(continuar);

        add(panel, BorderLayout.CENTER);
        add(panelBotones, BorderLayout.SOUTH);
    }

    private boolean validarCampos() {
        if (campoNombre.getText().trim().isEmpty() ||
                campoApellido.getText().trim().isEmpty() ||
                campoNacimiento.getDate() == null ||
                campoTelefono.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Todos los campos son obligatorios.");
            return false;
        }

        Pattern soloLetras = Pattern.compile("^[\\p{L} ]+$");

        if (!soloLetras.matcher(campoNombre.getText()).matches() ||
                !soloLetras.matcher(campoApellido.getText()).matches()) {
            JOptionPane.showMessageDialog(this, "El nombre y apellido no deben contener números ni símbolos.");
            return false;
        }

        if (!campoTelefono.getText().matches("\\d{7,15}")) {
            JOptionPane.showMessageDialog(this, "El número de teléfono debe contener solo dígitos (7-15).");
            return false;
        }

        return true;
    }
}
