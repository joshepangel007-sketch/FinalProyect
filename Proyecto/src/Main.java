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
        while (true) {
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
            
            // Verificar si el usuario canceló el diálogo
            if (input == null) {
                continue;
            }
            
            try {
                int option = Integer.parseInt(input);
                // Validar que la opción esté en el rango correcto
                if (option >= 1 && option <= 6) {
                    return option;
                } else {
                    JOptionPane.showMessageDialog(null, "Opción inválida. Por favor, seleccione un número entre 1 y 6.");
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Entrada inválida. Por favor, ingrese un número válido.");
            }
        }
    }

    private static void createProduct() throws SQLException {
        try {
            String name = JOptionPane.showInputDialog("Nombre:");
            if (name == null || name.trim().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Operación cancelada o nombre inválido");
                return;
            }
            
            double price = Double.parseDouble(JOptionPane.showInputDialog("Precio:"));
            int quantity = Integer.parseInt(JOptionPane.showInputDialog("Cantidad:"));
            String category = JOptionPane.showInputDialog("Categoría:");
            if (category == null) category = "";

            Product product = new Product(name, price, quantity, category);
            productService.createProduct(product);

            JOptionPane.showMessageDialog(null, "Producto creado exitosamente");
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Error: Debe ingresar un número válido para precio y cantidad");
        } catch (NullPointerException e) {
            JOptionPane.showMessageDialog(null, "Operación cancelada");
        }
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
        try {
            int id = Integer.parseInt(JOptionPane.showInputDialog("ID del producto:"));
            Product product = productService.getProductById(id);
            JOptionPane.showMessageDialog(null, "Producto encontrado:\n" + product);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Error: Debe ingresar un ID numérico válido");
        } catch (NullPointerException e) {
            JOptionPane.showMessageDialog(null, "Operación cancelada");
        }
    }

    private static void updateProduct() throws SQLException {
        try {
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
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Error: Debe ingresar un número válido para ID, precio o cantidad");
        } catch (NullPointerException e) {
            JOptionPane.showMessageDialog(null, "Operación cancelada");
        }
    }

    private static void deleteProduct() throws SQLException {
        try {
            int id = Integer.parseInt(JOptionPane.showInputDialog("ID del producto a eliminar:"));
            productService.deleteProduct(id);
            JOptionPane.showMessageDialog(null, "Producto eliminado exitosamente");
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Error: Debe ingresar un ID numérico válido");
        } catch (NullPointerException e) {
            JOptionPane.showMessageDialog(null, "Operación cancelada");
        }
    }
}