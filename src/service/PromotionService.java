/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import entity.Cart;
import entity.Promotion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import util.DataSource;

/**
 *
 * @author Yasmine
 */
public class PromotionService {

        Connection connection = null;

    public PromotionService() {
                connection =DataSource.getInstance().getConnection();

    }
    
    
    public void insert(Promotion promotion) {
        String req="INSERT INTO promotion (discount, starting_date , ending_date) VALUES (?,?,?)";
        try {
            PreparedStatement statment = connection.prepareStatement(req);
            statment.setDouble(1, promotion.getId_promotion());
            statment.setDate(2, promotion.getStarting_date());
            statment.setDate(3, promotion.getEnding_date());
            
            statment.execute();
            
        } catch (SQLException ex) {
            Logger.getLogger(ProductService.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
    
    public List<Promotion> selectAllPromotionFrom() {
        List<Promotion> promotions = new ArrayList<>();
        String req ="select * from promotion";
        try {
            Statement statement = (Statement) connection.createStatement();
            ResultSet result = statement.executeQuery(req);
            while (result.next())
            {
                Promotion promo = new Promotion(result.getDouble(1),result.getDate(2),result.getDate(3));
                promotions.add(promo);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(ProductService.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        return promotions;
    }
    
    
    
    public void delete(int id)
    {
        String req="Delete from promotion where id= ?";
        try {
            PreparedStatement statment = connection.prepareStatement(req);
            statment.setInt(1,id);
            statment.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(ProductService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
