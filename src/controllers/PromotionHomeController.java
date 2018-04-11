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
import entity.Promotion;
import function.navigation;
import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import service.PromotionService;

/**
 * FXML Controller class
 *
 * @author Yasmine
 */
public class PromotionHomeController implements Initializable {

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
    private TableView<Promotion> tablePromotion;
    @FXML
    private ContextMenu contextMenu;
    @FXML
    private TableColumn<Promotion, Date> startDate;
    navigation nav = new navigation();
    @FXML
    private JFXButton btn_add;
    @FXML
    private TableColumn<Promotion, Integer> id;
    @FXML
    private TableColumn<Promotion, Date> endDate;
    @FXML
    private TableColumn<Promotion, String> discount;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        id.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getId_promotion()).asObject());
        discount.setCellValueFactory(new PropertyValueFactory<>("discount"));
        startDate.setCellValueFactory(new PropertyValueFactory<>("starting_date"));
        endDate.setCellValueFactory(new PropertyValueFactory<>("ending_date"));
        buildData();
    }

    public void buildData() {
        PromotionService service = new PromotionService();

        ObservableList<Promotion> list;

        list = FXCollections.observableArrayList();
        list = service.selectAllPromotionFrom();
        tablePromotion.setItems(list);

    }
}
