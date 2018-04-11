/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import com.jfoenix.controls.JFXTextField;
import entity.Event;
import entity.Pastry;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import service.EventService;
import service.PastryService;

/**
 * FXML Controller class
 *
 * @author haffez
 */
public class AddPastryController implements Initializable {

    @FXML
    private AnchorPane pane;
    @FXML
    private JFXTextField address;
    @FXML
    private JFXTextField nbTable;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    

    @FXML
    private void inputClicked(ActionEvent event) {
        
        SimpleDateFormat sdf = new SimpleDateFormat("dd MMM yyyy ");
 
       
            Calendar cal = Calendar.getInstance();
            String setaddress=address.getText();
            String setNbTable=nbTable.getText();

            Pastry p = new Pastry(setaddress, Integer.parseInt(setNbTable));
            PastryService service = new PastryService();
            service.add(p);
    }
    
}
