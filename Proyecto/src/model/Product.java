package model;

public class Product {
    private int id; 
    private String name; 
    private double price; 
    private int quantity;
    private String category; 

    public Product() {} //Constructor vacio, obligatorio 

    public Product(String name, double price, int quantity, String category) {//Constructor lleno
        this.name = name;
        this.price = price;//parametros que vas a recibir, y parametros de la clase
        this.quantity = quantity;
        this.category = category;
    }

    //Permiten leer y escribir los atributos privados de la clase
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    
    public String getname() { return name;}   
    public void setname(String name) {this.name = name;}

    public double getprice (){ return price;}
    public void setprice (double price){this.price = price;}

    public int getquantity (){ return quantity;}
    public void setquantity (int quantity){this.quantity = quantity;}

    public String getcategory (){ return category;}
    public void setcategory (String category){this.category = category;}

    @Override
    public String toString() {
        return String.format("ID: %d | Nombre: %s | Precio: $%.2f | Cantidad: %d | Categoría: %s",//Formato para devolver el valor 
                id, name, price, quantity, category);
    }
}
