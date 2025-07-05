package org.main.java.com.vuelosreservas.ui;

import com.itextpdf.text.Document;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import org.main.java.com.vuelosreservas.reserva.ReservaFinal;

import javax.swing.*;
import java.awt.*;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ResumenFinalVentana extends JFrame {

    private final ReservaFinal reserva;

    public ResumenFinalVentana(ReservaFinal reserva) {
        this.reserva = reserva;

        setTitle("Resumen de Reserva");
        setSize(600, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        initUI();
    }

    private void initUI() {
        setLayout(new BorderLayout());

        JTextArea areaResumen = new JTextArea();
        areaResumen.setEditable(false);
        areaResumen.setFont(new Font("Monospaced", Font.PLAIN, 13));
        areaResumen.setText(reserva.generarResumenCompleto());

        JScrollPane scroll = new JScrollPane(areaResumen);
        add(scroll, BorderLayout.CENTER);

        // 📎 Botón PDF
        JButton btnPDF = new JButton("⬇️ Descargar PDF");
        btnPDF.addActionListener(e -> generarPDF());

        // 🏠 Botón Volver al inicio
        JButton btnInicio = new JButton("⬅️ Volver al inicio");
        btnInicio.addActionListener(e -> {
            dispose(); // Cierra ventana actual
            new SeleccionTipoViajeVentana().setVisible(true); // Abre la primera
        });

        // 🧱 Panel de botones inferior
        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.RIGHT, 15, 10));
        panelBotones.add(btnInicio);
        panelBotones.add(btnPDF);

        add(panelBotones, BorderLayout.SOUTH);
    }

    private void generarPDF() {
        try {
            String nombreArchivo = "Reserva_" + new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date()) + ".pdf";
            Document doc = new Document();
            PdfWriter.getInstance(doc, new FileOutputStream(nombreArchivo));
            doc.open();

            Paragraph titulo = new Paragraph("✈️ Comprobante de Reserva", FontFactory.getFont(FontFactory.HELVETICA_BOLD, 18));
            titulo.setAlignment(Paragraph.ALIGN_CENTER);
            doc.add(titulo);

            doc.add(new Paragraph("Generado: " + new SimpleDateFormat("dd MMM yyyy HH:mm:ss").format(new Date())));
            doc.add(new Paragraph("--------------------------------------------------"));

            String resumen = reserva.generarResumenCompleto();
            for (String linea : resumen.split("\n")) {
                doc.add(new Paragraph(linea));
            }

            doc.add(new Paragraph("--------------------------------------------------"));
            doc.add(new Paragraph("Total a pagar: $" + reserva.getTotal(), FontFactory.getFont(FontFactory.HELVETICA_BOLD, 14)));

            doc.close();

            JOptionPane.showMessageDialog(this, "📄 PDF guardado como:\n" + nombreArchivo);
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error al generar el PDF.");
        }
    }
}
