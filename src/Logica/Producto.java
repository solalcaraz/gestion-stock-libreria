package Logica;

//Clase Producto con sus atributos, su constructor junto con los set y get

public class Producto {
    private String codigo;
    private String titulo;
    private String autor;
    private String editorial;
    private int cantidad;

    Producto (String codigo, String titulo, String autor, String editorial, int cantidad){
        this.codigo = codigo;
        this.titulo = titulo;
        this.autor = autor;
        this.editorial = editorial;
        setCantidad(cantidad); //llamo al set con la cantidad nueva
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getEditorial() {
        return editorial;
    }

    public void setEditorial(String editorial) {
        this.editorial = editorial;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
}
