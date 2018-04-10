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
import populator.ClaimMaster;
import service.ClaimService;
import util.Views;

/**
 * FXML Controller class
 *
 * @author ding
 */
public class ClaimListController implements Initializable {

    public static ClaimMaster sselectedItem;

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
    private JFXTextField search;
    @FXML
    private ContextMenu contextMenu;
    @FXML
    private TableView<ClaimMaster> tableView;
    @FXML
    private TableColumn<ClaimMaster, String> clientName;
    @FXML
    private TableColumn<ClaimMaster, String> description;
    @FXML
    private TableColumn<ClaimMaster, String> postedOn;
    @FXML
    private TableColumn<ClaimMaster, String> answer;
    @FXML
    private TableColumn<ClaimMaster, String> answeredBy;

    private List<ClaimMaster> cm;
    private ClaimService cs;
    @FXML
    private JFXButton refreshbtn;
    @FXML
    private JFXButton answer_btn;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        filter.getItems().add(new Label("Answered"));
        filter.getItems().add(new Label("Not Answered"));
        tableView.setEditable(true);
        cs = new ClaimService();
        cm = cs.getAll()
                .stream()
                .map(e -> new ClaimMaster(e))
                .collect(Collectors.toList());

        PropertyValueFactory<ClaimMaster, String> clientNameProp
                = new PropertyValueFactory<>("clientName");
        clientName.setCellValueFactory(clientNameProp);

        PropertyValueFactory<ClaimMaster, String> descriptionProp
                = new PropertyValueFactory<>("description");
        description.setCellValueFactory(descriptionProp);

        PropertyValueFactory<ClaimMaster, String> postedOnProp
                = new PropertyValueFactory<>("postedOn");
        postedOn.setCellValueFactory(postedOnProp);

        PropertyValueFactory<ClaimMaster, String> answerProp
                = new PropertyValueFactory<>("answer");
        answer.setCellValueFactory(answerProp);

        PropertyValueFactory<ClaimMaster, String> answeredByProp
                = new PropertyValueFactory<>("answeredBy");
        answeredBy.setCellValueFactory(answeredByProp);

        tableView.setItems(FXCollections.observableArrayList(cm));
    }

    @FXML
    private void tombolClose(ActionEvent event) {
        System.out.println("called");
        refreshClicked(null);
        blur.setEffect(null);
        new FadeOutLeftTransition(trans).play();
        clearParameter();
    }

    private void clearParameter() {

    }

    @FXML
    private void deleteClicked(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Delete");
        alert.setHeaderText(null);
        alert.setContentText("Do you confirm ?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            ClaimMaster selectedItem = tableView.getSelectionModel().getSelectedItem();
            cm.remove(selectedItem);
            tableView.getItems().remove(selectedItem);
            cs.delete(selectedItem.getClaim());
        }
    }

    @FXML
    private void searchAction(KeyEvent event) {
        this.filterClaims();
    }

    private void filterClaims() {
        String searchText = search.getText().toLowerCase();
        LocalDate time = date.getValue();
        Label boxValue = filter.getValue();
        Stream<ClaimMaster> stream = cm.stream();
        if (!search.getText().isEmpty()) {
            stream = stream
                    .filter(e -> e.getClientName().toLowerCase().contains(searchText)
                    || e.getAnswer().toLowerCase().contains(searchText)
                    || e.getAnsweredBy().toLowerCase().contains(searchText)
                    || e.getDescription().toLowerCase().contains(searchText));
        }
        if (time != null) {
            stream = stream.filter(x -> x.getPostedOn().equals(time.toString()));
        }
        if (boxValue != null) {
            if (boxValue.getText().equals("Answered")) {
                stream = stream.filter(x -> !x.getAnsweredBy().isEmpty());
            } else {
                stream = stream.filter(x -> x.getAnsweredBy().isEmpty());
            }
        }
        tableView.setItems(stream
                .collect(Collectors.collectingAndThen(Collectors.toList(), l -> FXCollections.observableArrayList(l))));

    }

    @FXML
    private void dateAction(ActionEvent event) {
        String time = date.getValue().toString();
        Date.setText(time);
        this.filterClaims();
    }

    @FXML
    private void refreshClicked(ActionEvent event) {
        cm = cs.getAll()
                .stream()
                .map(e -> new ClaimMaster(e))
                .collect(Collectors.toList());
        tableView.setItems(FXCollections.observableArrayList(cm));
        search.setText("");
        Date.setText("");
        filter.setValue(null);
    }

    @FXML
    private void answerClicked(ActionEvent event) throws IOException {
        ClaimListController.sselectedItem = tableView.getSelectionModel().getSelectedItem();
        blur.setEffect(new GaussianBlur(10));
        new FadeInRightTransition(trans).play();
        AnchorPane pane = FXMLLoader.load(getClass().getResource(Views.ANSWER_CLAIM));
        loadPane.getChildren().setAll(pane);
    }

    @FXML
    private void comboBoxChanged(ActionEvent event) {
        this.filterClaims();
    }
}