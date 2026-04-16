package model;

public class Product {
    private int id;
    private String name;
    private double price;
    private int quantity;
    private String category;

    public Product() {
    } // Constructor vacio, obligatorio

    public Product(String name, double price, int quantity, String category) {// Constructor lleno
        this.name = name;
        this.price = price;// parametros que vas a recibir, y parametros de la clase
        this.quantity = quantity;
        this.category = category;
    }

    // Permiten leer y escribir los atributos privados de la clase
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return String.format("ID: %d | Nombre: %s | Precio: $%.2f | Cantidad: %d | Categoría: %s", // Formato para
                                                                                                   // devolver el valor
                id, name, price, quantity, category);
    }
}