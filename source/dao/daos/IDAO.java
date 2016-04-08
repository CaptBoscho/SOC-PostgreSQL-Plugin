package daos;


import dto.IDTO;

/**
 * Created by boscho on 4/4/16.
 */
public interface IDAO {

    /**
     * Handles adding a user,
     * adding a command
     * adding a game
     * @param dto
     */
    void addObject(IDTO dto);

    /**
     * Handles verifying user which returns userID
     * Getting the current game model
     * getting a list of Commands
     * @param dto
     * @return
     */
    IDTO readData(IDTO dto);

    /**
     * mostly be used for updating the game blob state
     * @param dto
     */
    void updateData(IDTO dto);

    /**
     * Mostly be used for deleting commands every n
     * moves.
     * @param dto
     */
    void deleteData(IDTO dto);

}
