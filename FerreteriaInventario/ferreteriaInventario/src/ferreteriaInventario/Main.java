package ferreteriaInventario;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        ListaProductos listaProductos = new ListaProductos();

        menu(br, listaProductos);
    }

    // Operaciones
    public static void menu(BufferedReader br, ListaProductos listaProductos) throws IOException {

        int opcion;

        do {
            System.out.println("Menú principal");
            System.out.println("1. Insertar producto al inicio");
            System.out.println("2. Insertar producto al final");
            System.out.println("3. Mostrar productos");
            System.out.println("4. Buscar producto");
            System.out.println("5. Modificar producto");
            System.out.println("6. Eliminar producto");
            System.out.println("7. Mostrar reporte de costos");
            System.out.println("8. Salir");
            System.out.print("Digite una opción: ");
            opcion = leerEntero(br);

            switch (opcion) {

                case 1:
                    insertarProductoInicio(br, listaProductos);
                    break;

                case 2:
                    insertarProductoFinal(br, listaProductos);
                    break;

                case 3:
                    listaProductos.mostrar();
                    break;

                case 4:
                    buscarProducto(br, listaProductos);
                    break;

                case 5:
                    modificarProducto(br, listaProductos);
                    break;

                case 6:
                    eliminarProducto(br, listaProductos);
                    break;

                case 7:
                    listaProductos.reporteCostos();
                    break;

                case 8:
                    System.out.println("Programa finalizado.");
                    break;

                default:
                    System.out.println("Opción inválida.\n");
                    break;
            }

        } while (opcion != 8);
    }

    public static Producto leerProducto(BufferedReader br) throws IOException {

        System.out.print("Digite el nombre del producto: ");
        String nombre = leerTexto(br);

        System.out.print("Digite el precio del producto: ");
        double precio = leerDecimal(br);

        System.out.print("Digite la categoría del producto: ");
        String categoria = leerTexto(br);

        System.out.print("Digite la fecha de vencimiento o deje vacío si no aplica: ");
        String fechaVencimiento = br.readLine();

        if (fechaVencimiento.trim().isEmpty()) {
            fechaVencimiento = "No aplica";
        }

        System.out.print("Digite la cantidad: ");
        int cantidad = leerCantidad(br);

        return new Producto(nombre, precio, categoria, fechaVencimiento, cantidad);
    }

    public static void insertarProductoInicio(BufferedReader br, ListaProductos listaProductos) throws IOException {

        System.out.println("Insertar producto al inicio");

        Producto producto = leerProducto(br);
        listaProductos.insertarInicio(producto);

        System.out.println("Producto insertado correctamente.\n");
    }

    public static void insertarProductoFinal(BufferedReader br, ListaProductos listaProductos) throws IOException {

        System.out.println("Insertar producto al final");

        Producto producto = leerProducto(br);
        listaProductos.insertarFinal(producto);

        System.out.println("Producto insertado correctamente.\n");
    }

    public static void buscarProducto(BufferedReader br, ListaProductos listaProductos) throws IOException {

        System.out.println("Buscar producto");

        System.out.print("Digite el nombre del producto: ");
        String nombre = leerTexto(br);

        Producto producto = listaProductos.buscar(nombre);

        if (producto == null) {
            System.out.println("El producto buscado no está en la lista.\n");
        } else {
            System.out.println("Producto encontrado:");
            System.out.println(producto);
        }
    }

    public static void modificarProducto(BufferedReader br, ListaProductos listaProductos) throws IOException {

        System.out.println("Modificar producto");

        System.out.print("Digite el nombre del producto: ");
        String nombreActual = leerTexto(br);

        Producto producto = listaProductos.buscar(nombreActual);

        if (producto == null) {
            System.out.println("El producto buscado no está en la lista.\n");
            return;
        }

        int opcion;

        do {
            System.out.println("Producto seleccionado:");
            System.out.println(producto);

            System.out.println("Opciones de modificación");
            System.out.println("1. Cambiar nombre");
            System.out.println("2. Cambiar precio");
            System.out.println("3. Cambiar categoría");
            System.out.println("4. Cambiar fecha de vencimiento");
            System.out.println("5. Cambiar cantidad");
            System.out.println("6. Agregar imagen");
            System.out.println("7. Volver");
            System.out.print("Digite una opción: ");
            opcion = leerEntero(br);

            switch (opcion) {

                case 1:
                    System.out.print("Digite el nuevo nombre: ");
                    String nuevoNombre = leerTexto(br);

                    listaProductos.modificarNombre(nombreActual, nuevoNombre);
                    nombreActual = nuevoNombre;
                    producto = listaProductos.buscar(nombreActual);

                    System.out.println("Nombre modificado correctamente.\n");
                    break;

                case 2:
                    System.out.print("Digite el nuevo precio: ");
                    double nuevoPrecio = leerDecimal(br);

                    listaProductos.modificarPrecio(nombreActual, nuevoPrecio);
                    System.out.println("Precio modificado correctamente.\n");
                    break;

                case 3:
                    System.out.print("Digite la nueva categoría: ");
                    String nuevaCategoria = leerTexto(br);

                    listaProductos.modificarCategoria(nombreActual, nuevaCategoria);
                    System.out.println("Categoría modificada correctamente.\n");
                    break;

                case 4:
                    System.out.print("Digite la nueva fecha de vencimiento o deje vacío si no aplica: ");
                    String nuevaFechaVencimiento = br.readLine();

                    if (nuevaFechaVencimiento.trim().isEmpty()) {
                        nuevaFechaVencimiento = "No aplica";
                    }

                    listaProductos.modificarFechaVencimiento(nombreActual, nuevaFechaVencimiento);
                    System.out.println("Fecha de vencimiento modificada correctamente.\n");
                    break;

                case 5:
                    System.out.print("Digite la nueva cantidad: ");
                    int nuevaCantidad = leerCantidad(br);

                    listaProductos.modificarCantidad(nombreActual, nuevaCantidad);
                    System.out.println("Cantidad modificada correctamente.\n");
                    break;

                case 6:
                    System.out.print("Digite la ruta de la imagen: ");
                    String rutaImagen = leerTexto(br);

                    listaProductos.agregarImagen(nombreActual, rutaImagen);
                    System.out.println("Imagen agregada correctamente.\n");
                    break;

                case 7:
                    System.out.println("Volviendo al menú principal.\n");
                    break;

                default:
                    System.out.println("Opción inválida.\n");
                    break;
            }

        } while (opcion != 7);
    }

    public static void eliminarProducto(BufferedReader br, ListaProductos listaProductos) throws IOException {

        System.out.println("Eliminar producto");

        System.out.print("Digite el nombre del producto: ");
        String nombre = leerTexto(br);

        Producto productoEliminado = listaProductos.eliminar(nombre);

        if (productoEliminado == null) {
            System.out.println("El producto buscado no está en la lista.\n");
        } else {
            System.out.println("Producto eliminado:");
            System.out.println(productoEliminado);
        }
    }

    public static String leerTexto(BufferedReader br) throws IOException {

        String texto = br.readLine();

        while (texto.trim().isEmpty()) {
            System.out.print("Digite un texto válido: ");
            texto = br.readLine();
        }

        return texto;
    }

    public static int leerEntero(BufferedReader br) throws IOException {

        int numero = 0;
        boolean valido = false;

        while (!valido) {
            try {
                numero = Integer.parseInt(br.readLine());
                valido = true;
            } catch (NumberFormatException e) {
                System.out.print("Digite un número válido: ");
            }
        }

        return numero;
    }

    public static int leerCantidad(BufferedReader br) throws IOException {

        int cantidad = leerEntero(br);

        while (cantidad < 0) {
            System.out.print("Digite una cantidad mayor o igual a 0: ");
            cantidad = leerEntero(br);
        }

        return cantidad;
    }

    public static double leerDecimal(BufferedReader br) throws IOException {

        double numero = 0;
        boolean valido = false;

        while (!valido) {
            try {
                numero = Double.parseDouble(br.readLine().replace(",", "."));

                if (numero >= 0) {
                    valido = true;
                } else {
                    System.out.print("Digite un número mayor o igual a 0: ");
                }

            } catch (NumberFormatException e) {
                System.out.print("Digite un número válido: ");
            }
        }

        return numero;
    }
}