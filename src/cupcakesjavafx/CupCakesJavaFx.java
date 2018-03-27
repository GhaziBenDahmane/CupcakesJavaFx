/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cupcakesjavafx;

import entity.Contact;
import entity.Product;
import service.CartService;
import service.ContactService;
import service.ProductService;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author Arshavin
 */
public class CupCakesJavaFx extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        Button btn = new Button();
        btn.setText("Say 'Hello World'");
        Contact c1 = new Contact(71444521, "STYLESHEET","MODENA"," STYLESHEET_MODENA"," STYLESHEET_CASPIAN"," STYLESHEET@CASPIAN.com",false);
        Product p1 = new Product("TESTJAVA","TESTJAVA",12.5,"TESSSST","TESSSST");
        ProductService s = new ProductService();
        ContactService cs1 = new ContactService();
        s.insert(p1);
        cs1.create(c1);
        CartService cart =new CartService();
        btn.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                System.out.println("Hello World!");
                System.out.println(s.selectAll());
                p1.setName("TEST UPDATE");
                System.out.println(s.selectProductById(123));
                s.update(p1);
                s.delete(127);
                System.out.println(cart.selectAllProductsFromCart());
            }
        });
        
        StackPane root = new StackPane();
        root.getChildren().add(btn);
        
        Scene scene = new Scene(root, 300, 250);
        
        primaryStage.setTitle("Hello World!");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
