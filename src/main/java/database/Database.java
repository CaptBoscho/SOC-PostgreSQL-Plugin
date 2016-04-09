package database;

import io.ConfigReader;

import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
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

    public static Connection getConnection() {
        return getInstance().connection;
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

            //Creating tables
            Statement users = this.connection.createStatement();
            String sqlUser = "CREATE TABLE USERS " +
                         "(ID INT PRIMARY KEY   NOT NULL," +
                         " USERNAME     VARCHAR(50)  NOT NULL, " +
                         " PASSWORD     VARCHAR(50)  NOT NULL)";
            users.executeUpdate(sqlUser);
            users.close();

            Statement commands = this.connection.createStatement();
            String sqlCommands = "CREATE TABLE COMMANDS" +
                                 "(ID INT PRIMARY KEY   NOT NULL," +
                                 " GAMEID       INT     NOT NULL," +
                                 " VERSION      INT     NOT NULL," +
                                 " COMMANDBLOB  BLOB    NOT NULL)";
            commands.execute(sqlCommands);
            commands.close();

            Statement games = this.connection.createStatement();
            String sqlGames =   "CREATE TABLE GAMES" +
                                "(ID INT PRIMARY KEY    NOT NULL," +
                                " TITLE     VARCHAR(50) NOT NULL," +
                                " STATE         BLOB    NOT NULL)";
            games.execute(sqlGames);
            games.close();
            System.out.println("Tables initialized");

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
