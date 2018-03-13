/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Haroun
 */
public class DataSource {
    
    private String url;
    private String login; 
    private String password;
    private Connection connection;
    private static DataSource datasource;

    public DataSource() {
        url ="jdbc:mysql://sql11.freemysqlhosting.net:3306/sql11222383";
        login="sql11222383";
        password ="6TuZt5JUMi";

        try {
            connection = DriverManager.getConnection(url, login, password);
        } 
        catch (SQLException ex) {
            Logger.getLogger(DataSource.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Connection getConnection() {
        return connection;
    }
    
    public static DataSource getInstance() {
        if(datasource==null) {
            datasource = new DataSource();
        }
        return datasource;
    }
    
}
