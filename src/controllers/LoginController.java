/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import animation.FadeInRightTransition;
import animation.FadeOutLeftTransition;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import entity.User;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import service.NotificationService;
import service.UserService;
import util.Util;
import util.Views;

/**
 * FXML Controller class
 *
 * @author Anis-PC
 */
public class LoginController implements Initializable {

    @FXML
    private AnchorPane effectFade;
    @FXML
    private AnchorPane loginForm;
    @FXML
    private AnchorPane Header;
    @FXML
    private Label exit;
    @FXML
    private JFXTextField username;
    @FXML
    private JFXPasswordField password;
    @FXML
    private JFXButton login_btn;
    @FXML
    private JFXButton register_btn;
    @FXML
    private StackPane trans;
    @FXML
    private Group groups;
    @FXML
    private AnchorPane loadPane;
    @FXML
    private AnchorPane blur;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void register(ActionEvent event) throws IOException {

        blur.setEffect(new GaussianBlur(10));
        new FadeInRightTransition(trans).play();
        AnchorPane pane = FXMLLoader.load(getClass().getResource(Views.REGISTER));
        loadPane.getChildren().setAll(pane);
    }

    @FXML
    private void login(ActionEvent event) {
        UserService us = new UserService();
        User user = us.login(username.getText(), password.getText());
        if (user != null) {
            NotificationService.successBlueNotification("login Successful", "Welcome " + user.getUsername());
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            FXMLLoader loader = new FXMLLoader(getClass().getResource(Views.HOME));
            try {
                Pane pane = (Pane) loader.load();
                stage.setTitle("Accueil");
                Scene scene = new Scene(pane);
                scene.setFill(javafx.scene.paint.Color.TRANSPARENT);
                stage.centerOnScreen();
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
            }
        } else {
            Util.showError("Wrong username/password combination");

        }
    }

    @FXML
    private void tombolClose(ActionEvent event) {
        blur.setEffect(null);
        new FadeOutLeftTransition(trans).play();
    }

}
