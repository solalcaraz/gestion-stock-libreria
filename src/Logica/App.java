package Logica;

/* Programa "Control de Stock para librerias".

* Este programa permite gestionar el stock de las librerias.
*En primer lugar el usuario puede crear un usuario, darse de alta e ingresar a un nuevo menu especifico para los productos.
*Este menu permite agregar los productos nuevos con los siguientes datos: uncodigo, un titulo, el autor, la editorial
y una cantidad.
*El usuario al crearse una cuenta se le pedira un nombre y una contraseña con la que puede iniciar sesion. Esto le
permitira al usuario tener una lista de prodcutos que va creando y almacenando.
*Ademas, el usuario puede modificar los datos ingresados para el producto, esto dentro del menu de Stock. Otras funciones
que se le permite realizar es el listado completo de su Stock, eliminar algun producto y el cierre de sesion.
*El cierre de sesion lo devuelve al menu inicial donde puede volver a ingresar sesion o cerrar el programa. */

import java.util.*;

public class App {
    //Metodo menu inicial con sus parametros
    public static void menuInicial(List<Usuario> usuarios, Scanner sc) {
        
        int opcion_menuini;

        //Mostrar menu mientras que no salga del programa (!3)
        do {
            
            //Leo lo que se ingreso y lo guardo en la variable.
            opcion_menuini = sc.nextInt();
            sc.nextLine();

            //Ingreso a los casos. Segun la "opcion" se ejecuta una accion
            switch (opcion_menuini) {
                case 1:
                    crearUsuario(usuarios, sc);
                    break;
                case 2:
                    //Determino una variable que indica si el usuario ingreso correctamente y esta conectado
                    Usuario usuarioConectado = iniciarSesion(usuarios, sc);
                    if (usuarioConectado != null) {
                        menuStock(usuarios, sc, usuarioConectado);
                    }else{
                        menuInicial(usuarios, sc);
                    }
                    break;
                case 3:
                    System.out.println ("---------------------------");
                    System.out.println("Saliendo del programa...");
                    System.out.println ("---------------------------");
                    //Esta linea la dejo para que cierre el programa sin repetir el menu inicial
                    System.exit(0);
                    break;
                default:
                    System.out.println ("---------------------------");
                    System.out.println("Opción inválida. Inténtalo de nuevo.");
                    System.out.println ("---------------------------");
                    break;
            }
        } while (opcion_menuini != 3);
    }

    //Metodo crear usuario y almacenarlo en una lista de usuarios
    public static void crearUsuario (List<Usuario> usuarios, Scanner sc) {

        System.out.println ("###########################");
        System.out.println ("-----CREAR USUARIO-----");
        System.out.println ("###########################");
        System.out.print("Ingrese un nombre de usuario: ");
        String nombreUsuario = sc.nextLine();
        System.out.println ("---------------------------");
        System.out.print ("Ingrese una contraseña: ");
        String contrasena = sc.nextLine();
        System.out.println ("---------------------------");

        //Verificar si el nombre de usuario ya existe en la lista con una nueva variable que lo almacena
        boolean nombreExiste = false;
        for (Usuario usuario : usuarios) {
            if (usuario.getNombreUsuario().equals(nombreUsuario)) {
                nombreExiste = true;
                break;
            }
        }
        //Mensaje para la posibilidad que el usuario ya este en uso o si se creo correctamente
        if (nombreExiste){
            System.out.println ("El nombre de usuario ya está en uso. Por favor inténtelo con otro.");
            crearUsuario(usuarios, sc);
        }else{
            //Almaceno el nuevo usuario en la lista usuarios
            usuarios.add(new Usuario(nombreUsuario, contrasena));
            System.out.println ("Usuario creado exitosamente.");
        }
    }

    //Metodo inicio de sesion que devuelve un objeto de tipo Usuario, para determinar si esta en linea
    public static Usuario iniciarSesion(List<Usuario> usuarios, Scanner sc){

        System.out.println ("###########################");
        System.out.println("-----INICIAR SESIÓN-----");
        System.out.println ("###########################");
        System.out.print ("Ingresar usuario: ");
        String nombreUsuario = sc.nextLine();
        System.out.println ("---------------------------");
        System.out.print ("Ingresar contraseña: ");
        String contrasena = sc.nextLine();
        System.out.println ("---------------------------");

        //Corroboro que exista el usuario y que su contraseña sea la misma
        Usuario usuarioExiste = null;
        Usuario contrasenaBien = null;

        //Recorro la lista con un for each y traigo los datos guardados
        for (Usuario usuario : usuarios) {
            if (usuario.getNombreUsuario().equals(nombreUsuario)){
                usuarioExiste = usuario;
                if (usuario.getContrasena().equals(contrasena)){
                    contrasenaBien = usuario;
                }
            }
        }
        //Si existe inicio sesion
        if (usuarioExiste != null && contrasenaBien !=null ){
            System.out.println("Sesión iniciada exitosamente.");
            System.out.println("¡Bienvenid@, "+nombreUsuario+"!");
            System.out.println ("---------------------------");
            return usuarioExiste;
        //Para el usuario incorrecto
        }else if (usuarioExiste == null) {
            System.out.println ("Usuario no encontrado. Inténtelo nuevamente o cree un usuario nuevo.");
            return null;
        //Para contraseña incorrecta
        }else if (contrasenaBien == null){
            System.out.println("Contraseña incorrecta. Inténtelo de nuevo.");
            return null;
        }else {
            return null;
        }
    }

    //Metodo para el menu de stock con sus parametros
    public static void menuStock (List<Usuario> usuarios, Scanner sc, Usuario usuarioConectado){

        int opcion_menustock;

        //Mostrar menu mientras que no cierre sesion (!5)
        do {
            
            opcion_menustock = sc.nextInt();
            sc.nextLine();

            switch (opcion_menustock){
                case 1:
                    ingresoProductos(usuarios,sc, usuarioConectado);
                    menuStock(usuarios, sc, usuarioConectado);
                    break;
                case 2:
                    modificarProductos(usuarios, sc, usuarioConectado);
                    break;
                case 3:
                    //Llamo el metodo eliminar productos desde la clase usuario
                    usuarioConectado.eliminarProductos(sc);
                    menuStock(usuarios, sc, usuarioConectado);
                    break;
                case 4:
                    //Llamo el metodo listado desde la clase usuario
                    usuarioConectado.listado();
                    menuStock(usuarios, sc, usuarioConectado);
                    break;
                case 5:
                    System.out.println ("Sesión cerrada. Gracias por utilizar la aplicación, "+usuarioConectado.getNombreUsuario()+"!");
                    System.out.println ("---------------------------");
                    usuarioConectado = null;
                    menuInicial(usuarios, sc);
                    break;
                default:
                    System.out.println("Opción inválida. Inténtelo de nuevo.");
                    System.out.println ("---------------------------");
                    break;
            }
        } while (opcion_menustock != 5);
    }

    //Metodo ingreso de productos con sus parametros
    public static void ingresoProductos(List<Usuario> usuarios, Scanner sc, Usuario usuarioConectado) {
        
        System.out.println ("###########################");
        System.out.println ("-----INGRESO DE PRODUCTOS-----");
        System.out.println ("###########################");
        System.out.print ("Ingresar codigo: ");
        String codigo = sc.nextLine();
        System.out.println ("---------------------------");
        System.out.print ("Ingresar titulo: ");
        String titulo = sc.nextLine();
        System.out.println ("---------------------------");
        System.out.print("Ingresar autor: ");
        String autor = sc.nextLine();
        System.out.println ("---------------------------");
        System.out.print("Ingresar editorial: ");
        String editorial = sc.nextLine();
        System.out.println ("---------------------------");
        System.out.print("Ingresar cantidad de ejemplares: ");
        int cantidad = sc.nextInt();
        sc.nextLine();
        System.out.println ("---------------------------");

        //Guarda todos los valores ingresados y los guarda con la configuracion del set productos desde la clase usuario
        usuarioConectado.setProductos(new Producto(codigo, titulo, autor, editorial, cantidad));

        System.out.println("Producto ingresado de manera exitosa.");
    }
    
    //Metodo modificar productos con sus parametros
    public static void modificarProductos (List<Usuario> usuarios, Scanner sc, Usuario usuarioConectado) {
        System.out.println ("###########################");
        System.out.println ("-----MODIFICAR PRODUCTOS-----");
        System.out.println ("###########################");
        System.out.print("Ingrese el código del producto que desee modificar: ");
        String codigo = sc.nextLine();
        System.out.println ("---------------------------");

        //Una variable para determinar si el producto se encontro segun lo que devuelva el metodo buscar productos dentro de la clase usuario
        Producto productoEncontrado = Usuario.buscaProducto(usuarioConectado.getProductos(), codigo);

        if (productoEncontrado != null) {

            //Abro un menu de opciones segun lo que quiera modificar
            System.out.println("Indique lo que desee modificar, según la opción. (T/A/E/C)");
            System.out.println ("[T]: Modificar titulo.");
            System.out.println ("[A]: Modificar autor.");
            System.out.println ("[E]: Modificar editorial.");
            System.out.println ("[C]: Modificar cantidad.");
            String opcion = sc.nextLine();
            System.out.println ("-----------------------------");

            //Segun lo indicado, detallo el prodcuto con la caracteristica actual y se pide la modificacion guardandolo en la variable
            switch (opcion) {
                case "T":
                    System.out.println ("Detalles del producto encontrado:");
                    System.out.println ("El TÍTULO actual es: " +productoEncontrado.getTitulo());
                    System.out.println ("-----------------------------");
                    System.out.println ("Ingrese el nuevo título: ");
                    String titulo = sc.nextLine();
                    productoEncontrado.setTitulo(titulo);
                    System.out.println ("-----------------------------");
                    System.out.println("Modificación exitosa.");
                    break;
                case "A":
                    System.out.println ("Detalles del producto encontrado.");
                    System.out.println ("El AUTOR actual es: " +productoEncontrado.getAutor());
                    System.out.println ("-----------------------------");
                    System.out.println ("Ingrese el nuevo autor: ");
                    String autor = sc.nextLine();
                    productoEncontrado.setAutor(autor);
                    System.out.println ("-----------------------------");
                    System.out.println ("Modificación exitosa.");
                case "E":
                    System.out.println ("Detalles del producto encontrado.");
                    System.out.println ("La EDITORIAL actual es: " +productoEncontrado.getEditorial());
                    System.out.println ("-----------------------------");
                    System.out.println ("Ingrese la nueva editorial: ");
                    String editorial = sc.nextLine();
                    productoEncontrado.setEditorial(editorial);;
                    System.out.println ("-----------------------------");
                    System.out.println ("Modificación exitosa.");
                case "C":
                    System.out.println ("Detalles del producto encontrado.");
                    System.out.println ("La CANTIDAD actual es: " +productoEncontrado.getCantidad());
                    System.out.println ("-----------------------------");
                    System.out.println ("Ingrese la nueva cantidad: ");
                    int cantidad = sc.nextInt();
                    sc.nextLine();
                    productoEncontrado.setCantidad(cantidad);
                    System.out.println ("-----------------------------");
                    System.out.println ("Modificación exitosa.");
                default:
                    System.out.println ("Opción inválida.");
                    break;
            }
        }else {
            System.out.println ("Producto no encontrado.");
        }
        menuStock(usuarios, sc, usuarioConectado);
    }

    //Inicio del metodo MAIN
    public static void main (String [] args) throws Exception {
        List<Usuario> usuarios = new ArrayList<Usuario>();
        Scanner sc = new Scanner (System.in);
        menuInicial(usuarios, sc);
    }
}