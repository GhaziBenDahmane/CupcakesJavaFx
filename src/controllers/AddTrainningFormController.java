/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import com.jfoenix.controls.JFXTextField;
import com.teknikindustries.bulksms.SMS;
import entity.Formation;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import service.FormationService;
import service.UploadService;

/**
 * FXML Controller class
 *
 * @author ding
 */
public class AddTrainningFormController implements Initializable {

    @FXML
    private AnchorPane pane;
    @FXML
    private Button browseVideo;
    @FXML
    private JFXTextField name;
    @FXML
    private JFXTextField status;
    @FXML
    private DatePicker startingDate;
    @FXML
    private JFXTextField videoText;
    @FXML
    private DatePicker expirationDate;
    private File selectedfile;
    String path_img;
    UploadService u = new UploadService();
    Formation f = new Formation();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void browse(ActionEvent event) throws FileNotFoundException, IOException {
        FileChooser fc = new FileChooser();
        fc.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Video", "*.jpg", "*.png", "*.mp4")
        );
        selectedfile = fc.showOpenDialog(null);
        if (selectedfile != null) {
            System.out.println(selectedfile.getName());
            FileInputStream inp = new FileInputStream(selectedfile.getPath());
            System.out.println(selectedfile.getName());
            // ImageView a = new ImageView(new Image(inp));
            //a.setFitHeight(150);
            //a.setFitWidth(100);

            // paneImage.getChildren().add(a);
            String path = "C:/wamp/www/CupcakesWeb/web/uploads/";
            videoText.setText(selectedfile.getName());
            Files.copy(selectedfile.toPath(),
                    (new File(path + selectedfile.getName())).toPath(),
                    StandardCopyOption.REPLACE_EXISTING);
            f.setVideo(selectedfile.getName());
            System.out.println("sssssssssssssssss");
        } else {
            System.out.println("FICHIER erroné");
        }

    }

    @FXML
    private void inputClicked(ActionEvent event) throws SQLException {

        FormationService sv = new FormationService();
        //Ajout d'un entier
        Date d1 = java.sql.Date.valueOf(startingDate.getValue());
        Date d2 = java.sql.Date.valueOf(expirationDate.getValue());
        Date d3 = java.sql.Date.valueOf(LocalDate.now());

        f.setNom(name.getText());
        f.setStatus(status.getText());
        f.setStart_date_formation(d1);
        f.setEnd_date_formation(d2);
        if (d1.before(d3)) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("ERREUR");
            alert.setHeaderText("Error Dialog");
            alert.setContentText("Verifier la date!");
            alert.showAndWait();

        } else {
            SMS smsTut = new SMS();
            smsTut.SendSMS("omarkriaa", "151JMT1842", "heeey", "+216 22595913", "https://bulksms.vsms.net/eapi/submission/send_sms/2/2.0");

            sv.AjouterFormation(f);

            System.out.println("bonnnnn");

        }

    }

}
