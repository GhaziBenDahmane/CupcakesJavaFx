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
import java.sql.SQLException;
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
        String req="INSERT INTO product (id,name,price,type,description,photo,promotion_id) VALUES (?,?,?,?,?,?,?)";
        try {
            PreparedStatement statment = connection.prepareStatement(req);
            statment.setInt(1, p.getId());
            statment.setString(2, p.getName());
            statment.setDouble(3, p.getPrice());
            statment.setString(4, p.getType());
            statment.setString(5,p.getDescription());
            statment.setString(6, p.getPhoto());
            statment.setInt(7,1);
            statment.execute();
            
        } catch (SQLException ex) {
            Logger.getLogger(ProductService.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
    
}
