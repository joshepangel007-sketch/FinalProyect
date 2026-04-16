package dao;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
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
    
    public List<Product> getAllProducts() throws SQLException {
        List<Product> products = new ArrayList<>();
        String sql = "SELECT * FROM products";
        
        try (Connection conn = db.ConnectionDB.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                Product p = new Product();
                p.setId(rs.getInt("id"));
                p.setname(rs.getString("name"));
                p.setprice(rs.getDouble("price"));
                p.setquantity(rs.getInt("quantity"));
                p.setcategory(rs.getString("category"));
                products.add(p);
            }
        }
        return products;
    }
}



