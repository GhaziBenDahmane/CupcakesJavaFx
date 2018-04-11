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

    private String query = "select id,nbTable,nbPerson,dateReservation "
            + "DATE_FORMAT(input_time,'%d %M %Y %T') from contact ";
    private String filter;
    private String detailCari;
    public String queryLoad = "";

    private boolean statusInsert = false;
    private boolean statusUpdate = false;
    private boolean statusDelete = false;

    public void create(Delivery d) {
        String req = "INSERT INTO delivery (dateDelivery,email,adress,status,service_type,notes) VALUES (?,?,?,?,?,?)";
        try {
            PreparedStatement statment = connection.prepareStatement(req);
            statment.setDate(1, d.getDateDelivery());
            statment.setString(2, d.getEmail());
            statment.setString(3, d.getAdress());
            statment.setBoolean(4, false);
            statment.setString(5, d.getServiceType());
            statment.setString(6, d.getNotes());

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

                Delivery r = new Delivery(result.getInt(1), result.getString(3), result.getString(4), result.getString(5), result.getString(7), result.getDate(2), result.getBoolean(6));

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
