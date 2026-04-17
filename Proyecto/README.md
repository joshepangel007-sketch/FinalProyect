# FinalProyect
Este repositorio contiene el proyecto final de programacion y consiste en unir una base de datos MySQL con Java

# Inventory Management System

!https://img.shields.io/badge/Java-17%2B-orange
!https://img.shields.io/badge/MySQL-8.0-blue
!https://img.shields.io/badge/License-MIT-green
!https://img.shields.io/badge/Status-Active-brightgreen

Sistema de gestión de inventario en Java. Implementa arquitectura por capas: Modelo → DAO → Service → Main.

## Tecnologías
- *Lenguaje*: Java 17+
- *Base de datos*: MySQL 8.0
- *Conexión*: JDBC
- *Interfaz*: JOptionPane
- *Arquitectura*: DAO + Service + Model

## Script SQL
Copia y ejecuta este script para crear la base de datos y tabla:

-- Crear base de datos
CREATE DATABASE IF NOT EXISTS inventory_db;
USE inventory_db;

-- Crear tabla products
CREATE TABLE IF NOT EXISTS products (
    id_products INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    price DOUBLE NOT NULL,
    quantity INT NOT NULL,
    category VARCHAR(50) NOT NULL
);

-- Datos de prueba opcionales
INSERT INTO products (name, price, quantity, category) VALUES
('Laptop HP', 899.99, 10, 'Electrónica'),
('Mouse Logitech', 25.50, 50, 'Accesorios'),
('Escritorio', 150.00, 8, 'Muebles');


## Configuración de Base de Datos

*Base de datos*: `inventory_db`

*Tabla*: `products`

| Campo | Tipo | Descripción |
| id_products | INT | Clave primaria, AUTO_INCREMENT |
| name | VARCHAR(100) | Nombre del producto, NOT NULL |
| price | DOUBLE | Precio del producto, NOT NULL |
| quantity | INT | Cantidad disponible, NOT NULL |
| category | VARCHAR(50) | Categoría del producto, NOT NULL |


*ConnectionDB*  
- *URL*: `jdbc:mysql://localhost:3306/inventory_db`
- *Usuario*: `jfalcon`
- *Contraseña*: vacía
- *Driver*: `com.mysql.cj.jdbc.Driver`

## Estructura del proyecto
src/
├── model/
│   └── Product.java
├── db/
│   └── ConnectionDB.java
├── dao/
│   └── ProductDAO.java
├── service/
│   └── ProductService.java
└── Main.java


## Clases

### 1. Product
*Descripción*: Modelo de datos que representa un producto. POJO que mapea la tabla `products`.

*Atributos*:
- `id`: int
- `name`: String 
- `price`: double
- `quantity`: int
- `category`: String

*Constructores*:
- `Product()` : Constructor vacío
- `Product(String name, double price, int quantity, String category)`

*Métodos*:
- Getters y Setters para todos los atributos
- `toString()`: Retorna formato `ID: [id] | Nombre: [name] | Precio: $[price] | Cantidad: [quantity] | Categoría: [category]`

### 2. ConnectionDB
*Descripción*: Clase encargada de establecer la conexión con MySQL.

*Método*:
- `public static Connection getConnection() throws SQLException`: Carga el driver y retorna una conexión usando `DriverManager`.

*Manejo de errores*:
- Lanza `SQLException` si el driver no se encuentra o falla la conexión.

*Notas*:
- Método estático. No necesita instanciar la clase.
- Requiere el driver de MySQL agregado al proyecto.

### 3. ProductDAO
*Descripción*: Gestiona el acceso a la base de datos para la entidad `Product`. Implementa operaciones CRUD usando JDBC y `PreparedStatement`.

*Dependencias*:
- `java.sql._`
- `java.util.ArrayList`, `java.util.List`
- `model.Product`
- `db.ConnectionDB`

*Métodos*:
- `insertProduct(Product product)`: Inserta producto con `INSERT`.
- `getAllProducts()`: Retorna `List<Product>` con `SELECT _`.
- `getProductById(int id)`: Busca por ID con `SELECT WHERE`. Retorna `Product` o `null`.
- `updateProduct(Product product)`: Actualiza con `UPDATE`.
- `deleteProduct(int id)`: Elimina con `DELETE`.

*Manejo de recursos*: Usa `try-with-resources` para cerrar `Connection`, `PreparedStatement` y `ResultSet`.

*Manejo de errores*: Todos los métodos lanzan `SQLException`.

*Notas*:
- Usa `PreparedStatement` para evitar inyección SQL.
- Cada registro se convierte en objeto `Product`.

### 4. ProductService
*Descripción*: Capa de servicios. Intermediaria entre la lógica de negocio y el DAO. Valida datos antes de enviarlos al DAO.

*Dependencias*:
- `dao.ProductDAO`
- `model.Product`
- `java.sql.SQLException`
- `java.util.List`

*Atributos*:
- `productDAO`: Instancia de `ProductDAO`.

*Métodos*:
- `createProduct(Product product)`: Valida y crea. Nombre no vacío, precio >= 0, cantidad >= 0.
- `getAllProducts()`: Retorna `List<Product>`.
- `getProductById(int id)`: Valida ID > 0. Lanza excepción si no existe.
- `updateProduct(Product product)`: Valida ID > 0.
- `deleteProduct(int id)`: Valida ID > 0.

*Manejo de errores*:
- `IllegalArgumentException`: Datos inválidos.
- `SQLException`: Errores de BD o producto no existe.

*Notas*:
- Separa lógica de negocio del acceso a datos.
- Es usada por el `Main` o controladores.

### 5. Main
*Descripción*: Punto de entrada. Interfaz gráfica básica con `JOptionPane`. Menú interactivo para CRUD de productos.

*Dependencias*:
- `model.Product`
- `service.ProductService`
- `java.sql.SQLException`
- `javax.swing.JOptionPane`

*Menú de opciones*:
1. Crear producto
2. Listar productos
3. Buscar producto por ID
4. Actualizar producto
5. Eliminar producto
6. Salir

*Métodos principales*:
- `main(String[] args)`: Ejecuta el programa y controla el menú.
- `showMenu()`: Muestra menú y valida opción entre 1 y 6.
- `createProduct()`: Solicita datos y crea producto.
- `listProducts()`: Muestra todos los productos.
- `searchProduct()`: Busca por ID.
- `updateProduct()`: Modifica datos. Campos vacíos mantienen valores.
- `deleteProduct()`: Elimina por ID.

*Manejo de errores*:
- `SQLException`: Errores de base de datos.
- `IllegalArgumentException`: Errores de validación.
- `NumberFormatException`: Entrada inválida.
- `NullPointerException`: Cancelación de diálogos.

## Cómo ejecutar
1. Instalar MySQL y crear la base de datos ejecutando el *Script SQL* de arriba.
2. Descargar MySQL Connector/J y agregarlo al classpath del proyecto.
3. Configurar usuario y contraseña en `ConnectionDB.java` si son diferentes.
4. Compilar: `javac -d bin src/*_/_.java`
5. Ejecutar: `java -cp bin:mysql-connector-j-8.x.x.jar Main`

## Notas generales
- Arquitectura en capas: Main → Service → DAO → DB.
- Se usa `PreparedStatement` en todos los queries.
- `try-with-resources` garantiza cierre de recursos.
- Las validaciones están en `ProductService`, no en el DAO.


