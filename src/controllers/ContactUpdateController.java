package controllers;

import entity.Contact;
import function.navigation;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.util.Duration;
import service.ContactService;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

public class ContactUpdateController implements Initializable {

    SimpleDateFormat sdf = new SimpleDateFormat("dd MMMM yyyy");
    navigation nav = new navigation();
    ContactService model = new ContactService();

    @FXML
    private TextField firstName,adress, phone, message;

    @FXML
    private Label idUangKeluar;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        nav.harusAngka(phone);
    }

    public void setData(String id, String UfirstName, String Uadress, String Uphone, String UMessage) {
        idUangKeluar.setText(id);
        firstName.setText(UfirstName);
        adress.setText(Uadress);
        phone.setText(Uphone);
        message.setText(UMessage);
        System.out.println("id " + id);
    }

    @FXML
    private void simpanClicked(ActionEvent event) {
        if (firstName.getText().equals("") || adress.getText().equals("")
                || message.getText().equals("") || phone.getText().equals("")) {
            nav.showAlert(Alert.AlertType.WARNING, "WARNING", null, "Complete the data first !!");
        } else {
            TrayNotification tray = new TrayNotification();
            tray.setNotificationType(NotificationType.CUSTOM);
            tray.setTitle("Update Success");
            tray.setMessage("Contact Updated...");
            tray.setAnimationType(AnimationType.POPUP);
            tray.showAndDismiss(Duration.millis(5000));
            tray.setRectangleFill(Color.valueOf("#4183D7"));
            String idText = idUangKeluar.getText();
            String firstNameText = firstName.getText();
            String phoneText = phone.getText();
            String adressText = adress.getText();
            String messageText = message.getText();
            Contact c = new Contact(Integer.parseInt(idText), Integer.parseInt(phoneText), firstNameText,  messageText, adressText, false);
            model.update(c);
            if (model.getStatusUpdate() == true) {
                nav.showAlert(Alert.AlertType.INFORMATION, "Success", null, "The Contact has been updated..");
            } else {
                nav.showAlert(Alert.AlertType.ERROR, "Error", null, "Failed..");
            }
        }
    }

}
