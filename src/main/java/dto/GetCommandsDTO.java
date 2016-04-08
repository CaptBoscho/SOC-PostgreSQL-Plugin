package dto;

/**
 * Get Commands that coincide with gameID
 * Created by boscho on 4/6/16.
 */
public class GetCommandsDTO implements IDTO {

    private int gameID;
    private Object iCommand;
    private int version;

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public Object getiCommand() {
        return iCommand;
    }

    public void setiCommand(Object iCommand) {
        this.iCommand = iCommand;
    }

    public int getGameID() {
        return gameID;
    }

    public void setGameID(int gameID) {
        this.gameID = gameID;
    }


}
