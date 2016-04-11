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
        ResultSet rs = stmt.executeQuery("SELECT ID FROM GAMES WHERE ID = " + dto.getGameID() + ";");

        int newID;
        if (!rs.isBeforeFirst()) {
            // gameID doesn't exist
            newID = dto.getGameID();
            rs.close();
            stmt.close();
        } else {
            rs.close();
            stmt.close();

            Statement tempStatement = Database.getConnection().createStatement();
            ResultSet tempRS = tempStatement.executeQuery("SELECT ID FROM GAMES ORDER BY ID DESC LIMIT 1;");
            tempRS.next();
            newID = tempRS.getInt("id");
            tempRS.close();
            tempStatement.close();
        }

        Statement finalStatement = Database.getConnection().createStatement();
        String sql = "INSERT INTO GAMES (ID,TITLE,STATE) "
                + "VALUES (" + newID + ", " + dto.getTitle() + ", " + dto.getState() + " );";
        finalStatement.executeUpdate(sql);
        finalStatement.close();
        Database.getConnection().commit();

        return newID;
    }

    /**
     * Getting the current game model
     * @return
     */
    @Override
    public GameDTO getGameModel(int gameID) throws SQLException {
        Statement stmt = Database.getConnection().createStatement();
        ResultSet rs = stmt.executeQuery( "SELECT * FROM GAMES WHERE GAMEID = " + gameID +";");
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
        String sql = "UPDATE GAME set STATE = " + dto.getState()
                + "where GAMEID =" + dto.getGameID() + ";";
        stmt.executeUpdate(sql);
        Database.getConnection().commit();
        stmt.close();
    }

    /**
     * Deletes all Games
     */
    @Override
    public void deleteAllGames() throws SQLException{
            Statement stmt = Database.getConnection().createStatement();
            stmt.executeUpdate("DELETE FROM GAMES;");
            Database.getConnection().commit();
            stmt.close();
    }

    @Override
    public void deleteGame(int gameID) throws SQLException {
            Statement stmt = Database.getConnection().createStatement();
            String sql = "DELETE FROM GAMES where GAMEID=" + gameID + ";";
            stmt.executeUpdate(sql);
            Database.getConnection().commit();
            stmt.close();
    }
}
