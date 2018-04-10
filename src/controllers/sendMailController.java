/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javax.mail.*;
import javax.mail.Message.RecipientType;
import javax.mail.internet.*;

/**
 * FXML Controller class
 *
 * @author ding
 */
public class sendMailController implements Initializable {

    @FXML
    private StackPane trans;
    @FXML
    private Group groups;
    @FXML
    private AnchorPane loadPane;
    @FXML
    private AnchorPane dataUangMasuk;
    @FXML
    private AnchorPane blur;
    @FXML
    private JFXComboBox<?> filter;
    @FXML
    private JFXComboBox<?> bulan;
    @FXML
    private JFXTextField tahun;
    @FXML
    private JFXButton send_btn;
    @FXML
    private JFXTextField to;
    @FXML
    private JFXTextField from;
    @FXML
    private JFXTextField subject;
    @FXML
    private TextArea message;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void tombolClose(ActionEvent event) {
    }

    @FXML
    private void comboBoxChanged(ActionEvent event) {
    }

    @FXML
    private void sendClicked(ActionEvent event) {
        try {

            String host = "smtp.gmail.com";
            String user = "yesmine.jouirou@esprit.tn";
            String pass = "22595913";
            String From = from.getText();
            String To = to.getText();
            String Subject = subject.getText();
            String Message = message.getText();
            boolean sessionDebug = false;

            Properties props = System.getProperties();

            props.put("mail.smtp.starttls.enable", "true");
            props.put("mail.smtp.host", host);
            props.put("mail.smtp.port", "587");
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.starttls.required", "true");

            java.security.Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());
            Session mailSession = Session.getDefaultInstance(props, null);
            mailSession.setDebug(sessionDebug);
            Message msg = new MimeMessage(mailSession);
            msg.setFrom(new InternetAddress(From));
            // InternetAddress[] address = {new InternetAddress(To)};
            msg.setRecipient(RecipientType.TO, new InternetAddress(To));
            msg.setSubject(Subject);
            msg.setSentDate(new Date());
            msg.setText(Message);
            Transport transport = mailSession.getTransport("smtp");
            transport.connect(host, user, pass);

            transport.sendMessage(msg, msg.getAllRecipients());
            transport.close();
            System.out.println("message send successfully");
        } catch (Exception ex) {
        }

    }
}
