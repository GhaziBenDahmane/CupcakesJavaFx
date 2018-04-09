/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import entity.Claim;
import entity.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import servicesInterfaces.CrudService;
import util.DataSource;

/**
 *
 * @author ding
 */
public class ClaimService implements CrudService<Claim> {

    private PreparedStatement ste;
    private final Connection connection;

    public ClaimService() {
        connection = DataSource.getInstance().getConnection();

    }

    @Override
    public List<Claim> getAll() {
        List<Claim> claims = new ArrayList();
        String request = "SELECT * FROM claim";
        try {
            ste = connection.prepareStatement(request);

            ResultSet rs = ste.executeQuery();
            while (rs.next()) {
                claims.add(fromRs(rs));
            }
        } catch (SQLException ex) {
        }
        return claims;
    }

    @Override
    public Claim get(int id) {
        String request = "SELECT * FROM claim WHERE id = ?";
        try {
            ste = connection.prepareStatement(request);
            ste.setInt(1, id);
            ResultSet rs = ste.executeQuery();
            while (rs.next()) {
                return fromRs(rs);
            }
        } catch (SQLException ex) {
        }
        return null;
    }

    @Override
    public List<Claim> getBy(String param, String val) {
        List<Claim> claims = new ArrayList();

        String request = "SELECT * FROM claim WHERE " + param + " = ?";
        try {
            ste = connection.prepareStatement(request);
            ste.setString(1, val);
            System.out.println(ste.toString());
            ResultSet rs = ste.executeQuery();
            if (rs.next()) {
                claims.add(fromRs(rs));
            }
        } catch (SQLException ex) {
        }

        return claims;
    }

    @Override
    public void add(Claim a) {
        String request = "INSERT INTO `claim` "
                + "(`id``client_id``answered_by_id``description``answer``posted_on``answered`) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?);";
        try {
            ste = connection.prepareStatement(request);
            ste.setInt(1, a.getId());
            ste.setInt(2, a.getClient().getId());
            ste.setInt(3, a.getAnswer().isEmpty() ? null : a.getAnsweredBy().getId());
            ste.setString(4, a.getDescription());
            ste.setString(5, a.getAnswer());
            ste.setDate(6, new java.sql.Date(a.getPostedOn().getTime()));
            ste.setBoolean(7, !a.getAnswer().isEmpty());
            ste.executeUpdate();
        } catch (Exception e) {
            System.err.println(e);
        }
    }

    @Override
    public void update(Claim a) {
        String request = "UPDATE `claim` SET `client_id`=?, `answered_by_id`=?, `description`=?, `answer`=?, "
                + "`posted_on`=?, `answered`=?"
                + "WHERE id=?";
        try {
            ste = connection.prepareStatement(request);
            ste.setInt(1, a.getClient().getId());
            ste.setInt(2, a.getAnswer().isEmpty() ? null : a.getAnsweredBy().getId());
            ste.setString(3, a.getDescription());
            ste.setString(4, a.getAnswer());
            ste.setDate(5, new java.sql.Date(a.getPostedOn().getTime()));
            ste.setBoolean(6, !a.getAnswer().isEmpty());
            ste.setInt(7, a.getId());
            ste.executeUpdate();
        } catch (Exception e) {
            System.err.println(e);
        }
    }

    @Override
    public void delete(Claim a) {
        deleteId(a.getId());
    }

    public void deleteId(int a) {
        String req = "DELETE from claim WHERE id= ?";
        try {
            PreparedStatement statment = connection.prepareStatement(req);
            statment.setInt(1, a);
            statment.executeUpdate();

        } catch (SQLException ex) {
            System.err.println(ex);
        }
    }

    public void Claim(int id, User client, User answeredBy, String description, String answer, Date postedOn, boolean answered) {
    }

    @Override
    public Claim fromRs(ResultSet rs) {
        UserService us = new UserService();
        try {
            return new Claim(rs.getInt("id"),
                    us.get(rs.getInt("client_id")),
                    us.get(rs.getInt("answered_by_id")),
                    rs.getString("description"),
                    rs.getString("answer"),
                    rs.getDate("posted_on"),
                    rs.getBoolean("answered"));

        } catch (SQLException ex) {
        }
        return null;
    }

    public static void main(String[] args) {
        ClaimService c = new ClaimService();
        System.out.println(c.getBy("type", "Technical Problem"));
    }
}
