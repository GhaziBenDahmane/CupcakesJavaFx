/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import static controllers.ActualityController.dia;
import entity.Actuality;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import service.ActualityService;

/**
 * FXML Controller class
 *
 * @author Yasmine
 */
public class AddActualityController implements Initializable {
    
    
    String[]extention={
   "png","jpeg","jpg"
    
    };
        Boolean verifForm;
    @FXML
    private AnchorPane pane;
    @FXML
    private JFXTextField titleA;
    @FXML
    private JFXTextArea contentA;
    @FXML
    private JFXTextField photoA;
    @FXML
    private DatePicker dateA;
    @FXML
    private Label label;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
         dateA.setDayCellFactory((picker) -> new DateCell() {
                            public void updateItem(LocalDate date, boolean empty) {
                                super.updateItem(date, empty);
                                LocalDate today = LocalDate.now();
                                
                                setDisable(empty || date.compareTo(today) < 0);
                            }
                        });
         
         titleA.textProperty().addListener((observable, oldValue, newValue) -> {
                            if (newValue.startsWith(" ")&&newValue.endsWith(" ")) {
                                    titleA.setStyle("-fx-border-color : #FF0000");
                                label.setText("Spaces Detected ! ");
                                label.setStyle("-fx-text-fill :#FF0000");
                                label.setVisible(true);
                                verifForm = true;
                                
                            } else if (newValue.equals("")) {
                                 titleA.setStyle("-fx-border-color : #FF0000");
                                label.setText("Title Field shouldnt be empty ! ");
                                label.setStyle("-fx-text-fill :#FF0000");
                                   label.setVisible(true);
                            } else {

                               
                                label.setStyle("-fx-text-fill :#000000");
                                titleA.setStyle("-fx-border-color : #0000");
                                verifForm = false;
                                label.setVisible(false);
                                
                            }
                        });
         
          contentA.textProperty().addListener((observable, oldValue, newValue) -> {
                            if (newValue.startsWith(" ")&&newValue.endsWith(" ")) {
                                    contentA.setStyle("-fx-border-color : #FF0000");
                                label.setText("Spaces Detected ! ");
                                label.setStyle("-fx-text-fill :#FF0000");
                                label.setVisible(true);
                                verifForm = true;
                                
                            } else if (newValue.equals("")) {
                                 contentA.setStyle("-fx-border-color : #FF0000");
                                label.setText("Title Field shouldnt be empty ! ");
                                label.setStyle("-fx-text-fill :#FF0000");
                                   label.setVisible(true);
                            } else {

                               
                                label.setStyle("-fx-text-fill :#000000");
                                contentA.setStyle("-fx-border-color : #0000");
                                verifForm = false;
                                label.setVisible(false);
                                
                            }
                        });
           photoA.textProperty().addListener((observable, oldValue, newValue) -> {
                            if (newValue.startsWith(" ")&&newValue.endsWith(" ")) {
                                    photoA.setStyle("-fx-border-color : #FF0000");
                                label.setText("Spaces Detected ! ");
                                label.setStyle("-fx-text-fill :#FF0000");
                                label.setVisible(true);
                                verifForm = true;
                                
                            } 
                            
                            else if (newValue.equals("")) {
                                 photoA.setStyle("-fx-border-color : #FF0000");
                                label.setText("Title Field shouldnt be empty ! ");
                                label.setStyle("-fx-text-fill :#FF0000");
                                   label.setVisible(true);
                            } else {

                               
                                label.setStyle("-fx-text-fill :#000000");
                                photoA.setStyle("-fx-border-color : #0000");
                                verifForm = false;
                                label.setVisible(false);
                                
                            }
                        });
           if(dateA.getValue()==null)
           {
           verifForm=true;
           }
           else{
           verifForm=false;
           }

         
         
         

    }    

    @FXML
    private void submitClicked(ActionEvent event) {
                    
                        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure To add this Actuality?", ButtonType.OK, ButtonType.CANCEL);
                        alert.initStyle(StageStyle.DECORATED);

                        Optional<ButtonType> result = alert.showAndWait();
                        if (result.get() == ButtonType.OK) {
                                        
                                    if(verifForm==false)
                                    {                                ActualityService ser=new ActualityService();
                                java.sql.Date d = java.sql.Date.valueOf(dateA.getValue());
                                Actuality a=new Actuality(titleA.getText() ,contentA.getText(),photoA.getText(),d);
                                  ser.add(a);
                                        dia.close();
                                        dia=new Stage();
                                       
                                    
                                    }
                                    else
                                    {
                                     Alert falseuuu = new Alert(Alert.AlertType.ERROR, "Fields shouldn't be empty !", ButtonType.OK);
                        falseuuu.initStyle(StageStyle.DECORATED);
                         Optional<ButtonType> resul = falseuuu.showAndWait();
                                    
                                    }
                        }
       
         
      
       }
    
    
}
