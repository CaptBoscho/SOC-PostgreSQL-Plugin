package daos;

import dto.IDTO;

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
        return null;
    }

    /**
     * mostly be used for updating the game blob state
     *
     * @param dto
     */
    @Override
    public void updateGame(IDTO dto) {

    }

    /**
     * Mostly be used for deleting commands every n
     * moves.
     *
     * @param dto
     */
    @Override
    public void deleteAllGames(IDTO dto) {

    }

    @Override
    public void deleteGame(IDTO dto) {

    }
}
