package daos;

import database.Database;
import dto.*;
import exceptions.CommandTableException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by Kyle 'TMD' Cornelison on 4/2/2016.
 */
public class CommandDAO implements ICommandDAO {


    @Override
    public void addCommand(IDTO dto) throws CommandTableException, SQLException {
        if(dto instanceof AddCommandDTO){
            Statement stmt = Database.getInstance().getConnection().createStatement();
            String sql = "INSERT INTO COMMANDS (GAMEID,VERSION,COMMAND) "
                    + "VALUES (" + ((AddCommandDTO) dto).getGameID() + ", " + ((AddCommandDTO) dto).getVersion() +
                    ", " + ((AddCommandDTO) dto).getiCommand() + " );";
            stmt.executeUpdate(sql);
            stmt.close();
            Database.getInstance().getConnection().commit();
        } else {
            throw new CommandTableException("wrong DTO");
        }
    }

    @Override
    public IDTO getCommands(IDTO dto) throws CommandTableException, SQLException {
        Statement stmt = Database.getInstance().getConnection().createStatement();
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
    public void deleteAllCommands(IDTO dto) {

    }

    @Override
    public void deleteCommandsFromGame(IDTO dto) {

    }
}
