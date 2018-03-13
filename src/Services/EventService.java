/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Connection.DataSource;
import entities.event.Event;
import servicesInterfaces.IEventService;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Haroun
 */
public class EventService implements IEventService{
    
    private Connection connection;

    public EventService() {
        connection = DataSource.getInstance().getConnection();
        }

    @Override
    public void add(Event event) {
        try {
            String req = "INSERT INTO `event` (`id`, `title`, `description`, `startDate`, `endDate`, `location` , `ratingEvent` ) VALUES (NULL, ?, ?, ?, ?, ?, ?);";
            PreparedStatement ps = connection.prepareStatement(req);
            ps.setString(1, event.getTitle());

           
            
            
            
            ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void update(Event event) {
        try {
            String req = "update event set (title, description) values (?,?) where id = ?";
            PreparedStatement ps = connection.prepareStatement(req);
            ps.setString(1, event.getTitle());

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

  
    
    
    
    
    


   
    
    
    
}