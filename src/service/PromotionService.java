package service;

import entity.Promotion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import util.DataSource;

/**
 *
 * @author Yasmine
 */
public class PromotionService {

    Connection connection = null;

    public PromotionService() {
        connection = DataSource.getInstance().getConnection();

    }

    public void insert(Promotion promotion) {
        String req = "INSERT INTO promotion (discount, starting_date , ending_date) VALUES (?,?,?)";
        try {
            PreparedStatement statment = connection.prepareStatement(req);
            statment.setDouble(1, promotion.getDiscount());
            statment.setDate(2, promotion.getStarting_date());
            statment.setDate(3, promotion.getEnding_date());

            statment.execute();

        } catch (SQLException ex) {
            Logger.getLogger(ProductService.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public List<Promotion> selectAllPromotionFrom() {
        List<Promotion> promotions = new ArrayList<>();
        String req = "select * from promotion";
        try {
            Statement statement = (Statement) connection.createStatement();
            ResultSet result = statement.executeQuery(req);
            while (result.next()) {
                Promotion promo = new Promotion(result.getDouble(1), result.getDate(2), result.getDate(3));
                promotions.add(promo);
            }

        } catch (SQLException ex) {
            Logger.getLogger(ProductService.class.getName()).log(Level.SEVERE, null, ex);
        }

        return promotions;
    }

    public List<Promotion> selectAll() {
        List<Promotion> promotions = new ArrayList<>();
        String req = "select * from promotion";

        try {
            Statement statement = (Statement) connection.createStatement();
            ResultSet result = statement.executeQuery(req);

            while (result.next()) {

                Promotion promotion = new Promotion(result.getInt("id"), result.getDouble("discount"), result.getDate("starting_date"), result.getDate("ending_date"));

                promotions.add(promotion);
            }

        } catch (SQLException ex) {
            Logger.getLogger(ProductService.class.getName()).log(Level.SEVERE, null, ex);
        }

        return promotions;
    }
    
    public Promotion selectPromotionById(Integer id) {
        Promotion promotion=null; ;
        String req = "select * from promotion where id=?";

        try {
            PreparedStatement statement = connection.prepareStatement(req);
            statement.setInt(1, id);
            ResultSet result = statement.executeQuery();
            
if (result.next()) {
        promotion = new Promotion(result.getInt("id"), result.getDouble("discount"), result.getDate("starting_date"), result.getDate("ending_date"));

}    
            

        } catch (SQLException ex) {
            Logger.getLogger(ProductService.class.getName()).log(Level.SEVERE, null, ex);
        }

        return promotion;
    }

    public void delete(int id) {
        String req = "Delete from promotion where id= ?";
        try {
            PreparedStatement statment = connection.prepareStatement(req);
            statment.setInt(1, id);
            statment.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(ProductService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
