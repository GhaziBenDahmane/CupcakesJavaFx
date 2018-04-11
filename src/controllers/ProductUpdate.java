/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import com.jfoenix.controls.JFXButton;
import entity.Product;
import entity.Promotion;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import static org.apache.poi.hssf.usermodel.HeaderFooter.file;
import service.ProductService;
import service.PromotionService;

/**
 *
 * @author Arshavin
 */
public class ProductUpdate implements Initializable {

    private final ProductService product = new ProductService();
    private final PromotionService promo = new PromotionService();
    private ObservableList<String> listPromotions;

    @FXML
    private TextField id, barcode, name, category, price, description;

    @FXML
    private ComboBox promotion;
    
    @FXML
    private ImageView imagefx;
    @FXML
    private AnchorPane pane;
    @FXML
    private JFXButton add;
    @FXML
    private Button browse;

    private void setPromotion() {

        List<Promotion> promotions = new ArrayList<>();
        promotions = promo.selectAll();
        List<String> discounts = new ArrayList<>();
        for (Promotion p : promotions) {
            Double d = p.getDiscount() * 100;
            discounts.add(p.getId_promotion() + "-" + d.toString() + "%");
        }
        listPromotions = FXCollections.observableList(discounts);
        promotion.setItems(listPromotions);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        isNumber(barcode);
        isNumber(price);
        setPromotion();
        
    }

    public void setData(String id, String barcode, String name, String type, String price, String description,String promotion,Image image,String id_promo) {
        this.id.setText(id);
        this.barcode.setText(barcode);
        this.name.setText(name);
        this.price.setText(price);
        category.setText(type);
        this.description.setText(description);
        this.promotion.getSelectionModel().select(id_promo+"-"+promotion+"%");
        System.out.println(id_promo+"-"+promotion+"%");
        imagefx.setImage(image);
          


    }
   

    @FXML
    private void updateClicked(ActionEvent event) {
        if (price.getText().equals("") || name.getText().equals("") || category.getText().equals("")) {
            showAlert(Alert.AlertType.WARNING, "ALERT", null, "There is some empty fields !");
        } else {
            String newId = id.getText();
            String newBarcode = barcode.getText();
            String newName = name.getText();
            String newCategory = category.getText();
            String newPrice = price.getText();
            String newDescription = description.getText();
            Product p = new Product(Integer.parseInt(newId), Integer.parseInt(newBarcode), newName, newCategory, Double.parseDouble(newPrice), newDescription);
            product.update(p);
            if (product.isStatusUpdate()) {
                showAlert(Alert.AlertType.INFORMATION, "Succes", null, "Product  update successfuly !");
            } else {
                showAlert(Alert.AlertType.ERROR, "Error", null, "Product update fail !");
            }
        }
    }

    public void isNumber(TextField text) {
        text.setOnKeyReleased((javafx.scene.input.KeyEvent event) -> {
            if (!text.getText().matches("[0-9]*")) {
                showAlert(Alert.AlertType.WARNING, "Peringatan", null, "Hanya boleh angka !!");
                text.setText("");
                text.requestFocus();
            }
        });
    }

    public void showAlert(Alert.AlertType type, String title, String header, String text) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(text);
        alert.showAndWait();
    }
}
