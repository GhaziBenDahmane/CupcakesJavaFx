/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import util.DataSource;
import entity.Contact;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import servicesInterfaces.IContactService;

/**
 *
 * @author Anis-PC
 */
public class ContactService {

    private Connection connection = null;

    public ContactService() {
        connection = DataSource.getInstance().getConnection();

    }


    private String filter;
    private String rechercheEtat;

    private boolean statusInsert = false;
    private boolean statusUpdate = false;
    private boolean statusDelete = false;

    public void create(Contact contact) {
        String req = "INSERT INTO contact (firstName,adress,tel,message,status) VALUES (?,?,?,?,?)";
        try {
            PreparedStatement statment = connection.prepareStatement(req);
            statment.setString(1, contact.getFirstName());
            statment.setString(2, contact.getAdress());
            statment.setInt(3, contact.getPhone());
            statment.setString(4, contact.getMessage());
            statment.setBoolean(5, false);

            statment.execute();

            statusInsert = true;
        } catch (SQLException ex) {
            statusInsert = false;
        }

    }

    public boolean getStatusInert() {
        return statusInsert;
    }

    public List<Contact> selectAll() {
        List<Contact> contacs = new ArrayList<>();
        String req = "select id,firstName,adress,tel,message,status from contact";
        try {
            Statement statement = (Statement) connection.createStatement();
            ResultSet result = statement.executeQuery(req);
            while (result.next()) {

                Contact p = new Contact(result.getInt(1), result.getInt(4), result.getString(2), result.getString(3), result.getString(5), false);

                contacs.add(p);
            }

        } catch (SQLException ex) {
            System.out.println("select error");
        }

        return contacs;
    }

    public void update(Contact contact) {
        String req = "Update contact set firstName=?, adress=?,tel=? ,message=? , status=?  where id=?";
        try {
            PreparedStatement statment = connection.prepareStatement(req);
            statment.setString(1, contact.getFirstName());
            statment.setString(2, contact.getAdress());
            statment.setInt(3, contact.getPhone());
            statment.setString(4, contact.getMessage());
            statment.setBoolean(5, contact.isStatus());
            statment.setInt(6, contact.getId());
            statment.executeUpdate();
            statusUpdate = true;
        } catch (Exception e) {
            statusUpdate = false;
        }
    }

    public boolean getStatusUpdate() {
        return statusUpdate;
    }

    /*@Override
    public void update(Contact contact) {
         String req="Update contact set firstName=? , email=? , status=? ,adress=?, lastName=? where id=1";
        try {
            PreparedStatement statment = connection.prepareStatement(req);
            statment.setString(1,contact.getFirstName());
            statment.setString(2, contact.getEmail());
            statment.setBoolean(3, contact.isStatus());
            statment.setString(4,contact.getAdress());
            statment.setString(5,contact.getLastName());
            
            statment.executeUpdate();
              statusUpdate=true;
            
        } catch (SQLException ex) {
              statusUpdate=false;
        }
    }
     public boolean getStatusUpdate(){
        return statusUpdate;
    }
     */
    public void delete(int id) {
        String req = "Delete from contact where id=?";
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
