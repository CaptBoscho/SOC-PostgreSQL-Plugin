package database;

import server.persistence.dto.GameDTO;

/**
 * Created by boscho on 4/4/16.
 */
public interface IDatabase {

    void clear();

    void shutdown();

    void addGame(GameDTO dto);


}
