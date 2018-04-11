package controllers;

import animation.FadeOutLeftTransition;
import de.humatic.dsj.rc.RendererControls;
import entity.Product;
import entity.Promotion;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import service.ProductService;
import service.PromotionService;
import service.QRService;

public class ProductAddController implements Initializable {

    ObservableList<String> listPromotions ;
    ImageView myImageView;
    Image image;
    private Stage stage;
    private File file;

    ProductService product = new ProductService();
    
    @FXML
    private StackPane trans;

    @FXML
    private AnchorPane pane;

    @FXML
    private ComboBox promotion;

    @FXML
    private ImageView imagex;
    
    @FXML
    private Label start,end;
    
 

    @FXML
    private TextField barcode, name, category, price, description;
    
    PromotionService promo = new  PromotionService();
    private void clear() {
        name.setText("");
        category.setText("");
        barcode.setText("");
        description.setText("");
        price.setText("");
        promotion.requestFocus();
    }

    private void setPromotion() {
        
        List<Promotion> promotions =  new ArrayList<>();
        promotions = promo.selectAll();
        List<String> discounts = new ArrayList<>();
        for (Promotion p : promotions) {
            Double d= p.getDiscount()*100;
            discounts.add(p.getId_promotion()+"-"+d.toString()+"%");
                    }
        listPromotions=FXCollections.observableList(discounts);
        promotion.setItems(listPromotions);
    }

    private void setBarCode() {
        barcode.setOnKeyReleased((javafx.scene.input.KeyEvent event) -> {
            if (!barcode.getText().matches("[0-9]*")) {
                showAlert(Alert.AlertType.WARNING, "WARNING", null, " Only numbers are allowed");
                barcode.setText("");
                barcode.requestFocus();
            }
        });
    }

    private void setPrice() {
        price.setOnKeyReleased((javafx.scene.input.KeyEvent event) -> {
            if (!price.getText().matches("[0-9]*")) {
                showAlert(Alert.AlertType.WARNING, "WARNING", null, "Only numbers are allowed !");
                price.setText("");
                price.requestFocus();
            } else {
            }
        });
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        setBarCode();
        setPrice();
        clear();
        setPromotion();
        promotion.requestFocus();
        promotion.setOnKeyPressed(event -> {
            
            if (event.getCode() == KeyCode.ENTER) {
                try {
                    addProduct();
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(ProductAddController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        barcode.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                try {
                    addProduct();
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(ProductAddController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        price.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                try {
                    addProduct();
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(ProductAddController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

    }

    @FXML
    private void addClicked(ActionEvent event) throws FileNotFoundException {
        addProduct();
    }
    
    

    private void addProduct() throws FileNotFoundException {
        if (name.getText().equals("") || barcode.getText().equals("")
                || category.getText().equals("") || price.getText().equals("")) {
            showAlert(Alert.AlertType.WARNING, "ALERT", null, "There is some empty fields !");
        } else {
            Integer barcod = Integer.parseInt(barcode.getText());
            String nam = name.getText();
            String type = category.getText();
            String desc = description.getText();
            Double pric = Double.parseDouble(price.getText());
            String promot = promotion.getSelectionModel().getSelectedItem().toString();
            Integer id_promo=Integer.parseInt(promot.split("-")[0]);
            System.out.println(id_promo);
            Promotion promotion=promo.selectPromotionById(id_promo);
            Product p = new Product(nam, type, pric, 0, 0, "pas encore", desc, barcod,promotion);
            product.insert(p, file);
            showAlert(Alert.AlertType.INFORMATION, "Succes", null, "Insert with success !");
            clear();

        }
    }

    public void showAlert(Alert.AlertType type, String title, String header, String text) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(text);
        alert.showAndWait();
    }
    
    @FXML
    private void codeOnClick(MouseEvent event) {
       
        QRService qr = new QRService(barcode);
    }
    

    @FXML
    private void browse(ActionEvent event) {

        FileChooser fileChooser = new FileChooser();

        //Set extension filter
        FileChooser.ExtensionFilter extFilterJPG = new FileChooser.ExtensionFilter("JPG files (*.jpg)", "*.JPG");
        FileChooser.ExtensionFilter extFilterPNG = new FileChooser.ExtensionFilter("PNG files (*.png)", "*.PNG");
        fileChooser.getExtensionFilters().addAll(extFilterJPG, extFilterPNG);
        stage = (Stage) pane.getScene().getWindow();
        //Show open file dialog
        file = fileChooser.showOpenDialog(stage);

        image = new Image(file.getAbsoluteFile().toURI().toString(), imagex.getFitHeight(), imagex.getFitWidth(), true, true);
        imagex.setImage(image);
        imagex.setPreserveRatio(true);
        System.out.println(file.getAbsolutePath() + imagex);

    }
    
   

}
