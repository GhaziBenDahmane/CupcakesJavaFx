/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import util.DataSource;
import entity.Cart;
import entity.Product;
import entity.Promotion;
import java.io.IOException;
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

    private Connection connection = null;
    private boolean isDeleteStatus=false;

    public CartService() {
        connection = DataSource.getInstance().getConnection();
    }

    public void insert(Cart c) {
        String req = "INSERT INTO cart (id_product) VALUES (?)";
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
        String req = "select * from cart c join product p where c.id_product=p.id";
        try {
            Statement statement = (Statement) connection.createStatement();
            ResultSet result = statement.executeQuery(req);
            while (result.next()) {
                PromotionService ps = new PromotionService();
                Promotion promotion= ps.selectPromotionById(result.getInt("promotion_id"));
                
                Product p = new Product(result.getInt("id"), result.getString("name"), result.getString("type"), result.getDouble("price"),
                        result.getInt("nb_viewed"), result.getInt("nb_seller"), result.getString("photo"), result.getString("description"), result.getInt("barcode"),promotion, result.getBlob("image"));
                Cart c = new Cart(result.getInt(1), p);
                carts.add(c);
            }

        } catch (SQLException ex) {
            Logger.getLogger(ProductService.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println(carts.size());
        return carts;
    }
    
    public Product selectCartById(int id) throws IOException {
        Product p=null ;
        String req = "select * from product p join cart r where p.id=r.id_product and r.id=?";
        try {
            PreparedStatement statement = connection.prepareStatement(req);
            statement.setInt(1, id);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                PromotionService ps = new PromotionService();
                Promotion promotion=ps.selectPromotionById(result.getInt(1));

                p = new Product(result.getInt(1), result.getString(3), result.getString(4), result.getDouble(5),
                        result.getInt(6), result.getInt(7), result.getString(8), result.getString(9), result.getInt(7), promotion,result.getBlob("image"));
               
            

            }
           } catch (SQLException ex) {
            Logger.getLogger(ProductService.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println(p);
        return p;
        }

    public void delete(int id) {
        String req = "Delete from cart where id= ?";
        try {
            PreparedStatement statment = connection.prepareStatement(req);
            statment.setInt(1, id);
            statment.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(ProductService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void deleteProductFromCart(int id) {
        String req = "Delete from cart where id_product= ?";
        try {
            PreparedStatement statment = connection.prepareStatement(req);
            statment.setInt(1, id);
            statment.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(ProductService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public boolean isIsDeleteStatus() {
        return isDeleteStatus;
    }

    public void setIsDeleteStatus(boolean isDeleteStatus) {
        this.isDeleteStatus = isDeleteStatus;
    }
    
    

}
