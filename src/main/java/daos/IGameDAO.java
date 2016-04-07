package daos;

import dto.IDTO;

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
    void addGameObject(IDTO dto);

    /**
     * Handles verifying user which returns userID
     * Getting the current game model
     * getting a list of Commands
     * @param dto
     * @return
     */
    IDTO getGameModel(IDTO dto);

    /**
     * mostly be used for updating the game blob state
     * @param dto
     */
    void updateGame(IDTO dto);

    /**
     * Deletes all games
     * @param dto
     */
    void deleteAllGames(IDTO dto);

    /**
     * Deletes a game
     * @param dto
     */
    void deleteGame(IDTO dto);
}
