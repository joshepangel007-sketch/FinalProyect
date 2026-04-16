package service;

import java.sql.Connection;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import db.ConnectionDB;

public class Main {

    private static int showMenu() {
        String menu = """
                SISTEMA DE INVENTARIO
                1. Crear producto
                2. Listar productos
                3. Buscar producto por ID
                4. Actualizar producto
                5. Eliminar producto
                6. Salir
                """;
        String input = JOptionPane.showInputDialog(menu + "\nSeleccione una opción:");
        return Integer.parseInt(input);
    }

    public static void main(String[] args) {
        // Pedimos usuario y contraseña al iniciar la app
        String user = JOptionPane.showInputDialog("Usuario:");
        String password = JOptionPane.showInputDialog("Contraseña:");

        // Guardamos las credenciales y verificamos la conexion
        ConnectionDB.setCredentials(user, password);
        try {
            Connection conn = ConnectionDB.getConnection();
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