/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import entity.Contact;
import entity.Delivery;
import entity.Reservation;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import util.DataSource;

/**
 *
 * @author USER
 */
public class DeliveryService {

    private Connection connection = null;

    public DeliveryService() {
        connection = DataSource.getInstance().getConnection();

    }


    private boolean statusInsert = false;
    private boolean statusUpdate = false;
    private boolean statusDelete = false;

    public void create(Delivery d) {
        String req = "INSERT INTO delivery (name,phone,dateDelivery,contactTime,email,adress,status,service_type,notes) VALUES (?,?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement statment = connection.prepareStatement(req);
            statment.setString(1, d.getName());
            statment.setInt(2, d.getPhone());
            statment.setDate(3, d.getDateDelivery());
            statment.setDate(4, d.getContactTime());
            statment.setString(5, d.getEmail());
            statment.setString(6, d.getAdress());
            statment.setBoolean(7, false);
            statment.setString(8, d.getServiceType());
            statment.setString(9, d.getNotes());

            statment.execute();

            statusInsert = true;
        } catch (SQLException ex) {
            statusInsert = false;
        }

    }

    public boolean getStatusInert() {
        return statusInsert;
    }

    public List<Delivery> selectAll() {
        List<Delivery> reservation = new ArrayList<>();
        String req = "select * from delivery";
        try {
            Statement statement = (Statement) connection.createStatement();
            ResultSet result = statement.executeQuery(req);
            while (result.next()) {

                Delivery r = new Delivery(result.getInt(1), result.getInt(3),
                        result.getString(2), result.getString(6), result.getString(7),result.getString(8),result.getString(10),
                        result.getDate(4),result.getDate(5), result.getBoolean(9));

                reservation.add(r);
            }

        } catch (SQLException ex) {
            System.out.println("select error");
        }

        return reservation;
    }

    public void update(Delivery d) {
        String req = "Update delivery set status=?  where id=?";
        try {
            PreparedStatement statment = connection.prepareStatement(req);
            statment.setBoolean(1, d.isStatus());
            statment.setInt(2, d.getId());
            statment.executeUpdate();
            statusUpdate = true;
        } catch (Exception e) {
            statusUpdate = false;
        }
    }

    public boolean getStatusUpdate() {
        return statusUpdate;
    }

    public void delete(int id) {
        String req = "Delete from delivery where id= ?";
        try {
            PreparedStatement statment = connection.prepareStatement(req);
            statment.setInt(1, id);
            statment.executeUpdate();
            statusDelete = true;
        } catch (SQLException ex) {
            statusDelete = false;
        }
    }

    public boolean getStatusDelete() {
        return statusDelete;
    }

}
