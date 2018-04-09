/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import animasi.FadeInRightTransition;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import entity.Event;
import function.navigation;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import service.EventService;

/**
 * FXML Controller class
 *
 * @author haffe
 */
public class EventListController implements Initializable {

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
    private JFXTextField Date;
    @FXML
    private JFXDatePicker date;
    @FXML
    private JFXComboBox<?> bulan;
    @FXML
    private JFXTextField tahun;
    @FXML
    private JFXTextField search;
    @FXML
    private TableView<Event> tableEvent;
    @FXML
    private ContextMenu contextMenu;
    @FXML
    private TableColumn<Event, String> title;
    @FXML
    private TableColumn<Event, String> nbPerson;
    private TableColumn<Event, String> endDate;
    @FXML
    private TableColumn<Event, String> nbTable;
    @FXML
    private TableColumn<Event, String> band;
    @FXML
    private TableColumn<Event, String> status;
    private TableColumn<Event, String> cost;
    @FXML
    private TableColumn<Event, String> startDate;

    navigation nav = new navigation();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        title.setCellValueFactory(new PropertyValueFactory<>("title"));
        nbPerson.setCellValueFactory(new PropertyValueFactory<>("nbPerson"));
        nbTable.setCellValueFactory(new PropertyValueFactory<>("nbTable"));
        band.setCellValueFactory(new PropertyValueFactory<>("band"));
        status.setCellValueFactory(new PropertyValueFactory<>("status"));
        startDate.setCellValueFactory(new PropertyValueFactory<>("startDate"));
        tableEvent.setItems(getEvent());

    }

    @FXML

    public ObservableList<Event> getEvent() {
        EventService service = new EventService();
        ObservableList<Event> list;
        list = FXCollections.observableArrayList();
        list = service.selectAllEventFrom();
        return list;
    }

    @FXML
    private void deleteClicked(ActionEvent event) throws IOException {

        openUbah();

    }

    private void openUbah() throws IOException {
        FXMLLoader Loader = new FXMLLoader();
        Loader.setLocation(getClass().getResource(nav.getUbahUangMasuk()));
        blur.setEffect(new GaussianBlur(10));
        new FadeInRightTransition(trans).play();
        AnchorPane pane = Loader.load();
        EventListController eventList = Loader.getController();
        eventList.setData(title, nbPerson, endDate, nbTable, band, cost, startDate);
        loadPane.getChildren().setAll(pane);
    }

    public void setData(String title, String nbPerson, String endDate, String nbTable, String band, String cost, String startDate) {

        this.title.setText(title);
        this.nbPerson.setText(nbPerson);
        this.endDate.setText(endDate);
        this.nbTable.setText(nbTable);
        this.band.setText(band);
        this.cost.setText(cost);
        this.startDate.setText(startDate);

    }

    @FXML
    private void tambahClicked() throws IOException {
        blur.setEffect(new GaussianBlur(10));
        new FadeInRightTransition(trans).play();
        AnchorPane pane = FXMLLoader.load(getClass().getResource(nav.getAddEventForm()));
        loadPane.getChildren().setAll(pane);
    }

    private void setData(TableColumn<Event, String> title, TableColumn<Event, String> nbPerson, TableColumn<Event, String> endDate, TableColumn<Event, String> nbTable, TableColumn<Event, String> band, TableColumn<Event, String> cost, TableColumn<Event, String> startDate) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
