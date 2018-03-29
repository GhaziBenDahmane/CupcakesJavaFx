/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cupcakesjavafx;

import entity.User;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import util.Views;

/**
 *
 * @author Arshavin
 */
public class CupCakesJavaFx extends Application {

    public static User loggedUser;

    @Override
    public void start(Stage stage) {
        Views views = new Views();
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/views/loginWindow.fxml"));
            stage.setTitle("login");
            Scene scene = new Scene(root);
            scene.setFill(javafx.scene.paint.Color.TRANSPARENT);
            stage.centerOnScreen();
            stage.setScene(scene);

            System.out.println("lmao");
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        System.out.println("lol");
        launch(args);
    }

}
