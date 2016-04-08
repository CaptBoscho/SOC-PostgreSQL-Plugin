package daos;

import dto.IDTO;
import exceptions.CommandTableException;

import java.sql.SQLException;

/**
 * Created by Kyle 'TMD' Cornelison on 4/5/2016.
 */
public interface ICommandDAO {
    /**
     * Handles adding a user,
     * adding a command
     * adding a game
     * @param dto
     */
    void addCommand(IDTO dto) throws CommandTableException, SQLException;

    /**
     * Handles verifying user which returns userID
     * Getting the current game model
     * getting a list of Commands
     * @param dto
     * @return
     */
    IDTO getCommands(IDTO dto) throws CommandTableException, SQLException;

    /**
     * mostly be used for updating the game blob state
     * @param dto
     */
    void deleteAllCommands(IDTO dto) throws CommandTableException, SQLException;

    /**
     * Mostly be used for deleting commands every n
     * moves.
     * @param dto
     */
    void deleteCommandsFromGame(IDTO dto) throws CommandTableException, SQLException;
}
