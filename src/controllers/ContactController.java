/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import animation.FadeInRightTransition;
import animation.FadeOutLeftTransition;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import entity.Contact;
import function.navigation;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import populator.ContactMaster;
import service.ContactService;
import util.DataSource;

public class ContactController implements Initializable {

    ObservableList<String> comboFilter = FXCollections.observableArrayList("Day", "Month", "Year");
    ObservableList<String> comboBulan = FXCollections.observableArrayList("January", "February",
            "March", "April", "May", "June", "July", "August", "September", "October", "November", "December");
    SimpleDateFormat sdf = new SimpleDateFormat("dd MMMM yyyy");

    @FXML
    private TableView<ContactMaster> tableContact;

    @FXML
    private TableColumn<ContactMaster, String> columnID;

    @FXML
    private TableColumn<ContactMaster, String> columnFirstName;

    @FXML
    private TableColumn<ContactMaster, String> columnAdress;
    @FXML
    private TableColumn<ContactMaster, String> columnEmail;

    @FXML
    private TableColumn<ContactMaster, String> columnPhone;
    @FXML
    private TableColumn<ContactMaster, String> columnMessage;


    @FXML
    private AnchorPane blur;

    @FXML
    private AnchorPane loadPane;

    @FXML
    private StackPane trans;

    @FXML
    private Group groups;

    @FXML
    private ContextMenu contextMenu;

    private CheckBox check;

    @FXML
    private ComboBox month;
    @FXML
    private TextField year;

    private String id = "", firstName = "", adress = "", phone = "", message = "";
    public static ContactMaster sselectedItem;
    DataSource kon = new DataSource();
    ContactService model = new ContactService();
    navigation nav = new navigation();
    @FXML
    private AnchorPane utama;
    @FXML
    private AnchorPane dataUangKeluar;
    @FXML
    private JFXButton btn_add;
    @FXML
    private JFXButton btn_update;
    @FXML
    private JFXTextField search;


    private void loadTable() {
        try {
            nav.animationFade(tableContact);
            columnID.setCellValueFactory(new PropertyValueFactory<>("id"));
            columnFirstName.setCellValueFactory(new PropertyValueFactory<>("firstName"));
            columnEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
            columnAdress.setCellValueFactory(new PropertyValueFactory<>("adress"));
            columnPhone.setCellValueFactory(new PropertyValueFactory<>("phone"));
            columnMessage.setCellValueFactory(new PropertyValueFactory<>("message"));
            
            //  columnInputTime.setCellValueFactory(new PropertyValueFactory<>("inputTimes"));

            ContactService contact = new ContactService();
            List<Contact> allContacts;
            allContacts = contact.selectAll();
            List<ContactMaster> cm = allContacts
                    .stream().map(e -> new ContactMaster(e))
                    .collect(Collectors.toList());
            tableContact.setItems(FXCollections.observableArrayList(cm));
        } catch (Exception e) {
            System.out.println("error controller");;
        }
    }

    private void clearParameter() {
       //  id="";   firstName=""; adress="";message="";phone="";
    }

    @FXML
    private void refreshClicked(ActionEvent event) {
        loadTable();
    }



    @FXML
    private void ambilID(MouseEvent event) throws IOException {
        if (event.getClickCount() == 1) {
            id = Integer.toString(tableContact.getSelectionModel().getSelectedItem().getContact().getId());
            firstName = tableContact.getSelectionModel().getSelectedItem().getContact().getFirstName();
            adress = tableContact.getSelectionModel().getSelectedItem().getContact().getAdress();
            phone = Integer.toString(tableContact.getSelectionModel().getSelectedItem().getContact().getPhone());
            message = tableContact.getSelectionModel().getSelectedItem().getContact().getMessage();
        } else if (event.getClickCount() == 2) {
            id = Integer.toString(tableContact.getSelectionModel().getSelectedItem().getContact().getId());
            firstName = tableContact.getSelectionModel().getSelectedItem().getContact().getFirstName();
            adress = tableContact.getSelectionModel().getSelectedItem().getContact().getAdress();
            phone = Integer.toString(tableContact.getSelectionModel().getSelectedItem().getContact().getPhone());
            message = tableContact.getSelectionModel().getSelectedItem().getContact().getMessage();
            openUbah();
        } else if (event.getButton() == MouseButton.SECONDARY) {
            contextMenu.show(tableContact, event.getScreenX(), event.getScreenY());
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // setStyleTable();
        loadTable();
    }

    @FXML
    private void tombolClose(ActionEvent event) {
        loadTable();
        blur.setEffect(null);
        new FadeOutLeftTransition(trans).play();
        clearParameter();
    }

    @FXML
    private void addClicked() throws IOException {
        blur.setEffect(new GaussianBlur(10));
        new FadeInRightTransition(trans).play();
        AnchorPane pane = FXMLLoader.load(getClass().getResource(nav.getContactAdd()));
        loadPane.getChildren().setAll(pane);

    }

    private void updateClicked(ActionEvent event) throws IOException {
        if (adress.equals("")) {
            nav.showAlert(Alert.AlertType.WARNING, "WARNING", null, "Please select the data..");
        } else {
            openUbah();
        }
    }

    @FXML
    private void deleteClicked(ActionEvent event) throws IOException {
        if (adress.equals("")) {
            nav.showAlert(Alert.AlertType.WARNING, "WARNING", null, "Please select the data..");
        } else {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Delete Contact");
            alert.setHeaderText("First Name\t: " + firstName
                  
                    + "Adress: " + adress);
            alert.setContentText("Are you sure you want to delete this data?");

            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
                model.delete(Integer.parseInt(id));
                if (model.getStatusDelete() == true) {
                    nav.showAlert(Alert.AlertType.INFORMATION, "Success", null, "Data successfully deleted..");
                    loadTable();
                    clearParameter();
                } else {
                    nav.showAlert(Alert.AlertType.ERROR, "Error", null, "Data failed to delete..");
                }
            }
        }
    }

    private void openUbah() throws IOException {
        FXMLLoader Loader = new FXMLLoader();
        Loader.setLocation(getClass().getResource(nav.getContactUpdate()));
        blur.setEffect(new GaussianBlur(10));
        new FadeInRightTransition(trans).play();
        AnchorPane pane = Loader.load();
        ContactUpdateController ContactUpdate = Loader.getController();
        ContactUpdate.setData(id, firstName, adress, phone, message);
        loadPane.getChildren().setAll(pane);
    }

    @FXML
    private void ubahUpdate(ActionEvent event) throws IOException {
        if (adress.equals("")) {
            nav.showAlert(Alert.AlertType.WARNING, "WARNING", null, "Please select the data..");
        } else {
            openUbah();
        }
    }

    @FXML
    private void ubahClicked(ActionEvent event) {
    }

    @FXML
    private void hapusClicked(ActionEvent event) {
    }

}
