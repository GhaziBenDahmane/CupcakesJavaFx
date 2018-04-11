/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import util.DataSource;
import entity.Event;
import entity.Promotion;
import servicesInterfaces.IEventService;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class EventService implements IEventService {

    private Connection connection;

    public EventService() {
        connection = DataSource.getInstance().getConnection();
    }

    @Override
    public void add(Event event) {
        try {
            String req = "INSERT INTO `event` (`id`, `title`, `url`, `bgColor`,"
                    + "`cssClass`, `startDatetime`, `endDatetime`, `allDay`, "
                    + "`nb_person`, `nb_table`, `band`, `cost`, `participants`,"
                    + "`status` ,`user` ) "
                    + "VALUES (NULL, ?, NULL, NULL, NULL, "
                    + "?, ?, '0', ?,"
                    + " ?, ?, ?, NULL, ? , '0' )";

            PreparedStatement ps = connection.prepareStatement(req);
            ps.setString(1, event.getTitle());
            ps.setDate(2, event.getStartDate());
            ps.setDate(3, event.getEndDate());
            ps.setInt(4, event.getNbPerson());
            ps.setInt(5, event.getNbTable());
            ps.setInt(6, event.getBand());
            ps.setDouble(7, event.getCost());
            ps.setString(8, event.getStatus());

            ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void update(Event event) {
        try {
            

            String req = "UPDATE `event` SET `title` = ? WHERE `event`.`id` = ?;";
            PreparedStatement ps = connection.prepareStatement(req);
            ps.setString(1, event.getTitle());
            ps.setInt(2, event.getId());
            ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
    public void updateIntervalle(LocalDateTime st , LocalDateTime end , int id) {
        try {
            

            String req = "UPDATE `event` SET `startDatetime` = '"+st+"', "
                    + "`endDatetime` = '"+end+"' WHERE `event`.`id` = '"+id+"';";
            //String req = "update event set (title) values (?) where id = ?";
            PreparedStatement ps = connection.prepareStatement(req);
            
            ps.executeUpdate();
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
        
    }
    
    @Override
    public void delete(int id) {
        try {
            String req = "delete from event where id =?";
            PreparedStatement ps = connection.prepareStatement(req);
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
    public ObservableList<Event>  selectAllEventFrom() {
        ObservableList<Event> events = FXCollections.observableArrayList();
        String req ="select * from event";
        try {
            Statement statement = (Statement) connection.createStatement();
            ResultSet result = statement.executeQuery(req);
            while (result.next())
            {
                Event event = new Event(result.getInt("id"),
                        result.getString("title"), result.getInt("nb_person"),result.getDate("startDatetime"),
                        result.getDate("endDatetime"), result.getInt("nb_table"), result.getInt("band"),
                        result.getString("status"), result.getDouble("cost"));
                events.add(event);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(ProductService.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        return events;
    }

}
