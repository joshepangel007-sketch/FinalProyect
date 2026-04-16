package service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

import db.ConnectionDB;

public class Main {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);

    // Pedimos usuario y contraseña al iniciar la app
    System.out.print("Usuario: ");
    String user = scanner.nextLine();

    System.out.print("Contraseña: ");
    String password = scanner.nextLine();

    // Intentamos conectarnos con las credenciales ingresadas
    try {
      Connection conn = ConnectionDB.getConnection(user, password);
      System.out.println("Conexión exitosa! Bienvenido " + user);
      conn.close();
    } catch (SQLException e) {
      System.out.println("Usuario o contraseña incorrectos");
      return; // Salimos si no hay conexion
    }

    // Menu principal
    int opcion = 0;
    while (opcion != 6) {
      System.out.println("\n=== Sistema de Inventario ===");
      System.out.println("1. Crear producto");
      System.out.println("2. Listar productos");
      System.out.println("3. Buscar producto por ID");
      System.out.println("4. Actualizar producto");
      System.out.println("5. Eliminar producto");
      System.out.println("6. Salir");
      System.out.print("Opcion: ");
      opcion = scanner.nextInt();
    }

    System.out.println("Hasta luego!");
    scanner.close();
  }
}