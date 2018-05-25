/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import util.DataSource;
import entity.Product;
import entity.Promotion;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;
import javax.imageio.ImageIO;

/**
 *
 * @author Arshavin
 */
public class ProductService {

    private Connection connection = null;
    private boolean statusUpdate = false;
    private boolean statusDelete = false;
    private boolean statusAdd = false;

    public ProductService() {
        connection = DataSource.getInstance().getConnection();
    }

    public void insert(Product p) {
        String req = "INSERT INTO product (name,price,type,description,photo,promotion_id,nb_viewed,nb_seller,barcode) VALUES (?,?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement statment = connection.prepareStatement(req);
            statment.setString(1, p.getName());
            statment.setDouble(2, p.getPrice());
            statment.setString(3, p.getType());
            statment.setString(4, p.getDescription());
            statment.setString(5, p.getPhoto());
            statment.setInt(6, 1);
            statment.setInt(7, p.getNb_view());
            statment.setInt(8, p.getNb_seller());
            statment.setInt(9, p.getBarcode());
            statment.execute();

        } catch (SQLException ex) {
            Logger.getLogger(ProductService.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void insert(Product p, File file) throws FileNotFoundException, IOException {
        String req = "INSERT INTO product (name,price,type,description,promotion_id,nb_viewed,photo,barcode,image) VALUES (?,?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement statment = connection.prepareStatement(req);
            statment.setString(1, p.getName());
            statment.setDouble(2, p.getPrice());
            statment.setString(3, p.getType());
            statment.setString(4, p.getDescription());
            FileInputStream fis = new FileInputStream(file);
            statment.setBinaryStream(9, fis, file.length());
            statment.setInt(5, p.getPromotion().getId_promotion());
            statment.setInt(6, p.getNb_view());
            String[] s;
            s = file.getAbsolutePath().split("\\\\");
            statment.setString(7,s[s.length-1]);
            statment.setInt(8, p.getBarcode());
            statment.execute();

        } catch (SQLException ex) {
            Logger.getLogger(ProductService.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public List<Product> selectAll() {
        List<Product> products = new ArrayList<>();
        String req = "select * from product p join promotion r where p.promotion_id=r.id";

        try {
            Statement statement = (Statement) connection.createStatement();
            ResultSet result = statement.executeQuery(req);

            while (result.next()) {

                Promotion promotion = new Promotion(result.getInt("promotion_id"), result.getDouble("discount"), result.getDate("starting_date"), result.getDate("ending_date"));
                Product p = new Product(result.getInt("id"), result.getString("name"), result.getString(4), result.getDouble(5),
                        result.getInt(6), result.getInt(7), result.getString(8), result.getString(9), result.getInt("barcode"), promotion, result.getBlob("image"));
                products.add(p);
            }

        } catch (SQLException ex) {
            Logger.getLogger(ProductService.class.getName()).log(Level.SEVERE, null, ex);
        }

        return products;
    }

    public void update(Product p) {

        String req = "Update product set name=? , type=? , price=? ,description=? ,barcode=?  where id= ?";
        try {
            PreparedStatement statment = connection.prepareStatement(req);
            statment.setString(1, p.getName());
            statment.setDouble(3, p.getPrice());
            statment.setString(2, p.getType());
            statment.setString(4, p.getDescription());
            statment.setInt(5, p.getBarcode());
            statment.setInt(6, p.getId());
            statment.executeUpdate();
            setStatusUpdate(true);

        } catch (SQLException ex) {
            Logger.getLogger(ProductService.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void delete(int id) {
        String req = "Delete from product where id= ?";
        try {
            PreparedStatement statment = connection.prepareStatement(req);
            statment.setInt(1, id);
            statment.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(ProductService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Product selectProductById(int id) throws IOException {
        Product p=null ;
        String req = "select * from product p join promotion r where p.promotion_id=r.id and p.id=?";
        try {
            PreparedStatement statement = connection.prepareStatement(req);
            statement.setInt(1, id);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                Promotion promotion = new Promotion(result.getInt("promotion_id"), result.getDouble("discount"), result.getDate("starting_date"), result.getDate("ending_date"));
                System.out.println(promotion);
                p = new Product(result.getInt(1), result.getString(3), result.getString(4), result.getDouble(5),
                        result.getInt(6), result.getInt(8), result.getString(10), result.getString(9), result.getInt(7), promotion,result.getBlob("image"));
               
            

            }

        } catch (SQLException ex) {
            Logger.getLogger(ProductService.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println(p);
        return p;
    }
    
    public Image selectImageById(int id) throws IOException {
        Product p = null;
        Image image=null;
        String req = "select * from product p join promotion r where p.promotion_id=r.id and p.id=?";
        try {
            PreparedStatement statement = connection.prepareStatement(req);
            statement.setInt(1, id);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
               
                System.out.println(result.getBinaryStream("image"));
                   InputStream is = result.getBinaryStream("image"); // image from database
            BufferedImage imBuff = ImageIO.read(is);  //converting to buffered image
            image = SwingFXUtils.toFXImage(imBuff, null);  //converting to 
                System.out.println(image);
            

            }

        } catch (SQLException ex) {
            Logger.getLogger(ProductService.class.getName()).log(Level.SEVERE, null, ex);
        }

        return image;
    }

    public boolean isStatusDelete() {
        return statusDelete;
    }

    public void setStatusDelete(boolean statusDelete) {
        this.statusDelete = statusDelete;
    }

    public boolean isStatusAdd() {
        return statusAdd;
    }

    public void setStatusAdd(boolean statusAdd) {
        this.statusAdd = statusAdd;
    }

    public boolean isStatusUpdate() {
        return statusUpdate;
    }

    public void setStatusUpdate(boolean statusUpdate) {
        this.statusUpdate = statusUpdate;
    }

}
