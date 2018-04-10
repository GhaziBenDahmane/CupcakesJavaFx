/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextArea;
import cupcakesjavafx.CupCakesJavaFx;
import entity.Claim;
import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import service.ClaimService;
import service.UserService;
import util.Util;

/**
 * FXML Controller class
 *
 * @author ding
 */
public class addClaimController implements Initializable {

    @FXML
    private AnchorPane dataUangMasuk;
    @FXML
    private AnchorPane blur;
    @FXML
    private JFXComboBox<Label> claimType;
    @FXML
    private JFXButton Add;
    @FXML
    private JFXTextArea claimText;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        claimType.getItems().add(new Label("Technical Problem"));
        claimType.getItems().add(new Label("Commercial Problem"));
    }

    @FXML
    private void comboBoxChanged(ActionEvent event) {
    }

    @FXML
    private void addClicked(ActionEvent event) {
        ClaimService cs = new ClaimService();
        UserService us = new UserService();
        System.out.println(claimType.getValue().getText());
        if (claimType.getValue() != null && !claimText.getText().isEmpty()) {
            Claim a = new Claim(CupCakesJavaFx.loggedUser, claimText.getText(), new Date(), claimType.getValue().getText());
            cs.add(a);
            Util.showInfo("Claim Added");

        } else {
            Util.showError("Missing data");

        }

    }

}
