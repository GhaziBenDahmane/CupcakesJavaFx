/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import entity.Claim;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import service.ClaimService;
import service.NotificationService;
import service.SMSService;
import util.Util;

/**
 * FXML Controller class
 *
 * @author ding
 */
public class AnswerClaimFormController implements Initializable {

    @FXML
    private AnchorPane pane;
    @FXML
    private JFXTextArea answer;
    @FXML
    private JFXButton submit_btn;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void submitAction(ActionEvent event) {
        if (answer.getText().isEmpty()) {
            Util.showError("Please enter a valid answer!");

        } else {
            ClaimService cs = new ClaimService();
            Claim claim = ClaimListController.sselectedItem.getClaim();
            cs.answer(claim, answer.getText());
            Util.showInfo("Claim answered!");
            NotificationService.successBlueNotification("Answer Submitted!", "Answer Submitted!");
            SMSService.sendMessage(claim.getClient().getPhone(), "Your claim was answered!");

        }

    }

}
