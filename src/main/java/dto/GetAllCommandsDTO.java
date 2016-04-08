package dto;

import java.util.List;
import java.util.Objects;

/**
 * Info to return all the commands
 * Created by boscho on 4/6/16.
 */
public class GetAllCommandsDTO implements IDTO {

    public List<Objects> getAllCommands() {
        return allCommands;
    }

    public void setAllCommands(List<Objects> allCommands) {
        this.allCommands = allCommands;
    }

    List<Objects> allCommands;
}
