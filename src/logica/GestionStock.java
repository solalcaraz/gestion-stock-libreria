package logica;

import java.io.IOException;

public class GestionStock {
    private final Consola consola;
    private final RegistroUsuarios registro;
    private final Usuario usuario;

    public GestionStock(Consola consola, RegistroUsuarios registro, Usuario usuario) {
        this.consola = consola;
        this.registro = registro;
        this.usuario = usuario;
    }

    public void ejecutar() {
        int opcion;
        do {
            opcion = Menu.stock(consola);
            switch (opcion) {
                case Menu.INGRESAR_PRODUCTO -> ingresarProducto();
                case Menu.MODIFICAR_PRODUCTO -> modificarProducto();
                case Menu.ELIMINAR_PRODUCTO -> eliminarProducto();
                case Menu.LISTAR_PRODUCTOS -> listarProductos();
                case Menu.CERRAR_SESION -> {
                    consola.mensaje("Sesión cerrada. Gracias por utilizar la aplicación, "
                            + usuario.getNombreUsuario() + "!");
                    consola.separador();
                }
                default -> Menu.opcionInvalida(consola);
            }
        } while (opcion != Menu.CERRAR_SESION);
    }

    private void ingresarProducto() {
        consola.titulo("-----INGRESO DE PRODUCTOS-----");
        String codigo = consola.leerTexto("Ingresar código: ");
        String titulo = consola.leerTexto("Ingresar título: ");
        String autor = consola.leerTexto("Ingresar autor: ");
        String editorial = consola.leerTexto("Ingresar editorial: ");
        int cantidad = consola.leerEntero("Ingresar cantidad de ejemplares: ");

        usuario.agregarProducto(new Producto(codigo, titulo, autor, editorial, cantidad));
        guardarCambios();
        consola.mensaje("Producto ingresado de manera exitosa.");
    }

    private void modificarProducto() {
        consola.titulo("-----MODIFICAR PRODUCTOS-----");
        String codigo = consola.leerTexto("Ingrese el código del producto que desee modificar: ");
        Producto producto = usuario.buscarProducto(codigo);
        if (producto == null) {
            consola.mensaje("Producto no encontrado.");
            return;
        }

        consola.mensaje("Indique lo que desee modificar, según la opción. (T/A/E/C)");
        consola.mensaje("[T]: Modificar título.");
        consola.mensaje("[A]: Modificar autor.");
        consola.mensaje("[E]: Modificar editorial.");
        consola.mensaje("[C]: Modificar cantidad.");
        String opcion = consola.leerTexto("");

        switch (opcion) {
            case "T" -> {
                mostrarValorActual("El TÍTULO actual es: " + producto.getTitulo());
                producto.setTitulo(consola.leerTexto("Ingrese el nuevo título: "));
            }
            case "A" -> {
                mostrarValorActual("El AUTOR actual es: " + producto.getAutor());
                producto.setAutor(consola.leerTexto("Ingrese el nuevo autor: "));
            }
            case "E" -> {
                mostrarValorActual("La EDITORIAL actual es: " + producto.getEditorial());
                producto.setEditorial(consola.leerTexto("Ingrese la nueva editorial: "));
            }
            case "C" -> {
                mostrarValorActual("La CANTIDAD actual es: " + producto.getCantidad());
                producto.setCantidad(consola.leerEntero("Ingrese la nueva cantidad: "));
            }
            default -> {
                consola.mensaje("Opción inválida.");
                return;
            }
        }
        guardarCambios();
        consola.mensaje("Modificación exitosa.");
    }

    private void mostrarValorActual(String valorActual) {
        consola.mensaje("Detalles del producto encontrado:");
        consola.mensaje(valorActual);
        consola.separador();
    }

    private void eliminarProducto() {
        consola.titulo("-----ELIMINAR PRODUCTOS-----");
        String codigo = consola.leerTexto("Ingrese el código del producto que desea eliminar: ");
        Producto producto = usuario.buscarProducto(codigo);
        if (producto == null) {
            consola.mensaje("No se encontró ningún producto con el código indicado.");
            return;
        }

        consola.mensaje("Detalles del producto:");
        consola.mostrarProducto(producto);
        consola.separador();
        String respuesta = consola.leerTexto("¿Está seguro que desea eliminar el producto? (s/n): ");
        if (respuesta.equalsIgnoreCase("s")) {
            usuario.eliminarProducto(producto);
            guardarCambios();
            consola.mensaje("El producto ha sido eliminado correctamente.");
        } else {
            consola.mensaje("El producto no se ha eliminado.");
        }
    }

    private void listarProductos() {
        for (Producto producto : usuario.getProductos()) {
            consola.separador();
            consola.mostrarProducto(producto);
        }
    }

    private void guardarCambios() {
        try {
            registro.guardar();
        } catch (IOException e) {
            consola.mensaje("No se pudieron guardar los cambios: " + e.getMessage());
        }
    }
}
