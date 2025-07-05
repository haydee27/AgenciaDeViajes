package org.main.java.com.vuelosreservas.ui;

import org.main.java.com.vuelosreservas.builderVuelo.ReservaBuilder;
import org.main.java.com.vuelosreservas.builderVuelo.ReservaVuelo;
import org.main.java.com.vuelosreservas.decorator.*;
import org.main.java.com.vuelosreservas.factory.ServicioVuelo;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Date;

public class SeleccionExtrasVentana extends JFrame {

    private final String vueloIdaTexto;
    private final String vueloRegresoTexto;
    private final boolean esRedondo;
    private final ServicioVuelo tipoVuelo;

    private String asientoIda = null;
    private String asientoRegreso = null;

    private JCheckBox checkMaleta;
    private JCheckBox checkAsistencia;
    private JCheckBox checkAcompaniante;

    public SeleccionExtrasVentana(String vueloIdaTexto, String vueloRegresoTexto, boolean esRedondo, ServicioVuelo tipoVuelo) {
        this.vueloIdaTexto = vueloIdaTexto;
        this.vueloRegresoTexto = vueloRegresoTexto;
        this.esRedondo = esRedondo;
        this.tipoVuelo = tipoVuelo;

        setTitle("🪑 Selección de Asientos y Extras");
        setSize(700, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        initComponents();
    }

    private void initComponents() {
        setLayout(new BorderLayout(10, 10));

        JPanel panelPrincipal = new JPanel();
        panelPrincipal.setLayout(new BoxLayout(panelPrincipal, BoxLayout.Y_AXIS));
        panelPrincipal.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        panelPrincipal.add(new JLabel("🪑 Seleccione su asiento de ida:"));
        panelPrincipal.add(crearPanelAsientos(asiento -> asientoIda = asiento));

        if (esRedondo) {
            panelPrincipal.add(Box.createVerticalStrut(10));
            panelPrincipal.add(new JLabel("🪑 Seleccione su asiento de regreso:"));
            panelPrincipal.add(crearPanelAsientos(asiento -> asientoRegreso = asiento));
        }

        panelPrincipal.add(Box.createVerticalStrut(20));
        panelPrincipal.add(new JLabel("🧳 Servicios adicionales:"));

        checkMaleta = new JCheckBox("🧳 Maleta adicional (+$50)");
        checkAsistencia = new JCheckBox("♿ Asistencia especial (gratis)");
        checkAcompaniante = new JCheckBox("🧓 Acompañante adulto mayor (+$100)");

        panelPrincipal.add(checkMaleta);
        panelPrincipal.add(checkAsistencia);
        panelPrincipal.add(checkAcompaniante);

        // ✅ BOTÓN CONTINUAR
        JButton btnContinuar = new JButton("➡️ Continuar");
        btnContinuar.addActionListener(e -> {
            if (asientoIda == null || (esRedondo && asientoRegreso == null)) {
                JOptionPane.showMessageDialog(this,
                        esRedondo ? "Debe seleccionar asiento de ida y regreso." : "Debe seleccionar un asiento.",
                        "Faltan asientos", JOptionPane.WARNING_MESSAGE);
                return;
            }

            ArrayList<String> extras = new ArrayList<>();
            if (checkMaleta.isSelected()) extras.add("Maleta");
            if (checkAsistencia.isSelected()) extras.add("Asistencia");
            if (checkAcompaniante.isSelected()) extras.add("Acompaniante");

            ReservaVuelo reservaIda = new ReservaBuilder()
                    .setOrigen("OrigenSimulado")
                    .setDestino("DestinoSimulado")
                    .setFechaHora(new Date())
                    .setTipoVuelo(tipoVuelo)
                    .build();

            ReservaExtra decoradaIda = new ReservaBasica(reservaIda);
            if (checkMaleta.isSelected()) decoradaIda = new ExtraEquipaje(decoradaIda);
            if (checkAsistencia.isSelected()) decoradaIda = new ExtraSillaRueda(decoradaIda);
            if (checkAcompaniante.isSelected()) decoradaIda = new ExtraAcompaniante(decoradaIda);
            decoradaIda = new ExtraAsiento(decoradaIda, asientoIda);

            ReservaVuelo reservaRegreso = null;
            ReservaExtra decoradaRegreso = null;
            if (esRedondo) {
                reservaRegreso = new ReservaBuilder()
                        .setOrigen("DestinoSimulado")
                        .setDestino("OrigenSimulado")
                        .setFechaHora(new Date())
                        .setTipoVuelo(tipoVuelo)
                        .build();

                decoradaRegreso = new ReservaBasica(reservaRegreso);
                if (checkMaleta.isSelected()) decoradaRegreso = new ExtraEquipaje(decoradaRegreso);
                if (checkAsistencia.isSelected()) decoradaRegreso = new ExtraSillaRueda(decoradaRegreso);
                if (checkAcompaniante.isSelected()) decoradaRegreso = new ExtraAcompaniante(decoradaRegreso);
                decoradaRegreso = new ExtraAsiento(decoradaRegreso, asientoRegreso);
            }

            dispose();
            new DatosPasajeroVentana(
                    reservaIda,
                    reservaRegreso,
                    vueloIdaTexto,
                    vueloRegresoTexto,
                    asientoIda,
                    asientoRegreso,
                    extras,
                    decoradaIda,
                    decoradaRegreso
            ).setVisible(true);
        });

        // ✅ BOTÓN VOLVER
        JButton btnVolver = new JButton("⬅️ Volver");
        btnVolver.addActionListener(e -> {
            dispose();
            new SeleccionVueloVentana(esRedondo ? false : true, java.time.LocalDate.now(), java.time.LocalDate.now(), tipoVuelo).setVisible(true);
        });

        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.RIGHT, 15, 10));
        panelBotones.setBackground(Color.DARK_GRAY); // O tu color base si estás usando FlatLaf
        panelBotones.add(btnVolver);
        panelBotones.add(btnContinuar);

        add(new JScrollPane(panelPrincipal), BorderLayout.CENTER);
        add(panelBotones, BorderLayout.SOUTH);
    }

    private JPanel crearPanelAsientos(java.util.function.Consumer<String> onSeleccion) {
        JPanel panel = new JPanel(new GridLayout(4, 6, 5, 5));
        ButtonGroup grupo = new ButtonGroup();
        String[] columnas = {"A", "B", "C", "D", "E", "F"};

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 6; j++) {
                String codigo = columnas[j] + (i + 1);
                JToggleButton btn = new JToggleButton(codigo);
                btn.addActionListener(e -> onSeleccion.accept(codigo));
                grupo.add(btn);
                panel.add(btn);
            }
        }

        return panel;
    }
}
