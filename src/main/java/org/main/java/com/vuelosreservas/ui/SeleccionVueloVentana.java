package org.main.java.com.vuelosreservas.ui;

import com.formdev.flatlaf.FlatDarculaLaf;
import org.main.java.com.vuelosreservas.factory.ServicioVuelo;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class SeleccionVueloVentana extends JFrame {

    private final boolean esViajeSencillo;
    private final LocalDate fechaSalida;
    private final LocalDate fechaRegreso;
    private final ServicioVuelo tipoVuelo;

    private final ButtonGroup grupoIda = new ButtonGroup();
    private final ButtonGroup grupoRegreso = new ButtonGroup();

    private JRadioButton vueloIdaSeleccionado;
    private JRadioButton vueloRegresoSeleccionado;

    public SeleccionVueloVentana(boolean esViajeSencillo, LocalDate fechaSalida, LocalDate fechaRegreso, ServicioVuelo tipoVuelo) {
        try {
            FlatDarculaLaf.setup();

            UIManager.put("Button.arc", 20);
            UIManager.put("Component.arc", 15);
            UIManager.put("Button.background", new Color(192, 57, 43));
            UIManager.put("Button.foreground", Color.BLACK);
            UIManager.put("Panel.background", new Color(43, 43, 43));
            UIManager.put("Label.foreground", new Color(224, 224, 224));
            UIManager.put("RadioButton.background", new Color(43, 43, 43));
            UIManager.put("RadioButton.foreground", new Color(224, 224, 224));
            UIManager.put("ScrollPane.background", new Color(43, 43, 43));
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        this.esViajeSencillo = esViajeSencillo;
        this.fechaSalida = fechaSalida;
        this.fechaRegreso = fechaRegreso;
        this.tipoVuelo = tipoVuelo;

        setTitle("🛫 Selección de Vuelos (" + tipoVuelo.getDescripcion() + ")");
        setSize(700, esViajeSencillo ? 400 : 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        initComponents();
    }

    private void initComponents() {
        setLayout(new BorderLayout(10, 10));
        getContentPane().setBackground(new Color(43, 43, 43));

        JPanel panelVuelos = new JPanel();
        panelVuelos.setLayout(new BoxLayout(panelVuelos, BoxLayout.Y_AXIS));
        panelVuelos.setBackground(new Color(43, 43, 43));
        panelVuelos.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));

        JLabel tituloIda = new JLabel("✈️ Seleccione vuelo de ida para el " + fechaSalida);
        tituloIda.setFont(new Font("SansSerif", Font.BOLD, 16));
        tituloIda.setForeground(new Color(224, 224, 224));
        panelVuelos.add(tituloIda);
        panelVuelos.add(Box.createVerticalStrut(10));
        panelVuelos.add(crearPanelVuelosSimulados(fechaSalida, grupoIda, true));

        if (!esViajeSencillo) {
            JLabel tituloRegreso = new JLabel("🔁 Seleccione vuelo de regreso para el " + fechaRegreso);
            tituloRegreso.setFont(new Font("SansSerif", Font.BOLD, 16));
            tituloRegreso.setForeground(new Color(224, 224, 224));
            panelVuelos.add(Box.createVerticalStrut(20));
            panelVuelos.add(tituloRegreso);
            panelVuelos.add(Box.createVerticalStrut(10));
            panelVuelos.add(crearPanelVuelosSimulados(fechaRegreso, grupoRegreso, false));
        }

        // ✅ BOTONES ABAJO
        JButton btnVolver = new JButton("⬅️ Volver");
        btnVolver.setFont(new Font("SansSerif", Font.BOLD, 13));
        btnVolver.addActionListener(e -> {
            dispose();
            new SeleccionTipoViajeVentana().setVisible(true);
        });

        JButton btnContinuar = new JButton("➡️ Continuar");
        btnContinuar.setFont(new Font("SansSerif", Font.BOLD, 14));
        btnContinuar.addActionListener(e -> {
            if (grupoIda.getSelection() == null) {
                JOptionPane.showMessageDialog(this, "Debes seleccionar un vuelo de ida.");
                return;
            }
            if (!esViajeSencillo && grupoRegreso.getSelection() == null) {
                JOptionPane.showMessageDialog(this, "Debes seleccionar un vuelo de regreso.");
                return;
            }

            String vueloIda = vueloIdaSeleccionado.getText();
            String vueloRegreso = esViajeSencillo ? null : vueloRegresoSeleccionado.getText();

            dispose();
            new SeleccionExtrasVentana(vueloIda, vueloRegreso, !esViajeSencillo, tipoVuelo).setVisible(true);
        });

        JPanel panelBoton = new JPanel(new FlowLayout(FlowLayout.RIGHT, 15, 10));
        panelBoton.setBackground(new Color(43, 43, 43));
        panelBoton.add(btnVolver);
        panelBoton.add(btnContinuar);

        add(new JScrollPane(panelVuelos), BorderLayout.CENTER);
        add(panelBoton, BorderLayout.SOUTH);
    }

    private JPanel crearPanelVuelosSimulados(LocalDate fecha, ButtonGroup grupo, boolean esIda) {
        JPanel panel = new JPanel(new GridLayout(3, 1, 10, 10));
        panel.setBackground(new Color(60, 63, 65));
        panel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(80, 80, 80), 1),
                BorderFactory.createEmptyBorder(10, 10, 10, 10)
        ));

        double precioBase = tipoVuelo.getPrecioBase();

        List<String> vuelos = new ArrayList<>();
        vuelos.add("Vuelo 101 - 06:00 AM - $" + precioBase);
        vuelos.add("Vuelo 202 - 12:30 PM - $" + precioBase);
        vuelos.add("Vuelo 303 - 08:45 PM - $" + precioBase);

        for (String vuelo : vuelos) {
            JRadioButton btnVuelo = new JRadioButton(vuelo);
            btnVuelo.setFont(new Font("SansSerif", Font.PLAIN, 14));
            btnVuelo.setBackground(new Color(43, 43, 43));
            btnVuelo.setForeground(new Color(224, 224, 224));
            grupo.add(btnVuelo);
            panel.add(btnVuelo);

            if (esIda) {
                btnVuelo.addActionListener(e -> vueloIdaSeleccionado = btnVuelo);
            } else {
                btnVuelo.addActionListener(e -> vueloRegresoSeleccionado = btnVuelo);
            }
        }

        return panel;
    }
}
