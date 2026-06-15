package ferreteriaInventario;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class Producto {

    // Atributos
    private String nombre;
    private double precio;
    private String categoria;
    private String fechaVencimiento;
    private int cantidad;
    private ArrayList<String> listaImagenes;

    // Constructor
    public Producto(String nombre, double precio, String categoria, String fechaVencimiento, int cantidad) {
        this.nombre = nombre;
        this.precio = precio;
        this.categoria = categoria;
        this.fechaVencimiento = fechaVencimiento;
        this.cantidad = cantidad;
        listaImagenes = new ArrayList<>();
    }

    // Getters
    public String getNombre() {
        return nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public String getCategoria() {
        return categoria;
    }

    public String getFechaVencimiento() {
        return fechaVencimiento;
    }

    public int getCantidad() {
        return cantidad;
    }

    public ArrayList<String> getListaImagenes() {
        return listaImagenes;
    }

    // Setters
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public void setFechaVencimiento(String fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public void setListaImagenes(ArrayList<String> listaImagenes) {
        this.listaImagenes = listaImagenes;
    }

    // Operaciones
    public void agregarImagen(String rutaImagen) {
        listaImagenes.add(rutaImagen);
    }

    public double calcularCostoTotal() {
        return precio * cantidad;
    }

    public String obtenerPrecioFormato() {
        DecimalFormat formato = new DecimalFormat("\u20A1#,##0.00");
        return formato.format(precio);
    }

    public String obtenerCostoTotalFormato() {
        DecimalFormat formato = new DecimalFormat("\u20A1#,##0.00");
        return formato.format(calcularCostoTotal());
    }

    // toString()
    public String toString() {
        String imagenes = "Sin imágenes registradas";

        if (!listaImagenes.isEmpty()) {
            imagenes = listaImagenes.toString();
        }

        return "Nombre: " + nombre +
                "\nPrecio: " + obtenerPrecioFormato() +
                "\nCategoria: " + categoria +
                "\nFecha de vencimiento: " + fechaVencimiento +
                "\nCantidad: " + cantidad +
                "\nCosto total: " + obtenerCostoTotalFormato() +
                "\nImagenes: " + imagenes +
                "\n";
    }
}