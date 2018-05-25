/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import com.jfoenix.controls.JFXButton;
import entity.Cart;
import entity.Product;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Pagination;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import service.CartService;
import service.ProductService;
import service.StripeService;

/**
 * FXML Controller class
 *
 * @author Arshavin
 */
public class ListProductController implements Initializable {

    @FXML
    private ImageView imagefx;
    @FXML
    private Label title;
    @FXML
    private Label category;
    @FXML
    private Label description;
    @FXML
    private Label price;
    @FXML
    private Label promotion;
    @FXML
    private Label t;
    @FXML
    private Label c;
    @FXML
    private Label d;
    @FXML
    private Label p;
    @FXML
    private Label promo;
    @FXML
    private StackPane pane;
    @FXML
    private JFXButton cart;
    @FXML
    private TableView<Cart> carts;
    @FXML
    private Label priceproducts;
    @FXML
    private TableColumn<Cart, Integer> idcart;

    @FXML
    private TableColumn<Cart, String> nameproduct;

    @FXML
    private TableColumn<Cart, String> priceproduct;

    @FXML
    private TableColumn<Cart, String> promot;

    @FXML
    private TableColumn<Cart, String> total;

    @FXML
    private TableColumn<Cart, Integer> quantity;
    private AnchorPane anchor = new AnchorPane();
     Stage pageStage = new Stage();

    Product po ;
    private Pagination pagination;
    private  ProductService ps = new ProductService();
    private  CartService cs = new CartService();
    private List<Product> products = new ArrayList<>();
    private Image image;
    private Product product;
    private List<Cart> allCarts;
     

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        System.out.println("ooooo");
        
       
        openPageWindow();
    }

    private void openPageWindow() {
        products = ps.selectAll();
        int numOfPage = products.size();
        System.out.println(numOfPage);

        pagination = new Pagination(numOfPage);
        pagination.setPageFactory(new Callback<Integer, Node>() {
            int i = 0;

            @Override
            public Node call(Integer pageIndex) {
                int i = pagination.currentPageIndexProperty().get();
                price.setText(products.get(i).getPrice().toString());
                title.setText(products.get(i).getName());
                description.setText(products.get(i).getDescription());
                category.setText(products.get(i).getType());
                try {
                    image = ps.selectImageById(products.get(i).getId());
                } catch (IOException ex) {
                    Logger.getLogger(ListProductController.class.getName()).log(Level.SEVERE, null, ex);
                }
                imagefx.setImage(image);
                Double d = (products.get(i).getPromotion().getDiscount() * 100);

                promotion.setText(d.toString() + "%");
                cart.setOnAction((ActionEvent event) -> {

                    try {
                        product = ps.selectProductById(products.get(i).getId());
                    } catch (IOException ex) {
                        Logger.getLogger(ListProductController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    System.out.println(product.getId());
                    Cart cart = new Cart(product);
                    cs.insert(cart);

                });

                return createPage(pageIndex);

            }
        });

        AnchorPane.setTopAnchor(pagination, 10.0);
        AnchorPane.setRightAnchor(pagination, 10.0);
        AnchorPane.setBottomAnchor(pagination, 10.0);
        AnchorPane.setLeftAnchor(pagination, 10.0);
        anchor.getChildren().add(pagination);

        anchor.getChildren().add(title);
        anchor.getChildren().add(category);
        anchor.getChildren().add(price);
        anchor.getChildren().add(description);
        anchor.getChildren().add(imagefx);
        anchor.getChildren().add(promotion);
        anchor.getChildren().add(cart);
        anchor.getChildren().add(p);
        anchor.getChildren().add(d);
        anchor.getChildren().add(t);
        anchor.getChildren().add(promo);
        anchor.getChildren().add(c);

        Scene scene = new Scene(anchor, 550, 500);

        pageStage.setTitle("Products List");
        pageStage.setScene(scene);
        
        

       

    }

    public void loadTable() {
         
        allCarts = cs.selectAllProductsFromCart();
                
       
        for (int i = 0; i < allCarts.size(); i++) {
            System.out.println(allCarts.get(i) + "lalala");
        }
        idcart.setCellValueFactory(new PropertyValueFactory<>("id_cart"));
        quantity.setEditable(true);
        quantity.setCellValueFactory(new PropertyValueFactory<>("1"));

        nameproduct.setCellValueFactory((TableColumn.CellDataFeatures<Cart, String> param) -> {

            try {
                po = po = cs.selectCartById(param.getValue().getProducts().getId());
            } catch (IOException ex) {
                Logger.getLogger(ListProductController.class.getName()).log(Level.SEVERE, null, ex);
            }

            return new SimpleStringProperty(po.getName());
        });
        priceproduct.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Cart, String>, ObservableValue<String>>() {

            public ObservableValue<String> call(TableColumn.CellDataFeatures<Cart, String> param) {
                try {
                    po = po = cs.selectCartById(param.getValue().getProducts().getId());
                    System.out.println(param.getValue().getProducts().getId()+"yes");
                    
                } catch (IOException ex) {
                    Logger.getLogger(ListProductController.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                
                return new SimpleStringProperty(po.getPrice().toString());
            }
        });
        promot.setCellValueFactory((TableColumn.CellDataFeatures<Cart, String> param) -> {

            try {
               po= po = cs.selectCartById(param.getValue().getProducts().getId());
                 System.out.println(param.getValue().getProducts().getId()+"yes");
                System.out.println(po);
            } catch (IOException ex) {
                Logger.getLogger(ListProductController.class.getName()).log(Level.SEVERE, null, ex);
            }
              Double dos = 0.1 * 100;
            return new SimpleStringProperty(dos.toString() + "%");
        });

        float s = 0;
        List<Cart> por = cs.selectAllProductsFromCart();
        for (Cart por1 : por) {
            Double a = por1.getProducts().getPrice() * por1.getProducts().getPromotion().getDiscount();
            float e = por1.getProducts().getPrice().floatValue() - a.floatValue();
            s = s + e;
        }
        priceproducts.setText(Float.toString(s));

        total.setCellValueFactory((TableColumn.CellDataFeatures<Cart, String> param) -> {
            try {
               Product po = ps.selectProductById(param.getValue().getProducts().getId());

            } catch (IOException ex) {
                Logger.getLogger(ListProductController.class.getName()).log(Level.SEVERE, null, ex);
            }
            Double d = 0.1 * po.getPrice();
            float f = po.getPrice().floatValue() - d.floatValue();
            return new SimpleStringProperty(Float.toString(f));
        });

        carts.setItems(FXCollections.observableArrayList(allCarts));

    }

    public VBox createPage(int index) {

        VBox pageBox = new VBox();
        pageBox.getChildren().add(pane);
        return pageBox;
    }

    @FXML
    private void deleteOnClick(ActionEvent event) throws IOException {

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText("ID:\t\t " + carts.getSelectionModel().getSelectedItem().getId_cart()
                + "\nName:\t " + carts.getSelectionModel().getSelectedItem().getProducts().getName()
        );
        alert.setContentText("Do you want to delete this product ?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            cs.delete(Integer.parseInt(carts.getSelectionModel().getSelectedItem().getId_cart().toString()));
            cs.setIsDeleteStatus(true);
            if (cs.isIsDeleteStatus()) {
                showAlert(Alert.AlertType.INFORMATION, "Succes", null, "Product was deleted successfuly !");
                loadTable();
            } else {
                showAlert(Alert.AlertType.ERROR, "Error", null, "Deleting data failed");
            }
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
    private void refreshOnClick(ActionEvent event) {
        loadTable();
    }

    @FXML
    private void showOnClick(ActionEvent event) {
    pageStage.show();
    }

    @FXML
    private void purchaseOnClick(ActionEvent event) {
        Double d = Double.parseDouble(priceproducts.getText());
        Integer a = d.intValue();
        StripeService s = new StripeService(a);

    }

    public class Std {

        private SimpleStringProperty idcart;
        private SimpleStringProperty idproduct;
        private TextField quantity;

        private Cart cart;

        public SimpleStringProperty idcart() {
            return idcart;
        }

        public SimpleStringProperty idproduct() {
            return idproduct;
        }

        public Std(Cart cart) {
            this.cart = cart;
            this.idcart
                    = idcart = new SimpleStringProperty(cart.getId_cart().toString());
            this.idproduct = new SimpleStringProperty(cart.getProducts().getId().toString());

        }

        public String getIdcart() {
            return idcart.get();
        }

        public void setIdcart(SimpleStringProperty idcart) {
            this.idcart = idcart;
        }

        public String getIdproduct() {
            return idproduct.get();
        }

        public void setIdproduct(SimpleStringProperty idproduct) {
            this.idproduct = idproduct;
        }

        public Cart getCart() {
            return cart;
        }

        public void setCart(Cart cart) {
            this.cart = cart;
        }

    }

}
