/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Connection.DataSource;
import Entities.Ecommerce.Cart;
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
public class CartService {
    
    Connection connection = null;
    public CartService()
    {
        connection =DataSource.getInstance().getConnection();
    }
    
    public void insert(Cart c) {
        String req="INSERT INTO product (id_product) VALUES (?)";
        try {
            PreparedStatement statment = connection.prepareStatement(req);
            statment.setInt(1, c.getProducts().getId());
            statment.execute();
            
        } catch (SQLException ex) {
            Logger.getLogger(ProductService.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
    
    public List<Cart> selectAllProductsFromCart() {
        List<Cart> carts = new ArrayList<>();
        String req ="select * from cart";
        try {
            Statement statement = (Statement) connection.createStatement();
            ResultSet result = statement.executeQuery(req);
            while (result.next())
            {
                ProductService productservice =new ProductService();
                Cart c = new Cart(result.getInt(1),productservice.selectProductById(result.getInt(2)));
                carts.add(c);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(ProductService.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        return carts;
    }
    
    
    
    public void delete(int id)
    {
        String req="Delete from cart where id= ?";
        try {
            PreparedStatement statment = connection.prepareStatement(req);
            statment.setInt(1,id);
            statment.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(ProductService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
