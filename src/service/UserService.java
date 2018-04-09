/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import cupcakesjavafx.CupCakesJavaFx;
import entity.User;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import servicesInterfaces.CrudService;
import util.DataSource;
import util.Util;

/**
 *
 * @author ding
 */
public class UserService implements CrudService<User> {

    private PreparedStatement ste;
    private final Connection connection;

    public UserService() {
        connection = DataSource.getInstance().getConnection();
    }

    public User login(String username, String pw) {
        if (username.trim().isEmpty() || pw.trim().isEmpty()) {
            return null;
        }
        String request = "SELECT * FROM `fos_user` WHERE `username` = ?";
        try {
            ste = connection.prepareStatement(request);
            ste.setString(1, username);
            ResultSet rs = ste.executeQuery();
            if (rs.next()) {
                String hashedpw = rs.getString("password");
                if (Util.checkpw(pw, hashedpw)) {
                    System.out.println("Logged in!");
                    CupCakesJavaFx.loggedUser = fromRs(rs);
                    return CupCakesJavaFx.loggedUser;

                }
            }
        } catch (Exception e) {
        }

        return null;
    }

    public void logout() {
        CupCakesJavaFx.loggedUser = null;

    }

    @Override
    public List<User> getAll() {
        List<User> users = new ArrayList();
        String request = "SELECT * FROM fos_user";
        try {
            ste = connection.prepareStatement(request);
            ResultSet rs = ste.executeQuery();
            while (rs.next()) {
                users.add(fromRs(rs));
            }
        } catch (SQLException ex) {
        }

        return users;
    }

    @Override
    public User get(int id) {
        String request = "SELECT * FROM fos_user WHERE id = ?";
        try {
            ste = connection.prepareStatement(request);
            ste.setInt(1, id);
            ResultSet rs = ste.executeQuery();
            if (rs.next()) {
                return fromRs(rs);
            }
        } catch (SQLException ex) {
        }

        return null;
    }

    @Override
    public List<User> getBy(String param, String val) {
        List<User> users = new ArrayList();

        String request = "SELECT * FROM fos_user WHERE " + param + " = ?";
        try {
            ste = connection.prepareStatement(request);
            ste.setString(1, val);
            ResultSet rs = ste.executeQuery();
            if (rs.next()) {
                users.add(fromRs(rs));
            }
        } catch (SQLException ex) {
        }

        return users;
    }

    @Override
    public void add(User u) {

        String request = "INSERT INTO `fos_user` "
                + "(`username`, `username_canonical`, `email`, `email_canonical`, "
                + "`enabled`, `password`,`roles`, `phone`, `picture`, `points`) "
                + "VALUES (?, ?, ?, ?, '1', ?,?, ?, ?,0);";
        try {
            ste = connection.prepareStatement(request);
            ste.setString(1, u.getUsername());
            ste.setString(2, u.getUsername().toLowerCase());
            ste.setString(3, u.getEmail());
            ste.setString(4, u.getEmail().toLowerCase());
            ste.setString(5, u.getPassword());
            ste.setString(6, Util.arrayToString(u.getRoles()));
            ste.setString(7, u.getPhone());
            ste.setString(8, u.getPhotoprofil());
            ste.executeUpdate();
        } catch (Exception e) {
            System.err.println(e);
        }
    }

    @Override
    public void update(User u) {
        String req = "UPDATE `fos_user` SET `username`=?, `username_canonical`=?, `email`=?, `email_canonical`=?, "
                + "`enabled`=?, `password`=?,`roles`=?, `phone`=?, `picture`=?, `points`=0"
                + "WHERE id=?";
        try {
            PreparedStatement statment = connection.prepareStatement(req);
            ste.setString(1, u.getUsername());
            ste.setString(2, u.getUsername().toLowerCase());
            ste.setString(3, u.getEmail());
            ste.setString(4, u.getEmail().toLowerCase());
            ste.setString(5, u.getPassword());
            ste.setString(6, Util.arrayToString(u.getRoles()));
            ste.setString(7, u.getPhone());
            ste.setString(8, u.getPhotoprofil());
            ste.executeUpdate();

        } catch (Exception e) {
            System.err.println(e);

        }
    }

    @Override
    public void delete(User a) {
        deleteId(a.getId());
    }

    @Override
    public void deleteId(int a) {
        String req = "DELETE from `fos_user` WHERE id= ?";
        try {
            PreparedStatement statment = connection.prepareStatement(req);
            statment.setInt(1, a);
            statment.executeUpdate();

        } catch (SQLException ex) {
        }
    }

    public static void main(String[] args) {
        UserService x = new UserService();
        x.login("ghazi", "ghazi");
        System.out.println(CupCakesJavaFx.loggedUser);
    }

    @Override
    public User fromRs(ResultSet rs) {
        try {
            User u = new User(rs.getInt("id"),
                    rs.getString("username"),
                    rs.getString("email"),
                    rs.getBoolean("enabled"),
                    rs.getString("salt"),
                    rs.getString("password"),
                    rs.getDate("last_login"),
                    rs.getString("confirmation_token"),
                    rs.getDate("password_requested_at"),
                    Util.stringToArray(rs.getString("roles")),
                    rs.getInt("points"),
                    rs.getString("phone"),
                    rs.getString("picture"));
            return u;

        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
