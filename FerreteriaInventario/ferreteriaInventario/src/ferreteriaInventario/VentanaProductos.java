package ferreteriaInventario;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

public class VentanaProductos extends JFrame {

    // Atributos
    private ListaProductos listaProductos;

    private JTextField campoBusqueda;
    private JTextField campoNombre;
    private JTextField campoPrecio;
    private JComboBox<String> campoCategoria;
    private JTextField campoFechaVencimiento;
    private JTextField campoCantidad;
    private JTextField campoImagen;

    private JTable tablaProductos;
    private DefaultTableModel modeloTabla;

    private JTextArea areaSalida;
    private JLabel etiquetaImagen;

    private final Color colorFondo = new Color(238, 240, 242);
    private final Color colorPanel = new Color(255, 255, 255);
    private final Color colorPrincipal = new Color(48, 52, 56);
    private final Color colorSecundario = new Color(92, 99, 106);
    private final Color colorBorde = new Color(210, 214, 218);
    private final Color colorTexto = new Color(35, 38, 41);
    private final Color colorBotonClaro = new Color(230, 232, 234);
    private final Color colorAlerta = new Color(166, 72, 72);

    // Constructor
    public VentanaProductos() {
        listaProductos = new ListaProductos();

        aplicarEstilo();

        setTitle("Ferretería Inventario");
        setSize(1180, 720);
        setMinimumSize(new Dimension(1050, 650));
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        crearInterfaz();

        setVisible(true);
    }

    // Operaciones
    public void aplicarEstilo() {
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Exception e) {
            System.out.println("No se pudo cargar el estilo visual.");
        }
    }

    public void crearInterfaz() {
        JPanel panelPrincipal = new JPanel(new BorderLayout(14, 14));
        panelPrincipal.setBackground(colorFondo);
        panelPrincipal.setBorder(BorderFactory.createEmptyBorder(14, 14, 14, 14));

        panelPrincipal.add(crearEncabezado(), BorderLayout.NORTH);
        panelPrincipal.add(crearContenido(), BorderLayout.CENTER);

        add(panelPrincipal);
    }

    public JPanel crearEncabezado() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(colorPrincipal);
        panel.setBorder(BorderFactory.createEmptyBorder(20, 24, 20, 24));

        JLabel titulo = new JLabel("Ferretería Inventario");
        titulo.setFont(new Font("Arial", Font.BOLD, 26));
        titulo.setForeground(Color.WHITE);

        JLabel subtitulo = new JLabel("Control de productos, imágenes y costos");
        subtitulo.setFont(new Font("Arial", Font.PLAIN, 14));
        subtitulo.setForeground(new Color(220, 223, 226));

        JPanel textos = new JPanel(new GridLayout(2, 1));
        textos.setOpaque(false);
        textos.add(titulo);
        textos.add(subtitulo);

        panel.add(textos, BorderLayout.WEST);

        return panel;
    }

    public JPanel crearContenido() {
        JPanel panel = new JPanel(new BorderLayout(14, 14));
        panel.setBackground(colorFondo);

        panel.add(crearPanelIzquierdo(), BorderLayout.WEST);
        panel.add(crearPanelDerecho(), BorderLayout.CENTER);

        return panel;
    }

    public JPanel crearPanelIzquierdo() {
        JPanel panel = new JPanel(new BorderLayout(12, 12));
        panel.setBackground(colorFondo);
        panel.setPreferredSize(new Dimension(400, 0));

        panel.add(crearFormulario(), BorderLayout.CENTER);
        panel.add(crearBotones(), BorderLayout.SOUTH);

        return panel;
    }

    public JPanel crearFormulario() {
        JPanel panel = crearTarjeta();
        panel.setLayout(new GridBagLayout());

        campoBusqueda = crearCampoTexto("Nombre del producto que desea buscar, modificar o eliminar");
        campoNombre = crearCampoTexto("Ejemplo: Martillo de acero");
        campoPrecio = crearCampoTexto("Ejemplo: 4500");
        campoCategoria = crearCategorias();
        campoFechaVencimiento = crearCampoTexto("Deje vacío si no aplica");
        campoCantidad = crearCampoTexto("Ejemplo: 10");
        campoImagen = crearCampoTexto("Ruta de la imagen");

        agregarFila(panel, "Producto a buscar", campoBusqueda, 0);
        agregarFila(panel, "Nombre", campoNombre, 1);
        agregarFila(panel, "Precio", campoPrecio, 2);
        agregarFila(panel, "Categoría", campoCategoria, 3);
        agregarFila(panel, "Fecha de vencimiento", campoFechaVencimiento, 4);
        agregarFila(panel, "Cantidad", campoCantidad, 5);
        agregarFila(panel, "Ruta de imagen", campoImagen, 6);

        return panel;
    }

    public JComboBox<String> crearCategorias() {
        String[] categorias = {
                "Herramientas manuales",
                "Herramientas eléctricas",
                "Construcción",
                "Pinturas",
                "Electricidad",
                "Plomería",
                "Tornillería",
                "Seguridad",
                "Adhesivos y selladores",
                "Otros"
        };

        JComboBox<String> combo = new JComboBox<>(categorias);
        combo.setEditable(true);
        combo.setFont(new Font("Arial", Font.PLAIN, 14));
        combo.setBackground(Color.WHITE);
        combo.setToolTipText("Seleccione o escriba la categoría del producto.");

        return combo;
    }

    public JPanel crearBotones() {
        JPanel panel = crearTarjeta();
        panel.setLayout(new GridLayout(5, 2, 8, 8));

        JButton botonInsertarInicio = crearBotonOscuro("Insertar al inicio");
        JButton botonInsertarFinal = crearBotonOscuro("Insertar al final");
        JButton botonMostrar = crearBotonClaro("Mostrar productos");
        JButton botonBuscar = crearBotonClaro("Buscar producto");
        JButton botonModificar = crearBotonMedio("Modificar producto");
        JButton botonSeleccionarImagen = crearBotonMedio("Seleccionar imagen");
        JButton botonAgregarImagen = crearBotonMedio("Agregar imagen");
        JButton botonReporte = crearBotonMedio("Reporte de costos");
        JButton botonEliminar = crearBotonRojo("Eliminar producto");
        JButton botonLimpiar = crearBotonClaro("Limpiar campos");

        botonInsertarInicio.addActionListener(e -> insertarInicio());
        botonInsertarFinal.addActionListener(e -> insertarFinal());
        botonMostrar.addActionListener(e -> mostrarProductos());
        botonBuscar.addActionListener(e -> buscarProducto());
        botonModificar.addActionListener(e -> modificarProducto());
        botonSeleccionarImagen.addActionListener(e -> seleccionarImagen());
        botonAgregarImagen.addActionListener(e -> agregarImagen());
        botonReporte.addActionListener(e -> mostrarReporteCostos());
        botonEliminar.addActionListener(e -> eliminarProducto());
        botonLimpiar.addActionListener(e -> limpiarCampos());

        panel.add(botonInsertarInicio);
        panel.add(botonInsertarFinal);
        panel.add(botonMostrar);
        panel.add(botonBuscar);
        panel.add(botonModificar);
        panel.add(botonSeleccionarImagen);
        panel.add(botonAgregarImagen);
        panel.add(botonReporte);
        panel.add(botonEliminar);
        panel.add(botonLimpiar);

        return panel;
    }

    public JPanel crearPanelDerecho() {
        JPanel panel = new JPanel(new BorderLayout(12, 12));
        panel.setBackground(colorFondo);

        panel.add(crearPanelImagen(), BorderLayout.NORTH);
        panel.add(crearTablaProductos(), BorderLayout.CENTER);
        panel.add(crearAreaSalida(), BorderLayout.SOUTH);

        return panel;
    }

    public JPanel crearPanelImagen() {
        JPanel panel = crearTarjeta();
        panel.setLayout(new BorderLayout(8, 8));
        panel.setPreferredSize(new Dimension(0, 220));

        JLabel titulo = new JLabel("Vista previa del producto");
        titulo.setFont(new Font("Arial", Font.BOLD, 16));
        titulo.setForeground(colorTexto);

        etiquetaImagen = new JLabel("Seleccione una imagen para verla aquí");
        etiquetaImagen.setFont(new Font("Arial", Font.PLAIN, 13));
        etiquetaImagen.setHorizontalAlignment(SwingConstants.CENTER);
        etiquetaImagen.setOpaque(true);
        etiquetaImagen.setBackground(new Color(248, 249, 250));
        etiquetaImagen.setBorder(BorderFactory.createLineBorder(colorBorde));

        panel.add(titulo, BorderLayout.NORTH);
        panel.add(etiquetaImagen, BorderLayout.CENTER);

        return panel;
    }

    public JScrollPane crearTablaProductos() {
        String[] columnas = {"Nombre", "Precio", "Categoría", "Vencimiento", "Cantidad", "Total", "Imagen"};

        modeloTabla = new DefaultTableModel(columnas, 0) {
            public boolean isCellEditable(int fila, int columna) {
                return false;
            }
        };

        tablaProductos = new JTable(modeloTabla);
        tablaProductos.setFont(new Font("Arial", Font.PLAIN, 13));
        tablaProductos.setRowHeight(28);
        tablaProductos.setGridColor(colorBorde);
        tablaProductos.setSelectionBackground(new Color(218, 222, 226));
        tablaProductos.setSelectionForeground(colorTexto);
        tablaProductos.getTableHeader().setFont(new Font("Arial", Font.BOLD, 13));
        tablaProductos.getTableHeader().setBackground(new Color(225, 228, 231));
        tablaProductos.getTableHeader().setForeground(colorTexto);

        tablaProductos.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                cargarProductoSeleccionado();
            }
        });

        JScrollPane scroll = new JScrollPane(tablaProductos);
        scroll.setBorder(BorderFactory.createLineBorder(colorBorde));

        return scroll;
    }

    public JScrollPane crearAreaSalida() {
        areaSalida = new JTextArea();
        areaSalida.setEditable(false);
        areaSalida.setRows(7);
        areaSalida.setFont(new Font("Consolas", Font.PLAIN, 14));
        areaSalida.setForeground(colorTexto);
        areaSalida.setBackground(Color.WHITE);
        areaSalida.setLineWrap(true);
        areaSalida.setWrapStyleWord(true);
        areaSalida.setText("Aquí se mostrará la información de los productos.\n");

        JScrollPane scroll = new JScrollPane(areaSalida);
        scroll.setBorder(BorderFactory.createLineBorder(colorBorde));

        return scroll;
    }

    public JPanel crearTarjeta() {
        JPanel panel = new JPanel();
        panel.setBackground(colorPanel);
        panel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(colorBorde),
                BorderFactory.createEmptyBorder(16, 16, 16, 16)
        ));

        return panel;
    }

    public JLabel crearEtiqueta(String texto) {
        JLabel etiqueta = new JLabel(texto);
        etiqueta.setFont(new Font("Arial", Font.BOLD, 13));
        etiqueta.setForeground(colorTexto);

        return etiqueta;
    }

    public JTextField crearCampoTexto(String ayuda) {
        JTextField campo = new JTextField();
        campo.setFont(new Font("Arial", Font.PLAIN, 14));
        campo.setToolTipText(ayuda);
        campo.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(colorBorde),
                BorderFactory.createEmptyBorder(7, 8, 7, 8)
        ));

        return campo;
    }

    public JButton crearBotonOscuro(String texto) {
        return crearBoton(texto, colorPrincipal, Color.WHITE);
    }

    public JButton crearBotonMedio(String texto) {
        return crearBoton(texto, colorSecundario, Color.WHITE);
    }

    public JButton crearBotonClaro(String texto) {
        return crearBoton(texto, colorBotonClaro, colorTexto);
    }

    public JButton crearBotonRojo(String texto) {
        return crearBoton(texto, colorAlerta, Color.WHITE);
    }

    public JButton crearBoton(String texto, Color fondo, Color letra) {
        JButton boton = new JButton(texto);
        boton.setFont(new Font("Arial", Font.BOLD, 13));
        boton.setBackground(fondo);
        boton.setForeground(letra);
        boton.setFocusPainted(false);
        boton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        boton.setBorder(BorderFactory.createEmptyBorder(10, 12, 10, 12));

        return boton;
    }

    public void agregarFila(JPanel panel, String texto, JTextField campo, int fila) {
        JLabel etiqueta = crearEtiqueta(texto);
        etiqueta.setLabelFor(campo);

        GridBagConstraints gbcEtiqueta = new GridBagConstraints();
        gbcEtiqueta.gridx = 0;
        gbcEtiqueta.gridy = fila;
        gbcEtiqueta.anchor = GridBagConstraints.WEST;
        gbcEtiqueta.insets = new Insets(7, 4, 7, 10);

        GridBagConstraints gbcCampo = new GridBagConstraints();
        gbcCampo.gridx = 1;
        gbcCampo.gridy = fila;
        gbcCampo.weightx = 1;
        gbcCampo.fill = GridBagConstraints.HORIZONTAL;
        gbcCampo.insets = new Insets(7, 4, 7, 4);

        panel.add(etiqueta, gbcEtiqueta);
        panel.add(campo, gbcCampo);
    }

    public void agregarFila(JPanel panel, String texto, JComboBox<String> combo, int fila) {
        JLabel etiqueta = crearEtiqueta(texto);
        etiqueta.setLabelFor(combo);

        GridBagConstraints gbcEtiqueta = new GridBagConstraints();
        gbcEtiqueta.gridx = 0;
        gbcEtiqueta.gridy = fila;
        gbcEtiqueta.anchor = GridBagConstraints.WEST;
        gbcEtiqueta.insets = new Insets(7, 4, 7, 10);

        GridBagConstraints gbcCampo = new GridBagConstraints();
        gbcCampo.gridx = 1;
        gbcCampo.gridy = fila;
        gbcCampo.weightx = 1;
        gbcCampo.fill = GridBagConstraints.HORIZONTAL;
        gbcCampo.insets = new Insets(7, 4, 7, 4);

        panel.add(etiqueta, gbcEtiqueta);
        panel.add(combo, gbcCampo);
    }

    public Producto leerProductoFormulario() {
        try {
            String nombre = campoNombre.getText().trim();
            String categoria = obtenerCategoria();
            String fechaVencimiento = campoFechaVencimiento.getText().trim();
            String rutaImagen = campoImagen.getText().trim();

            if (nombre.isEmpty()) {
                mostrarMensaje("Digite el nombre del producto.");
                campoNombre.requestFocus();
                return null;
            }

            if (categoria.isEmpty()) {
                mostrarMensaje("Digite la categoría del producto.");
                campoCategoria.requestFocus();
                return null;
            }

            if (fechaVencimiento.isEmpty()) {
                fechaVencimiento = "No aplica";
            }

            double precio = Double.parseDouble(campoPrecio.getText().replace(",", "."));
            int cantidad = Integer.parseInt(campoCantidad.getText());

            if (precio < 0 || cantidad < 0) {
                mostrarMensaje("El precio y la cantidad no pueden ser negativos.");
                return null;
            }

            Producto producto = new Producto(nombre, precio, categoria, fechaVencimiento, cantidad);

            if (!rutaImagen.isEmpty()) {
                producto.agregarImagen(rutaImagen);
            }

            return producto;

        } catch (NumberFormatException e) {
            mostrarMensaje("Revise el precio y la cantidad.");
            campoPrecio.requestFocus();
            return null;
        }
    }

    public String obtenerCategoria() {
        Object seleccion = campoCategoria.getSelectedItem();

        if (seleccion == null) {
            return "";
        }

        return seleccion.toString().trim();
    }

    public void insertarInicio() {
        Producto producto = leerProductoFormulario();

        if (producto != null) {
            listaProductos.insertarInicio(producto);
            actualizarTabla();
            areaSalida.setText("Producto insertado al inicio:\n\n" + producto);
            mostrarMensaje("Producto insertado correctamente.");
            limpiarCamposProducto();
        }
    }

    public void insertarFinal() {
        Producto producto = leerProductoFormulario();

        if (producto != null) {
            listaProductos.insertarFinal(producto);
            actualizarTabla();
            areaSalida.setText("Producto insertado al final:\n\n" + producto);
            mostrarMensaje("Producto insertado correctamente.");
            limpiarCamposProducto();
        }
    }

    public void buscarProducto() {
        String nombre = campoBusqueda.getText().trim();

        if (nombre.isEmpty()) {
            mostrarMensaje("Digite el nombre del producto a buscar.");
            campoBusqueda.requestFocus();
            return;
        }

        Producto producto = listaProductos.buscar(nombre);

        if (producto == null) {
            areaSalida.setText("El producto buscado no está en la lista.\n");
        } else {
            areaSalida.setText("Producto encontrado:\n\n" + producto);
            cargarProductoEnCampos(producto);
            mostrarImagenProducto(producto);
        }
    }

    public void modificarProducto() {
        String nombreActual = campoBusqueda.getText().trim();

        if (nombreActual.isEmpty()) {
            mostrarMensaje("Digite el nombre del producto que desea modificar.");
            campoBusqueda.requestFocus();
            return;
        }

        Producto productoActual = listaProductos.buscar(nombreActual);

        if (productoActual == null) {
            mostrarMensaje("El producto buscado no está en la lista.");
            return;
        }

        Producto productoNuevo = leerProductoFormulario();

        if (productoNuevo != null) {
            productoActual.setNombre(productoNuevo.getNombre());
            productoActual.setPrecio(productoNuevo.getPrecio());
            productoActual.setCategoria(productoNuevo.getCategoria());
            productoActual.setFechaVencimiento(productoNuevo.getFechaVencimiento());
            productoActual.setCantidad(productoNuevo.getCantidad());

            if (!productoNuevo.getListaImagenes().isEmpty()) {
                productoActual.agregarImagen(productoNuevo.getListaImagenes().get(0));
            }

            actualizarTabla();
            areaSalida.setText("Producto modificado:\n\n" + productoActual);
            mostrarImagenProducto(productoActual);
            mostrarMensaje("Producto modificado correctamente.");
        }
    }

    public void seleccionarImagen() {
        JFileChooser selector = new JFileChooser();
        int resultado = selector.showOpenDialog(this);

        if (resultado == JFileChooser.APPROVE_OPTION) {
            File archivo = selector.getSelectedFile();
            String rutaProyecto = copiarImagenAlProyecto(archivo);

            campoImagen.setText(rutaProyecto);
            mostrarVistaPrevia(rutaProyecto);
        }
    }

    public String copiarImagenAlProyecto(File archivo) {
        try {
            File carpetaImagenes = new File("imagenes");

            if (!carpetaImagenes.exists()) {
                carpetaImagenes.mkdir();
            }

            File destino = new File(carpetaImagenes, archivo.getName());

            Files.copy(archivo.toPath(), destino.toPath(), StandardCopyOption.REPLACE_EXISTING);

            return "imagenes/" + archivo.getName();

        } catch (Exception e) {
            mostrarMensaje("No se pudo copiar la imagen al proyecto. Se usará la ruta original.");
            return archivo.getPath();
        }
    }

    public void agregarImagen() {
        String nombre = campoBusqueda.getText().trim();
        String rutaImagen = campoImagen.getText().trim();

        if (nombre.isEmpty()) {
            mostrarMensaje("Digite el nombre del producto.");
            campoBusqueda.requestFocus();
            return;
        }

        if (rutaImagen.isEmpty()) {
            mostrarMensaje("Seleccione o digite la ruta de la imagen.");
            campoImagen.requestFocus();
            return;
        }

        boolean agregada = listaProductos.agregarImagen(nombre, rutaImagen);

        if (agregada) {
            Producto producto = listaProductos.buscar(nombre);
            actualizarTabla();
            mostrarImagenProducto(producto);
            areaSalida.setText("Imagen agregada al producto: " + nombre + "\nRuta: " + rutaImagen);
            mostrarMensaje("Imagen agregada correctamente.");
        } else {
            mostrarMensaje("El producto buscado no está en la lista.");
        }
    }

    public void eliminarProducto() {
        String nombre = campoBusqueda.getText().trim();

        if (nombre.isEmpty()) {
            mostrarMensaje("Digite el nombre del producto que desea eliminar.");
            campoBusqueda.requestFocus();
            return;
        }

        Producto productoEliminado = listaProductos.eliminar(nombre);

        if (productoEliminado == null) {
            mostrarMensaje("El producto buscado no está en la lista.");
        } else {
            actualizarTabla();
            areaSalida.setText("Producto eliminado:\n\n" + productoEliminado);
            mostrarMensaje("Producto eliminado correctamente.");
            limpiarCamposProducto();
        }
    }

    public void mostrarProductos() {
        actualizarTabla();

        if (listaProductos.getPrimero() == null) {
            areaSalida.setText("La lista de productos está vacía.\n");
            return;
        }

        StringBuilder texto = new StringBuilder();
        NodoProducto temp = listaProductos.getPrimero();

        while (temp != null) {
            texto.append(temp.getProducto()).append("\n");
            temp = temp.getSiguiente();
        }

        areaSalida.setText(texto.toString());
    }

    public void mostrarReporteCostos() {
        if (listaProductos.getPrimero() == null) {
            areaSalida.setText("La lista de productos está vacía.\n");
            return;
        }

        StringBuilder texto = new StringBuilder();
        double totalAcumulado = 0;

        NodoProducto temp = listaProductos.getPrimero();

        texto.append("Reporte de costos\n\n");

        while (temp != null) {
            Producto producto = temp.getProducto();
            double totalProducto = producto.calcularCostoTotal();

            texto.append("Producto: ").append(producto.getNombre()).append("\n");
            texto.append("Precio unitario: ").append(producto.obtenerPrecioFormato()).append("\n");
            texto.append("Cantidad: ").append(producto.getCantidad()).append("\n");
            texto.append("Costo total del producto: ").append(producto.obtenerCostoTotalFormato()).append("\n\n");

            totalAcumulado += totalProducto;
            temp = temp.getSiguiente();
        }

        texto.append("Costo total acumulado: ").append(formatoColones(totalAcumulado)).append("\n");

        areaSalida.setText(texto.toString());
    }

    public String formatoColones(double monto) {
        java.text.DecimalFormat formato = new java.text.DecimalFormat("\u20A1#,##0.00");
        return formato.format(monto);
    }

    public void actualizarTabla() {
        modeloTabla.setRowCount(0);

        NodoProducto temp = listaProductos.getPrimero();

        while (temp != null) {
            Producto producto = temp.getProducto();

            String tieneImagen = "No";

            if (!producto.getListaImagenes().isEmpty()) {
                tieneImagen = "Sí";
            }

            Object[] fila = {
                    producto.getNombre(),
                    producto.obtenerPrecioFormato(),
                    producto.getCategoria(),
                    producto.getFechaVencimiento(),
                    producto.getCantidad(),
                    producto.obtenerCostoTotalFormato(),
                    tieneImagen
            };

            modeloTabla.addRow(fila);
            temp = temp.getSiguiente();
        }
    }

    public void cargarProductoSeleccionado() {
        int fila = tablaProductos.getSelectedRow();

        if (fila >= 0) {
            String nombre = tablaProductos.getValueAt(fila, 0).toString();
            Producto producto = listaProductos.buscar(nombre);

            if (producto != null) {
                campoBusqueda.setText(producto.getNombre());
                cargarProductoEnCampos(producto);
                mostrarImagenProducto(producto);
                areaSalida.setText("Producto seleccionado:\n\n" + producto);
            }
        }
    }

    public void cargarProductoEnCampos(Producto producto) {
        campoNombre.setText(producto.getNombre());
        campoPrecio.setText(String.valueOf(producto.getPrecio()));
        campoCategoria.setSelectedItem(producto.getCategoria());
        campoFechaVencimiento.setText(producto.getFechaVencimiento());
        campoCantidad.setText(String.valueOf(producto.getCantidad()));

        if (!producto.getListaImagenes().isEmpty()) {
            campoImagen.setText(producto.getListaImagenes().get(0));
        }
    }

    public void mostrarImagenProducto(Producto producto) {
        if (producto != null && !producto.getListaImagenes().isEmpty()) {
            String rutaImagen = producto.getListaImagenes().get(0);
            campoImagen.setText(rutaImagen);
            mostrarVistaPrevia(rutaImagen);
        } else {
            etiquetaImagen.setText("Este producto no tiene imagen registrada");
            etiquetaImagen.setIcon(null);
        }
    }

    public void mostrarVistaPrevia(String rutaImagen) {
        ImageIcon imagenOriginal = new ImageIcon(rutaImagen);

        if (imagenOriginal.getIconWidth() <= 0) {
            etiquetaImagen.setText("No se pudo cargar la imagen");
            etiquetaImagen.setIcon(null);
            return;
        }

        int anchoMaximo = etiquetaImagen.getWidth() - 20;
        int altoMaximo = etiquetaImagen.getHeight() - 20;

        if (anchoMaximo <= 0) {
            anchoMaximo = 420;
        }

        if (altoMaximo <= 0) {
            altoMaximo = 165;
        }

        int anchoOriginal = imagenOriginal.getIconWidth();
        int altoOriginal = imagenOriginal.getIconHeight();

        double proporcionAncho = (double) anchoMaximo / anchoOriginal;
        double proporcionAlto = (double) altoMaximo / altoOriginal;
        double proporcion = Math.min(proporcionAncho, proporcionAlto);

        int nuevoAncho = (int) (anchoOriginal * proporcion);
        int nuevoAlto = (int) (altoOriginal * proporcion);

        Image imagenEscalada = imagenOriginal.getImage().getScaledInstance(nuevoAncho, nuevoAlto, Image.SCALE_SMOOTH);

        etiquetaImagen.setText("");
        etiquetaImagen.setHorizontalAlignment(SwingConstants.CENTER);
        etiquetaImagen.setVerticalAlignment(SwingConstants.CENTER);
        etiquetaImagen.setIcon(new ImageIcon(imagenEscalada));
    }

    public void limpiarCamposProducto() {
        campoNombre.setText("");
        campoPrecio.setText("");
        campoCategoria.setSelectedIndex(0);
        campoFechaVencimiento.setText("");
        campoCantidad.setText("");
        campoImagen.setText("");
        etiquetaImagen.setText("Seleccione una imagen para verla aquí");
        etiquetaImagen.setIcon(null);
    }

    public void limpiarCampos() {
        campoBusqueda.setText("");
        limpiarCamposProducto();
        tablaProductos.clearSelection();
        areaSalida.setText("Aquí se mostrará la información de los productos.\n");
    }

    public void mostrarMensaje(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje);
    }

    public static void main(String[] args) {
        new VentanaProductos();
    }
}