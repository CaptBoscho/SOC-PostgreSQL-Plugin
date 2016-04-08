package daos;

import database.Database;
import dto.*;
import exceptions.GameTableException;

import java.sql.*;

import java.sql.SQLException;
import java.sql.Statement;

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
    public void addGameObject(IDTO dto) throws GameTableException, SQLException {
        if(dto instanceof NewGameDTO){
            Statement stmt = Database.getConnection().createStatement();
            String sql = "INSERT INTO GAMES (ID,STATE) "
                    + "VALUES (" + ((NewGameDTO) dto).getGameID() + ", " + ((NewGameDTO) dto).getGameState() + " );";
            stmt.executeUpdate(sql);
            stmt.close();
            Database.getConnection().commit();
        } else {
            throw new GameTableException("wrong DTO");
        }
    }

    /**
     * Handles verifying user which returns userID
     * Getting the current game model
     * getting a list of Commands
     * for just the getGameBlobDto, should only go through once
     * @param dto
     * @return
     */
    @Override
    public IDTO getGameModel(IDTO dto) throws SQLException, GameTableException {
        Statement stmt = Database.getConnection().createStatement();
        if(dto instanceof GetGameBlobDTO){
            ResultSet rs = stmt.executeQuery( "SELECT * FROM GAMES WHERE GAMEID = "
                    + ((GetGameBlobDTO) dto).getGameID() +";");
            while( rs.next()){
                ((GetGameBlobDTO) dto).setGameState(rs.getBlob("state"));
            }
            rs.close();
            stmt.close();
            return dto;
        } else if(dto instanceof GetAllGamesDTO){
            ResultSet rs = stmt.executeQuery("SELECT * FROM GAMES;");
            while (rs.next()){
                GameDTO game = new GameDTO();
                game.setGameID(rs.getInt("id"));
                game.setState(rs.getBlob("state"));
                ((GetAllGamesDTO) dto).addGame(game);
            }
            rs.close();
            stmt.close();
            return dto;
        } else {
            throw new GameTableException("wrong dto");
        }
    }

    /**
     * mostly be used for updating the game blob state
     *
     * @param dto
     */
    @Override
    public void updateGame(IDTO dto) throws GameTableException, SQLException {
        if(dto instanceof UpdateGameDTO){
            Statement stmt = Database.getConnection().createStatement();
            String sql = "UPDATE GAME set STATE = " + ((UpdateGameDTO) dto).getGameState()
                    + "where GAMEID=" + ((UpdateGameDTO) dto).getGameID() + ";";
            stmt.executeUpdate(sql);
            Database.getConnection().commit();
            stmt.close();
        } else {
            throw new GameTableException("wrong dto");
        }
    }

    /**
     * Mostly be used for deleting commands every n
     * moves.
     *
     * @param dto
     */
    @Override
    public void deleteAllGames(IDTO dto) throws SQLException, GameTableException {
        if(dto instanceof DeleteAllGamesDTO){
            Statement stmt = Database.getConnection().createStatement();
            String sql = "DELETE FROM GAMES;";
            stmt.executeUpdate(sql);
            Database.getConnection().commit();
            stmt.close();
        } else {
            throw new GameTableException("wrong dto");
        }
    }

    @Override
    public void deleteGame(IDTO dto) throws GameTableException, SQLException {
        if(dto instanceof  DeleteGameDTO){
            Statement stmt = Database.getConnection().createStatement();
            String sql = "DELETE FROM GAMES where " + ((DeleteGameDTO) dto).getGameID() + ";";
            stmt.executeUpdate(sql);
            Database.getConnection().commit();
            stmt.close();
        } else {
            throw new GameTableException("wrong dto");
        }
    }
}
