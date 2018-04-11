/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import entity.Cart;
import entity.Event;
import entity.Pastry;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import util.DataSource;

/**
 *
 * @author haffez
 */
public class PastryService {
     private Connection connection;

    public PastryService() {
        connection = DataSource.getInstance().getConnection();
    }
    public void add(Pastry p) {
        String req="INSERT INTO `pastry` (  `address`, `nb_table`) VALUES (  ?, ?)";
        try {
            PreparedStatement statment = connection.prepareStatement(req);
            statment.setString(1, p.getAddress());
            statment.setInt(2, p.getNb_table());
            statment.execute();
            
        } catch (SQLException ex) {
            Logger.getLogger(ProductService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public ObservableList<Pastry> selectAllPastry() {
        ObservableList<Pastry> pastry = FXCollections.observableArrayList();
        String req ="select * from pastry";
        try {
            Statement statement = (Statement) connection.createStatement();
            ResultSet result = statement.executeQuery(req);
            while (result.next())
            {
                Pastry p = new Pastry(result.getInt(1), result.getInt(2), result.getString(3), result.getInt(4));
                pastry.add(p);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(ProductService.class.getName()).log(Level.SEVERE, null, ex);
        }
       
        return pastry;
    }
    
    
    
    public void delete(int id)
    {
        String req="Delete from pastry where id= ?";
        try {
            PreparedStatement statment = connection.prepareStatement(req);
            statment.setInt(1,id);
            statment.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ProductService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
