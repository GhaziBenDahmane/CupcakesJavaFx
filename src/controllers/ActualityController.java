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
public class ActualityController implements Initializable {

    /*
     * Initializes the controller class.
     */
    @FXML
    private StackPane trans;
    @FXML
    private Group groups;
    @FXML
    private AnchorPane loadPane;
    public static AnchorPane loadPaneAfter;
    @FXML
    private AnchorPane dataUangMasuk;
    @FXML
    private AnchorPane blur;
    @FXML
    private JFXComboBox<?> bulan;
    @FXML
    private JFXTextField tahun;
    @FXML
    private TableView<Actuality> tableActuality;
    @FXML
    private ContextMenu contextMenu;

    @FXML
    private TableColumn<Actuality, Integer> id;
    @FXML
    private TableColumn<Actuality, String> title;
    @FXML
    private TableColumn<Actuality, String> content;
    @FXML
    private TableColumn<Actuality, String> photo;
    @FXML
    private TableColumn<Actuality, Date> dateA;
    public static Stage dia;

    navigation nav = new navigation();
    @FXML
    private JFXComboBox<?> filter;
    @FXML
    private JFXTextField Date;
    @FXML
    private JFXDatePicker date;
    @FXML
    private JFXButton Refresh;
    @FXML
    private JFXTextField search;
    @FXML
    private JFXButton btn_add_actuality;
    @FXML
    private JFXButton btn_delete;

    public void buildData() {

        ActualityService ser = new ActualityService();
        ObservableList<Actuality> list;
       list = FXCollections.observableArrayList();
        list = ser.selectAllActualityFrom();
        tableActuality.setItems(list);
        /*  } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }*/
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        id.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getId()).asObject());
        title.setCellValueFactory(new PropertyValueFactory<>("title"));
        content.setCellValueFactory(new PropertyValueFactory<>("content"));
        photo.setCellValueFactory(new PropertyValueFactory<>("photo"));
        dateA.setCellValueFactory(new PropertyValueFactory<>("date"));
        tableActuality.setEditable(true);
        title.setCellFactory(TextFieldTableCell.forTableColumn());
        content.setCellFactory(TextFieldTableCell.forTableColumn());
        photo.setCellFactory(TextFieldTableCell.forTableColumn());
        
        
         
            

        buildData();        
        ;
//         tableActuality.setEditable(true);
//        tableActuality.getSelectionModel().setCellSelectionEnabled(true);  // selects cell only, not the whole row
//        tableActuality.setOnMouseClicked(new EventHandler<MouseEvent>() {
//            @Override
//            public void handle(MouseEvent click) {
//                if (click.getClickCount() == 2) {
//                    @SuppressWarnings("rawtypes")
//                    TablePosition pos = tableActuality.getSelectionModel().getSelectedCells().get(0);
//                    int row = pos.getRow();
//                    int col = pos.getColumn();
//                    @SuppressWarnings("rawtypes")
//                    TableColumn column = pos.getTableColumn();
//                    String val = column.getCellData(row).toString();
//                   // System.out.println("Selected Value, " + val + ", Column: " + col + ", Row: " + row);
//                   
//                    
//                    if (col == 1) {
//                        
//                        
//                        System.out.println(val);
//                    }
//                    if (col == 2) {
//                    }
//                    if (col == 3) {
//                    }
//                    if (col == 4) {
//                    }
//                }
//            }
//        });


    }

    @FXML
    private void deleteClicked(ActionEvent event) throws IOException {
ActualityService ser= new ActualityService();
        ser.delete(tableActuality.getSelectionModel().getSelectedItem().getId());
         tableActuality.getItems().removeAll(tableActuality.getSelectionModel().getSelectedItem());
    }

    private void setData(TableColumn<Actuality, String> title, TableColumn<Actuality, String> content, TableColumn<Actuality, String> photo, TableColumn<Actuality, Date> dateA) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @FXML
    private void AddActualityClicked(ActionEvent event) throws IOException {

        AnchorPane node = (AnchorPane) FXMLLoader.load(getClass().getResource("/views/AddActuality.fxml"));

        dia = new Stage();
        dia.initOwner((Stage) loadPane.getScene().getWindow());

        dia.initModality(Modality.APPLICATION_MODAL);
        dia.initStyle(StageStyle.TRANSPARENT);
        Scene scene = new Scene(node);
        dia.setScene(scene);

        dia.show();

    }

    @FXML
    private void changerTitle(TableColumn.CellEditEvent edditedCell) {
   Actuality EvSelect = tableActuality.getSelectionModel().getSelectedItem();
       EvSelect.setTitle(edditedCell.getNewValue().toString());
         ActualityService ser= new ActualityService();
        ser.updateTitle(edditedCell.getNewValue().toString(),EvSelect.getId());
        System.out.println("Heelo");
   
       
    }
     @FXML
    private void changerContent(TableColumn.CellEditEvent edditedCell) {
   Actuality EvSelect = tableActuality.getSelectionModel().getSelectedItem();
       EvSelect.setTitle(edditedCell.getNewValue().toString());
         ActualityService ser= new ActualityService();
        ser.updateTitle(edditedCell.getNewValue().toString(),EvSelect.getId());
        System.out.println("Heelo");
   
       
    }
     @FXML
    private void changerPhoto(TableColumn.CellEditEvent edditedCell) {
   Actuality EvSelect = tableActuality.getSelectionModel().getSelectedItem();
       EvSelect.setTitle(edditedCell.getNewValue().toString());
         ActualityService ser= new ActualityService();
        ser.updateTitle(edditedCell.getNewValue().toString(),EvSelect.getId());
        System.out.println("Heelo");
   
       
    }
//     @FXML
    //private void changerDate(TableColumn.CellEditEvent edditedCell) throws ParseException {
//        
//        String string = edditedCell.getNewValue().toString();
//        DateFormat format = new SimpleDateFormat("MMMM d, yyyy", Locale.ENGLISH);
//        Date date = (Date) format.parse(string);
//   Actuality EvSelect = tableActuality.getSelectionModel().getSelectedItem();
//       EvSelect.setDate(date);
//         ActualityService ser= new ActualityService();
//        ser.updateDate(date,EvSelect.getId());
//        System.out.println("Heelo");
//   
//       
//    }
}

