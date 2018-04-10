/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import animation.FadeInRightTransition;
import animation.FadeOutLeftTransition;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import entity.Formation;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import service.FormationService;
import util.Views;

/**
 * FXML Controller class
 *
 * @author ding
 */
public class TrainningListController implements Initializable {

    @FXML
    private StackPane trans;
    @FXML
    private Group groups;
    @FXML
    private AnchorPane loadPane;
    @FXML
    private AnchorPane dataUangMasuk;
    @FXML
    private AnchorPane blur;
    @FXML
    private JFXComboBox<?> filter;
    @FXML
    private JFXComboBox<?> bulan;
    @FXML
    private JFXTextField tahun;
    @FXML
    private JFXButton refreshbtn;
    @FXML
    private TableView<Formation> tableView;
    @FXML
    private ContextMenu contextMenu;
    @FXML
    private TableColumn<?, ?> idC;
    @FXML
    private TableColumn<?, ?> Nom;
    @FXML
    private TableColumn<?, ?> Video;
    @FXML
    private TableColumn<?, ?> DateD;
    @FXML
    private TableColumn<?, ?> DateF;
    @FXML
    private TableColumn<?, ?> Status;
    public ArrayList<Formation> ran;
    @FXML
    private JFXButton add_btn;
    @FXML
    private JFXButton update_btn;
    @FXML
    private JFXButton delete_btn;
    public static Formation selectedItem;
    @FXML
    private JFXButton video_btn;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        afficherFormation();
        // TODO
    }

    @FXML
    private void tombolClose(ActionEvent event) {
        blur.setEffect(null);
        new FadeOutLeftTransition(trans).play();
        afficherFormation();
    }

    @FXML
    private void comboBoxChanged(ActionEvent event) {
    }

    @FXML
    private void refreshClicked(ActionEvent event) {
        afficherFormation();
    }

    @FXML
    private void deleteClicked(ActionEvent event) throws SQLException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation suppression");
        alert.setHeaderText("voulez vous effacer cette Formation ?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            int r = tableView.getSelectionModel().getSelectedItem().getId();

            FormationService s = new FormationService();
            s.supprimerFormation(r);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("Delete succes!");
            alert.show();

            afficherFormation();
        }
    }

    public void afficherFormation() {
        FormationService fs = new FormationService();
        idC.setCellValueFactory(new PropertyValueFactory<>("id"));
        Nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        Video.setCellValueFactory(new PropertyValueFactory<>("video"));

        DateD.setCellValueFactory(new PropertyValueFactory<>("start_date_formation"));
        DateF.setCellValueFactory(new PropertyValueFactory<>("end_date_formation"));
        Status.setCellValueFactory(new PropertyValueFactory<>("status"));

        try {
            tableView.setItems(FXCollections.observableArrayList(fs.AfficherFormation()));
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }

    @FXML
    private void addClicked(ActionEvent event) throws IOException {
        blur.setEffect(new GaussianBlur(10));
        new FadeInRightTransition(trans).play();
        AnchorPane pane = FXMLLoader.load(getClass().getResource(Views.ADD_TRAINNING));
        loadPane.getChildren().setAll(pane);
    }

    @FXML
    private void updateClicked(ActionEvent event) throws IOException {
        selectedItem = tableView.getSelectionModel().getSelectedItem();
        blur.setEffect(new GaussianBlur(10));
        new FadeInRightTransition(trans).play();
        AnchorPane pane = FXMLLoader.load(getClass().getResource(Views.UPDATE_TRAINNING));
        loadPane.getChildren().setAll(pane);
    }

    @FXML
    private void videoClicked(ActionEvent event) throws IOException {
        selectedItem = tableView.getSelectionModel().getSelectedItem();
        blur.setEffect(new GaussianBlur(10));
        new FadeInRightTransition(trans).play();
        AnchorPane pane = FXMLLoader.load(getClass().getResource(Views.VIDEO_TRAINNING));
        loadPane.getChildren().setAll(pane);

    }
}
