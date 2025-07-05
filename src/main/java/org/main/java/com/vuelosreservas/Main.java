package org.main.java.com.vuelosreservas;

import com.formdev.flatlaf.FlatDarculaLaf;
import org.main.java.com.vuelosreservas.ui.SeleccionTipoViajeVentana;
import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {
        try {
            FlatDarculaLaf.setup();

            UIManager.put("Button.arc", 20);
            UIManager.put("Component.arc", 15);
            UIManager.put("Button.background", new Color(192, 57, 43)); // rojo-granate
            UIManager.put("Button.foreground", Color.BLACK);
            UIManager.put("Panel.background", new Color(43, 43, 43));
            UIManager.put("Label.foreground", new Color(224, 224, 224));
            UIManager.put("TextField.background", Color.WHITE);
            UIManager.put("TextField.foreground", Color.BLACK);
            UIManager.put("ComboBox.background", Color.WHITE);
            UIManager.put("ComboBox.foreground", Color.BLACK);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        new SeleccionTipoViajeVentana();
    }
}