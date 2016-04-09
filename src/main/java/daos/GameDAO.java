package daos;

import database.Database;
import dto.*;
import exceptions.GameTableException;

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
    public void addGameObject(GameDTO dto) throws GameTableException, SQLException {
        Statement stmt = Database.getConnection().createStatement();
        String sql = "INSERT INTO GAMES (ID,TITLE,STATE) "
                + "VALUES (" + dto.getGameID() + ", " + dto.getTitle() + ", " + dto.getState() + " );";
        stmt.executeUpdate(sql);
        stmt.close();
        Database.getConnection().commit();
    }

    /**
     * Getting the current game model
     * @return
     */
    @Override
    public GameDTO getGameModel(int gameID) throws SQLException, GameTableException {
        Statement stmt = Database.getConnection().createStatement();
        ResultSet rs = stmt.executeQuery( "SELECT * FROM GAMES WHERE GAMEID = "
                + gameID +";");
        GameDTO dto = new GameDTO();
        dto.setGameID(gameID);
        dto.setTitle(rs.getString("title"));
        dto.setState(rs.getBlob("state"));
        rs.close();
        stmt.close();
        return dto;
    }

    @Override
    public List<GameDTO> getAllGames() throws SQLException {
        List<GameDTO> games = new ArrayList<>();
        Statement stmt = Database.getConnection().createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM GAMES;");
        while (rs.next()){
            GameDTO game = new GameDTO();
            game.setGameID(rs.getInt("id"));
            game.setTitle(rs.getString("title"));
            game.setState(rs.getBlob("state"));
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
                + "where GAMEID=" + dto.getGameID() + ";";
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
            String sql = "DELETE FROM GAMES;";
            stmt.executeUpdate(sql);
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
