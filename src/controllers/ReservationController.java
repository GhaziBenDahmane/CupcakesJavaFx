/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import animation.FadeInRightTransition;
import animation.FadeOutLeftTransition;
import com.jfoenix.controls.JFXButton;
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
import populator.ReservationMaster;
import service.ReservationService;
import util.DataSource;

/**
 *
 * @author USER
 */
public class ReservationController {

    ObservableList<String> comboFilter = FXCollections.observableArrayList("Day", "Month", "Year");
    ObservableList<String> comboBulan = FXCollections.observableArrayList("January", "February",
            "March", "April", "May", "June", "July", "August", "September", "October", "November", "December");
    SimpleDateFormat sdf = new SimpleDateFormat("dd MMMM yyyy");

    @FXML
    private TableView<ReservationMaster> reservationTable;

    @FXML
    private TableColumn<ReservationMaster, String> columnID;

    @FXML
    private TableColumn<ReservationMaster, String> columnNbTable;

    @FXML
    private TableColumn<ReservationMaster, String> columnNbPerson;

    @FXML
    private TableColumn<ReservationMaster, String> columnDateReservation;

    @FXML
    private TableColumn<ReservationMaster, String> columnInputTime;

    //  private ObservableList<Reservation> data;
    @FXML
    private AnchorPane utama;

    @FXML
    private AnchorPane blur;

    @FXML
    private AnchorPane loadPane;

    @FXML
    private AnchorPane dataUangKeluar;

    @FXML
    private StackPane trans;

    @FXML
    private Group groups;

    @FXML
    private ContextMenu contextMenu;

    @FXML
    private CheckBox check;

    @FXML
    private ComboBox filter, bulan;

    @FXML
    private TextField hari, tahun, cari;

    @FXML
    private DatePicker hari_pilih;

    private String id = "", nbTable = "", nbPerson = "", dateReservation = "";

    DataSource kon = new DataSource();
    ReservationService model = new ReservationService();
    NumberFormat num = NumberFormat.getInstance();
    navigation nav = new navigation();
    time time = new time();
    @FXML
    private JFXButton btn_ubah;
    @FXML
    private JFXButton btn_tambah;

    private void setFilter() {
        filter.setValue("Hari");
        filter.setItems(comboFilter);
    }

    private void setHari() {
        hari.setText(time.tanggal());
        hari_pilih.setValue(LocalDate.parse(time.tanggalQuery()));
    }

    private void setBulan() {
        bulan.setValue(time.tanggalBulan());
        bulan.setItems(comboBulan);
    }

    private void setTahun() {
        tahun.setText(time.tanggalTahun());
    }

    /* private void setStyleTable() {
        columnID.setStyle("-fx-alignment: CENTER");
        columnNbTable.setStyle("-fx-alignment: CENTER");
        columnNbPerson.setStyle("-fx-alignment: CENTER");
        columnDateReservation.setStyle("-fx-alignment: CENTER");

    }*/
    @FXML
    private void ambilID(MouseEvent event) throws IOException {
        if (event.getClickCount() == 1) {
            id = Integer.toString(reservationTable.getSelectionModel().getSelectedItem().getReservation().getId());
            nbTable = Integer.toString(reservationTable.getSelectionModel().getSelectedItem().getReservation().getNbTables());
            nbPerson = Integer.toString(reservationTable.getSelectionModel().getSelectedItem().getReservation().getNbPersonnes());
            dateReservation = reservationTable.getSelectionModel().getSelectedItem().getReservation().getDateReservation().toString();
        } else if (event.getClickCount() == 2) {
            id = Integer.toString(reservationTable.getSelectionModel().getSelectedItem().getReservation().getId());
            nbTable = Integer.toString(reservationTable.getSelectionModel().getSelectedItem().getReservation().getNbTables());
            nbPerson = Integer.toString(reservationTable.getSelectionModel().getSelectedItem().getReservation().getNbPersonnes());
            dateReservation = reservationTable.getSelectionModel().getSelectedItem().getReservation().getDateReservation().toString();
            openUbah();
        } else if (event.getButton() == MouseButton.SECONDARY) {
            contextMenu.show(reservationTable, event.getScreenX(), event.getScreenY());
        }
    }

    private void loadTable() {

        try {
            nav.animationFade(reservationTable);
            columnID.setCellValueFactory(new PropertyValueFactory<>("id"));
            columnNbTable.setCellValueFactory(new PropertyValueFactory<>("nbTable"));
            columnNbPerson.setCellValueFactory(new PropertyValueFactory<>("nbPerson"));
            columnDateReservation.setCellValueFactory(new PropertyValueFactory<>("dateReservation"));
            // columnInputTime.setCellValueFactory(new PropertyValueFactory<>("inputTime"));
            ReservationService reservation = new ReservationService();
            List<Reservation> allReservations;
            allReservations = reservation.selectAll();
            List<ReservationMaster> cm = allReservations
                    .stream().map(e -> new ReservationMaster(e))
                    .collect(Collectors.toList());
            reservationTable.setItems(FXCollections.observableArrayList(cm));
        } catch (Exception e) {
            System.out.println("error controller");;
        }

    }

    private void clearParameter() {
        // id = "";nbTable = ""; nbPerson = "";dateReservation = "";
    }

    @FXML
    private void refreshClicked(ActionEvent event) {
        loadTable();
    }

    @FXML
    private void filterClicked(ActionEvent event) {
        if (filter.getSelectionModel().getSelectedItem().toString().equals("Hari")) {
            bulan.setVisible(false);
            hari.setVisible(true);
            hari_pilih.setVisible(true);
            tahun.setVisible(false);
            setHari();
        } else if (filter.getSelectionModel().getSelectedItem().toString().equals("Bulan")) {
            bulan.setVisible(true);
            hari.setVisible(false);
            hari_pilih.setVisible(false);
            tahun.setVisible(true);
            setBulan();
            setTahun();
        } else {
            bulan.setVisible(false);
            hari.setVisible(false);
            hari_pilih.setVisible(false);
            tahun.setVisible(false);
        }
    }

    @FXML
    private void dateClicked(ActionEvent event) {
        String dateText = sdf.format(Date.valueOf(hari_pilih.getValue()));
        hari.setText(dateText);
    }

    @FXML
    private void checkClicked(ActionEvent event) {
        if (check.isSelected() == true) {
            columnInputTime.setVisible(true);
        } else {
            columnInputTime.setVisible(false);
        }
    }

    public void initialize(URL url, ResourceBundle rb) {
        setFilter();
        setHari();
        setBulan();
        setTahun();
        // setStyleTable();

        refreshClicked(null);
    }

    @FXML
    private void tombolClose(ActionEvent event) {
        loadTable();
        blur.setEffect(null);
        new FadeOutLeftTransition(trans).play();
        clearParameter();
    }

    @FXML
    private void tambahClicked() throws IOException {
        blur.setEffect(new GaussianBlur(10));
        new FadeInRightTransition(trans).play();
        AnchorPane pane = FXMLLoader.load(getClass().getResource(nav.getReservationAdd()));
        loadPane.getChildren().setAll(pane);
    }

    @FXML
    private void ubahClicked(ActionEvent event) throws IOException {
        if (columnID.equals("")) {
            nav.showAlert(Alert.AlertType.WARNING, "WARNING", null, "Please select the data ..");
        } else {
            openUbah();
        }
    }

    @FXML
    private void hapusClicked(ActionEvent event) throws IOException {
        if (columnID.equals("")) {
            nav.showAlert(Alert.AlertType.WARNING, "WARNING", null, "Please select the data..");
        } else {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Delete Reservation");
            alert.setHeaderText("Nb Table\t\t: " + nbTable
                    + "\nb Person\t: " + nbPerson
            );
            alert.setContentText("Are you sure you want to delete this data ?");

            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
                model.delete(Integer.parseInt(id));
                if (model.getStatusDelete() == true) {
                    nav.showAlert(Alert.AlertType.INFORMATION, "Success", null, "Reservation Deleted..");
                    loadTable();
                    clearParameter();
                } else {
                    nav.showAlert(Alert.AlertType.ERROR, "Error", null, "Failed..");
                }
            }
        }
    }

    private void openUbah() throws IOException {
        FXMLLoader Loader = new FXMLLoader();
        Loader.setLocation(getClass().getResource(nav.getReservationUpdate()));
        blur.setEffect(new GaussianBlur(10));
        new FadeInRightTransition(trans).play();
        AnchorPane pane = Loader.load();
        ReservationUpdateController ReservationUpdate = Loader.getController();
        ReservationUpdate.setData(id, nbTable, nbPerson, dateReservation);
        loadPane.getChildren().setAll(pane);
    }

}
