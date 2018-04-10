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
            Parent root = FXMLLoader.load(getClass()
                    .getResource(Views.LOGIN));
            stage.setTitle("login");
            Scene scene = new Scene(root);
            scene.setFill(javafx.scene.paint.Color.TRANSPARENT);
            stage.centerOnScreen();
            stage.setScene(scene);
            stage.show();

        } catch (Exception e) {
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
