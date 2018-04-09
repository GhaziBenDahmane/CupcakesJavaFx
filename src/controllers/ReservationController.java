/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import animasi.FadeInRightTransition;
import animasi.FadeOutLeftTransition;
import entity.Contact;
import entity.Reservation;
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
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import service.ContactService;
import service.ReservationService;
import util.DataSource;

/**
 *
 * @author USER
 */
public class ReservationController {
     ObservableList<String> comboFilter = FXCollections.observableArrayList("Hari","Bulan","Semua");
    ObservableList<String> comboBulan = FXCollections.observableArrayList("January","February"
            ,"March","April","May","June","July","August","September","October","November","December");
    SimpleDateFormat sdf = new SimpleDateFormat("dd MMMM yyyy");
    
    @FXML
    private TableView<Reservation> reservationTable;
    
    @FXML
    private TableColumn<Reservation, String> columnID;
    
    @FXML
    private TableColumn<Reservation, String> columnNbTable;
    
    @FXML
    private TableColumn<Reservation, String> columnNbPerson;
    
    @FXML
    private TableColumn<Reservation, String> columnDateReservation;
    
   
    
    @FXML
    private TableColumn<Reservation, String> columnInputTime;
    
  //  private ObservableList<Reservation> data;
    
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
    private ComboBox filter, bulan;
    
    @FXML
    private TextField hari, tahun, cari;
    
    @FXML
    private DatePicker hari_pilih;
    
 
    private String id="", nbTable="", nbPerson="", dateReservation="";
    
    DataSource kon = new DataSource();
    ReservationService model = new ReservationService();
    NumberFormat num = NumberFormat.getInstance();
    navigation nav = new navigation();
    time time = new time();
    
    private void setFilter(){
        filter.setValue("Hari");
        filter.setItems(comboFilter);
    }
    
    private void setHari(){
        hari.setText(time.tanggal());
        hari_pilih.setValue(LocalDate.parse(time.tanggalQuery()));
    }
    
    private void setBulan(){
        bulan.setValue(time.tanggalBulan());
        bulan.setItems(comboBulan);
    }
    
    private void setTahun(){
        tahun.setText(time.tanggalTahun());
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
   
        
            nav.animationFade(reservationTable);
       
            columnID.setCellValueFactory(new PropertyValueFactory<>("id"));
            columnNbTable.setCellValueFactory(new PropertyValueFactory<>("nbTable"));
            columnNbPerson.setCellValueFactory(new PropertyValueFactory<>("nbPerson"));
            columnDateReservation.setCellValueFactory(new PropertyValueFactory<>("dateReservation"));
            System.out.println("fok ala zebi aad");
           
           // columnInputTime.setCellValueFactory(new PropertyValueFactory<>("inputTime"));
            reservationTable.setItems(getReservations());
 
    
    }
    
    private void clearParameter(){
     id="";   nbTable=""; nbPerson=""; dateReservation="";
    }
    
    @FXML
    private void refreshClicked(ActionEvent event){
        loadTable();
    }
    
    @FXML
    private void filterClicked(ActionEvent event){
        if(filter.getSelectionModel().getSelectedItem().toString().equals("Hari")){
            bulan.setVisible(false);
            hari.setVisible(true);
            hari_pilih.setVisible(true);
            tahun.setVisible(false);
            setHari();
        }
        else if (filter.getSelectionModel().getSelectedItem().toString().equals("Bulan")){
            bulan.setVisible(true);
            hari.setVisible(false);
            hari_pilih.setVisible(false);
            tahun.setVisible(true);
            setBulan();
            setTahun();
        }
        else{
            bulan.setVisible(false);
            hari.setVisible(false);
            hari_pilih.setVisible(false);
            tahun.setVisible(false);
        }
    }
    
    @FXML
    private void dateClicked(ActionEvent event){
        String dateText = sdf.format(Date.valueOf(hari_pilih.getValue()));
        hari.setText(dateText);
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
    
        public ObservableList<Reservation> getReservations()
         {
             ReservationService reservation = new ReservationService();
            ObservableList<Reservation> r = FXCollections.observableArrayList();
            List<Reservation> allReservations;
              allReservations=reservation.selectAll();
              allReservations.stream().forEach(( allReservation) -> {
                r.add(new Reservation(allReservation.getId(), allReservation.getNbTables(),
                        allReservation.getNbPersonnes(),allReservation.getDateReservation() ));
        });
            return r;
            
         }
    
    public void initialize(URL url, ResourceBundle rb) {
        setFilter();
        setHari();
        setBulan();
        setTahun();
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
        AnchorPane pane = FXMLLoader.load(getClass().getResource(nav.getContactAdd()));
        loadPane.getChildren().setAll(pane);
    }
    
    @FXML
    private void ubahClicked(ActionEvent event) throws IOException {
        if(dateReservation.equals("")){
            nav.showAlert(Alert.AlertType.WARNING, "Peringatan", null, "Please select the data ..");
        }
        else{
            openUbah();
        }
    }
    
    @FXML
    private void hapusClicked(ActionEvent event) throws IOException{
        if(dateReservation.equals("")){
            nav.showAlert(Alert.AlertType.WARNING, "Peringatan", null, "Please select the data..");
        }
        else{
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Delete Reservation");
        alert.setHeaderText("Nb Table\t\t: "+nbTable
                +"\nb Person\t: "+nbPerson
               
                );
        alert.setContentText("Anda yakin ingin menghapus data ini ?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
          //  model.delete(id);
            if(model.getStatusDelete()==true){
                nav.showAlert(Alert.AlertType.INFORMATION, "Sukses", null, "Data berhasil dihapus..");
                loadTable();
                clearParameter();
            }
            else{
                nav.showAlert(Alert.AlertType.ERROR, "Error", null, "Data gagal dihapus..");
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
       // ContactUpdate.setData(id,nbTable,nbTable,dateReservation );
        loadPane.getChildren().setAll(pane);
    }
    
}
