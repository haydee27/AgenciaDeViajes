package org.main.java.com.vuelosreservas.singletonUsuario;

public class Usuario {
    private static Usuario instancia;
    private String nombre;

    private Usuario() {}

    public static Usuario getInstancia() {
        if (instancia == null) {
            instancia = new Usuario();
        }
        return instancia;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

}
