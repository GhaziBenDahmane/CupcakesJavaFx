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

    public ObservableList<Promotion> selectAllPromotionFrom() {
        ObservableList<Promotion> promotions = FXCollections.observableArrayList();
        String req = "select * from promotion";
        try {
            Statement statement = (Statement) connection.createStatement();
            ResultSet result = statement.executeQuery(req);
            while (result.next()) {
                Promotion promotion = new Promotion(
                        result.getInt("id"), result.getDouble("discount"), result.getDate("starting_date"),
                        result.getDate("ending_date"));

                promotions.add(promotion);
            }

        } catch (SQLException ex) {
            Logger.getLogger(ProductService.class.getName()).log(Level.SEVERE, null, ex);
        }

        return promotions;
    }

    public void delete(int id) {

        try {
            String req = "Delete from promotion where id= ?";
            PreparedStatement statment = connection.prepareStatement(req);
            statment.setInt(1, id);
            statment.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(ProductService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
