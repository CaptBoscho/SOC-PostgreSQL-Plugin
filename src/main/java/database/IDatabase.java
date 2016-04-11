package database;

import server.persistence.dto.CommandDTO;
import server.persistence.dto.GameDTO;
import server.persistence.dto.UserDTO;

import java.util.List;

/**
 * Created by boscho on 4/4/16.
 */
public interface IDatabase {

    void clear();

    void shutdown();

    int addUser(UserDTO dto);

    List<UserDTO> getUsers();

    int addGame(GameDTO dto);

    List<GameDTO> getAllGames();

    List<CommandDTO> getCommands(int gameId);

    void addCommand(CommandDTO dto);

    void updateGame(GameDTO dto);

    void deleteCommandsFromGame(int gameID);
}