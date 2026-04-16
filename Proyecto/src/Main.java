import java.sql.SQLException;
import javax.swing.JOptionPane;
import model.Product;
import service.ProductService;

public class Main {
    private static ProductService productService = new ProductService();

    public static void main(String[] args) {
        while (true) {
            int option = showMenu();

            try {
                switch (option) {
                    case 1:
                        createProduct();
                        break;
                    case 2:
                        listProducts();
                        break;
                    case 3:
                        searchProduct();
                        break;
                    case 4:
                        updateProduct();
                        break;
                    case 5:
                        deleteProduct();
                        break;
                    case 6:
                        JOptionPane.showMessageDialog(null, "¡Hasta luego!");
                        System.exit(0);
                        break;
                    default:
                        JOptionPane.showMessageDialog(null, "Opción inválida");
                }
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error de base de datos: " + e.getMessage());
            } catch (IllegalArgumentException e) {
                JOptionPane.showMessageDialog(null, "Error de validación: " + e.getMessage());
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Error inesperado: " + e.getMessage());
            }
        }
    }

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

    private static void createProduct() throws SQLException {
        String name = JOptionPane.showInputDialog("Nombre:");
        double price = Double.parseDouble(JOptionPane.showInputDialog("Precio:"));
        int quantity = Integer.parseInt(JOptionPane.showInputDialog("Cantidad:"));
        String category = JOptionPane.showInputDialog("Categoría:");

        Product product = new Product(name, price, quantity, category);
        productService.createProduct(product);

        JOptionPane.showMessageDialog(null, "Producto creado exitosamente");
    }

    private static void listProducts() throws SQLException {
        var products = productService.getAllProducts();

        if (products.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No hay productos registrados");
        } else {
            StringBuilder list = new StringBuilder("=== LISTA DE PRODUCTOS ===\n");
            for (Product p : products) {
                list.append(p).append("\n");
            }
            JOptionPane.showMessageDialog(null, list.toString());
        }
    }

    private static void searchProduct() throws SQLException {
        int id = Integer.parseInt(JOptionPane.showInputDialog("ID del producto:"));
        Product product = productService.getProductById(id);

        JOptionPane.showMessageDialog(null, "Producto encontrado:\n" + product);
    }

    private static void updateProduct() throws SQLException {
        int id = Integer.parseInt(JOptionPane.showInputDialog("ID del producto a actualizar:"));

        Product product = productService.getProductById(id);

        JOptionPane.showMessageDialog(null, "Producto actual:\n" + product);

        String name = JOptionPane.showInputDialog("Nuevo nombre (dejar vacío para mantener):");
        if (name != null && !name.isEmpty()) product.setName(name);

        String priceStr = JOptionPane.showInputDialog("Nuevo precio (dejar vacío para mantener):");
        if (priceStr != null && !priceStr.isEmpty()) product.setPrice(Double.parseDouble(priceStr));

        String quantityStr = JOptionPane.showInputDialog("Nueva cantidad (dejar vacío para mantener):");
        if (quantityStr != null && !quantityStr.isEmpty()) product.setQuantity(Integer.parseInt(quantityStr));

        String category = JOptionPane.showInputDialog("Nueva categoría (dejar vacío para mantener):");
        if (category != null && !category.isEmpty()) product.setCategory(category);

        productService.updateProduct(product);

        JOptionPane.showMessageDialog(null, "Producto actualizado exitosamente");
    }

    private static void deleteProduct() throws SQLException {
        int id = Integer.parseInt(JOptionPane.showInputDialog("ID del producto a eliminar:"));

        productService.deleteProduct(id);

        JOptionPane.showMessageDialog(null, "Producto eliminado exitosamente");
    }
}