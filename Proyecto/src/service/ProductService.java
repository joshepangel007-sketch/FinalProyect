package service;

import dao.ProductDAO;
import model.Product;
import java.sql.SQLException;
import java.util.List;
import javax.swing.JOptionPane;

public class ProductService {

    private ProductDAO productDAO; //Objeto que accede a la base de datos

    public ProductService() {
        this.productDAO = new ProductDAO(); //Creamos una instancia del DAO
    }

    //Metodo para crear un producto, valida los datos antes de insertarlo
    public void createProduct(Product product) throws SQLException {
        try {
            //Validaciones antes de insertar
            if (product.getName() == null || product.getName().trim().isEmpty()) {
                throw new IllegalArgumentException("El nombre no puede estar vacío");
            }
            if (product.getPrice() < 0) {
                throw new IllegalArgumentException("El precio no puede ser negativo");
            }
            if (product.getQuantity() < 0) {
                throw new IllegalArgumentException("La cantidad no puede ser negativa");
            }
            productDAO.insertProduct(product); //Llama al DAO para insertar
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage()); //Muestra el error al usuario
            throw e;
        }
    }

    //Metodo para obtener todos los productos de la base de datos
    public List<Product> getAllProducts() throws SQLException {
        try {
            return productDAO.getAllProducts();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al obtener productos: " + e.getMessage());
            throw e;
        }
    }

    //Metodo para buscar un producto por su ID
    public Product getProductById(int id) throws SQLException {
        try {
            if (id <= 0) {
                throw new IllegalArgumentException("ID inválido");
            }
            Product product = productDAO.getProductById(id);
            if (product == null) {
                throw new SQLException("Producto no encontrado con ID: " + id);
            }
            return product;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
            throw e;
        }
    }

    //Metodo para actualizar un producto existente
    public void updateProduct(Product product) throws SQLException {
        try {
            if (product.getId() <= 0) {
                throw new IllegalArgumentException("ID inválido");
            }
            productDAO.updateProduct(product); //Llama al DAO para actualizar
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
            throw e;
        }
    }

    //Metodo para eliminar un producto por su ID
    public void deleteProduct(int id) throws SQLException {
        try {
            if (id <= 0) {
                throw new IllegalArgumentException("ID inválido");
            }
            productDAO.deleteProduct(id); //Llama al DAO para eliminar
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
            throw e;
        }
    }
}