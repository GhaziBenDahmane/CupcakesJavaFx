/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import entity.Contact;
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
public class ReservationService {
   

    private Connection connection=null;

    public ReservationService() {
         connection = DataSource.getInstance().getConnection();
         
    }
    
     private String query="select id,nbTable,nbPerson,dateReservation "
            +"DATE_FORMAT(input_time,'%d %M %Y %T') from contact ";
    private String filter;
    private String detailCari;
    public String queryLoad="";
    

    
    private boolean statusInsert = false;
    private boolean statusUpdate = false;
    private boolean statusDelete = false;
    
    public void filterHari(String hari, String cari){
        filter=" where tanggal='"+hari+"'";
        detailCari=" and detail like '%"+cari+"%'";
        queryLoad=query+filter+detailCari;
    }
    
    public void filterBulan(String bulan, String tahun, String cari){
        filter=" where DATE_FORMAT(tanggal,'%M/%Y')='"+bulan+"/"+tahun+"'";
        detailCari=" and detail like '%"+cari+"%'";
        queryLoad=query+filter+detailCari;
    }
    
    public void filterSemua(String cari){
        detailCari=" where detail like '%"+cari+"%'";
        queryLoad=query+detailCari;
    }

    

  
    public void create(Reservation reser) {
                String req="INSERT INTO reservation (nbTable,nbPerson,dateReservation) VALUES (?,?,?)";
        try {
            PreparedStatement statment = connection.prepareStatement(req);
            statment.setInt(1,reser.getNbPersonnes());
            statment.setInt(2, reser.getNbTables());
            statment.setDate(3, reser.getDateReservation());
    
            statment.execute();
 
            statusInsert=true;
        } catch (SQLException ex) {
            statusInsert=false;
        }
        
       
    }
        public boolean getStatusInert(){
        return statusInsert;
    }
     public List<Reservation> selectAll() {
         List<Reservation> reservation = new ArrayList<>();
        String req ="select * from reservation";
        try {
            Statement statement = (Statement) connection.createStatement();
            ResultSet result = statement.executeQuery(req);
            while (result.next())
            {
               
                Reservation r = new Reservation(result.getInt(1),result.getInt(2),result.getInt(3),result.getDate(4));
               
                reservation.add(r);
            }
            
        } catch (SQLException ex) {
            System.out.println("select error");
        }
        
        
        return reservation;
    }
     public void update(String id, String email, String firstName, String lastName){
       String req="Update reservation set nbTable=? , nbPerson=? ,dateReservation=? where id=1";
         try {
             PreparedStatement statment = connection.prepareStatement(req);
            statment.executeUpdate("update contact set firstName='"+firstName+
                    "',lastName='"+lastName+
                    "',email='"+email+"' where id='"+id+"'");
            statusUpdate=true;
        } catch (Exception e) {
            statusUpdate=false;
        }
    }
    
    public boolean getStatusUpdate(){
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
        String req="Delete from reservation where id= ?";
        try {
            PreparedStatement statment = connection.prepareStatement(req);
            statment.setInt(1,id);
            statment.executeUpdate();
            statusDelete=true;
        } catch (SQLException ex) {
            statusDelete=false;
        }
    }
       public boolean getStatusDelete(){
        return statusDelete;
    }
    
}
