/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import animation.FadeInRightTransition;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import entity.Event;
import entity.Pastry;
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
import service.PastryService;
import util.Views;

/**
 * FXML Controller class
 *
 * @author haffez
 */
public class PastryListController implements Initializable {

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
    private JFXButton btn_tambah;
    @FXML
    private JFXButton btn_update;
    @FXML
    private TableView<Pastry> tableEvent;
    @FXML
    private ContextMenu contextMenu;
    @FXML
    private TableColumn<Pastry, String> id;
    @FXML
    private TableColumn<Pastry, String> address;
    @FXML

    private TableColumn<Pastry, String> nb_table;
    PastryService service = new PastryService();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
           id.setCellValueFactory(new PropertyValueFactory<>("id"));
           address.setCellValueFactory(new PropertyValueFactory<>("address"));
           nb_table.setCellValueFactory(new PropertyValueFactory<>("nb_table"));
           tableEvent.setItems(getPastry()); 
    }    

    @FXML
    private void addClicked(ActionEvent event) throws IOException {
        
        blur.setEffect(new GaussianBlur(10));
        new FadeInRightTransition(trans).play();
        AnchorPane pane = FXMLLoader.load(getClass().getResource(Views.PASTRY_ADD));
        loadPane.getChildren().setAll(pane);
    }

    @FXML
    private void updateClicked(ActionEvent event) {
    }

    @FXML
    private void deleteClicked(ActionEvent event) {
            Pastry pSelect = tableEvent.getSelectionModel().getSelectedItem();
            service.delete(pSelect.getId());
            tableEvent.getItems().remove(pSelect);
    }
     public ObservableList<Pastry> getPastry()
         {
            PastryService service = new PastryService();
            ObservableList<Pastry> list;
            list = FXCollections.observableArrayList();
            list =  service.selectAllPastry();
            return list;
         }
}
