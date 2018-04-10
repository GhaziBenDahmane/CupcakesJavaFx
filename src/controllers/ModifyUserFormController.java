/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import entity.User;
import java.net.URL;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import service.UserService;
import util.Util;

/**
 * FXML Controller class
 *
 * @author ding
 */
public class ModifyUserFormController implements Initializable {

    @FXML
    private AnchorPane pane;
    @FXML
    private JFXTextField username;
    @FXML
    private JFXPasswordField password;
    @FXML
    private JFXTextField email;
    @FXML
    private DatePicker lastLogin;
    @FXML
    private JFXComboBox<Label> role;
    @FXML
    private JFXTextField phone;
    @FXML
    private JFXTextField picture;
    UserService us = new UserService();
    public User selectedUser;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        selectedUser = UserListController.selectedUser.getUser();
        System.out.println(selectedUser.getLastLogin());
        //LocalDate localDateLastLogin = selectedUser.getLastLogin().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

        role.getItems().add(new Label("Admin"));
        role.getItems().add(new Label("User"));
        role.setPromptText("");
        username.setText(selectedUser.getUsername());

        email.setText(selectedUser.getEmail());
        phone.setText(selectedUser.getPhone());

        //lastLogin.setValue(localDateLastLogin);
        picture.setText(selectedUser.getPhotoprofil());
    }

    @FXML
    private void lastLoginAction(ActionEvent event) {
    }

    @FXML
    private void inputClicked(ActionEvent event) {
        selectedUser = UserListController.selectedUser.getUser();
        System.out.println(selectedUser);
        ArrayList<String> uroles = new ArrayList<>();
        String uusername = username.getText();
        String upassword = password.getText();
        String uemail = email.getText();
        LocalDate value = lastLogin.getValue();
        Date lastLoginDate = Date.from(value.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
        if (role.getValue() != null && role.getValue().getText().equals("Admin")) {
            uroles.add("ROLE_SIMPLE_USER");
            uroles.add("ADMIN");
            uroles.add("ROLE_SUPER_ADMIN");
        } else {
            uroles.add("ROLE_SIMPLE_USER");

        }
        String uphone = phone.getText();
        String upicture = picture.getText();
        String hashedpw = Util.hashpw(upassword);
        selectedUser.setUsername(uusername);
        selectedUser.setPassword(hashedpw);
        selectedUser.setEmail(uemail);
        selectedUser.setLastLogin(lastLoginDate);
        selectedUser.setRoles(uroles);
        selectedUser.setPhone(uphone);
        selectedUser.setPhotoprofil(upicture);
        us.update(selectedUser);
    }

}