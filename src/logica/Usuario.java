package logica;

import java.util.ArrayList;
import java.util.List;

public class Usuario {
    private final String nombreUsuario;
    private final String contrasena;
    private final List<Producto> productos = new ArrayList<>();

    public Usuario(String nombreUsuario, String contrasena) {
        this.nombreUsuario = nombreUsuario;
        this.contrasena = contrasena;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public String getContrasena() {
        return contrasena;
    }

    public boolean contrasenaCorrecta(String contrasena) {
        return this.contrasena.equals(contrasena);
    }

    public List<Producto> getProductos() {
        return productos;
    }

    public void agregarProducto(Producto producto) {
        productos.add(producto);
    }

    public void eliminarProducto(Producto producto) {
        productos.remove(producto);
    }

    /** Devuelve null si el usuario no tiene un producto con ese código. */
    public Producto buscarProducto(String codigo) {
        for (Producto producto : productos) {
            if (producto.getCodigo().equals(codigo)) {
                return producto;
            }
        }
        return null;
    }
}
