package database;

import io.ConfigReader;

import java.sql.*;
import java.util.Properties;

/**
 * This class is what the main application will use to make transactions
 */
public class Database implements IDatabase {

    private static Database instance;
    private Connection connection;

    public static Database getInstance() {
        if(instance == null) {

            instance = new Database();
        }
        return instance;
    }

    private Database() {
        try {
            Class.forName("org.postgresql.Driver");
            Properties properties = ConfigReader.readConfig();
            String dbName = properties.getProperty("dbname");
            String userName = properties.getProperty("username");
            String password = properties.getProperty("password");
            String hostname = properties.getProperty("hostname");
            String port = properties.getProperty("port");
            String jdbcUrl = "jdbc:postgresql://" + hostname + ":" + port + "/" + dbName + "?user=" + userName + "&password=" + password;
            this.connection = DriverManager.getConnection(jdbcUrl);
            System.out.println("Database connected");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void clear() {
        try {
            connection.setAutoCommit(false);
            Statement st = this.connection.createStatement();
            st.executeUpdate("DROP SCHEMA PUBLIC CASCADE");
            st.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void shutdown() {
        try {
            this.connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
