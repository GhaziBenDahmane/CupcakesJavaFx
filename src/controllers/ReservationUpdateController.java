package controllers;

import entity.Reservation;
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
import service.DeliveryService;
import service.ReservationService;

public class ReservationUpdateController implements Initializable {

    SimpleDateFormat sdf = new SimpleDateFormat("dd MMMM yyyy");
    navigation nav = new navigation();
    ReservationService model = new ReservationService();

    @FXML
    private TextField nbTable, nbPerson, dateReservation_text;

    @FXML
    private DatePicker dateReservation;

    @FXML
    private Label idUangKeluar;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        nav.harusAngka(nbTable);
        nav.harusAngka(nbPerson);
    }

    @FXML
    private void dateClicked(ActionEvent event) {
        String dateText = sdf.format(Date.valueOf(dateReservation.getValue()));
        dateReservation_text.setText(dateText);
    }

    public void setData(String id, String UnbTable, String UnbPerson, String UdateReservation_text) {
        idUangKeluar.setText(id);
        nbTable.setText(UnbTable);
        nbPerson.setText(UnbPerson);
        dateReservation_text.setText(UdateReservation_text);
        System.out.println("id " + id);

    }

    @FXML
    private void simpanClicked(ActionEvent event) {
        if (nbTable.getText().equals("") || nbPerson.getText().equals("") || dateReservation_text.getText().equals("")) {
            nav.showAlert(Alert.AlertType.WARNING, "WARNING", null, "Complete the data first!!");
        } else {
            String idText = idUangKeluar.getText();
            String nbTable_text = nbTable.getText();
            String nbPerson_text = nbPerson.getText();
            String date_text = dateReservation_text.getText();
            // String date=dateReservation.getValue().toString();
            java.sql.Date date = java.sql.Date.valueOf(date_text);
            Reservation reser = new Reservation(Integer.parseInt(idText), Integer.parseInt(nbTable_text), Integer.parseInt(nbPerson_text), date);
            model.update(reser);
            if (model.getStatusUpdate() == true) {
                nav.showAlert(Alert.AlertType.INFORMATION, "Success", null, "Reservation Updated..");
            } else {
                nav.showAlert(Alert.AlertType.ERROR, "Error", null, "Failed >_<..");
            }
        }
    }

}
