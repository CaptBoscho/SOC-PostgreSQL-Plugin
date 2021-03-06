package daos;

import server.persistence.Database;
import server.persistence.dto.GameDTO;

import java.sql.*;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

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
    public int addGameObject(GameDTO dto) throws SQLException {
        Statement stmt = Database.getConnection().createStatement();
        ResultSet rs = stmt.executeQuery("SELECT ID FROM GAMES ORDER BY ID DESC LIMIT 1;");

        int newID;
        if (!rs.isBeforeFirst()) {
            // no games in database
            newID = 0;
        } else {
            rs.next();
            newID = rs.getInt("id") + 1;
        }
        rs.close();
        stmt.close();

        Statement finalStatement = Database.getConnection().createStatement();
        String sql = "INSERT INTO GAMES (ID, TITLE, STATE) VALUES (" + newID + ", '" + dto.getTitle() + "', '" + dto.getState() + "' );";
        System.out.println("running: " + sql);
        finalStatement.executeUpdate(sql);
        finalStatement.close();
//        Database.getConnection().commit();

        return newID;
    }

    /**
     * Getting the current game model
     * @return
     */
    @Override
    public GameDTO getGameModel(int gameID) throws SQLException {
        Statement stmt = Database.getConnection().createStatement();
        ResultSet rs = stmt.executeQuery( "SELECT * FROM GAMES WHERE ID = " + gameID +";");
        GameDTO dto = new GameDTO(rs.getInt("id"), rs.getString("title"), rs.getString("state"));
        rs.close();
        stmt.close();
        return dto;
    }

    @Override
    public List<GameDTO> getAllGames() throws SQLException {
        Statement stmt = Database.getConnection().createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM GAMES;");

        List<GameDTO> games = new ArrayList<>();
        while (rs.next()){
            GameDTO game = new GameDTO(rs.getInt("id"), rs.getString("title"), rs.getString("state"));
            games.add(game);
        }
        rs.close();
        stmt.close();
        return games;
    }

    /**
     * used for updating the game blob state
     *
     * @param dto
     */
    @Override
    public void updateGame(GameDTO dto) throws SQLException {
        Statement stmt = Database.getConnection().createStatement();
        String sql = "UPDATE GAMES set STATE = '" + dto.getState()
                + "' where ID = " + dto.getGameID() + ";";
        stmt.executeUpdate(sql);
//        Database.getConnection().commit();
        stmt.close();
    }

    /**
     * Deletes all Games
     */
    @Override
    public void deleteAllGames() throws SQLException{
            Statement stmt = Database.getConnection().createStatement();
            stmt.executeUpdate("DELETE FROM GAMES;");
//            Database.getConnection().commit();
            stmt.close();
    }

    @Override
    public void deleteGame(int gameID) throws SQLException {
            Statement stmt = Database.getConnection().createStatement();
            String sql = "DELETE FROM GAMES where ID = " + gameID + ";";
            stmt.executeUpdate(sql);
//            Database.getConnection().commit();
            stmt.close();
    }
}
