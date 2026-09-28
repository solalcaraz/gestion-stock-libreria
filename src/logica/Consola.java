package logica;

import java.util.Scanner;

public class Consola {
    private static final String LINEA_TITULO = "###########################";
    private static final String SEPARADOR = "---------------------------";

    private final Scanner scanner;

    public Consola(Scanner scanner) {
        this.scanner = scanner;
    }

    public void titulo(String texto) {
        System.out.println(LINEA_TITULO);
        System.out.println(texto);
        System.out.println(LINEA_TITULO);
    }

    public void separador() {
        System.out.println(SEPARADOR);
    }

    public void mensaje(String texto) {
        System.out.println(texto);
    }

    public String leerTexto(String pregunta) {
        System.out.print(pregunta);
        String texto = scanner.nextLine();
        separador();
        return texto;
    }

    public int leerEntero(String pregunta) {
        while (true) {
            String texto = leerTexto(pregunta);
            try {
                return Integer.parseInt(texto.trim());
            } catch (NumberFormatException e) {
                mensaje("Ingrese un número válido.");
            }
        }
    }

    /** Devuelve -1 si no se ingresó un número, así el menú lo trata como una opción inválida más. */
    public int leerOpcion(String pregunta) {
        System.out.print(pregunta);
        try {
            return Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    public void mostrarProducto(Producto producto) {
        mensaje("Código: " + producto.getCodigo());
        mensaje("Título: " + producto.getTitulo());
        mensaje("Autor: " + producto.getAutor());
        mensaje("Editorial: " + producto.getEditorial());
        mensaje("Cantidad: " + producto.getCantidad());
    }
}
