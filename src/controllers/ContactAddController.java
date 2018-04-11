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
import entity.Contact;
import java.io.FileOutputStream;
import javafx.scene.paint.Color;
import javafx.util.Duration;
import service.ContactService;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

public class ContactAddController implements Initializable {

    ObservableList<String> comboPilihan = FXCollections.observableArrayList("Bakul", "Hotel");

    SimpleDateFormat sdf = new SimpleDateFormat("dd MMMM yyyy");
    ContactService model = new ContactService();
    navigation nav = new navigation();

    @FXML
    private TextField firstName, lastName, email, adress, phone, message;

    @FXML
    private AnchorPane pane;

    private int i = 1;

    private void clear() {
        firstName.setText("");
        lastName.setText("");
        email.setText("");
        adress.setText("");
        phone.setText("");
        //date_uang_masuk_text.setText("");
        //date_uang_masuk.setValue(null);
        message.setText("");
    }

    private void setPhone() {

        phone.setOnKeyReleased(new EventHandler<javafx.scene.input.KeyEvent>() {
            @Override
            public void handle(javafx.scene.input.KeyEvent event) {
                if (!phone.getText().matches("[0-9]*")) {
                    nav.showAlert(Alert.AlertType.WARNING, "WARNING", null, "Only numbers!!");
                    phone.setText("");
                    phone.requestFocus();
                }

            }
        });
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        setPhone();
        firstName.requestFocus();
        firstName.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                inputMethod();
            }
        });
        lastName.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                inputMethod();
            }
        });
        phone.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                inputMethod();
            }
        });
        email.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                inputMethod();
            }
        });
        adress.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                inputMethod();
            }
        });
        message.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                inputMethod();
            }
        });
        clear();
    }

    @FXML
    private void inputClicked(ActionEvent event) {
        inputMethod();
    }

    private void inputMethod() {
        if (firstName.getText().equals("") || lastName.getText().equals("") || adress.getText().equals("")
                || message.getText().equals("") || email.getText().equals("") || phone.getText().equals("")) {
            nav.showAlert(Alert.AlertType.WARNING, "WARNING", null, "Complete the data first !!");

        } else {
            DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            Calendar cal = Calendar.getInstance();
            String firstNameText = firstName.getText();
            String lastNameText = lastName.getText();
            String emailText = email.getText();
            String phoneText = phone.getText();
            String adressText = adress.getText();
            String messageText = message.getText();
            String inputTime = dateFormat.format(cal.getTime());

            Contact c = new Contact(i, Integer.parseInt(phoneText), inputTime, firstNameText, lastNameText, messageText, adressText, emailText, false);
            i++;
            model.create(c);
            if (model.getStatusInert() == true) {
                TrayNotification tray = new TrayNotification();
                tray.setNotificationType(NotificationType.CUSTOM);
                tray.setTitle("Save Success");
                tray.setMessage("Contac added...");
                tray.setAnimationType(AnimationType.POPUP);
                tray.showAndDismiss(Duration.millis(5000));
                tray.setRectangleFill(Color.valueOf("#4183D7"));
                clear();
            } else {
                nav.showAlert(Alert.AlertType.ERROR, "Error", null, "Contact add failed..");
            }
        }
    }

}
