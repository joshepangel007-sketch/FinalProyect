import javax.swing.JOptionPane;

public class Main {





    
   
     private static int showMenu() { //Metodo para mostrar el menu luego en el main 
        String menu = """
                SISTEMA DE INVENTARIO
                1. Crear producto
                2. Listar productos
                3. Buscar producto por ID
                4. Actualizar producto
                5. Eliminar producto
                6. Salir
                """;  //Varible para mostrar el menu 

        String input = JOptionPane.showInputDialog(menu + "\nSeleccione una opción:");
        return Integer.parseInt(input);
    }
}