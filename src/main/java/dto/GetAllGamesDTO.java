package dto;

import java.util.List;

/**
 * Created by boscho on 4/6/16.
 */
public class GetAllGamesDTO {

    public List<String> getGameStates() {
        return gameStates;
    }

    public void setGameStates(List<String> gameStates) {
        this.gameStates = gameStates;
    }

    //Json in form of string
    List<String> gameStates;
}
