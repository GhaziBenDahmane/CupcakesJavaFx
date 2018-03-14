/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Connection.DataSource;
import Entities.Ecommerce.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Arshavin
 */
public class ProductService {
    
    Connection connection = null;
    public ProductService()
    {
        connection =DataSource.getInstance().getConnection();
    }
    
    public void insert(Product p) {
        String req="INSERT INTO product (name,price,type,description,photo,promotion_id,nb_viewed,nb_seller) VALUES (?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement statment = connection.prepareStatement(req);
            statment.setString(1, p.getName());
            statment.setDouble(2, p.getPrice());
            statment.setString(3, p.getType());
            statment.setString(4,p.getDescription());
            statment.setString(5, p.getPhoto());
            statment.setInt(6,1);
            statment.setInt(7,p.getNb_view());
            statment.setInt(8,p.getNb_seller());
            statment.execute();
            
        } catch (SQLException ex) {
            Logger.getLogger(ProductService.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
    
    public List<Product> selectAll() {
        List<Product> products = new ArrayList<>();
        String req ="select * from product";
        try {
            Statement statement = (Statement) connection.createStatement();
            ResultSet result = statement.executeQuery(req);
            while (result.next())
            {
                Product p = new Product(result.getInt(1),result.getString(3),result.getString(4),result.getDouble(5),
                result.getInt(6),result.getInt(7),result.getString(9),result.getString(8));
                products.add(p);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(ProductService.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        return products;
    }
    
    public void update (Product p)
    {
        
        String req="Update product set name=? , type=? , price=? ,description=?, photo=? where id=123";
        try {
            PreparedStatement statment = connection.prepareStatement(req);
            statment.setString(1, p.getName());
            statment.setDouble(3, p.getPrice());
            statment.setString(2, p.getType());
            statment.setString(4,p.getDescription());
            statment.setString(5, p.getPhoto());
            
            statment.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(ProductService.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public void delete(int id)
    {
        String req="Delete from product where id= ?";
        try {
            PreparedStatement statment = connection.prepareStatement(req);
            statment.setInt(1,id);
            statment.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(ProductService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public Product selectProductById(int id)
    {   Product p=null;
        String req ="select * from product where id= ? ";
        try {
            PreparedStatement statement =  connection.prepareStatement(req);
            statement.setInt(1,id);
            ResultSet result = statement.executeQuery();
            while (result.next())
            {
                 p = new Product(result.getInt(1),result.getString(3),result.getString(4),result.getDouble(5),
                result.getInt(6),result.getInt(7),result.getString(9),result.getString(8));
               
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(ProductService.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        return p;
    }
}
