package daos;

import dto.IDTO;

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
    public void addUser(IDTO dto) {

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
    public IDTO getUsers(IDTO dto) {
        return null;
    }


    /**
     * Mostly be used for deleting commands every n
     * moves.
     *
     * @param dto
     */
    @Override
    public void deleteUsers(IDTO dto) {

    }
}
