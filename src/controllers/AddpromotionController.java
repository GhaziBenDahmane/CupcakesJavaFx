/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXSlider;
import com.jfoenix.controls.JFXTextField;
import static controllers.ActualityController.dia;
import entity.Promotion;
import java.net.URL;
import java.time.LocalDate;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DateCell;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import service.PromotionService;

/**
 * FXML Controller class
 *
 * @author Yasmine
 */
public class AddpromotionController implements Initializable {
Boolean verifForm;
    @FXML
    private AnchorPane pane;
    @FXML
    private JFXSlider discount;
    @FXML
    private JFXDatePicker startingdate;
    @FXML
    private JFXDatePicker endingdate;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
          
         startingdate.setDayCellFactory((picker) -> new DateCell() {
                            public void updateItem(LocalDate date, boolean empty) {
                                super.updateItem(date, empty);
                                LocalDate today = LocalDate.now();
                                
                                setDisable(empty || date.compareTo(today) < 0);
                            }
                        });
         endingdate.setDayCellFactory((picker) -> new DateCell() {
                            public void updateItem(LocalDate date, boolean empty) {
                                super.updateItem(date, empty);
                                LocalDate today = LocalDate.now();
                                
                                setDisable(empty || date.compareTo(today) < 0);
                            }
                        });
         
          startingdate.valueProperty().addListener((ov, oldValue, newValue) -> {

                            // System.out.println("date changed !"+oldValue +"to  "+newValue);

                                    if(newValue!=null)
                  {
                  
                                endingdate.setDisable(false);
                                
                  
                  }
                  else{
                                    
                                        Tooltip t =new Tooltip("you should pick the begining date first !");
                                        Tooltip.install(startingdate, t);
                                    }
          });
          discount.valueProperty().addListener((observable,oldv,newval) -> {
              
               System.out.println("Value changing from : " + oldv+"\n"+
                       
                       "To new Value  : "+newval);
          });
          System.out.println(discount.getValue());
       
    
    }    

    @FXML
    private void inputClicked(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure To add this Actuality?", ButtonType.OK, ButtonType.CANCEL);
                        alert.initStyle(StageStyle.DECORATED);

                        Optional<ButtonType> result = alert.showAndWait();
                        if (result.get() == ButtonType.OK) {
                                        
                                    
                                        PromotionService ser=new PromotionService();
                                java.sql.Date d = java.sql.Date.valueOf(startingdate.getValue());
                                java.sql.Date dd = java.sql.Date.valueOf(endingdate.getValue());
                            
                                        Promotion p = new Promotion(discount.getValue(),d,dd);
                                  ser. insert(p);
                dia.close();
                dia=new Stage();
        
                 }
                                    
                                    
                                    }
                        }
    
    
