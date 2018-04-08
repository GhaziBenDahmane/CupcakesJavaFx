package controllers;

import function.navigation;
import java.awt.event.KeyEvent;
import java.net.URL;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import entity.Event;
import java.text.ParseException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.logging.Level;
import java.util.logging.Logger;
import service.EventService;

public class AddEventFormController implements Initializable {

    ObservableList<String> comboPilihan = FXCollections.observableArrayList("Bakul","Hotel");
    
    SimpleDateFormat sdf = new SimpleDateFormat("dd MMMM yyyy");
    EventService service = new EventService();
    navigation nav = new navigation();
    
    @FXML
    private DatePicker endDate;
    @FXML
    private DatePicker startDate;
    
    
    
    @FXML
    private TextField title, nbTable, nbPerson , sdate_text, edate ,band ;
            
    @FXML
    private AnchorPane pane;
    
    private void clear(){
        title.setText("");
        nbPerson.setText("");
        nbTable.setText("");
      
        title.requestFocus();
    }
    
   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        clear();
        
    }  
    
     @FXML
    private void dateClicked(ActionEvent event){
        String dateText = sdf.format(Date.valueOf(startDate.getValue()));
        sdate_text.setText(dateText);
    }
    
    @FXML
    private void startDateClicked(ActionEvent event){

            String dateText = sdf.format(Date.valueOf(startDate.getValue()));
            sdate_text.setText(dateText);
           
    }
    private void endDateClicked(ActionEvent event){
        String dateText = sdf.format(Date.valueOf(startDate.getValue()));
        sdate_text.setText(dateText);
    }
    
    @FXML
    private void inputClicked(ActionEvent event){
        try {
            inputMethod();
        } catch (ParseException ex) {
            Logger.getLogger(AddEventFormController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void inputMethod() throws ParseException{
	LocalDate now = LocalDate.now();
       /* if(title.getText().equals("")||nbPerson.getText().equals("")
                ||sdate_text.getText().equals("")||edate.getText().equals("")){
            nav.showAlert(Alert.AlertType.WARNING, "Error", null, "Please enter the following information");
        }

        if(startDate.getValue().isBefore(now))
        {
            nav.showAlert(Alert.AlertType.WARNING, "Error",null, "The date is wrong");

        }
*/
       
            SimpleDateFormat sdf = new SimpleDateFormat("dd MMM yyyy ");
 
       
            Calendar cal = Calendar.getInstance();
            String setTitle=title.getText();
            String setNbPerson=nbPerson.getText();
            String setNbTable=nbTable.getText();
            
            java.sql.Date dd = java.sql.Date.valueOf(startDate.getValue());
            
//            Date setEndDate= (Date) sdf.parse(sdate_text.getText());
             
            Event event = new Event(
                    setTitle, Integer.parseInt(setNbPerson),dd, 
                    dd, Integer.parseInt(setNbTable),0, "Pending", 0.0
            );
            EventService service = new EventService();
            service.add(event);
        
    }
    
}
