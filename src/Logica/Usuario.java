package Logica;

// Clase Usuario con sus atributos, su constructor junto con los set y get

/*Dentro de esta clase determino tres metodos para llamar desde la App.
 * Esto esta realizado con la logica de no agregar los parametros de la clase Producto,
 * Solo utilizarlos aca con las modificaciones necesarias */

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Usuario {
    
    private String nombreUsuario;
    private String contrasena;
    private List<Producto> productos = new ArrayList<Producto>(); //cada usuario tendra una lista de productos propios

    Usuario(String nombreUsuario, String contrasena) {
        this.nombreUsuario = nombreUsuario;
        this.contrasena = contrasena;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public List<Producto> getProductos() {
        return productos;
    }

    //Editado para que se agrege un producto por vez a la lista completa
    public void setProductos(Producto productos) {
        this.productos.add(productos); //adhiere el producto creado a la lista del usuario
    }
    
    //Metodo listado para mostrar los productos almacenados para el usuario
    public void listado() {
        for (Producto producto : productos) {
            System.out.println ("###########################");
            System.out.println ("Codigo: "+producto.getCodigo());
            System.out.println ("Titulo: "+producto.getTitulo());
            System.out.println ("Autor: "+producto.getAutor());
            System.out.println ("Editorial: "+producto.getEditorial());
            System.out.println ("Cantidad: "+producto.getCantidad());
        }
    }
    
    //Metodo eliminar productos
    public void eliminarProductos (Scanner sc){
        System.out.println ("###########################");
        System.out.println("-----ELIMINAR PRODUCTOS-----");
        System.out.println ("###########################");
        System.out.print("Ingrese el codigo del producto que desea eliminar: ");
        String codigo = sc.nextLine();
        System.out.println ("---------------------------");

        //Buscar si existe el producto
        Producto existe = null;
        for (Producto producto : productos){
            if (producto.getCodigo().equals(codigo)){
                existe = producto;
            }
        }

        //Si existe lo muestro
        if (existe != null){
            System.out.println ("Detalles del producto:");
            System.out.println ("Codigo: "+existe.getCodigo());
            System.out.println ("Titulo: "+existe.getTitulo());
            System.out.println ("Autor: "+existe.getAutor());
            System.out.println ("Editorial: "+existe.getEditorial());
            System.out.println ("Cantidad: "+existe.getCantidad());
            System.out.println ("--------------------------------");
            System.out.print ("¿Esta seguro que desea eliminar el producto? (s/n): ");
            String respuesta = sc.nextLine();
            System.out.println ("---------------------------");

            //Acciono segun lo que indico, comparando la respues con una s, independientemente de que sea S o s
            if (respuesta.equalsIgnoreCase("s")) {
                productos.remove(existe);
                System.out.println ("El producto ha sido eliminado correctamente");
            }else {
                System.out.println ("El producto no se ha eliminado.");
            }
        }else {
            System.out.println ("No se encontro ningun producto con el condigo indicado.");
        }
    }

    //Metodo para buscar el producto y asi modificarlo, ademas devuelve un objeto de tipo Producto para determinar si existe
    public static Producto buscaProducto(List<Producto> productos, String codigo){
        //Busco la existencia del producto
        for (Producto producto : productos){
            if (producto.getCodigo().equals(codigo)){
                return producto;
            }
        }
        return null;
    }
}  
