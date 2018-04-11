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
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import entity.Contact;
import entity.Delivery;
import entity.Reservation;
import function.navigation;
import function.time;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import populator.DeliveryMaster;
import populator.ReservationMaster;
import service.ContactService;
import service.DeliveryService;
import util.DataSource;

/**
 *
 * @author USER
 */
public class DeliveryListController {

    @FXML
    private AnchorPane utama;
    @FXML
    private StackPane trans;
    @FXML
    private Group groups;
    @FXML
    private AnchorPane loadPane;
    @FXML
    private AnchorPane dataUangKeluar;
    @FXML
    private AnchorPane blur;
    @FXML
    private JFXButton btn_ubah;
    @FXML
    private JFXComboBox filter;
    @FXML
    private JFXTextField hari;
    @FXML
    private JFXDatePicker hari_pilih;
    @FXML
    private JFXComboBox bulan;
    @FXML
    private JFXTextField tahun;
    @FXML
    private JFXTextField cari;
    @FXML
    private TableView<DeliveryMaster> deliveryTable;
    @FXML
    private ContextMenu contextMenu;
    @FXML
    private TableColumn<DeliveryMaster, String> columnID;
    @FXML
    private TableColumn<DeliveryMaster, String> columnDeliveryDate;
    @FXML
    private TableColumn<DeliveryMaster, String> columnAdress;
    @FXML
    private TableColumn<DeliveryMaster, String> columnServiceType;
    @FXML
    private TableColumn<DeliveryMaster, String> columnEmail;
    @FXML
    private TableColumn<DeliveryMaster, String> columnNotes;
    @FXML
    private TableColumn<DeliveryMaster, String> columnStatus;
    ObservableList<String> comboFilter = FXCollections.observableArrayList("Day", "Month", "Year");
    ObservableList<String> comboBulan = FXCollections.observableArrayList("January", "February",
            "March", "April", "May", "June", "July", "August", "September", "October", "November", "December");
    SimpleDateFormat sdf = new SimpleDateFormat("dd MMMM yyyy");
    navigation nav = new navigation();
    time time = new time();
    DataSource kon = new DataSource();
    DeliveryService model = new DeliveryService();

    String id = "", deliveryDate = "", adress = "", serviceType = "", email = "", notes = "", status = "";

    @FXML
    private void tombolClose(ActionEvent event) {
        loadTable();
        blur.setEffect(null);
        new FadeOutLeftTransition(trans).play();
    }

    private void loadTable() {

        try {
            nav.animationFade(deliveryTable);
            columnID.setCellValueFactory(new PropertyValueFactory<>("id"));
            columnDeliveryDate.setCellValueFactory(new PropertyValueFactory<>("deliveryDate"));
            columnAdress.setCellValueFactory(new PropertyValueFactory<>("adress"));
            columnServiceType.setCellValueFactory(new PropertyValueFactory<>("serviceType"));
            columnEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
            columnNotes.setCellValueFactory(new PropertyValueFactory<>("notes"));
            columnStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
            DeliveryService delivery = new DeliveryService();
            List<Delivery> allReservations;
            allReservations = delivery.selectAll();
            List<DeliveryMaster> cm = allReservations
                    .stream().map(e -> new DeliveryMaster(e))
                    .collect(Collectors.toList());
            deliveryTable.setItems(FXCollections.observableArrayList(cm));
        } catch (Exception e) {
            System.out.println("error controller");;
        }

    }

    @FXML
    private void ubahClicked(ActionEvent event) {
    }

    @FXML
    private void filterClicked(ActionEvent event) {
    }

    @FXML
    private void dateClicked(ActionEvent event) {
        String dateText = sdf.format(Date.valueOf(hari_pilih.getValue()));
        hari.setText(dateText);
    }

    @FXML
    private void refreshClicked(ActionEvent event) {
        loadTable();
    }

    @FXML
    private void hapusClicked(ActionEvent event) {
    }

    @FXML
    private void ambilID(MouseEvent event) throws IOException {
        if (event.getClickCount() == 1) {
            id = Integer.toString(deliveryTable.getSelectionModel().getSelectedItem().getDelivery().getId());
            deliveryDate = deliveryTable.getSelectionModel().getSelectedItem().getDelivery().getDateDelivery().toString();
            adress = deliveryTable.getSelectionModel().getSelectedItem().getDelivery().getAdress();
            serviceType = deliveryTable.getSelectionModel().getSelectedItem().getDelivery().getServiceType();
            email = deliveryTable.getSelectionModel().getSelectedItem().getDelivery().getEmail();
            notes = deliveryTable.getSelectionModel().getSelectedItem().getDelivery().getNotes();
            status = Boolean.toString(deliveryTable.getSelectionModel().getSelectedItem().getDelivery().isStatus());
        } else if (event.getClickCount() == 2) {
            id = Integer.toString(deliveryTable.getSelectionModel().getSelectedItem().getDelivery().getId());
            deliveryDate = deliveryTable.getSelectionModel().getSelectedItem().getDelivery().getDateDelivery().toString();
            adress = deliveryTable.getSelectionModel().getSelectedItem().getDelivery().getAdress();
            serviceType = deliveryTable.getSelectionModel().getSelectedItem().getDelivery().getServiceType();
            email = deliveryTable.getSelectionModel().getSelectedItem().getDelivery().getEmail();
            notes = deliveryTable.getSelectionModel().getSelectedItem().getDelivery().getNotes();
            status = Boolean.toString(deliveryTable.getSelectionModel().getSelectedItem().getDelivery().isStatus());
            openUbah();
        } else if (event.getButton() == MouseButton.SECONDARY) {
            contextMenu.show(deliveryTable, event.getScreenX(), event.getScreenY());
        }

    }

    private void openUbah() throws IOException {
        FXMLLoader Loader = new FXMLLoader();
        Loader.setLocation(getClass().getResource(nav.getDeliveryUpdate()));
        blur.setEffect(new GaussianBlur(10));
        new FadeInRightTransition(trans).play();
        AnchorPane pane = Loader.load();
        DeliveryUpdateController DeliveryUpdate = Loader.getController();
        DeliveryUpdate.setData(id, status);
        loadPane.getChildren().setAll(pane);
    }

}
