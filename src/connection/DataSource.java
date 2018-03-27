package connection;

import com.mysql.jdbc.Connection;
import java.sql.*;

public final class DataSource {

    private Connection conn;
    private static DataSource db;

    private DataSource() {
        String url = "jdbc:mysql://sql11.freemysqlhosting.net:3306/";
        String dbName = "sql11222383";
        String driver = "com.mysql.jdbc.Driver";
        String userName = "sql11222383";
        String password = "6TuZt5JUMi";
        try {
            Class.forName(driver).newInstance();
            this.conn = (Connection) DriverManager.getConnection(url + dbName, userName, password);
        } catch (Exception sqle) {
            sqle.printStackTrace();
        }
    }

    public Connection getConnection() {
        return this.conn;
    }

    public static synchronized DataSource getInstance() {
        if (db == null) {
            db = new DataSource();
        }
        return db;

    }

}