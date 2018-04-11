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
import entity.Event;
import function.navigation;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import service.EventService;
import util.Views;

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
    private TableColumn<Event,String> id;
    @FXML
    private TableColumn<Event,String> title;
    @FXML
    private TableColumn<Event,String> nbPerson;
    private TableColumn<Event,String> endDate;
    @FXML
    private TableColumn<Event,String> nbTable;
    @FXML
    private TableColumn<Event,String> band;
    @FXML
    private TableColumn<Event,String> status;
    private TableColumn<Event,String> cost;
    @FXML
    private TableColumn<Event,String> startDate;

    
    
    EventService service = new EventService();
    @FXML
    private JFXButton btn_tambah;
    @FXML
    private JFXButton btn_update;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
           id.setCellValueFactory(new PropertyValueFactory<>("id"));
           title.setCellValueFactory(new PropertyValueFactory<>("title"));
           nbPerson.setCellValueFactory(new PropertyValueFactory<>("nbPerson"));
           nbTable.setCellValueFactory(new PropertyValueFactory<>("nbTable"));
           band.setCellValueFactory(new PropertyValueFactory<>("band"));
           status.setCellValueFactory(new PropertyValueFactory<>("status"));
           startDate.setCellValueFactory(new PropertyValueFactory<>("startDate"));
           tableEvent.setItems(getEvent());    
           
           

    }    
    
     public ObservableList<Event> getEvent()
         {
            EventService service = new EventService();
            ObservableList<Event> list;
            list = FXCollections.observableArrayList();
            list =  service.selectAllEventFrom();
            return list;
         }

        

     @FXML
    private void addClicked() throws IOException{
        blur.setEffect(new GaussianBlur(10));
        new FadeInRightTransition(trans).play();
        AnchorPane pane = FXMLLoader.load(getClass().getResource(Views.EVENT_ADD));
        loadPane.getChildren().setAll(pane);
    }

  
       @FXML
    private void tombolClose(ActionEvent event){
        tableEvent.setItems(getEvent());    
        blur.setEffect(null);
        new FadeOutLeftTransition(trans).play();
    }
   @FXML
    private void updateClicked(ActionEvent event) throws IOException {
           blur.setEffect(new GaussianBlur(10));
        new FadeInRightTransition(trans).play();
        AnchorPane pane = FXMLLoader.load(getClass().getResource(Views.EVENT_ADD));
        loadPane.getChildren().setAll(pane);
    }
    
   public void changerIntitule(TableColumn.CellEditEvent edditedCell) {
        Event EvSelect = tableEvent.getSelectionModel().getSelectedItem();
        EvSelect.setTitle(edditedCell.getNewValue().toString());
        service.update(EvSelect);
    }
   
    @FXML
    private void deleteClicked(ActionEvent event) {
     Event evSelect = tableEvent.getSelectionModel().getSelectedItem();
     service.delete(evSelect.getId());
     tableEvent.getItems().remove(evSelect);

        
    }
}
