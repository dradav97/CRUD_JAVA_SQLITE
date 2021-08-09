/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.misiontic.model;

/**
 *
 * @author USUARIO
 */
public class Product {
    private int id;
    private String name; 
    private int amount;
    private String category; 
    private double price;
    
    public Product(){}

    public Product(int id, String name, int amount, String category, double price) {
        this.id = id;
        this.name = name;
        this.amount = amount;
        this.category = category;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getAmount() {
        return amount;
    }

    public String getCategory() {
        return category;
    }

    public double getPrice() {
        return price;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Producto{" + "id=" + id + ", name=" + name + ", amount=" + amount + ", category=" + category + ", price=" + price + '}';
    }
    
}
