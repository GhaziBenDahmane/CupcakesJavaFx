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
import service.NotificationService;
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
        username.setText(selectedUser.getUsername());

        email.setText(selectedUser.getEmail());
        phone.setText(selectedUser.getPhone());

        //lastLogin.setValue(localDateLastLogin);
    }

    @FXML
    private void lastLoginAction(ActionEvent event) {
    }

    @FXML
    private void inputClicked(ActionEvent event) {
        selectedUser = UserListController.selectedUser.getUser();
        ArrayList<String> uroles = new ArrayList<>();
        String uusername = username.getText();
        String uemail = email.getText();
        LocalDate value = lastLogin.getValue();
        if (value != null) {
            Date lastLoginDate = Date.from(value.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
            selectedUser.setLastLogin(lastLoginDate);

        }
        if (role.getValue() != null) {
            if (role.getValue().getText().equals("Admin")) {
                uroles.add("ROLE_SIMPLE_USER");
                uroles.add("ADMIN");
                uroles.add("ROLE_SUPER_ADMIN");
            } else {
                uroles.add("ROLE_SIMPLE_USER");

            }
        }
        String uphone = phone.getText();
        selectedUser.setUsername(uusername);
        selectedUser.setEmail(uemail);
        selectedUser.setRoles(uroles);
        selectedUser.setPhone(uphone);
        if (!password.getText().isEmpty()) {
            String upassword = password.getText();
            String hashedpw = Util.hashpw(upassword);
            selectedUser.setPassword(hashedpw);

        }
        us.update(selectedUser);
        Util.showInfo("Modification done");
        NotificationService.successBlueNotification("User modified!", "User modified!");

    }

}
