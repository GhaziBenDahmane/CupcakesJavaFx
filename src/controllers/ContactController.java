/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import animasi.FadeInRightTransition;
import animasi.FadeOutLeftTransition;
import entity.Contact;
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
import service.ContactService;
import util.DataSource;


public class ContactController implements Initializable {
    
    ObservableList<String> comboFilter = FXCollections.observableArrayList("Day","Month","Year");
    ObservableList<String> comboBulan = FXCollections.observableArrayList("January","February"
            ,"March","April","May","June","July","August","September","October","November","December");
    SimpleDateFormat sdf = new SimpleDateFormat("dd MMMM yyyy");
    
    @FXML
    private TableView<Contact> tableUangKeluar;
    
    @FXML
    private TableColumn<Contact, String> columnID;
    
    @FXML
    private TableColumn<Contact, String> columnFirstName;
    
    @FXML
    private TableColumn<Contact, String> columnLastName;
    
    @FXML
    private TableColumn<Contact, String> columnEmail;
    
    @FXML
    private TableColumn<Contact, String> columnAdress;
    
    @FXML
    private TableColumn<Contact, String> columnPhone;
        @FXML
    private TableColumn<Contact, String> columnMessage;
    
    @FXML
    private TableColumn<Contact, String> columnStatus;
    
    @FXML
    private TableColumn<Contact, String> columnInputTime;
    
    private ObservableList<Contact> data;
    
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
    private ComboBox filter, month;
    
    @FXML
    private TextField day, year, cari;
    
    @FXML
    private DatePicker select_day;
    
    private String id="";
    private String  firstName="", lastName="", email="", adress="",message="",phone="";
    
    
    DataSource kon = new DataSource();
    ContactService model = new ContactService();
    navigation nav = new navigation();
    time time = new time();
    
    private void setFilter(){
        filter.setValue("day");
        filter.setItems(comboFilter);
    }
    
    private void  setDay(){
        day.setText(time.tanggal());
        select_day.setValue(LocalDate.parse(time.tanggalQuery()));
    }
    
    private void setMonth(){
        month.setValue(time.tanggalBulan());
        month.setItems(comboBulan);
    }
    
    private void setYear(){
        year.setText(time.tanggalTahun());
    }
    
    /*private void setStyleTable(){
        columnID.setStyle("-fx-alignment: CENTER");
        columnFirstName.setStyle("-fx-alignment: CENTER");
        columnLastName.setStyle("-fx-alignment: CENTER");
        columnAdress.setStyle("-fx-alignment: CENTER");
        columnEmail.setStyle("-fx-alignment: CENTER");
        columnPhone.setStyle("-fx-alignment: CENTER");
    }*/
    
    private void loadTable(){
        try {
           
      
            columnID.setCellValueFactory(new PropertyValueFactory<>("id"));
            columnFirstName.setCellValueFactory(new PropertyValueFactory<>("firstName"));
            columnLastName.setCellValueFactory(new PropertyValueFactory<>("lastName"));
            columnEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
            columnAdress.setCellValueFactory(new PropertyValueFactory<>("adress"));
            columnPhone.setCellValueFactory(new PropertyValueFactory<>("phone"));
            columnMessage.setCellValueFactory(new PropertyValueFactory<>("message"));
            columnInputTime.setCellValueFactory(new PropertyValueFactory<>("inputTime"));
            tableUangKeluar.setItems(getContacts());
        } catch (Exception e) {
            System.out.println("error controller");;
        }
    }
    
    private void clearParameter(){
     id="";   firstName=""; lastName=""; email=""; adress="";message="";phone="";
    }
    
    @FXML
    private void refreshClicked(ActionEvent event){
        loadTable();
    }
    
    @FXML
    private void filterClicked(ActionEvent event){
        if(filter.getSelectionModel().getSelectedItem().toString().equals("day")){
            month.setVisible(false);
            day.setVisible(true);
            select_day.setVisible(true);
            year.setVisible(false);
             setDay();
        }
        else if (filter.getSelectionModel().getSelectedItem().toString().equals("month")){
            month.setVisible(true);
            day.setVisible(false);
            select_day.setVisible(false);
            year.setVisible(true);
            setMonth();
            setYear();
        }
        else{
            month.setVisible(false);
            day.setVisible(false);
            select_day.setVisible(false);
            year.setVisible(false);
        }
    }
    
    @FXML
    private void dateClicked(ActionEvent event){
        String dateText = sdf.format(Date.valueOf(select_day.getValue()));
        day.setText(dateText);
    }
    
    @FXML
    private void checkClicked(ActionEvent event){
        if(check.isSelected()==true){
            columnInputTime.setVisible(true);
        }
        else{
            columnInputTime.setVisible(false);
        }
    }
     @FXML
    private void ambilID(MouseEvent event) throws IOException{
        if(event.getClickCount()==1){
           // id = tableUangKeluar.getSelectionModel().getSelectedItem().getId();
            firstName = tableUangKeluar.getSelectionModel().getSelectedItem().getFirstName();
            lastName = tableUangKeluar.getSelectionModel().getSelectedItem().getLastName();
            email = tableUangKeluar.getSelectionModel().getSelectedItem().getEmail();
            adress = tableUangKeluar.getSelectionModel().getSelectedItem().getAdress();
          //  phone = tableUangKeluar.getSelectionModel().getSelectedItem().getPhone();
            message = tableUangKeluar.getSelectionModel().getSelectedItem().getMessage();
        }
        else if(event.getClickCount()==2){
          //   id = tableUangKeluar.getSelectionModel().getSelectedItem().getId();
            firstName = tableUangKeluar.getSelectionModel().getSelectedItem().getFirstName();
            lastName = tableUangKeluar.getSelectionModel().getSelectedItem().getLastName();
            email = tableUangKeluar.getSelectionModel().getSelectedItem().getEmail();
            adress = tableUangKeluar.getSelectionModel().getSelectedItem().getAdress();
          //  phone = tableUangKeluar.getSelectionModel().getSelectedItem().getPhone();
            message = tableUangKeluar.getSelectionModel().getSelectedItem().getMessage();
            openUbah();
        }
        else if (event.getButton()==MouseButton.SECONDARY){
            contextMenu.show(tableUangKeluar, event.getScreenX(), event.getScreenY());
        }
    }
    
        public ObservableList<Contact> getContacts()
         {
             ContactService contact = new ContactService();
            ObservableList<Contact> p = FXCollections.observableArrayList();
            List<Contact> allContacts;
             allContacts=contact.selectAll();
             allContacts.stream().forEach((allContact) -> {
                p.add(new Contact(allContact.getId(),allContact.getPhone(),allContact.getInputTime(),allContact.getFirstName(),allContact.getLastName(),
                        allContact.getMessage(),allContact.getAdress(),allContact.getEmail()));
        });
            return p;
            
         }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        setFilter();
        setDay();
        setMonth();
        setYear();
       // setStyleTable();
        loadTable();
    }    
    
    @FXML
    private void tombolClose(ActionEvent event){
        loadTable();
        blur.setEffect(null);
        new FadeOutLeftTransition(trans).play();
        clearParameter();
    }


    @FXML
    private void tambahClicked() throws IOException{
       blur.setEffect(new GaussianBlur(10));
        new FadeInRightTransition(trans).play();
//       AnchorPane pane = FXMLLoader.load(getClass().getResource(nav.getContactAdd()));
        AnchorPane pane = FXMLLoader.load(getClass().getResource(nav.getContactAdd()));
        loadPane.getChildren().setAll(pane);
        
    }
    
    @FXML
    private void ubahClicked(ActionEvent event) throws IOException {
      if(id.equals("")){
            nav.showAlert(Alert.AlertType.WARNING, "WARNING", null, "Please select the data..");
        }
      else {
            openUbah();
       }    
    }
    
    @FXML
    private void hapusClicked(ActionEvent event) throws IOException{
        if(id.equals("")){
            nav.showAlert(Alert.AlertType.WARNING, "WARNING", null, "Please select the data..");
        }
        else{
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Delete Contact");
        alert.setHeaderText("First Name\t: "+firstName
                +"lastName:"+lastName
                +"Email:"+email
                +"Adress: "+adress
                +"Email: "+email);
        alert.setContentText("Are you sure you want to delete this data?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            model.delete(Integer.parseInt(id));
            if(model.getStatusDelete()==true){
                nav.showAlert(Alert.AlertType.INFORMATION, "Success", null, "Data successfully deleted..");
                loadTable();
                clearParameter();
            }
            else{
                nav.showAlert(Alert.AlertType.ERROR, "Error", null, "Data failed to delete..");
            }
        } 
        }
    }
    
    private void openUbah() throws IOException{
        FXMLLoader Loader = new FXMLLoader();
        Loader.setLocation(getClass().getResource(nav.getContactUpdate()));
        blur.setEffect(new GaussianBlur(10));
        new FadeInRightTransition(trans).play();
        AnchorPane pane = Loader.load();
        ContactUpdateController ContactUpdate = Loader.getController();
        ContactUpdate.setData(id,firstName, lastName,email, adress,phone,message );
        loadPane.getChildren().setAll(pane);
    }
    
}
