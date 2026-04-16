package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionDB {
    private static final String URL = "jdbc:mysql://100.96.142.77:3306/inventory_db";

    private static String user;
    private static String password;

    // Guarda las credenciales al hacer login
    public static void setCredentials(String u, String p) {
        user = u;
        password = p;
    }

    // Metodo que retorna la conexion a la base de datos
    public static Connection getConnection() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(URL, user, password);
        } catch (ClassNotFoundException e) {
            throw new SQLException("Driver no encontrado", e);
        }
    }
}