/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import com.jfoenix.controls.JFXComboBox;
import entity.Delivery;
import function.navigation;
import java.net.URL;
import java.util.Properties;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import service.DeliveryService;
import java.util.*;

import javax.mail.*;

import javax.mail.internet.*;

import javax.mail.internet.MimeMessage;

/**
 * FXML Controller class
 *
 * @author USER
 */
public class DeliveryUpdateController implements Initializable {

    @FXML
    private Label idUangKeluar;
    @FXML
    private JFXComboBox status;

    ObservableList<String> comboStatus = FXCollections.observableArrayList("Delivered", "Not Delivered");

    DeliveryService ds = new DeliveryService();
    navigation nav = new navigation();

    private void setStatus() {
        status.setValue("Delivery Status");
        status.setItems(comboStatus);
    }

    /**
     * Initializes the controller class.
     */
    public void setData(String id, String Ustatus) {
        idUangKeluar.setText(id);
        status.setValue(Ustatus);

        System.out.println("id " + id);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    @FXML
    private void simpanClicked(ActionEvent event) {

        String idText = idUangKeluar.getText();
        String statusText = status.getSelectionModel().getSelectedItem().toString();
        if (statusText.equals("Not Delivered")) {
            statusText = "false";
        } else {
            statusText = "true";
        }
        Delivery d = new Delivery(Integer.parseInt(idText), Boolean.valueOf(statusText));
        ds.update(d);
        if (ds.getStatusUpdate() == true) {
            try {

                String host = "smtp.gmail.com";

                String user = "cupcakejavafx@gmail.com";

                String pass = "cupcakejavafx123";

                String to = "anis.helaoui@esprit.tn ";

                String from = "cupcakejavafx@gmail.com";

                String subject = "Delivery received";

                String messageText = "Thank you for using our services";

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
                msg.setSentDate(new Date(12, 2, 2018));

                msg.setText(messageText);
                Transport transport = mailSession.getTransport("smtp");

                transport.connect(host, user, pass);

                transport.sendMessage(msg, msg.getAllRecipients());

                transport.close();

                System.out.println("message send successfully");

            } catch (Exception ex) {

                System.out.println(ex);
            }

            nav.showAlert(Alert.AlertType.INFORMATION, "Success", null, "Delivery status has been updated..");
        } else {
            nav.showAlert(Alert.AlertType.ERROR, "Error", null, "Failed..");
        }
    }

}
