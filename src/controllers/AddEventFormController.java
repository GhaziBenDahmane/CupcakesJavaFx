package controllers;

import entity.Delivery;
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
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import service.DeliveryService;
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
        if(title.getText().equals("")||nbPerson.getText().equals("")
                ){
            nav.showAlert(Alert.AlertType.WARNING, "Error", null, "Please enter the following information");
        }
        else
        {

     

       
            SimpleDateFormat sdf = new SimpleDateFormat("dd MMM yyyy ");
 
       
            Calendar cal = Calendar.getInstance();
            String setTitle=title.getText();
            String setNbPerson=nbPerson.getText();
            String setNbTable=nbTable.getText();
            
            java.sql.Date dd = java.sql.Date.valueOf(startDate.getValue());
            java.sql.Date date = new java.sql.Date(Calendar.getInstance().getTime().getTime());

            System.out.println("after"+dd.after(date));
            System.out.println("before"+dd.before(date));
            
            if(dd.before(date))
            {
                nav.showAlert(Alert.AlertType.WARNING, "Error",null, "The date is wrong");

            }
            else {
//            Date setEndDate= (Date) sdf.parse(sdate_text.getText());
             
            Event event = new Event(
                    setTitle, Integer.parseInt(setNbPerson),dd, 
                    dd, Integer.parseInt(setNbTable),0, "Pending", 0.0
            );
            EventService service = new EventService();
            service.add(event);
            
    
            try {

                String host = "smtp.gmail.com";

                String user = "cupcakejavafx@gmail.com";

                String pass = "cupcakejavafx123";

                String to = "haffez23@gmail.com";

                String from = "cupcakejavafx@gmail.com";

                String subject = "Delivery received";

                String messageText = "New event added at "+dd ;

                boolean sessionDebug = false;

                Properties props = System.getProperties();

                props.put("mail.smtp.starttls.enable", "true");

                props.put("mail.smtp.host", host);

                props.put("mail.smtp.port", "587");

                props.put("mail.smtp.auth", "true");

                props.put("mail.smtp.starttls.required", "true");
                props.put("mail.smtp.ssl.trust", "smtp.gmail.com");

                java.security.Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());

                Session mailSession = Session.getDefaultInstance(props, null);

                mailSession.setDebug(sessionDebug);

                Message msg = new MimeMessage(mailSession);

                msg.setFrom(new InternetAddress(from));

                InternetAddress[] address = {new InternetAddress(to)};

                msg.setRecipients(Message.RecipientType.TO, address);

                msg.setSubject(subject);
                msg.setSentDate(new java.util.Date(12, 2, 2018));

                msg.setText(messageText);
                Transport transport = mailSession.getTransport("smtp");

                transport.connect(host, user, pass);

                transport.sendMessage(msg, msg.getAllRecipients());

                transport.close();

                System.out.println("message send successfully");

            } catch (Exception ex) {

                System.out.println(ex);
            }

        
    
            
            
            
            }
    }
    }
}
