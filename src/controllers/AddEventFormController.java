package controllers;

import entity.Event;
import function.navigation;
import java.net.URL;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import service.EventService;

public class AddEventFormController implements Initializable {

    ObservableList<String> comboPilihan = FXCollections.observableArrayList("Bakul", "Hotel");

    SimpleDateFormat sdf = new SimpleDateFormat("dd MMMM yyyy");
    EventService service = new EventService();
    navigation nav = new navigation();

    @FXML
    private DatePicker endDate;
    @FXML
    private DatePicker startDate;

    @FXML
    private TextField title, nbTable, nbPerson, sdate_text, edate, band;

    @FXML
    private AnchorPane pane;

    private void clear() {
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
    private void dateClicked(ActionEvent event) {
        String dateText = sdf.format(Date.valueOf(startDate.getValue()));
        sdate_text.setText(dateText);
    }

    @FXML
    private void startDateClicked(ActionEvent event) {

        String dateText = sdf.format(Date.valueOf(startDate.getValue()));
        sdate_text.setText(dateText);

    }

    private void endDateClicked(ActionEvent event) {
        String dateText = sdf.format(Date.valueOf(startDate.getValue()));
        sdate_text.setText(dateText);
    }

    @FXML
    private void inputClicked(ActionEvent event) {
        try {
            inputMethod();
        } catch (ParseException ex) {
            Logger.getLogger(AddEventFormController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void inputMethod() throws ParseException {
        LocalDate now = LocalDate.now();
        SimpleDateFormat sdf = new SimpleDateFormat("dd MMM yyyy ");

        Calendar cal = Calendar.getInstance();
        String setTitle = title.getText();
        String setNbPerson = nbPerson.getText();
        String setNbTable = nbTable.getText();

        java.sql.Date dd = java.sql.Date.valueOf(startDate.getValue());
        Event event = new Event(
                setTitle, Integer.parseInt(setNbPerson), dd,
                dd, Integer.parseInt(setNbTable), 0, "Pending", 0.0
        );
        EventService service = new EventService();
        service.add(event);

    }

}
