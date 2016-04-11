package server.persistence;

import daos.*;
import factory.DAOFactory;
import io.ConfigReader;
import server.persistence.dto.CommandDTO;
import server.persistence.dto.GameDTO;
import server.persistence.dto.UserDTO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.*;
import java.util.List;
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

    public Database() {
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

            final Statement db = this.connection.createStatement();
            ResultSet myResult = db.executeQuery("select count(*) from pg_catalog.pg_database where datname = 'everyone' ;");
            myResult.next();
            if(myResult.getInt(1) == 0) {
                db.executeUpdate("CREATE DATABASE everyone;");
                db.executeUpdate("CREATE SCHEMA public;");
            }

            //Creating tables
            this.createUsersTable();
            this.createCommandsTable();
            this.createGamesTable();
            System.out.println("Tables initialized");

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    private void createUsersTable() throws SQLException {
        final Statement users = this.connection.createStatement();
        String sqlUser = "CREATE TABLE IF NOT EXISTS USERS " +
                "(ID INT PRIMARY KEY   NOT NULL," +
                " USERNAME     VARCHAR(50)  NOT NULL, " +
                " PASSWORD     VARCHAR(50)  NOT NULL)";
        users.executeUpdate(sqlUser);
        users.close();
    }

    private void createCommandsTable() throws SQLException {
        final Statement commands = this.connection.createStatement();
        String sqlCommands = "CREATE TABLE IF NOT EXISTS COMMANDS" +
                "(ID SERIAL PRIMARY KEY," +
                " GAMEID       INT     NOT NULL," +
                " COMMANDBLOB  TEXT    NOT NULL)";
        commands.execute(sqlCommands);
        commands.close();
    }

    private void createGamesTable() throws SQLException {
        final Statement games = this.connection.createStatement();
        String sqlGames =   "CREATE TABLE IF NOT EXISTS GAMES" +
                "(ID INT PRIMARY KEY    NOT NULL," +
                " TITLE     VARCHAR(50) NOT NULL," +
                " STATE     TEXT        NOT NULL)";
        games.execute(sqlGames);
        games.close();
    }

    @Override
    public void clear() {
        try {
            IGameDAO gameDAO = new GameDAO();
            gameDAO.deleteAllGames();
            IUserDAO userDAO = new UserDAO();
            userDAO.deleteUsers();
            ICommandDAO commandDAO = new CommandDAO();
            commandDAO.deleteAllCommands();
            System.out.println("Success");
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

    @Override
    public int addUser(UserDTO dto) {
        IUserDAO dao = DAOFactory.getInstance().createUserDAO();
        try {
            return dao.addUser(dto);
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
    }

    @Override
    public List<UserDTO> getUsers() {
        IUserDAO dao = DAOFactory.getInstance().createUserDAO();
        try {
            return dao.getUsers();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public int addGame(GameDTO dto) {
        try {
            IGameDAO dao = new GameDAO();
            return dao.addGameObject(dto);
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
    }

    @Override
    public List<GameDTO> getAllGames() {
        IGameDAO dao = new GameDAO();
        try {
            return dao.getAllGames();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<CommandDTO> getCommands(int gameId) {
        ICommandDAO dao = new CommandDAO();
        try {
            return dao.getCommands(gameId);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void addCommand(CommandDTO dto) {
        ICommandDAO dao = new CommandDAO();
        try {
            dao.addCommand(dto);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateGame(GameDTO dto) {
        IGameDAO dao = new GameDAO();
        try {
            dao.updateGame(dto);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteCommandsFromGame(int gameID) {
        ICommandDAO dao = new CommandDAO();
        try {
            dao.deleteCommandsFromGame(gameID);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<CommandDTO> getAllCommands() {
        ICommandDAO dao = new CommandDAO();
        try {
            return dao.getAllCommands();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void dropTables() throws SQLException {
        final Statement dropTables = connection.createStatement();
        dropTables.execute(sql);
        dropTables.close();
    }
}
