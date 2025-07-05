package org.main.java.com.vuelosreservas.ui;

import org.main.java.com.vuelosreservas.adapterVueloExterno.Servicio;
import org.main.java.com.vuelosreservas.adapterVueloExterno.VueloAdapter;
import org.main.java.com.vuelosreservas.reserva.ReservaFinal;
import org.main.java.com.vuelosreservas.strategy.*;

import javax.swing.*;
import javax.swing.text.*;
import java.awt.*;
import java.text.ParseException;

public class PagoVentana extends JFrame {

    private final JRadioButton radioTarjeta;
    private final JRadioButton radioPaypal;

    private final JPanel panelTarjeta;
    private final JPanel panelPaypal;

    private JTextField campoCorreoPaypal;
    private JPasswordField campoClavePaypal;

    private JTextField campoNombre;
    private JTextField campoCorreo;
    private JTextField campoNumero;
    private JFormattedTextField campoVencimiento;
    private JTextField campoCVV;

    private final ReservaFinal reservaFinal;
    private final JFrame ventanaAnterior;

    public PagoVentana(ReservaFinal reservaFinal, JFrame ventanaAnterior) {
        this.reservaFinal = reservaFinal;
        this.ventanaAnterior = ventanaAnterior;

        setTitle("💳 Método de Pago");
        setSize(600, 550);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        // Método de pago
        JPanel panelOpciones = new JPanel(new FlowLayout(FlowLayout.LEFT, 20, 10));
        radioTarjeta = new JRadioButton("Tarjeta", true);
        radioPaypal = new JRadioButton("PayPal");
        ButtonGroup grupoPago = new ButtonGroup();
        grupoPago.add(radioTarjeta);
        grupoPago.add(radioPaypal);
        panelOpciones.setBorder(BorderFactory.createTitledBorder("Selecciona un método de pago:"));
        panelOpciones.add(radioTarjeta);
        panelOpciones.add(radioPaypal);
        add(panelOpciones, BorderLayout.NORTH);

        // Panel tarjeta
        panelTarjeta = new JPanel(new GridLayout(5, 2, 10, 10));
        panelTarjeta.setBorder(BorderFactory.createTitledBorder("Pago con Tarjeta"));

        campoNombre = crearCampoTexto(40, true);
        campoCorreo = crearCampoTexto(40, false);
        campoNumero = crearCampoTarjeta();
        campoCVV = crearCampoNumerico(3);

        try {
            MaskFormatter formatter = new MaskFormatter("##/##");
            formatter.setPlaceholderCharacter('_');
            campoVencimiento = new JFormattedTextField(formatter);
        } catch (ParseException e) {
            campoVencimiento = new JFormattedTextField();
        }

        campoVencimiento.setBackground(Color.WHITE);

        panelTarjeta.add(new JLabel("👤 Nombre en la tarjeta:"));
        panelTarjeta.add(campoNombre);
        panelTarjeta.add(new JLabel("📧 Correo electrónico:"));
        panelTarjeta.add(campoCorreo);
        panelTarjeta.add(new JLabel("💳 Número de tarjeta:"));
        panelTarjeta.add(campoNumero);
        panelTarjeta.add(new JLabel("📆 Vencimiento (MM/AA):"));
        panelTarjeta.add(campoVencimiento);
        panelTarjeta.add(new JLabel("🔒 CVV (3 dígitos):"));
        panelTarjeta.add(campoCVV);

        // Panel PayPal
        panelPaypal = new JPanel(new GridLayout(2, 2, 10, 10));
        panelPaypal.setBorder(BorderFactory.createTitledBorder("Acceso PayPal"));

        campoCorreoPaypal = crearCampoTexto(50, false);
        campoClavePaypal = new JPasswordField();
        campoClavePaypal.setBackground(Color.WHITE);

        panelPaypal.add(new JLabel("📧 Correo PayPal:"));
        panelPaypal.add(campoCorreoPaypal);
        panelPaypal.add(new JLabel("🔐 Contraseña:"));
        panelPaypal.add(campoClavePaypal);
        panelPaypal.setVisible(false);

        // Alternar visibilidad de paneles
        radioTarjeta.addActionListener(e -> {
            panelTarjeta.setVisible(true);
            panelPaypal.setVisible(false);
        });

        radioPaypal.addActionListener(e -> {
            panelTarjeta.setVisible(false);
            panelPaypal.setVisible(true);
        });

        JPanel panelCentral = new JPanel(new CardLayout());
        panelCentral.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        panelCentral.add(panelTarjeta, "Tarjeta");
        panelCentral.add(panelPaypal, "PayPal");

        add(panelCentral, BorderLayout.CENTER);

        // Botones
        JButton btnFinalizar = new JButton("✅ Finalizar Reserva");
        btnFinalizar.addActionListener(e -> {
            MetodoPago metodo;
            if (radioTarjeta.isSelected()) {
                if (!validarTarjeta()) return;
                metodo = new PagoTarjeta();
            } else {
                if (!validarPaypal()) return;
                metodo = new PagoPaypal();
            }

            ProcesadorPago procesador = new ProcesadorPago();
            procesador.setMetodo(metodo);
            procesador.procesar(reservaFinal.getTotal());

            Servicio adaptador = new VueloAdapter();
            adaptador.reservar(reservaFinal);

            JOptionPane.showMessageDialog(this, "✅ ¡Reserva confirmada!");
            dispose();
            new ResumenFinalVentana(reservaFinal).setVisible(true);
        });

        JButton btnVolver = new JButton("⬅️ Volver");
        btnVolver.addActionListener(e -> {
            dispose();
            if (ventanaAnterior != null) ventanaAnterior.setVisible(true);
        });

        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.RIGHT, 15, 10));
        panelBotones.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        panelBotones.add(btnVolver);
        panelBotones.add(btnFinalizar);

        add(panelBotones, BorderLayout.SOUTH);
    }

    // Utilidades de campos
    private JTextField crearCampoTexto(int maxLength, boolean permiteEspacios) {
        JTextField field = new JTextField();
        field.setBackground(Color.WHITE);
        ((AbstractDocument) field.getDocument()).setDocumentFilter(new CampoTextoFilter(maxLength, permiteEspacios));
        return field;
    }

    private JTextField crearCampoNumerico(int maxDigits) {
        JTextField field = new JTextField();
        field.setBackground(Color.WHITE);
        ((AbstractDocument) field.getDocument()).setDocumentFilter(new DigitosExactosFilter(maxDigits));
        return field;
    }

    private JTextField crearCampoTarjeta() {
        JTextField field = new JTextField();
        field.setBackground(Color.WHITE);
        ((AbstractDocument) field.getDocument()).setDocumentFilter(new TarjetaFilter());
        return field;
    }

    // Validaciones
    private boolean validarTarjeta() {
        if (campoNombre.getText().isEmpty() || campoCorreo.getText().isEmpty()
                || campoNumero.getText().isEmpty() || campoVencimiento.getText().isEmpty() || campoCVV.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Complete todos los datos de la tarjeta.");
            return false;
        }

        if (!campoCorreo.getText().matches("^[\\w._%+-]+@[\\w.-]+\\.[a-zA-Z]{2,}$")) {
            JOptionPane.showMessageDialog(this, "Correo inválido.");
            return false;
        }

        if (!campoNumero.getText().matches("^\\d{4}-\\d{4}-\\d{4}-\\d{4}$")) {
            JOptionPane.showMessageDialog(this, "Número de tarjeta inválido.");
            return false;
        }

        if (!campoCVV.getText().matches("\\d{3}")) {
            JOptionPane.showMessageDialog(this, "CVV inválido.");
            return false;
        }

        if (!campoVencimiento.getText().matches("^(0[1-9]|1[0-2])/\\d{2}$")) {
            JOptionPane.showMessageDialog(this, "Fecha inválida. Use MM/AA.");
            return false;
        }

        return true;
    }

    private boolean validarPaypal() {
        String correo = campoCorreoPaypal.getText().trim();
        String clave = new String(campoClavePaypal.getPassword());

        if (correo.isEmpty() || clave.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Ingrese correo y contraseña de PayPal.");
            return false;
        }

        if (!correo.matches("^[\\w._%+-]+@[\\w.-]+\\.[a-zA-Z]{2,}$")) {
            JOptionPane.showMessageDialog(this, "Correo PayPal inválido.");
            return false;
        }

        return true;
    }

    // Filtros
    private static class CampoTextoFilter extends DocumentFilter {
        private final int max;
        private final boolean permiteEspacios;

        public CampoTextoFilter(int max, boolean permiteEspacios) {
            this.max = max;
            this.permiteEspacios = permiteEspacios;
        }

        @Override
        public void replace(FilterBypass fb, int offset, int length, String texto, AttributeSet attrs) throws BadLocationException {
            String limpio = permiteEspacios ? texto.replaceAll("[^a-zA-ZáéíóúÁÉÍÓÚñÑ ]", "") : texto.replaceAll("[^a-zA-Z0-9@._]", "");
            if (fb.getDocument().getLength() - length + limpio.length() <= max) {
                super.replace(fb, offset, length, limpio, attrs);
            }
        }
    }

    private static class DigitosExactosFilter extends DocumentFilter {
        private final int max;

        public DigitosExactosFilter(int max) {
            this.max = max;
        }

        @Override
        public void replace(FilterBypass fb, int offset, int length, String texto, AttributeSet attrs) throws BadLocationException {
            if (texto.matches("\\d*") && fb.getDocument().getLength() - length + texto.length() <= max) {
                super.replace(fb, offset, length, texto, attrs);
            }
        }
    }

    private static class TarjetaFilter extends DocumentFilter {
        @Override
        public void replace(FilterBypass fb, int offset, int length, String texto, AttributeSet attrs) throws BadLocationException {
            StringBuilder builder = new StringBuilder();
            Document doc = fb.getDocument();
            String current = doc.getText(0, doc.getLength());
            String result = current.substring(0, offset) + texto + current.substring(offset + length);
            String digits = result.replaceAll("\\D", "");

            for (int i = 0; i < digits.length(); i++) {
                if (i > 0 && i % 4 == 0) builder.append("-");
                builder.append(digits.charAt(i));
            }

            if (builder.length() <= 19) {
                fb.remove(0, doc.getLength());
                fb.insertString(0, builder.toString(), attrs);
            }
        }
    }
}
