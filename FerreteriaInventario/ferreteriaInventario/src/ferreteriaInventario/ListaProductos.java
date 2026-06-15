package ferreteriaInventario;

import java.text.DecimalFormat;

public class ListaProductos {

    // Atributos
    private NodoProducto primero;

    // Constructor
    public ListaProductos() {
        primero = null;
    }

    // Getters
    public NodoProducto getPrimero() {
        return primero;
    }

    // Setters
    public void setPrimero(NodoProducto primero) {
        this.primero = primero;
    }

    // Operaciones
    private boolean estaVacia() {
        return primero == null;
    }

    private String formatoColones(double monto) {
        DecimalFormat formato = new DecimalFormat("\u20A1#,##0.00");
        return formato.format(monto);
    }

    public void insertarInicio(Producto producto) {
        NodoProducto nodo = new NodoProducto(producto);
        nodo.setSiguiente(primero);
        primero = nodo;
    }

    public void insertarFinal(Producto producto) {
        NodoProducto nodo = new NodoProducto(producto);

        if (estaVacia()) {
            primero = nodo;
            return;
        }

        NodoProducto temp = primero;

        while (temp.getSiguiente() != null) {
            temp = temp.getSiguiente();
        }

        temp.setSiguiente(nodo);
    }

    public Producto buscar(String nombre) {
        NodoProducto temp = primero;

        while (temp != null) {
            if (temp.getProducto().getNombre().equalsIgnoreCase(nombre)) {
                return temp.getProducto();
            }

            temp = temp.getSiguiente();
        }

        return null;
    }

    public boolean modificarNombre(String nombreActual, String nuevoNombre) {
        Producto producto = buscar(nombreActual);

        if (producto == null) {
            return false;
        }

        producto.setNombre(nuevoNombre);
        return true;
    }

    public boolean modificarPrecio(String nombre, double nuevoPrecio) {
        Producto producto = buscar(nombre);

        if (producto == null) {
            return false;
        }

        producto.setPrecio(nuevoPrecio);
        return true;
    }

    public boolean modificarCategoria(String nombre, String nuevaCategoria) {
        Producto producto = buscar(nombre);

        if (producto == null) {
            return false;
        }

        producto.setCategoria(nuevaCategoria);
        return true;
    }

    public boolean modificarFechaVencimiento(String nombre, String nuevaFechaVencimiento) {
        Producto producto = buscar(nombre);

        if (producto == null) {
            return false;
        }

        producto.setFechaVencimiento(nuevaFechaVencimiento);
        return true;
    }

    public boolean modificarCantidad(String nombre, int nuevaCantidad) {
        Producto producto = buscar(nombre);

        if (producto == null) {
            return false;
        }

        producto.setCantidad(nuevaCantidad);
        return true;
    }

    public boolean agregarImagen(String nombre, String rutaImagen) {
        Producto producto = buscar(nombre);

        if (producto == null) {
            return false;
        }

        producto.agregarImagen(rutaImagen);
        return true;
    }

    public Producto eliminar(String nombre) {
        if (estaVacia()) {
            return null;
        }

        if (primero.getProducto().getNombre().equalsIgnoreCase(nombre)) {
            NodoProducto aux = primero;
            primero = primero.getSiguiente();
            return aux.getProducto();
        }

        NodoProducto temp = primero;
        NodoProducto anterior = temp;

        while (temp != null && !temp.getProducto().getNombre().equalsIgnoreCase(nombre)) {
            anterior = temp;
            temp = temp.getSiguiente();
        }

        if (temp == null) {
            return null;
        }

        anterior.setSiguiente(temp.getSiguiente());
        return temp.getProducto();
    }

    public void mostrar() {
        if (estaVacia()) {
            System.out.println("La lista de productos está vacía.\n");
            return;
        }

        NodoProducto temp = primero;

        while (temp != null) {
            System.out.println(temp);
            temp = temp.getSiguiente();
        }
    }

    public void reporteCostos() {
        if (estaVacia()) {
            System.out.println("La lista de productos está vacía.\n");
            return;
        }

        double totalAcumulado = 0;
        NodoProducto temp = primero;

        System.out.println("Reporte de costos");

        while (temp != null) {
            Producto producto = temp.getProducto();
            double totalProducto = producto.calcularCostoTotal();

            System.out.println("Producto: " + producto.getNombre());
            System.out.println("Precio unitario: " + producto.obtenerPrecioFormato());
            System.out.println("Cantidad: " + producto.getCantidad());
            System.out.println("Costo total del producto: " + producto.obtenerCostoTotalFormato() + "\n");

            totalAcumulado += totalProducto;
            temp = temp.getSiguiente();
        }

        System.out.println("Costo total acumulado: " + formatoColones(totalAcumulado) + "\n");
    }
}