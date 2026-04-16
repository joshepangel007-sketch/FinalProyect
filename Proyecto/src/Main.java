package service;

import java.sql.Connection;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import db.ConnectionDB;

public class Main {

  private static int showMenu() { // Metodo para mostrar el menu luego en el main
    String menu = """
        SISTEMA DE INVENTARIO
        1. Crear producto
        2. Listar productos
        3. Buscar producto por ID
        4. Actualizar producto
        5. Eliminar producto
        6. Salir
        """; // Variable para mostrar el menu
    String input = JOptionPane.showInputDialog(menu + "\nSeleccione una opción:");
    return Integer.parseInt(input);
  }

  public static void main(String[] args) {
    // Pedimos usuario y contraseña al iniciar la app
    String user = JOptionPane.showInputDialog("Usuario:");
    String password = JOptionPane.showInputDialog("Contraseña:");

    // Intentamos conectarnos con las credenciales ingresadas
    try {
      Connection conn = ConnectionDB.getConnection(user, password);
      conn.close();
      JOptionPane.showMessageDialog(null, "Conexión exitosa! Bienvenido " + user);
    } catch (SQLException e) {
      JOptionPane.showMessageDialog(null, "Usuario o contraseña incorrectos");
      return; // Salimos si no hay conexion
    }

    // Menu principal
    int opcion = 0;
    while (opcion != 6) {
      opcion = showMenu();
    }

    JOptionPane.showMessageDialog(null, "Hasta luego!");
  }
}