package daos;

import dto.IDTO;
import exceptions.GameTableException;

import java.sql.SQLException;

/**
 * Created by Kyle 'TMD' Cornelison on 4/5/2016.
 */
public interface IGameDAO {
    /**
     * Handles adding a user,
     * adding a command
     * adding a game
     * @param dto
     */
    void addGameObject(IDTO dto) throws GameTableException, SQLException;

    /**
     * Handles verifying user which returns userID
     * Getting the current game model
     * getting a list of Commands
     * @param dto
     * @return
     */
    IDTO getGameModel(IDTO dto) throws SQLException, GameTableException;

    /**
     * mostly be used for updating the game blob state
     * @param dto
     */
    void updateGame(IDTO dto) throws GameTableException, SQLException;

    /**
     * Deletes all games
     * @param dto
     */
    void deleteAllGames(IDTO dto) throws SQLException, GameTableException;

    /**
     * Deletes a game
     * @param dto
     */
    void deleteGame(IDTO dto) throws GameTableException, SQLException;
}
