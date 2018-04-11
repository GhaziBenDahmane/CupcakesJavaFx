/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import entity.Actuality;
import function.navigation;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import service.ActualityService;

/**
 * FXML Controller class
 *
 * @author Yasmine
 */
public class ActualityHomeController implements Initializable {

    @FXML
    private TableView<Actuality> tableActuality;
    @FXML
    private TableColumn<Actuality, Integer> id;
    @FXML
    private TableColumn<Actuality, String> title;
    @FXML
    private TableColumn<Actuality, String> content;
   
    @FXML
    private TableColumn<Actuality, Date> dateA;
    @FXML
    private TableColumn<Actuality,String> photo;
   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       id.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getId()).asObject());
        title.setCellValueFactory(new PropertyValueFactory<>("title"));
        content.setCellValueFactory(new PropertyValueFactory<>("content"));
        photo.setCellValueFactory(new PropertyValueFactory<>("photo"));
        dateA.setCellValueFactory(new PropertyValueFactory<>("dateA"));
         buildData();
         
         
         photo.setCellValueFactory(new PropertyValueFactory<>("photo"));
    }    
    
    public void buildData() {

        ActualityService ser = new ActualityService();
        ObservableList<Actuality> list;
       list = FXCollections.observableArrayList();
        list = ser.selectAllActualityFrom();
        tableActuality.setItems(list);
     }




}

