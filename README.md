# Avance 1 - Gestión de Inventarios

Este proyecto corresponde al primer avance de la aplicación de gestión de inventarios para una ferretería. El sistema permite administrar una lista de productos mediante una lista enlazada simple, utilizando Java y una interfaz de consola como parte principal del proyecto.

Además, se agregó una interfaz gráfica como complemento, con el fin de facilitar la interacción visual con los productos, sus imágenes y sus costos.

## Descripción del proyecto

La aplicación simula la gestión inicial de productos de una ferretería. Cada producto registrado contiene información básica como nombre, precio, categoría, fecha de vencimiento, cantidad disponible y una lista de imágenes asociadas.

La estructura principal utilizada es una lista enlazada simple, donde cada nodo almacena un producto y una referencia al siguiente nodo.

## Funcionalidades principales

El sistema permite realizar las siguientes acciones:

* Insertar productos al inicio de la lista.
* Insertar productos al final de la lista.
* Mostrar todos los productos registrados.
* Buscar productos por nombre.
* Modificar los datos de un producto.
* Agregar rutas de imágenes a un producto.
* Eliminar productos de la lista.
* Generar un reporte de costos por producto.
* Calcular el costo total acumulado de la lista.

## Estructura del proyecto

El proyecto contiene las siguientes clases principales:

* `Producto.java`: representa los datos de cada producto de la ferretería.
* `NodoProducto.java`: representa cada nodo de la lista enlazada simple.
* `ListaProductos.java`: contiene la lógica de la lista enlazada y sus operaciones.
* `Main.java`: contiene el menú de consola para interactuar con el sistema.
* `VentanaProductos.java`: contiene una interfaz gráfica desarrollada con Java Swing como complemento visual.

## Estructura de datos utilizada

La estructura de datos principal es una lista enlazada simple. Esta permite conectar productos mediante nodos, donde cada nodo almacena un producto y una referencia al siguiente elemento de la lista.

La lista permite insertar, buscar, modificar, eliminar y recorrer productos de forma dinámica.

## Interfaz gráfica

Como complemento al menú de consola, se desarrolló una interfaz gráfica utilizando Java Swing. Esta interfaz permite registrar productos, seleccionarlos desde una tabla, agregar imágenes, visualizar una vista previa y consultar el reporte de costos.

La interfaz gráfica no reemplaza la lógica principal del proyecto, sino que utiliza la misma estructura `ListaProductos`.

## Carpeta de imágenes

El proyecto incluye una carpeta llamada `imagenes`, donde se pueden guardar las imágenes asociadas a los productos. Las rutas de estas imágenes se almacenan como texto dentro de cada producto.

Ejemplo de ruta:

```text
imagenes/martillo.png
```

## Ejecución del proyecto

Para ejecutar la versión por consola, se debe correr la clase:

```text
Main.java
```

Para ejecutar la interfaz gráfica, se debe correr la clase:

```text
VentanaProductos.java
```

## Tecnologías utilizadas

* Java
* Java Swing
* IntelliJ IDEA
* Lista enlazada simple
* ArrayList para almacenar rutas de imágenes

## Tema del proyecto

El sistema está basado en una ferretería, por lo que los productos utilizados pueden ser herramientas manuales, herramientas eléctricas, pinturas, materiales de construcción, artículos de electricidad, plomería, tornillería, seguridad, adhesivos y selladores.
