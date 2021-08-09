/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.misiontic.controller;

import co.edu.misiontic.model.Product;
import co.edu.misiontic.model.persistence.ConnectionDB;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author USUARIO
 */
public class ManageProduct {
    
    public boolean saveProduct(String name,int amount, String category, double price){
        ConnectionDB connection = new ConnectionDB();
        Product product = new Product(0, name, amount, category,price);
        String statement = "INSERT INTO productos(nombre, cantidad, categoria, precio)"
                +"VALUES ('"+product.getName()+"',"+product.getAmount()+",'"
                +product.getCategory()+"',"+product.getPrice()+");";
        if(connection.setAutoCommitBD(false)){
            if(connection.insertDB(statement)){
                connection.commitDB();
                connection.closeConnection();
                return true;
            } else {
                connection.rollbackDB();
                connection.closeConnection();
                return false;
            }
        }else {
            connection.closeConnection();
            return false;
        }
    }

    public boolean updateProduct(int id,String name,int amount, String category, double price){
        
        ConnectionDB connection = new ConnectionDB();
        Product product = new Product(id, name, amount, category, price);
        String statement = "UPDATE productos SET nombre='"+product.getName()+"',cantidad='"+product.getAmount()
                +"',categoria='"+product.getCategory()+"',precio="+product.getPrice()+" WHERE id="+product.getId();
        System.out.println(statement);
        
        if(connection.setAutoCommitBD(false)){
            if (connection.updateDB(statement)) {
                connection.commitDB();
                connection.closeConnection();
                return true;
            }else {
                connection.rollbackDB();
                connection.closeConnection();
                return false;
            }
        
        }else{
            connection.closeConnection();
            return false;
        }
    }

    public boolean deleteProduct(int id){
        ConnectionDB connection = new ConnectionDB();
        String statement = "DELETE FROM productos WHERE id='"+id+"';";
        
        if(connection.setAutoCommitBD(false)){
            if (connection.deleteDB(statement)) {
                connection.commitDB();
                connection.closeConnection();
                return true;
            }else {
                connection.rollbackDB();
                connection.closeConnection();
                return false;
            }
        
        }else{
            connection.closeConnection();
            return false;
        }
        
        
    }

    public Product getProduct(int id){
        ConnectionDB connectionDB = new ConnectionDB();
        String query = "SELECT * FROM productos WHERE id='"+id+"';";
        ResultSet rs = connectionDB.consultDB(query);
        Product product = null;
        try {
            product = new Product();
            if(rs.next()){
                product.setId(rs.getInt("id"));
                product.setName(rs.getString("nombre"));
                product.setAmount(rs.getInt("cantidad"));
                product.setCategory(rs.getString("categoria"));
                product.setPrice(rs.getDouble("precio"));
                connectionDB.closeConnection();
            }else{
                connectionDB.closeConnection();
                return null;
            }
        } catch (SQLException e) {
            System.out.println("error: "+ e.getMessage());
        }
        return product;
    }

    public ArrayList<Product> getAllProducts(){
        ConnectionDB connectionDB = new ConnectionDB();
        ArrayList<Product> listProducts = new ArrayList<>();
        String query = "SELECT * FROM productos;";
        System.out.println(query);
        ResultSet rs = connectionDB.consultDB(query);
        
        try {
            while (rs.next()) {
                Product product = new Product();
                product.setId(rs.getInt("id"));
                product.setName(rs.getString("nombre"));
                product.setAmount(rs.getInt("cantidad"));
                product.setCategory(rs.getString("categoria"));
                product.setPrice(rs.getDouble("precio"));                
                listProducts.add(product);
            }
            connectionDB.closeConnection();
            
        } catch (SQLException e) {
            System.out.println("Error: "+ e.getMessage());;
        }
        connectionDB.closeConnection();
        System.out.println(listProducts.toString());
        return listProducts;
    }
    
    
}
