package logica;

public class Menu {
    public static final int CREAR_USUARIO = 1;
    public static final int INICIAR_SESION = 2;
    public static final int SALIR = 3;

    public static final int INGRESAR_PRODUCTO = 1;
    public static final int MODIFICAR_PRODUCTO = 2;
    public static final int ELIMINAR_PRODUCTO = 3;
    public static final int LISTAR_PRODUCTOS = 4;
    public static final int CERRAR_SESION = 5;

    public static int inicial(Consola consola) {
        consola.titulo("----- MENÚ INICIAL -----");
        consola.mensaje("[1]. Crear usuario.");
        consola.mensaje("[2]. Iniciar sesión.");
        consola.mensaje("[3]. Salir.");
        return consola.leerOpcion("Elige una opción: ");
    }

    public static int stock(Consola consola) {
        consola.titulo("-----MENÚ: STOCK DE LIBRERÍA-----");
        consola.mensaje("[1]. Ingresar productos");
        consola.mensaje("[2]. Modificar productos");
        consola.mensaje("[3]. Eliminar productos");
        consola.mensaje("[4]. Listar los productos");
        consola.mensaje("[5]. Cerrar sesión");
        return consola.leerOpcion("Elige una opción: ");
    }

    public static void opcionInvalida(Consola consola) {
        consola.mensaje("Opción inválida. Inténtelo de nuevo.");
        consola.separador();
    }
}
