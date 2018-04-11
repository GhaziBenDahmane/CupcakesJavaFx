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
import static controllers.ActualityController.dia;
import entity.Promotion;
import function.navigation;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import service.PromotionService;

/**
 * FXML Controller class
 *
 * @author Yasmine
 */
public class PromotionController implements Initializable {

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
    @FXML
    private JFXButton btn_delete;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        id.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getId_promotion()).asObject());
        discount.setCellValueFactory(new PropertyValueFactory<>("discount"));
        startDate.setCellValueFactory(new PropertyValueFactory<>("starting_date"));
        endDate.setCellValueFactory(new PropertyValueFactory<>("ending_date"));
        tablePromotion.setEditable(true);
//         discount.setCellFactory(TextFieldTableCell.forTableColumn());
//        startDate.setCellFactory(TextFieldTableCell.forTableColumn());
//        endDate.setCellFactory(TextFieldTableCell.forTableColumn());
//

        buildData();
    }

    public void buildData() {
        PromotionService service = new PromotionService();

        ObservableList<Promotion> list;

        list = FXCollections.observableArrayList();
        list = service.selectAllPromotionFrom();
        tablePromotion.setItems(list);
        //   list = FXCollections.observableArrayList();
        // list= (ObservableList<Promotion>) service.selectAllPromotionFrom();
        //tablePromotion.setItems(list);
        /*  } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }*/
    }

    @FXML
    private void deleteClicked(ActionEvent event) {
        try {
            openUbah();
        } catch (IOException ex) {
            Logger.getLogger(PromotionController.class.getName()).log(Level.SEVERE, null, ex);
        }

        PromotionService ser = new PromotionService();
        ser.delete(tablePromotion.getSelectionModel().getSelectedItem().getId_promotion());
        tablePromotion.getItems().removeAll(tablePromotion.getSelectionModel().getSelectedItem());
    }

    private void openUbah() throws IOException {
        FXMLLoader Loader = new FXMLLoader();
        Loader.setLocation(getClass().getResource(nav.getUbahUangMasuk()));
        blur.setEffect(new GaussianBlur(10));
        new FadeInRightTransition(trans).play();
        AnchorPane pane = Loader.load();
        EventListController eventList = Loader.getController();
//        eventList.setData(title, nbPerson, endDate, nbTable, band, cost, startDate);
        loadPane.getChildren().setAll(pane);
    }

    @FXML
    private void addpromo(ActionEvent event) throws IOException {
//        blur.setEffect(new GaussianBlur(10));
//        new FadeInRightTransition(trans).play();
//        AnchorPane pane = FXMLLoader.load(getClass().getResource(nav.getAddPromotionForm()));
//        loadPane.getChildren().setAll(pane);

        AnchorPane node = (AnchorPane) FXMLLoader.load(getClass().getResource("/views/Addpromotion.fxml"));

        dia = new Stage();
        dia.initOwner((Stage) loadPane.getScene().getWindow());

        dia.initModality(Modality.APPLICATION_MODAL);
        dia.initStyle(StageStyle.TRANSPARENT);
        Scene scene = new Scene(node);
        dia.setScene(scene);

        dia.show();
    }

    // @FXML
//    private void changerDate(TableColumn.CellEditEvent edditedCell) {
//   Promotion EvSelect = tablePromotion.getSelectionModel().getSelectedItem();
//   Format formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//    String s = formatter.format(startDate);
//       EvSelect.setStarting_date(edditedCell.getNewValue().toString());
//         PromotionService ser= new PromotionService();
//        ser.updateSdate(edditedCell.getNewValue().toString(),EvSelect.getStarting_date());
//        System.out.println("Heelo");
//
//
//    }
}
