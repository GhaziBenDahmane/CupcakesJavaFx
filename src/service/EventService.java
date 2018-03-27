/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import connection.DataSource;
import entity.Event;
import servicesInterfaces.IEventService;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

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
                    + "VALUES (NULL, '?', NULL, NULL, NULL, "
                    + "?', '?', '0', '10',"
                    + " '10', '0', '200', NULL, 'Pending', '0' )";

            PreparedStatement ps = connection.prepareStatement(req);
            ps.setString(2, event.getTitle());
            ps.setDate(6, event.getStartDate());
            ps.setDate(7, event.getEndDate());
            ps.setString(9, event.getNbPerson());
            ps.setString(10, event.getNbTable());
            ps.setString(11, event.getBand());
            ps.setString(12, event.getCost());
            ps.setString(14, event.getStatus());

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
