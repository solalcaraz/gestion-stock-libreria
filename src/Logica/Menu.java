package Logica;

import java.util.Scanner;

public class Menu {
    public static int menuInicial(Scanner sc) {
        System.out.println ("###########################");
        System.out.println("----- MENÚ INICIAL -----");
        System.out.println ("###########################");
        System.out.println("[1]. Crear usuario.");
        System.out.println("[2]. Iniciar sesión.");
        System.out.println("[3]. Salir.");
        System.out.print("Elige una opción: ");
        
        return sc.nextInt();
    }
    
    public static int menuStock(Scanner sc) {
        System.out.println ("###########################");
        System.out.println ("-----MENÚ: STOCK DE LIBRERÍA-----");
        System.out.println ("###########################");
        System.out.println ("[1]. Ingresar productos");  
        System.out.println ("[2]. Modificar productos");
        System.out.println ("[3]. Eliminar productos");
        System.out.println ("[4]. Listar los productos");
        System.out.println ("[5]. Cerrar sesion");
        System.out.print ("Elige una opcion: ");
        
        return sc.nextInt();
    }
}
