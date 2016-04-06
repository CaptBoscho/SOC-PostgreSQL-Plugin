package main.java.daos;

import main.java.dto.IDTO;

/**
 * Created by Kyle 'TMD' Cornelison on 4/2/2016.
 */
public class UserDAO implements IUserDAO {

    /**
     * Handles adding a user,
     * adding a command
     * adding a game
     *
     * @param dto
     */
    @Override
    public void addObject(IDTO dto) {

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
    public IDTO readData(IDTO dto) {
        return null;
    }

    /**
     * mostly be used for updating the game blob state
     *
     * @param dto
     */
    @Override
    public void updateData(IDTO dto) {

    }

    /**
     * Mostly be used for deleting commands every n
     * moves.
     *
     * @param dto
     */
    @Override
    public void deleteData(IDTO dto) {

    }
}
