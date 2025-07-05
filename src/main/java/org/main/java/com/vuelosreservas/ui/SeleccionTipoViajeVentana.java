package org.main.java.com.vuelosreservas.ui;

import com.github.lgooddatepicker.components.DatePicker;
import com.github.lgooddatepicker.components.DatePickerSettings;
import org.main.java.com.vuelosreservas.factory.*;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;

public class SeleccionTipoViajeVentana extends JFrame {

    private JComboBox<String> comboOrigen;
    private JComboBox<String> comboDestino;
    private DatePicker pickerSalida;
    private DatePicker pickerRegreso;
    private JRadioButton radioSencillo;
    private JRadioButton radioRedondo;
    private JComboBox<String> comboClase; // Económica o Ejecutiva

    public SeleccionTipoViajeVentana() {
        setTitle("✈️ Selección de Viaje");
        setSize(500, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        initComponents();
        setVisible(true);
    }

    private void initComponents() {
        JPanel panel = new JPanel(new GridLayout(8, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        getContentPane().add(panel);

        radioSencillo = new JRadioButton("Viaje Sencillo", true);
        radioRedondo = new JRadioButton("Viaje Redondo");

        ButtonGroup grupoTipo = new ButtonGroup();
        grupoTipo.add(radioSencillo);
        grupoTipo.add(radioRedondo);

        String[] lugares = {"San Salvador", "Guatemala", "Tegucigalpa", "Bogotá", "CDMX", "Miami"};

        comboOrigen = new JComboBox<>(lugares);
        comboDestino = new JComboBox<>(lugares);

        // 🗓️ DatePicker + veto a fechas pasadas
        DatePickerSettings salidaSettings = new DatePickerSettings();
        pickerSalida = new DatePicker(salidaSettings); // primero construyes el DatePicker
        salidaSettings.setVetoPolicy(date -> date != null && !date.isBefore(LocalDate.now())); // luego el vetoPolicy


        DatePickerSettings regresoSettings = new DatePickerSettings();
        pickerRegreso = new DatePicker(regresoSettings);
        regresoSettings.setVetoPolicy(date -> date != null && !date.isBefore(LocalDate.now()));

        pickerRegreso.setEnabled(false); // solo si es redondo

        radioSencillo.addActionListener(e -> pickerRegreso.setEnabled(false));
        radioRedondo.addActionListener(e -> pickerRegreso.setEnabled(true));

        comboClase = new JComboBox<>(new String[]{"Económica", "Ejecutiva"});

        // 🧱 Agregamos al panel
        panel.add(new JLabel("Tipo de Vuelo:"));
        JPanel panelRadio = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelRadio.add(radioSencillo);
        panelRadio.add(radioRedondo);
        panel.add(panelRadio);

        panel.add(new JLabel("Origen:"));
        panel.add(comboOrigen);

        panel.add(new JLabel("Destino:"));
        panel.add(comboDestino);

        panel.add(new JLabel("Fecha de Salida:"));
        panel.add(pickerSalida);

        panel.add(new JLabel("Fecha de Regreso:"));
        panel.add(pickerRegreso);

        panel.add(new JLabel("Clase del Vuelo:"));
        panel.add(comboClase);

        JButton btnBuscar = new JButton("🔎 Buscar Vuelos");
        panel.add(new JLabel()); // espacio
        panel.add(btnBuscar);

        btnBuscar.addActionListener(e -> {
            String origen = (String) comboOrigen.getSelectedItem();
            String destino = (String) comboDestino.getSelectedItem();
            LocalDate salida = pickerSalida.getDate();
            LocalDate regreso = pickerRegreso.getDate();
            boolean redondo = radioRedondo.isSelected();
            String claseSeleccionada = (String) comboClase.getSelectedItem();

            // 🧠 Validaciones
            if (origen.equals(destino)) {
                JOptionPane.showMessageDialog(this, "El origen y destino no pueden ser iguales.");
                return;
            }

            if (salida == null || (redondo && regreso == null)) {
                JOptionPane.showMessageDialog(this, "Debe seleccionar todas las fechas requeridas.");
                return;
            }

            if (redondo && regreso.isBefore(salida)) {
                JOptionPane.showMessageDialog(this, "La fecha de regreso no puede ser anterior a la de salida.");
                return;
            }

            // 🏭 Selección del tipo de clase usando FACTORY
            VueloFactory factory;
            if (claseSeleccionada.equals("Económica")) {
                factory = new VueloEconomicoFactory();
            } else {
                factory = new VueloEjecutivoFactory();
            }

            ServicioVuelo servicioVuelo = factory.crearServicio();

            // 🚀 Ir a selección de vuelos
            dispose();
            new SeleccionVueloVentana(
                    !redondo,  // true si es sencillo
                    salida,
                    regreso,
                    servicioVuelo
            ).setVisible(true);
        });
    }
}
