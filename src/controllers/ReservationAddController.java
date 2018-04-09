package controllers;

import entity.Reservation;
import function.navigation;
import java.net.URL;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import service.ReservationService;

public class ReservationAddController implements Initializable {

    SimpleDateFormat sdf = new SimpleDateFormat("dd MMMM yyyy");
    navigation nav = new navigation();
    ReservationService model = new ReservationService();
       
    @FXML
    private TextField nbTable,nbPerson,dateReservation_text;
    
    @FXML
    private DatePicker dateReservation;
    public DateFormat formatter;
    private int i=1;
    
   
     
    private void clear(){
        nbTable.setText("");
        nbPerson.setText("");
        //date_uang_keluar_text.setText("");
        dateReservation.requestFocus();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        nav.harusAngka(nbTable);
        nav.harusAngka(nbPerson);
        nbTable.setOnKeyPressed(event->{if(event.getCode()==KeyCode.ENTER){inputMethod();}});
        nbPerson.setOnKeyPressed(event->{if(event.getCode()==KeyCode.ENTER){inputMethod();}});
    }    
    
    @FXML
    private void dateClicked(ActionEvent event){
        String dateText = sdf.format(Date.valueOf(dateReservation.getValue()));
        dateReservation_text.setText(dateText);
    }
    
    @FXML
    private void insertClicked(ActionEvent event){
        inputMethod();
    }
    
    private void inputMethod(){
        if(nbTable.getText().equals("")||nbPerson.getText().equals("")||dateReservation_text.getText().equals("")){
            nav.showAlert(Alert.AlertType.WARNING, "WARNING", null, "Complete the data first !!");
        }
        else{
            DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            Calendar cal = Calendar.getInstance();
            String nbTable_text=nbTable.getText();
            String nbPerson_text=nbPerson.getText();
            String date_text=dateReservation_text.getText();
           // String date=dateReservation.getValue().toString();
            java.sql.Date date = java.sql.Date.valueOf(dateReservation.getValue());
            String waktu_input=dateFormat.format(cal.getTime());
            
            formatter = new SimpleDateFormat("dd-MMM-yy");
            Reservation reser = new Reservation(i,Integer.parseInt(nbTable_text), Integer.parseInt(nbTable_text),date);
            i++;
            model.create(reser);
            if(model.getStatusInert()==true){
                nav.showAlert(Alert.AlertType.INFORMATION, "Success", null, "Reservation added..");
                clear();
            }
            else{
                nav.showAlert(Alert.AlertType.ERROR, "Error", null, "Reservation add failed..");
            }
        }
    }
    
}
