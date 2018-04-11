/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import entity.User;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import service.NotificationService;
import service.UserService;
import util.Util;

/**
 * FXML Controller class
 *
 * @author ding
 */
public class RegisterFormController implements Initializable {

    @FXML
    private AnchorPane pane;
    @FXML
    private JFXTextField username;
    @FXML
    private JFXPasswordField password;
    @FXML
    private JFXTextField email;
    @FXML
    private JFXTextField phone;
    @FXML
    private JFXTextField picture;
    @FXML
    private JFXPasswordField password1;
    UserService us = new UserService();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void inputClicked(ActionEvent event) {
        if (password1.getText().equals(password.getText())
                && !username.getText().isEmpty()
                && !password.getText().isEmpty()
                && !phone.getText().isEmpty()
                && Util.validateEmail(email.getText())) {
            ArrayList<String> uroles = new ArrayList<>();
            String uusername = username.getText();
            String upassword = password.getText();
            String uemail = email.getText();
            Date lastLoginDate = new Date();

            uroles.add("ROLE_SIMPLE_USER");

            String uphone = phone.getText();
            String hashedpw = Util.hashpw(upassword);
            User aa = new User();
            aa.setUsername(uusername);
            aa.setPassword(hashedpw);
            aa.setEmail(uemail);
            aa.setLastLogin(lastLoginDate);
            aa.setRoles(uroles);
            aa.setPhone(uphone);
            us.add(aa);
            NotificationService.successBlueNotification("Registration Successful", "Welcome " + aa.getUsername());

        } else {
            Util.showError("Check your data!");

        }

    }

}
