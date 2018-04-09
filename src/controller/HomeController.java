/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

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
    private VBox operator;
    @FXML
    private JFXButton btn_home;
    @FXML
    private JFXButton btn_uang_masuk;
    @FXML
    private JFXButton btn_uang_keluar;
    @FXML
    private JFXButton btn_laporan_uang_masuk_hr;
    @FXML
    private JFXButton btn_laporan_uang_masuk_bln;
    @FXML
    private JFXButton btn_laporan_uang_keluar_hr;
    @FXML
    private JFXButton btn_laporan_uang_keluar_bln;
    @FXML
    private VBox admin;
    @FXML
    private JFXButton btn_home1;
    @FXML
    private JFXButton btn_user_management;
    @FXML
    private VBox user;
    @FXML
    private JFXButton btn_home11;
    @FXML
    private JFXButton btn_laporan_uang_masuk_hr1;
    @FXML
    private JFXButton btn_laporan_uang_masuk_bln1;
    @FXML
    private JFXButton btn_laporan_uang_keluar_hr1;
    @FXML
    private JFXButton btn_laporan_uang_keluar_bln1;
    @FXML
    private AnchorPane rootPane;
    @FXML
    private Label userName;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        userName.setText(CupCakesJavaFx.loggedUser.getUsername());
        emailUser.setText(CupCakesJavaFx.loggedUser.getEmail());
    }

    @FXML
    private void tombolClose(ActionEvent event) {
    }

    @FXML
    private void imageHover(MouseEvent event) {
    }

    @FXML
    private void userClicked(MouseEvent event) {
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
    private void setBackgroundHome(MouseEvent event) {
    }

    @FXML
    private void homeClicked(ActionEvent event) {
    }

    @FXML
    private void setBackgroundUangMasuk(MouseEvent event) {
    }

    @FXML
    private void uangMasukClicked(ActionEvent event) {
    }

    @FXML
    private void setBackgroundUangKeluar(MouseEvent event) {
    }

    @FXML
    private void uangKeluarClicked(ActionEvent event) {
    }

    @FXML
    private void setBackgroundUangMasukHarian(MouseEvent event) {
    }

    @FXML
    private void laporanUangMasukHarianClicked(ActionEvent event) {
    }

    @FXML
    private void setBackgroundUangMasukBulanan(MouseEvent event) {
    }

    @FXML
    private void laporanUangMasukBulananClicked(ActionEvent event) {
    }

    @FXML
    private void setBackgroundUangKeluarHarian(MouseEvent event) {
    }

    @FXML
    private void laporanUangKeluarHarianClicked(ActionEvent event) {
    }

    @FXML
    private void setBackgroundUangKeluarBulanan(MouseEvent event) {
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
    }

    @FXML
    private void setBackgroundHomeUser(MouseEvent event) {
    }

    @FXML
    private void setBackgroundUangMasukHarianUser(MouseEvent event) {
    }

    @FXML
    private void setBackgroundUangMasukBulananUser(MouseEvent event) {
    }

    @FXML
    private void setBackgroundUangKeluarHarianUser(MouseEvent event) {
    }

    @FXML
    private void setBackgroundUangKeluarBulananUser(MouseEvent event) {
    }

}
