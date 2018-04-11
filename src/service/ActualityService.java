/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import entity.Actuality;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import util.DataSource;

public class ActualityService {

    private final Connection connection;

    public ActualityService() {
        connection = DataSource.getInstance().getConnection();
    }

    public void add(Actuality actuality) {
        String req = "INSERT INTO actuality (title, content , photo, date) VALUES (?,?,?,?)";
        try {
            PreparedStatement statment = connection.prepareStatement(req);

            statment.setString(1, actuality.getTitle());
            statment.setString(2, actuality.getContent());
            statment.setString(3, actuality.getPhoto());

            statment.setDate(4, actuality.getDate());

            statment.execute();

        } catch (SQLException ex) {
            Logger.getLogger(ProductService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void updateTitle(String title, int id) {

        try {
            Statement ste = connection.createStatement();
            String req = "UPDATE actuality SET title='" + title + "' WHERE id='" + id + "'";
            ste.executeUpdate(req);
        } catch (SQLException ex) {
            Logger.getLogger(ActualityService.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void delete(int id) {
        try {
            String req = "delete from actuality where id =?";
            PreparedStatement ps = connection.prepareStatement(req);
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException ex) {
        }
    }

    public void updateContent(String content, int id) {

        try {
            Statement ste = connection.createStatement();
            String req = "UPDATE actuality SET content='" + content + "' WHERE id='" + id + "'";
            ste.executeUpdate(req);
        } catch (SQLException ex) {
            Logger.getLogger(ActualityService.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void updatePhoto(String photo, int id) {

        try {
            Statement ste = connection.createStatement();
            String req = "UPDATE actuality SET photo='" + photo + "' WHERE id='" + id + "'";
            ste.executeUpdate(req);
        } catch (SQLException ex) {
            Logger.getLogger(ActualityService.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public ObservableList<Actuality> selectAllActualityFrom() {
        ObservableList<Actuality> actualities = FXCollections.observableArrayList();
        String req = "select * from actuality";
        try {
            Statement statement = (Statement) connection.createStatement();
            ResultSet result = statement.executeQuery(req);
            while (result.next()) {
                Actuality actuality = new Actuality(
                        result.getInt("id"), result.getString("title"), result.getString("content"),
                        result.getString("photo"),
                        result.getDate("date"));

                actualities.add(actuality);

            }

        } catch (SQLException ex) {
            Logger.getLogger(ProductService.class.getName()).log(Level.SEVERE, null, ex);
        }

        return actualities;
    }

    public void updateDate(Date date, int id) {

        try {
            Statement ste = connection.createStatement();
            String req = "UPDATE actuality SET date='" + date + "' WHERE id='" + id + "'";
            ste.executeUpdate(req);
        } catch (SQLException ex) {
            Logger.getLogger(ActualityService.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
