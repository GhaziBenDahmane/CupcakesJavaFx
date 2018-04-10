/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import static javafx.scene.media.MediaPlayer.Status.PLAYING;
import javafx.scene.media.MediaView;

/**
 * FXML Controller class
 *
 * @author ding
 */
public class videoTrainningController implements Initializable {

    @FXML
    private AnchorPane pane;
    @FXML
    private MediaView mv;
    @FXML
    private JFXButton play;
    @FXML
    private JFXButton stop;
    MediaPlayer mediaplayer;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        String VUrl = "file:/C:/Users/asus/Desktop/Formation/src/images/videoplayback.mp4";
        Media media = new Media(VUrl);
        mediaplayer = new MediaPlayer(media);
        mv.setFitHeight(100);
        mv.setFitWidth(120);
        mv.setMediaPlayer(mediaplayer);
        // TODO
    }

    @FXML
    private void playClicked(ActionEvent event) {
        if (mediaplayer.getStatus() == PLAYING) {
            mediaplayer.play();
            mediaplayer.stop();
        } else {
            mediaplayer.play();
        }
    }

    @FXML
    private void stopClicked(ActionEvent event) {
        mediaplayer.stop();
    }

}
