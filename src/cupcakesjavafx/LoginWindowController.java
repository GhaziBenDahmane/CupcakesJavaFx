/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cupcakesjavafx;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import service.UserService;

/**
 * FXML Controller class
 *
 * @author ding
 */
public class LoginWindowController implements Initializable {

    @FXML
    private TextField password;
    @FXML
    private TextField username;
    @FXML
    private Button loginButton;
    @FXML
    private Label errors;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void loginAction(ActionEvent event) {
        UserService us = new UserService();
        us.login(username.getText(), password.getText());
    }

}
