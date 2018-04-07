/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import com.fasterxml.jackson.databind.deser.impl.PropertyValue;
import entity.Event;
import java.net.URL;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Observable;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author haffe
 */
public class EventListController implements Initializable {

    @FXML
    private TableView<Event> Etable;
    @FXML
    private TableColumn<Event,String> title;
    @FXML
    private TableColumn<Event,String> nbPerson;
    @FXML
    private TableColumn<Event,String> endDate;
    @FXML
    private TableColumn<Event,String> nbTable;
    @FXML
    private TableColumn<Event,String> band;
    @FXML
    private TableColumn<Event,String> status;
    @FXML
    private TableColumn<Event,String> cost;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

            // Set up the columns in the table
           title.setCellValueFactory(new PropertyValueFactory<>("title"));
           nbPerson.setCellValueFactory(new PropertyValueFactory<>("nbPerson"));
           endDate.setCellValueFactory(new PropertyValueFactory<>("endDate"));
           nbTable.setCellValueFactory(new PropertyValueFactory<>("nbTable"));
           band.setCellValueFactory(new PropertyValueFactory<>("band"));
           status.setCellValueFactory(new PropertyValueFactory<>("status"));
           cost.setCellValueFactory(new PropertyValueFactory<>("nbPerson"));
           // Load data
           
           Etable.setItems(getEvent());    
                    
    } 
         public ObservableList<Event> getEvent()
         {
            ObservableList<Event> events = FXCollections.observableArrayList();
            Date mnt = new Date(System.currentTimeMillis());
            events.add(new Event("title", "nbPerson", mnt, mnt, "nbTable", "band", "status", "cost"));
            return events;
         }

    
}
