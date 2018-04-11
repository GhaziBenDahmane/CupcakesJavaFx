/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import animation.FadeInRightTransition;
import animation.FadeInTransition;
import animation.FadeOutLeftTransition;
import com.jfoenix.controls.JFXButton;
import cupcakesjavafx.CupCakesJavaFx;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import util.Views;

/**
 * FXML Controller class
 *
 * @author ding
 */
public class HomeController implements Initializable {

    @FXML
    private StackPane trans;
    @FXML
    private AnchorPane loadPane;
    @FXML
    private AnchorPane blur;
    @FXML
    private Label emailUser;
    @FXML
    private ImageView imageUser;
    @FXML
    private Label tanggal;
    @FXML
    private Label jam;
    @FXML
    private Label usernameUser;
    @FXML
    private Label idUser;
    @FXML
    private Label levelUser;
    @FXML
    private VBox admin;
    @FXML
    private JFXButton btn_home1;
    @FXML
    private VBox user;
    @FXML
    private JFXButton btn_home11;
    @FXML
    private JFXButton btn_laporan_uang_keluar_bln1;
    @FXML
    private AnchorPane rootPane;
    @FXML
    private Label userName;
    @FXML
    private JFXButton claims;
    @FXML
    private JFXButton btn_user_management1;
    @FXML
    private JFXButton btn_claim_management;
    @FXML
    private JFXButton btn_trainning_management1;
    @FXML
    private JFXButton btn_send_mail;
    @FXML
    private JFXButton event;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        userName.setText(CupCakesJavaFx.loggedUser.getUsername());
        emailUser.setText(CupCakesJavaFx.loggedUser.getEmail());
        if (CupCakesJavaFx.loggedUser.isAdmin()) {
            System.out.println("you are admin");
            admin.setVisible(true);
            user.setVisible(false);
        } else {
            System.out.println("you not admin");

            admin.setVisible(false);
            user.setVisible(true);
        }
    }

    @FXML
    private void tombolClose(ActionEvent event) {
        System.out.println("called");
        blur.setEffect(null);
        new FadeOutLeftTransition(trans).play();
    }

    @FXML
    private void imageHover(MouseEvent event) {
    }

    @FXML
    private void userClicked(MouseEvent event) throws IOException {
        blur.setEffect(new GaussianBlur(10));
        new FadeInRightTransition(trans).play();
        AnchorPane pane = FXMLLoader.load(getClass().getResource(Views.USER_PROFILE));
        loadPane.getChildren().setAll(pane);
    }

    @FXML
    private void logout(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Logout");
        alert.setHeaderText(null);
        alert.setContentText("Do you confirm ?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            CupCakesJavaFx.loggedUser = null;
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            FXMLLoader loader = new FXMLLoader(getClass().getResource(Views.LOGIN));
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
        }

    }

    @FXML
    private void homeClicked(ActionEvent event) {
    }

    @FXML
    private void laporanUangKeluarBulananClicked(ActionEvent event) {
    }

    @FXML
    private void setBackgroundHome1(MouseEvent event) {
    }

    @FXML
    private void setBackgroundUserManagement(MouseEvent event) {
    }

    @FXML
    private void userManagementClicked(ActionEvent event) {
        try {
            rootPane.getChildren().clear();
            rootPane.setOpacity(0);
            new FadeInTransition(rootPane).play();
            AnchorPane pane = FXMLLoader.load(getClass().getResource(Views.USERS_LIST));
            rootPane.getChildren().setAll(pane);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @FXML
    private void setBackgroundHomeUser(MouseEvent event) {
    }

    @FXML
    private void setBackgroundUangKeluarHarianUser(MouseEvent event) {
    }

    @FXML
    private void setBackgroundUangKeluarBulananUser(MouseEvent event) {
    }

    @FXML
    private void showClaims(ActionEvent event) {
        try {
            rootPane.getChildren().clear();
            rootPane.setOpacity(0);
            new FadeInTransition(rootPane).play();
            AnchorPane pane = FXMLLoader.load(getClass().getResource(Views.USER_CLAIM_LIST));
            rootPane.getChildren().setAll(pane);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @FXML
    private void claimManagementClicked(ActionEvent event) {
        try {
            rootPane.getChildren().clear();
            rootPane.setOpacity(0);
            new FadeInTransition(rootPane).play();
            AnchorPane pane = FXMLLoader.load(getClass().getResource(Views.ADMIN_CLAIMS_LIST));
            rootPane.getChildren().setAll(pane);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @FXML
    private void trainManagementClicked(ActionEvent event) {
        try {
            rootPane.getChildren().clear();
            rootPane.setOpacity(0);
            new FadeInTransition(rootPane).play();
            AnchorPane pane = FXMLLoader.load(getClass().getResource(Views.TRAINNING_LIST));
            rootPane.getChildren().setAll(pane);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @FXML
    private void sendMailClicked(ActionEvent event) {
        try {
            rootPane.getChildren().clear();
            rootPane.setOpacity(0);
            new FadeInTransition(rootPane).play();
            AnchorPane pane = FXMLLoader.load(getClass().getResource(Views.SEND_MAIL));
            rootPane.getChildren().setAll(pane);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @FXML
    private void showEvent(ActionEvent event) {
        try {
            rootPane.getChildren().clear();
            rootPane.setOpacity(0);
            new FadeInTransition(rootPane).play();
            AnchorPane pane = FXMLLoader.load(getClass().getResource(Views.EVENT_LIST));
            rootPane.getChildren().setAll(pane);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @FXML
    private void showMap(ActionEvent event) {
        try {
            rootPane.getChildren().clear();
            rootPane.setOpacity(0);
            new FadeInTransition(rootPane).play();
            AnchorPane pane = FXMLLoader.load(getClass().getResource(Views.GMAPS));
            rootPane.getChildren().setAll(pane);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

}
