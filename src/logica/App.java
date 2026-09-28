package logica;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Scanner;

public class App {
    private static final Path ARCHIVO_DATOS = Path.of("datos.txt");

    private final Consola consola;
    private final RegistroUsuarios registro;

    private App(Consola consola, RegistroUsuarios registro) {
        this.consola = consola;
        this.registro = registro;
    }

    public static void main(String[] args) {
        RegistroUsuarios registro;
        try {
            registro = new RegistroUsuarios(ARCHIVO_DATOS);
        } catch (IOException e) {
            // Si arrancara con la lista vacía, el primer guardado pisaría los datos que no se pudieron leer.
            System.out.println("No se pudo leer " + ARCHIVO_DATOS.toAbsolutePath() + ": " + e.getMessage());
            return;
        }

        try (Scanner scanner = new Scanner(System.in)) {
            new App(new Consola(scanner), registro).ejecutar();
        }
    }

    private void ejecutar() {
        int opcion;
        do {
            opcion = Menu.inicial(consola);
            switch (opcion) {
                case Menu.CREAR_USUARIO -> crearUsuario();
                case Menu.INICIAR_SESION -> {
                    Usuario usuario = iniciarSesion();
                    if (usuario != null) {
                        new GestionStock(consola, registro, usuario).ejecutar();
                    }
                }
                case Menu.SALIR -> {
                    consola.separador();
                    consola.mensaje("Saliendo del programa...");
                    consola.separador();
                }
                default -> Menu.opcionInvalida(consola);
            }
        } while (opcion != Menu.SALIR);
    }

    private void crearUsuario() {
        while (true) {
            consola.titulo("-----CREAR USUARIO-----");
            String nombreUsuario = consola.leerTexto("Ingrese un nombre de usuario: ");
            String contrasena = consola.leerTexto("Ingrese una contraseña: ");

            if (registro.buscar(nombreUsuario) == null) {
                registro.agregar(new Usuario(nombreUsuario, contrasena));
                try {
                    registro.guardar();
                } catch (IOException e) {
                    consola.mensaje("No se pudieron guardar los cambios: " + e.getMessage());
                }
                consola.mensaje("Usuario creado exitosamente.");
                return;
            }
            consola.mensaje("El nombre de usuario ya está en uso. Por favor inténtelo con otro.");
        }
    }

    private Usuario iniciarSesion() {
        consola.titulo("-----INICIAR SESIÓN-----");
        String nombreUsuario = consola.leerTexto("Ingresar usuario: ");
        String contrasena = consola.leerTexto("Ingresar contraseña: ");

        Usuario usuario = registro.buscar(nombreUsuario);
        if (usuario == null) {
            consola.mensaje("Usuario no encontrado. Inténtelo nuevamente o cree un usuario nuevo.");
            return null;
        }
        if (!usuario.contrasenaCorrecta(contrasena)) {
            consola.mensaje("Contraseña incorrecta. Inténtelo de nuevo.");
            return null;
        }
        consola.mensaje("Sesión iniciada exitosamente.");
        consola.mensaje("¡Bienvenid@, " + nombreUsuario + "!");
        consola.separador();
        return usuario;
    }
}
