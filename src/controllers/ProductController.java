/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import animation.FadeInRightTransition;
import animation.FadeOutLeftTransition;
import util.DataSource;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import service.ProductService;
import entity.Product;
import entity.Promotion;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import service.CartService;
import service.NotificationService;
import service.PromotionService;
import util.Util;

public class ProductController implements Initializable {

    ObservableList<String> sortList = FXCollections.observableArrayList("ID", "Name", "Category", "Price");

    @FXML
    private TableView<Std> products;

    @FXML
    private TableColumn<Std, Integer> id;

    @FXML
    private TableColumn<Std, Integer> barcode;

    @FXML
    private TableColumn<Std, String> description;

    @FXML
    private TableColumn<Std, String> name;

    @FXML
    private TableColumn<Std, String> type;

    @FXML
    private TableColumn<Std, Double> price;

    @FXML
    private TableColumn<Std, String> promotion;

    private ObservableList<Std> data;

    @FXML
    private AnchorPane utama;

    @FXML
    private AnchorPane blur;

    @FXML
    private AnchorPane loadPane;

    @FXML
    private AnchorPane dataUangKeluar;

    @FXML
    private StackPane trans;

    @FXML
    private Group groups;

    @FXML
    private ContextMenu contextMenu;

    @FXML
    private CheckBox check;

    @FXML
    private ComboBox sort;

    @FXML
    private TextField filter;
    private FilteredList filtered;
    List<Std> allProducts;

    private final ProductService product = new ProductService();
    private String promotion_id = "", id_product = "", name_product = "", category_product = "", price_product = "", barcode_product = "", description_product = "", promotion_product = "";

    private void setFilter() {
        sort.setValue("ID");
        sort.setItems(sortList);
    }

    private void setStyleTable() {
        id.setStyle("-fx-alignment: CENTER");
        barcode.setStyle("-fx-alignment: CENTER");
        name.setStyle("-fx-alignment: CENTER");
        type.setStyle("-fx-alignment: CENTER");
        promotion.setStyle("-fx-alignment: CENTER");
        price.setStyle("-fx-alignment: CENTER");
    }

    @FXML
    private void DoubleClick(MouseEvent event) throws IOException, SQLException {
        if (event.getClickCount() == 1) {
            id_product = products.getSelectionModel().getSelectedItem().getId();
            barcode_product = products.getSelectionModel().getSelectedItem().getBarcode();
            name_product = products.getSelectionModel().getSelectedItem().getName();
            category_product = products.getSelectionModel().getSelectedItem().getType();
            price_product = products.getSelectionModel().getSelectedItem().getPrice();
            description_product = products.getSelectionModel().getSelectedItem().getDescription();
           // d=Double.parseDouble(products.getSelectionModel().getSelectedItem().getDiscount())*100;
            // promotion_product = d.toString();
            promotion_id = products.getSelectionModel().getSelectedItem().getPromotion_Id();

        } else if (event.getClickCount() == 2) {

            id_product = products.getSelectionModel().getSelectedItem().getId();
            barcode_product = products.getSelectionModel().getSelectedItem().getBarcode();
            name_product = products.getSelectionModel().getSelectedItem().getName();
            category_product = products.getSelectionModel().getSelectedItem().getType();
            price_product = products.getSelectionModel().getSelectedItem().getPrice();
            description_product = products.getSelectionModel().getSelectedItem().getDescription();
          //  d= Double.parseDouble(products.getSelectionModel().getSelectedItem().getDiscount())*100;
            // promotion_product = d.toString();
            promotion_id = products.getSelectionModel().getSelectedItem().getPromotion_Id();
            updateProduct();

        } else if (event.getButton() == MouseButton.SECONDARY) {
            contextMenu.show(products, event.getScreenX(), event.getScreenY());
        }
    }

    @FXML
    private void addOnClick() throws IOException {
        blur.setEffect(new GaussianBlur(10));
        new FadeInRightTransition(trans).play();
        AnchorPane pane = FXMLLoader.load(getClass().getResource("/views/productAdd.fxml"));
        loadPane.getChildren().setAll(pane);
    }

    @FXML
    private void updateOnclick(ActionEvent event) throws IOException, SQLException {

        updateProduct();

    }

    @FXML
    private void pdfClicked(ActionEvent event) {
        FileChooser chooser = new FileChooser();
        chooser.setTitle("Save to PDF");
        File file = chooser.showSaveDialog(new Stage());
        if (!file.getAbsolutePath().endsWith(".pdf")) {
            toPDF(file.getAbsolutePath() + ".pdf");
        } else {
            toPDF(file.getAbsolutePath());
        }
    }

    private void toPDF(String file) {
        try {
            List<String> content = new ArrayList<>();
            content.add("id");
            content.add("barcode");
            content.add("name");
            content.add("type");

            content.add("price");

            products.getItems().stream().forEach(e -> {
                content.add(e.getId());
                content.add(e.getBarcode());
                content.add(e.getName());
                content.add(e.getType());
                content.add(e.getPrice());

            });
            PDFService.toPDF(file, "List of Products", content, 5);

            NotificationService.successBlueNotification("Export finished!", "Claims exported to " + file);

        } catch (Exception e) {
            Util.showError("Export failed");

        }
    }

    @FXML
    private void deleteOnClick(ActionEvent event) throws IOException {

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText("ID:\t\t " + products.getSelectionModel().getSelectedItem().getId()
                + "\nName:\t " + products.getSelectionModel().getSelectedItem().getName()
                + "\nCategory:\t " + products.getSelectionModel().getSelectedItem().getType());
        alert.setContentText("Do you want to delete this product ?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            CartService cs = new CartService();
            cs.deleteProductFromCart(Integer.parseInt(products.getSelectionModel().getSelectedItem().getId()));
            product.delete(Integer.parseInt(products.getSelectionModel().getSelectedItem().getId()));
            product.setStatusDelete(true);
            if (product.isStatusDelete()) {
                showAlert(Alert.AlertType.INFORMATION, "Succes", null, "Product was deleted successfuly !");
                loadTable();
            } else {
                showAlert(Alert.AlertType.ERROR, "Error", null, "Deleting data failed");
            }
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setFilter();
        setStyleTable();
        loadTable();
        filtered = new FilteredList(FXCollections.observableArrayList(allProducts), e -> true);

    }

    public void loadTable() {

        allProducts = product.selectAll()
                .stream()
                .map(e -> new Std(e))
                .collect(Collectors.toList());
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        barcode.setCellValueFactory(new PropertyValueFactory<>("barcode"));
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        type.setCellValueFactory(new PropertyValueFactory<>("type"));
        price.setCellValueFactory(new PropertyValueFactory<>("price"));
        description.setCellValueFactory(new PropertyValueFactory<>("description"));
        promotion.setCellValueFactory((CellDataFeatures<Std, String> param) -> {
            PromotionService ps = new PromotionService();
            Promotion promo = ps.selectPromotionById(Integer.parseInt(param.getValue().getPromotion_Id()));
            Double d = promo.getDiscount() * 100;
            return new SimpleStringProperty(d.toString() + "%");
        });

        products.setItems(FXCollections.observableArrayList(allProducts));

    }

    private void updateProduct() throws IOException, SQLException {
        FXMLLoader Loader = new FXMLLoader();
        Loader.setLocation(getClass().getResource("/views/productUpdate.fxml"));
        blur.setEffect(new GaussianBlur(10));
        new FadeInRightTransition(trans).play();
        AnchorPane pane = Loader.load();
        ProductUpdate p = Loader.getController();

        Image pro = product.selectImageById(1);

        p.setData(id_product, barcode_product, name_product, category_product, price_product, description_product, promotion_product, pro, promotion_id);
        loadPane.getChildren().setAll(pane);
    }

    public void showAlert(Alert.AlertType type, String title, String header, String text) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(text);
        alert.showAndWait();
    }

    @FXML
    private void closeOnClick(ActionEvent event) {
        loadTable();
        blur.setEffect(null);
        new FadeOutLeftTransition(trans).play();

    }

    @FXML
    private void filterOnClick(KeyEvent event) {
        filter.textProperty().addListener((observable, oldValue, newValue) -> {
            filtered.setPredicate((Predicate<? super Std>) (Std std) -> {
                if (newValue.isEmpty() || newValue == null) {
                    return true;
                } else if (std.getBarcode().contains(newValue)) {
                    return true;
                }
                return false;
            });
        });
        SortedList sortedList = new SortedList(filtered);
        sortedList.comparatorProperty().bind(products.comparatorProperty());
        products.setItems(sortedList);

    }

    @FXML
    private void refreshOnClick(ActionEvent event) {
        loadTable();
    }

    public class Std {

        private SimpleStringProperty id;
        private SimpleStringProperty barcode;
        private SimpleStringProperty name;
        private SimpleStringProperty type;
        private SimpleStringProperty price;
        private SimpleStringProperty description;
        private SimpleStringProperty promotion_id;
        private SimpleStringProperty discount;
        private Product product;

        public Std(Product product) {
            this.product = product;
            this.id
                    = id = new SimpleStringProperty(product.getId().toString());
            this.barcode = new SimpleStringProperty(product.getBarcode().toString());
            this.name = new SimpleStringProperty(product.getName());
            this.type = new SimpleStringProperty(product.getType());
            this.price = new SimpleStringProperty(product.getPrice().toString());
            this.promotion_id = new SimpleStringProperty(product.getPromotion().getId_promotion().toString());
            this.description = new SimpleStringProperty(product.getDescription());
        }

        public SimpleStringProperty id() {
            return id;
        }

        public SimpleStringProperty description() {
            return barcode;
        }

        public SimpleStringProperty type() {
            return type;
        }

        public SimpleStringProperty price() {
            return price;
        }

        public SimpleStringProperty name() {
            return name;
        }

        public String getId() {
            return id.get();
        }

        public void setId(SimpleStringProperty id) {
            this.id = id;
        }

        public String getBarcode() {
            return barcode.get();
        }

        public void setBarcode(SimpleStringProperty barcode) {
            this.barcode = barcode;
        }

        public String getName() {
            return name.get();
        }

        public void setName(SimpleStringProperty name) {
            this.name = name;
        }

        public String getType() {
            return type.get();
        }

        public void setType(SimpleStringProperty type) {
            this.type = type;
        }

        public String getPrice() {
            return price.get();
        }

        public void setPrice(SimpleStringProperty price) {
            this.price = price;
        }

        public String getPromotion_Id() {
            return promotion_id.get();
        }

        public void setPromotion_Id(SimpleStringProperty promotion_id) {
            this.promotion_id = promotion_id;
        }

        public String getDescription() {
            return description.get();
        }

        public void setDescription(SimpleStringProperty description) {
            this.description = description;
        }

    }

}
