package service;

import dao.ProductDAO;
import java.sql.SQLException;
import java.util.List;
import model.Product;

public class ProductService {
    private ProductDAO productDAO;
    
    public ProductService() {
        this.productDAO = new ProductDAO();
    }
    
    public void createProduct(Product product) throws SQLException {
        if (product.getName() == null || product.getName().trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre no puede estar vacío");
        }
        if (product.getPrice() < 0) {
            throw new IllegalArgumentException("El precio no puede ser negativo");
        }
        if (product.getQuantity() < 0) {
            throw new IllegalArgumentException("La cantidad no puede ser negativa");
        }
        productDAO.insertProduct(product);
    }
    
    public List<Product> getAllProducts() throws SQLException {
        return productDAO.getAllProducts();
    }
    
    public Product getProductById(int id) throws SQLException {
        if (id <= 0) {
            throw new IllegalArgumentException("ID inválido");
        }
        Product product = productDAO.getProductById(id);
        if (product == null) {
            throw new SQLException("Producto no encontrado con ID: " + id);
        }
        return product;
    }
    
    public void updateProduct(Product product) throws SQLException {
        if (product.getId() <= 0) {
            throw new IllegalArgumentException("ID inválido");
        }
        productDAO.updateProduct(product);
    }
    
    public void deleteProduct(int id) throws SQLException {
        if (id <= 0) {
            throw new IllegalArgumentException("ID inválido");
        }
        productDAO.deleteProduct(id);
    }
}