/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import animasi.FadeInRightTransition;
import animasi.FadeOutLeftTransition;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import populator.UserMaster;
import service.UserService;
import util.Views;

/**
 * FXML Controller class
 *
 * @author ding
 */
public class UserListController implements Initializable {

    @FXML
    private StackPane trans;
    @FXML
    private Group groups;
    @FXML
    private AnchorPane loadPane;
    @FXML
    private AnchorPane dataUangMasuk;
    @FXML
    private AnchorPane blur;
    @FXML
    private JFXComboBox<Label> filter;
    @FXML
    private JFXTextField Date;
    @FXML
    private JFXDatePicker date;
    @FXML
    private JFXComboBox<?> bulan;
    @FXML
    private JFXTextField tahun;
    @FXML
    private JFXButton refreshbtn;
    @FXML
    private JFXTextField search;
    @FXML
    private JFXButton Add;
    @FXML
    private TableView<UserMaster> tableView;
    @FXML
    private ContextMenu contextMenu;
    @FXML
    private TableColumn<UserMaster, String> username;
    @FXML
    private TableColumn<UserMaster, String> email;
    @FXML
    private TableColumn<UserMaster, String> password;
    @FXML
    private TableColumn<UserMaster, String> lastLogin;
    @FXML
    private TableColumn<UserMaster, String> Role;
    @FXML
    private TableColumn<UserMaster, String> phone;
    @FXML
    private TableColumn<UserMaster, String> picture;
    private List<UserMaster> cm;
    private UserService cs;
    public static UserMaster selectedUser;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        filter.getItems().add(new Label("User"));
        filter.getItems().add(new Label("Admin"));
        filter.setPromptText("");
        tableView.setEditable(true);
        cs = new UserService();
        cm = cs.getAll()
                .stream()
                .map(e -> new UserMaster(e))
                .collect(Collectors.toList());

        PropertyValueFactory<UserMaster, String> usernameProp
                = new PropertyValueFactory<>("username");
        username.setCellValueFactory(usernameProp);

        PropertyValueFactory<UserMaster, String> emailProp
                = new PropertyValueFactory<>("email");
        email.setCellValueFactory(emailProp);

        PropertyValueFactory<UserMaster, String> passwordProp
                = new PropertyValueFactory<>("password");
        password.setCellValueFactory(passwordProp);

        PropertyValueFactory<UserMaster, String> lastLoginProp
                = new PropertyValueFactory<>("lastLogin");
        lastLogin.setCellValueFactory(lastLoginProp);

        PropertyValueFactory<UserMaster, String> roleProp
                = new PropertyValueFactory<>("role");
        Role.setCellValueFactory(roleProp);

        PropertyValueFactory<UserMaster, String> phoneProp
                = new PropertyValueFactory<>("phone");
        phone.setCellValueFactory(phoneProp);

        PropertyValueFactory<UserMaster, String> pictureProp
                = new PropertyValueFactory<>("picture");
        picture.setCellValueFactory(pictureProp);

        tableView.setItems(FXCollections.observableArrayList(cm));
    }

    @FXML
    private void comboBoxChanged(ActionEvent event) {
        this.filterUsers();
    }

    @FXML
    private void dateAction(ActionEvent event) {
        String time = date.getValue().toString();
        Date.setText(time);
        this.filterUsers();
    }

    @FXML
    private void refreshClicked(ActionEvent event) {
        cm = cs.getAll()
                .stream()
                .map(e -> new UserMaster(e))
                .collect(Collectors.toList());
        tableView.setItems(FXCollections.observableArrayList(cm));
        search.setText("");
        Date.setText("");
        filter.setValue(null);
    }

    @FXML
    private void searchAction(KeyEvent event) {
        this.filterUsers();

    }

    @FXML
    private void deleteClicked(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Delete");
        alert.setHeaderText(null);
        alert.setContentText("Do you confirm ?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            UserMaster selectedItem = tableView.getSelectionModel().getSelectedItem();
            cm.remove(selectedItem);
            tableView.getItems().remove(selectedItem);
            cs.delete(selectedItem.getUser());
        }
    }
//new windows

    @FXML
    private void addClicked(ActionEvent event) throws IOException {
        blur.setEffect(new GaussianBlur(10));
        new FadeInRightTransition(trans).play();
        AnchorPane pane = FXMLLoader.load(getClass().getResource(Views.ADD_USER));
        loadPane.getChildren().setAll(pane);
    }

    @FXML
    private void editClicked(ActionEvent event) throws IOException {
        selectedUser = tableView.getSelectionModel().getSelectedItem();
        blur.setEffect(new GaussianBlur(10));
        new FadeInRightTransition(trans).play();
        AnchorPane pane = FXMLLoader.load(getClass().getResource(Views.MODIFY_USER));
        loadPane.getChildren().setAll(pane);
    }

    private void filterUsers() {
        String searchText = search.getText().toLowerCase();
        LocalDate time = date.getValue();
        Label boxValue = filter.getValue();
        Stream<UserMaster> stream = cm.stream();

        if (!search.getText().isEmpty()) {
            stream = stream
                    .filter(e -> e.getUsername().toLowerCase().contains(searchText)
                    || e.getEmail().toLowerCase().contains(searchText)
                    || e.getRole().toLowerCase().contains(searchText)
                    || e.getPhone().contains(searchText));
        }
        if (time != null) {
            stream = stream.filter(x -> x.getLastLogin().equals(time.toString()));
        }
        if (boxValue != null) {
            if (boxValue.getText().equals("Admin")) {
                System.out.println("here");
                stream = stream.filter(x -> x.getRole().contains("ADMIN"));
            } else {
                System.out.println("not here");

                stream = stream.filter(x -> !x.getRole().contains("ADMIN"));
            }
        }
        tableView.setItems(stream
                .collect(Collectors.collectingAndThen(Collectors.toList(), l -> FXCollections.observableArrayList(l))));
    }

    @FXML
    private void tombolClose(ActionEvent event) {
        refreshClicked(null);
        blur.setEffect(null);
        new FadeOutLeftTransition(trans).play();
    }

}
