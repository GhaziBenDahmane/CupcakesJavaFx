/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import entity.Delivery;
import function.navigation;
import java.net.URL;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.paint.Color;
import javafx.util.Duration;
import service.DeliveryService;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

/**
 * FXML Controller class
 *
 * @author USER
 */
public class DeliveryController implements Initializable {

    @FXML
    private JFXTextField deliveryDate;
    @FXML
    private JFXTextField email;
    @FXML
    private JFXTextField notes;

    private JFXDatePicker dateReservation;
    @FXML
    private JFXComboBox serviceType;
    @FXML
    private JFXDatePicker dateDelivery;
    SimpleDateFormat sdf = new SimpleDateFormat("dd MMMM yyyy");
    ObservableList<String> comboType = FXCollections.observableArrayList("Free", "Premium");
    navigation nav = new navigation();
    public DateFormat formatter;
    DeliveryService model = new DeliveryService();
    int i;
    @FXML
    private JFXTextField adress;

    /**
     * Initializes the controller class.
     */
    private void setType() {

        serviceType.setItems(comboType);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        setType();
    }

    private void clear() {
        email.setText("");
        notes.setText("");
        //date_uang_keluar_text.setText("");
        dateReservation.requestFocus();
    }

    @FXML
    private void dateClicked(ActionEvent event) {
        String dateText = sdf.format(Date.valueOf(dateDelivery.getValue()));
        deliveryDate.setText(dateText);
    }

    @FXML
    private void inputClicked(ActionEvent event) {
        if (deliveryDate.getText().equals("") || email.getText().equals("") || notes.getText().equals("")) {
            nav.showAlert(Alert.AlertType.WARNING, "WARNING", null, "Complete the data first !!");
        } else {
            DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            Calendar cal = Calendar.getInstance();
            String email_text = email.getText();
            String notes_text = notes.getText();
            String adress_text = adress.getText();
            String service_text = serviceType.getValue().toString();
            String deliveryDate_text = deliveryDate.getText();
            // String date=dateReservation.getValue().toString();
            java.sql.Date date = java.sql.Date.valueOf(dateDelivery.getValue());
            String waktu_input = dateFormat.format(cal.getTime());

            formatter = new SimpleDateFormat("dd-MMM-yy");
            Delivery d = new Delivery(i, notes_text, email_text, adress_text, service_text, date, false);
            i++;
            model.create(d);
            if (model.getStatusInert() == true) {
                nav.showAlert(Alert.AlertType.INFORMATION, "Success", null, "Delivery added..");
                TrayNotification tray = new TrayNotification();
                tray.setNotificationType(NotificationType.CUSTOM);
                tray.setTitle("Save Success");
                tray.setMessage("Delivery added...");
                tray.setAnimationType(AnimationType.POPUP);
                tray.showAndDismiss(Duration.millis(5000));
                tray.setRectangleFill(Color.valueOf("#4183D7"));
                clear();
                clear();
            } else {
                nav.showAlert(Alert.AlertType.ERROR, "Error", null, "Delivery failed..");
            }
        }

    }

}
