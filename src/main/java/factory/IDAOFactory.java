package main.java.factory;

import main.java.daos.*;

/**
 * Created by Kyle 'TMD' Cornelison on 4/2/2016.
 */
public interface IDAOFactory {
    /**
     * Creates a new UserDAO
     *
     * @return UserDAO
     */
    IUserDAO createUserDAO();

    /**
     * Creates a new GameDAO
     *
     * @return GameDAO
     */
    IGameDAO createGameDAO();

    /**
     * Creates a new CommandDAO
     *
     * @return CommandDAO
     */
    ICommandDAO createCommandDAO();
}
