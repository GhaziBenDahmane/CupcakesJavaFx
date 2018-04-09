package controllers;

import entity.Contact;
import function.navigation;
import java.net.URL;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import service.ContactService;


public class ContactUpdateController implements Initializable {
    
    SimpleDateFormat sdf = new SimpleDateFormat("dd MMMM yyyy");
    navigation nav = new navigation();
    ContactService model = new ContactService();
   
    @FXML
    private TextField firstName, lastName, email, adress,phone,message;
    
    
    @FXML
    private Label idUangKeluar;
        
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        nav.harusAngka(phone);
    }    
    

    
    public void setData(String id,String UfirstName, String UlastName, String Uemail, String Uadress,String Uphone,String UMessage){
        idUangKeluar.setText(id);
        firstName.setText(UfirstName);
        lastName.setText(UlastName);
        email.setText(Uemail);
        adress.setText(Uadress);
        phone.setText(Uphone);
        message.setText(UMessage);
    }
    
    @FXML
    private void simpanClicked(ActionEvent event){
        if(firstName.getText().equals("")||lastName.getText().equals("")||adress.getText().equals("")
             ||message.getText().equals("")   ||email.getText().equals("")||phone.getText().equals("")){
            nav.showAlert(Alert.AlertType.WARNING, "WARNING", null, "Complete the data first !!");
        }
       else{
            String idText=idUangKeluar.getText();
           String firstNameText=firstName.getText();
            String lastNameText=lastName.getText();
            String emailText=email.getText();
            String phoneText=phone.getText().toString();
            String adressText=adress.getText();
            String messageText=message.getText();
            Contact c = new Contact(idText,Integer.parseInt(phoneText), firstNameText, lastNameText, messageText, adressText, emailText, false);
           model.update(c);
           if(model.getStatusUpdate()==true){
               nav.showAlert(Alert.AlertType.INFORMATION, "Success", null, "The Contact has been updated..");
           }
           else{
               nav.showAlert(Alert.AlertType.ERROR, "Error", null, "Failed..");
           }
       }
    }
    
}
