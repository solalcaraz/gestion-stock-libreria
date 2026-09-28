package logica;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

/**
 * Guarda los usuarios y sus productos en un archivo de texto que se puede abrir con el Bloc de notas.
 * Cada línea es un registro con los campos separados por tabulaciones, y cada PRODUCTO
 * pertenece al último USUARIO que aparece arriba de él.
 */
public class RegistroUsuarios {
    private static final String SEPARADOR = "\t";
    private static final String USUARIO = "USUARIO";
    private static final String PRODUCTO = "PRODUCTO";

    private final Path archivo;
    private final List<Usuario> usuarios = new ArrayList<>();

    public RegistroUsuarios(Path archivo) throws IOException {
        this.archivo = archivo;
        if (Files.exists(archivo)) {
            cargar();
        }
    }

    /** Devuelve null si no existe un usuario con ese nombre. */
    public Usuario buscar(String nombreUsuario) {
        for (Usuario usuario : usuarios) {
            if (usuario.getNombreUsuario().equals(nombreUsuario)) {
                return usuario;
            }
        }
        return null;
    }

    public void agregar(Usuario usuario) {
        usuarios.add(usuario);
    }

    // Reescribe el archivo completo en cada cambio: con pocos datos es lo más simple
    // y el archivo siempre refleja el estado actual.
    public void guardar() throws IOException {
        List<String> lineas = new ArrayList<>();
        for (Usuario usuario : usuarios) {
            lineas.add(String.join(SEPARADOR, USUARIO, usuario.getNombreUsuario(), usuario.getContrasena()));
            for (Producto producto : usuario.getProductos()) {
                lineas.add(String.join(SEPARADOR, PRODUCTO, producto.getCodigo(), producto.getTitulo(),
                        producto.getAutor(), producto.getEditorial(), String.valueOf(producto.getCantidad())));
            }
        }
        Files.write(archivo, lineas, StandardCharsets.UTF_8);
    }

    private void cargar() throws IOException {
        List<String> lineas = Files.readAllLines(archivo, StandardCharsets.UTF_8);
        Usuario usuarioActual = null;
        for (int i = 0; i < lineas.size(); i++) {
            String[] campos = lineas.get(i).split(SEPARADOR, -1);
            if (campos[0].equals(USUARIO) && campos.length == 3) {
                usuarioActual = new Usuario(campos[1], campos[2]);
                usuarios.add(usuarioActual);
            } else if (campos[0].equals(PRODUCTO) && campos.length == 6 && usuarioActual != null
                    && campos[5].matches("-?\\d+")) {
                usuarioActual.agregarProducto(new Producto(campos[1], campos[2], campos[3], campos[4],
                        Integer.parseInt(campos[5])));
            } else if (!lineas.get(i).isBlank()) {
                throw new IOException("la línea " + (i + 1) + " no tiene un formato válido");
            }
        }
    }
}
