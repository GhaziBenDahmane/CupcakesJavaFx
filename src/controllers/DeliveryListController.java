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
import entity.Delivery;
import function.navigation;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import populator.DeliveryMaster;
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
    private JFXComboBox bulan;
    @FXML
    private JFXDatePicker date;
    @FXML
    private JFXTextField search;
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
    @FXML
    private TableColumn<DeliveryMaster, String> columnName;
    @FXML
    private TableColumn<DeliveryMaster, String> columnPhone;
    @FXML
    private TableColumn<DeliveryMaster, String> columnContactTime;

    private List<DeliveryMaster> cm;
    private DeliveryService cs;
    ObservableList<String> comboFilter = FXCollections.observableArrayList("Day", "Month", "Year");
    ObservableList<String> comboBulan = FXCollections.observableArrayList("January", "February",
            "March", "April", "May", "June", "July", "August", "September", "October", "November", "December");
    SimpleDateFormat sdf = new SimpleDateFormat("dd MMMM yyyy");
    navigation nav = new navigation();
    DataSource kon = new DataSource();
    DeliveryService model = new DeliveryService();

    String id = "",name="",phone="",contactTime="", deliveryDate = "", adress = "", serviceType = "", email = "", notes = "", status = "";
    @FXML
    private JFXTextField Date;
    @FXML
    private JFXTextField tahun;

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
            columnName.setCellValueFactory(new PropertyValueFactory<>("name"));
            columnPhone.setCellValueFactory(new PropertyValueFactory<>("phone"));
            columnContactTime.setCellValueFactory(new PropertyValueFactory<>("contactTime"));
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
    private void filterClicked() {
        String searchText = search.getText().toLowerCase();
        LocalDate time = date.getValue();
        Stream<DeliveryMaster> stream = cm.stream();
        if (!search.getText().isEmpty()) {
            stream = stream
                    .filter(e -> e.getDelivery().getAdress().toLowerCase().contains(searchText)
                    || e.getDelivery().getEmail().toLowerCase().contains(searchText)
                    || e.getDelivery().getNotes().toLowerCase().contains(searchText)
                    || e.getDelivery().getServiceType().toLowerCase().contains(searchText));
            deliveryTable.setItems(stream.collect(Collectors.collectingAndThen(Collectors.toList(), l -> FXCollections.observableArrayList(l))));
        }

    }

    @FXML
    private void dateClicked(ActionEvent event) {
        String time = date.getValue().toString();
        Date.setText(time);

    }
    
    public void initialize(URL url, ResourceBundle rb) {
        // setStyleTable();
        loadTable();
    }

    @FXML
    private void refreshClicked(ActionEvent event) {
        loadTable();
    }

    @FXML
    private void ambilID(MouseEvent event) throws IOException {
        if (event.getClickCount() == 1) {
            id = Integer.toString(deliveryTable.getSelectionModel().getSelectedItem().getDelivery().getId());
            status = Boolean.toString(deliveryTable.getSelectionModel().getSelectedItem().getDelivery().isStatus());
        } else if (event.getClickCount() == 2) {
            id = Integer.toString(deliveryTable.getSelectionModel().getSelectedItem().getDelivery().getId());
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

    @FXML
    private void ubahClicked(ActionEvent event) {
    }

    @FXML
    private void hapusClicked(ActionEvent event) {
    }

}
