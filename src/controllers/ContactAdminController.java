package controllers;

import util.DataSource;
import function.navigation;
import function.time;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Properties;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.util.Duration;
import javax.swing.JOptionPane;
import entity.Contact;
import java.util.List;
import service.ContactService;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

public class ContactAdminController implements Initializable {

    SimpleDateFormat sdf = new SimpleDateFormat("dd MMMM yyyy");
    ObservableList<String> comboEtat = FXCollections.observableArrayList("All","Contacted","Not Contacted");
    
    @FXML
    private TableView<Contact> tableContactAdmin;
    
    @FXML
    private TableColumn<Contact, String> columnID;
    
    @FXML
    private TableColumn<Contact, String> columnFirstName;
    
    @FXML
    private TableColumn<Contact, String> columnLastName;
    @FXML
    private TableColumn<Contact, String> columnEmail;
    @FXML
    private TableColumn<Contact, String> columnAdress;
    
    @FXML
    private TableColumn<Contact, String> columnPhone;

    
    @FXML
    private TableColumn<Contact, String> columnStatus;

    
    private ObservableList<Contact> data;
    
    @FXML
    private ComboBox etat;
    
    @FXML
    private TextField day;
    
    @FXML
    private DatePicker hari_pilih;

    @FXML
    private Button btnOutput;
    
    @FXML
    private ContextMenu contextOutput;
        
    DataSource kon = new DataSource();
    ContactService model = new ContactService();
    NumberFormat num = NumberFormat.getInstance();
    time time = new time();
    navigation nav = new navigation();
    
    private void setEtat(){
        etat.setValue("All");
        etat.setItems(comboEtat);
    }
    
   private void setDay(){
        day.setText(time.tanggal());
        hari_pilih.setValue(LocalDate.parse(time.tanggalQuery()));
    }
    
     @FXML
    private void printClicked(ActionEvent event){
        
    }
    
    @FXML
    private void openContext(MouseEvent event){
        contextOutput.show(btnOutput,event.getScreenX(), event.getScreenY());
    }
    
    @FXML
    private void saveXLSX(ActionEvent event) throws SQLException, FileNotFoundException, IOException{
     
    }
    
    
    private void loadTable(){
       try {
           
      
            columnID.setCellValueFactory(new PropertyValueFactory<>("id"));
            columnFirstName.setCellValueFactory(new PropertyValueFactory<>("firstName"));
            columnLastName.setCellValueFactory(new PropertyValueFactory<>("lastName"));
            columnEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
            columnAdress.setCellValueFactory(new PropertyValueFactory<>("adress"));
            columnPhone.setCellValueFactory(new PropertyValueFactory<>("phone"));
            columnStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
            //columnMessage.setCellValueFactory(new PropertyValueFactory<>("message"));
           // columnInputTime.setCellValueFactory(new PropertyValueFactory<>("inputTime"));
            tableContactAdmin.setItems(getContacts());
        } catch (Exception e) {
            System.out.println("error controller");;
        }
           
    } 
    public ObservableList<Contact> getContacts()
         {
             ContactService cs = new ContactService();
            ObservableList<Contact> p = FXCollections.observableArrayList();
            List <Contact> allContacts;
            allContacts=cs.selectAll();
            allContacts.stream().forEach((allContact) -> {
                p.add(new Contact(allContact.getId(),allContact.getPhone(), allContact.getFirstName(), allContact.getLastName(),allContact.getEmail(), allContact.getAdress(), allContact.isStatus()));
        });
            return p;
            
         }
   
    
      
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       // setDay();
        setEtat();
    //    setStyleTable();
        loadTable();
    }    
    
    @FXML
    private void dateClicked(ActionEvent event){
        String dateText = sdf.format(Date.valueOf(hari_pilih.getValue()));
        day.setText(dateText);
    }
    
    @FXML
    private void refreshClicked(ActionEvent event){
        loadTable();
    }
    
}
