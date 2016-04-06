package main.java.database;

import main.java.daos.*;
import main.java.factory.DAOFactory;
import main.java.factory.IDAOFactory;

/**
 * Created by Kyle 'TMD' Cornelison on 4/2/2016.
 *
 * Persistence Database based on PostgreSQL
 */
public class Database implements IDatabase {
    private IDAOFactory factory = DAOFactory.getInstance();

    /**
     * Constructor
     */
    public Database(){
        // TODO: 4/2/2016 Set up the plugin, eg: database, etc.
    }

    /**
     * Returns a connection to the database
     *
     * @return
     */
    @Override
    public Object getConnection() {
        return null;
    }

    /**
     * Clears the database
     */
    @Override
    public void clear() {

    }

    /**
     * Starts a transaction on the database
     */
    @Override
    public void startTransaction() {

    }

    /**
     * Ends a transaction on the database
     *
     * @param commitTransaction
     */
    @Override
    public void endTransaction(boolean commitTransaction) {

    }

    /**
     * Creates a new UserDAO
     *
     * @return UserDAO
     */
    @Override
    public IUserDAO createUserDAO() {
        return factory.createUserDAO();
    }

    /**
     * Creates a new GameDAO
     *
     * @return GameDAO
     */
    @Override
    public IGameDAO createGameDAO() {
        return factory.createGameDAO();
    }

    /**
     * Creates a new CommandDAO
     *
     * @return CommandDAO
     */
    @Override
    public ICommandDAO createCommandDAO() {
        return factory.createCommandDAO();
    }
}
