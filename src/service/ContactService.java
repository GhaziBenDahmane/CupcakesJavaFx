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
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import servicesInterfaces.IContactService;

/**
 *
 * @author Anis-PC
 */
public class ContactService implements IContactService{

    private Connection connection=null;

    public ContactService() {
         connection = DataSource.getInstance().getConnection();
         
    }
    
    

    @Override
    public void create(Contact contact) {
                String req="INSERT INTO contact (firstName,email,status,adress,lastName,phone,message) VALUES (?,?,?,?,?,?,?)";
        try {
            PreparedStatement statment = connection.prepareStatement(req);
            statment.setString(1,contact.getFirstName() );
            statment.setString(2, contact.getEmail());
            statment.setBoolean(3, contact.isStatus());
            statment.setString(4,contact.getAdress());
            statment.setString(5, contact.getLastName());
            statment.setInt(6,contact.getPhone());
            statment.setString(7, contact.getMessage());
            statment.execute();
            
        } catch (SQLException ex) {
            Logger.getLogger(ContactService.class.getName()).log(Level.SEVERE, null, ex);
        }
       
    }

    @Override
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
            
        } catch (SQLException ex) {
            Logger.getLogger(ContactService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void delete(int id) {
        String req="Delete from contact where id= ?";
        try {
            PreparedStatement statment = connection.prepareStatement(req);
            statment.setInt(1,id);
            statment.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(ContactService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
