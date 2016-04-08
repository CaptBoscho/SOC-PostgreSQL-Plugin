package daos;

import database.Database;
import dto.IDTO;

import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by Kyle 'TMD' Cornelison on 4/2/2016.
 */
public class GameDAO implements IGameDAO {
    /**
     * Handles adding a user,
     * adding a command
     * adding a game
     *
     * @param dto
     */
    @Override
    public void addGameObject(IDTO dto) {
        try {
            Statement st = Database.getConnection().createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Handles verifying user which returns userID
     * Getting the current game model
     * getting a list of Commands
     *
     * @param dto
     * @return
     */
    @Override
    public IDTO getGameModel(IDTO dto) {
        try {
            Statement st = Database.getConnection().createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * mostly be used for updating the game blob state
     *
     * @param dto
     */
    @Override
    public void updateGame(IDTO dto) {
        try {
            Statement st = Database.getConnection().createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Mostly be used for deleting commands every n
     * moves.
     *
     * @param dto
     */
    @Override
    public void deleteAllGames(IDTO dto) {
        try {
            Statement st = Database.getConnection().createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteGame(IDTO dto) {
        try {
            Statement st = Database.getConnection().createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
