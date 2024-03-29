/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import animation.FadeInRightTransition;
import animation.FadeOutLeftTransition;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
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
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import populator.UserMaster;
import service.NotificationService;
import service.UserService;
import util.Util;
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

    private List<UserMaster> cm;
    private UserService cs;
    public static UserMaster selectedUser;
    @FXML
    private JFXButton export_btn;
    @FXML
    private JFXButton export_btn1;

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
            NotificationService.successBlueNotification("User deleted!", "User deleted!");
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
                stream = stream.filter(x -> x.getRole().toLowerCase().contains("admin"));
            } else {
                System.out.println("not here");

                stream = stream.filter(x -> !x.getRole().toLowerCase().contains("admin"));
            }
        }
        tableView.setItems(stream
                .collect(Collectors.collectingAndThen(Collectors.toList(), l -> FXCollections.observableArrayList(l))));
    }

    private void toXLS(String file) {
        try (FileWriter fw = new FileWriter(file, true);
                BufferedWriter bw = new BufferedWriter(fw);
                PrintWriter out = new PrintWriter(bw)) {
            out.println("username" + "\t" + "email" + "\t" + "password" + "\t" + "lastLogin" + "\t" + "role" + "\t" + "phone");
            tableView.getItems().stream().forEach(e -> out.println(e.getUsername() + "\t" + e.getEmail()
                    + "\t" + e.getPassword() + "\t" + e.getLastLogin()
                    + "\t" + e.getRole() + "\t" + e.getPhone()));
            NotificationService.successBlueNotification("Export finished!", "Users exported to " + file);

        } catch (IOException e) {
            Util.showError("Export failed");

        }
    }

    @FXML
    private void tombolClose(ActionEvent event) {
        refreshClicked(null);
        blur.setEffect(null);
        new FadeOutLeftTransition(trans).play();
    }

    @FXML
    private void exportClicked(ActionEvent event) {
        FileChooser chooser = new FileChooser();
        chooser.setTitle("Save to XLS");
        File file = chooser.showSaveDialog(new Stage());
        if (!file.getAbsolutePath().endsWith(".xls")) {
            toXLS(file.getAbsolutePath() + ".xls");
        } else {
            toXLS(file.getAbsolutePath());
        }

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
            content.add("username");
            content.add("email");
            content.add("password");

            content.add("lastLogin");
            content.add("role");
            content.add("phone");

            tableView.getItems().stream().forEach(e -> {
                content.add(e.getUsername());
                content.add(e.getEmail());
                content.add(e.getPassword());

                content.add(e.getLastLogin());
                content.add(e.getRole());
                content.add(e.getPhone());

            });
            PDFService.toPDF(file, "List of Users", content, 6);

            NotificationService.successBlueNotification("Export finished!", "Users exported to " + file);

        } catch (Exception e) {
            Util.showError("Export failed");

        }
    }

}
