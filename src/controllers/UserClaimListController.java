/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import animation.FadeInRightTransition;
import animation.FadeOutLeftTransition;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import entity.Claim;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import service.ClaimService;
import service.UserService;
import util.Views;

/**
 * FXML Controller class
 *
 * @author ding
 */
public class UserClaimListController implements Initializable {

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
    private JFXComboBox<Label> filter;
    @FXML
    private JFXComboBox<?> bulan;
    @FXML
    private JFXTextField tahun;
    @FXML
    private JFXButton Add1;
    @FXML
    private JFXButton Add;
    @FXML
    private JFXTextField type;
    @FXML
    private JFXTextField description;
    @FXML
    private JFXTextField postedOn;
    private Label answered;
    @FXML
    private TextArea answer;
    @FXML
    private FontAwesomeIconView next;
    @FXML
    private FontAwesomeIconView previous;
    private ClaimService cs = new ClaimService();
    List<Claim> cm;
    int currentIndex = 0;
    UserService us;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        us = new UserService();
        filter.getItems().add(new Label("Answered"));
        filter.getItems().add(new Label("Not Answered"));
        refreshClicked(null);

    }

    @FXML
    private void tombolClose(ActionEvent event) {
        refreshClicked(null);
        blur.setEffect(null);
        new FadeOutLeftTransition(trans).play();
    }

    @FXML
    private void comboBoxChanged(ActionEvent event) {
        cm = cs.getByUser(cupcakesjavafx.CupCakesJavaFx.loggedUser);

        previous.setVisible(false);

        if (filter.getValue().getText().equals("Answered")) {
            cm = cm.stream().filter(e -> e.getAnswer() != null && !e.getAnswer().isEmpty())
                    .collect(Collectors.toList());
        } else {
            cm = cm.stream().filter(e -> e.getAnswer() == null || e.getAnswer().isEmpty())
                    .collect(Collectors.toList());
        }
        System.out.println(cm.size());
        this.currentIndex = 0;
        if (cm.size() <= 1) {
            next.setVisible(false);
        } else {
            next.setVisible(true);

        }
        loadData();

    }

    @FXML
    private void refreshClicked(ActionEvent event) {
        previous.setVisible(false);
        currentIndex = 0;
        cm = cs.getByUser(cupcakesjavafx.CupCakesJavaFx.loggedUser);
        //cm = cs.getByUser(us.get(1));

        if (cm.size() <= 1) {
            next.setVisible(false);
        }
        filter.setValue(null);
        if (cm.size() >= 1) {
            loadData();
        }
    }

    @FXML
    private void addClicked(ActionEvent event) throws IOException {
        blur.setEffect(new GaussianBlur(10));
        new FadeInRightTransition(trans).play();
        AnchorPane pane = FXMLLoader.load(getClass().getResource(Views.ADD_CLAIM));
        loadPane.getChildren().setAll(pane);
    }

    @FXML
    private void next(MouseEvent event) {
        this.currentIndex++;
        if (this.currentIndex == cm.size() - 1) {
            next.setVisible(false);
        }
        if (this.currentIndex > 0) {
            previous.setVisible(true);
        }
        loadData();

    }

    @FXML
    private void previous(MouseEvent event) {
        this.currentIndex--;
        if (this.currentIndex == 0) {
            previous.setVisible(false);
        }
        if (this.currentIndex < cm.size() - 1) {
            next.setVisible(true);
        }
        loadData();
    }

    private void loadData() {
        Claim claim = cm.get(this.currentIndex);
        type.setText(claim.getType());
        description.setText(claim.getDescription());
        postedOn.setText(claim.getPostedOn().toString());
        if (claim.getAnswer() != null && !claim.getAnswer().isEmpty()) {
            answer.setText(claim.getAnswer());

        } else {
            answer.setText("");

        }
    }

}
