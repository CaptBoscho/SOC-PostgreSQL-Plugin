package daos;

import database.Database;
import dto.*;
import exceptions.CommandTableException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by Kyle 'TMD' Cornelison on 4/2/2016.
 */
public class CommandDAO implements ICommandDAO {


    @Override
    public void addCommand(IDTO dto) throws CommandTableException, SQLException {
        if(dto instanceof AddCommandDTO){
            Statement stmt = Database.getConnection().createStatement();
            String sql = "INSERT INTO COMMANDS (GAMEID,VERSION,COMMAND) "
                    + "VALUES (" + ((AddCommandDTO) dto).getGameID() + ", " + ((AddCommandDTO) dto).getVersion() +
                    ", " + ((AddCommandDTO) dto).getiCommand() + " );";
            stmt.executeUpdate(sql);
            stmt.close();
            Database.getConnection().commit();
        } else {
            throw new CommandTableException("wrong DTO");
        }
    }

    @Override
    public IDTO getCommands(IDTO dto) throws CommandTableException, SQLException {
        Statement stmt = Database.getConnection().createStatement();
        if(dto instanceof GetCommandsDTO){
            ResultSet rs = stmt.executeQuery( "SELECT * FROM COMMANDS WHERE GAMEID = "
                    + ((GetCommandsDTO) dto).getGameID() + ";" );
            while (rs.next()){
                CommandDTO command = new CommandDTO();
                command.setVersion(rs.getInt("version"));
                command.setCommand(rs.getObject("command"));
                ((GetCommandsDTO) dto).addCommand(command);
            }
            rs.close();
            stmt.close();
            return dto;
        } else if(dto instanceof GetAllCommandsDTO){
            ResultSet rs = stmt.executeQuery( "SELECT * FROM COMMANDS;" );
            while (rs.next()){
                CommandDTO command = new CommandDTO();
                command.setGameID(rs.getInt("gameid"));
                command.setVersion(rs.getInt("version"));
                command.setCommand(rs.getObject("command"));
                ((GetAllCommandsDTO) dto).addCommand(command);
            }
            rs.close();
            stmt.close();
            return dto;
        } else {
            throw new CommandTableException("wrong DTO");
        }

    }

    @Override
    public void deleteAllCommands(IDTO dto) throws CommandTableException, SQLException {
        if(dto instanceof DeleteAllCommandsDTO){
            Statement stmt = Database.getConnection().createStatement();
            String sql = "DELETE FROM COMMANDS;";
            stmt.executeUpdate(sql);
            Database.getConnection().commit();
            stmt.close();
        } else {
            throw new CommandTableException("wrong dto");
        }
    }

    @Override
    public void deleteCommandsFromGame(IDTO dto) throws CommandTableException, SQLException {
        if(dto instanceof DeleteCommandsDTO){
            Statement stmt = Database.getConnection().createStatement();
            String sql = "DELETE from COMMANDS where GAMEID=" + ((DeleteCommandsDTO) dto).getGameID() + ";";
            stmt.executeUpdate(sql);
            Database.getConnection().commit();
            stmt.close();
        } else {
            throw new CommandTableException("wrong dto");
        }
    }
}
