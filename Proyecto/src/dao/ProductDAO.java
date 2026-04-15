package dao;
import java.sql.*;
import model.Product;
public class ProductDAO {

     public void insertProduct(Product product) throws SQLException {
        String sql = "INSERT INTO products (name, price, quantity, category) VALUES (?, ?, ?, ?)";
        
        try (Connection conn = db.ConnectionDB.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, product.getname());
            pstmt.setDouble(2, product.getprice());
            pstmt.setInt(3, product.getquantity());
            pstmt.setString(4, product.getcategory());
            pstmt.executeUpdate();
        }
    }

    
}



