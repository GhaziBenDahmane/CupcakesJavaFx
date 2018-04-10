/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import com.jfoenix.controls.JFXTextField;
import entity.Formation;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
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
public class updateTrainningFormController implements Initializable {

    @FXML
    private AnchorPane pane;
    @FXML
    private JFXTextField name;
    @FXML
    private Button browseVideo;
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
        Formation selectedItem = TrainningListController.selectedItem;
        name.setText(selectedItem.getNom());
        status.setText(selectedItem.getStatus());
        videoText.setText(selectedItem.getVideo());
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

        Date d1 = java.sql.Date.valueOf(startingDate.getValue());

        Date d2 = java.sql.Date.valueOf(expirationDate.getValue());
        int y = TrainningListController.selectedItem.getId();

        Formation e1 = new Formation(TrainningListController.selectedItem.getId(), name.getText(), videoText.getText(), d1, d2, status.getText());

        sv.ModifierFormation(e1, y);
    }

}
