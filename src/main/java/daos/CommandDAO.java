package daos;

import server.persistence.Database;
import server.persistence.dto.CommandDTO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kyle 'TMD' Cornelison on 4/2/2016.
 */
public class CommandDAO implements ICommandDAO {


    @Override
    public void addCommand(CommandDTO dto) throws SQLException {
        System.out.println(dto.toString());
        Statement stmt = Database.getConnection().createStatement();
        String sql = "INSERT INTO COMMANDS (GAMEID,COMMANDBLOB) "
                + "VALUES (" + dto.getGameID() + ", '" + dto.getCommand() + "' );";
        stmt.executeUpdate(sql);
        stmt.close();
//        Database.getConnection().commit();
    }

    @Override
    public List<CommandDTO> getCommands(int gameID) throws SQLException {
        Statement stmt = Database.getConnection().createStatement();
        ResultSet rs = stmt.executeQuery( "SELECT * FROM COMMANDS WHERE GAMEID = " + gameID + ";" );
        List<CommandDTO> commands = new ArrayList<>();
        while (rs.next()){
            CommandDTO command = new CommandDTO(rs.getInt("gameid"), rs.getString("commandblob"));
            commands.add(command);
        }
        rs.close();
        stmt.close();
        return commands;
    }

    @Override
    public List<CommandDTO> getAllCommands() throws SQLException {
        System.out.println("getting all commands");
        Statement stmt = Database.getConnection().createStatement();
        ResultSet rs = stmt.executeQuery( "SELECT * FROM COMMANDS;" );
        List<CommandDTO> commands = new ArrayList<>();
        while (rs.next()) {
            CommandDTO command = new CommandDTO(rs.getInt("gameid"), rs.getString("commandblob"));
            commands.add(command);
            System.out.println("adding a command with game id: " + command.getGameID());
        }
        rs.close();
        stmt.close();
        return commands;
    }

    @Override
    public void deleteAllCommands() throws SQLException {
        Statement stmt = Database.getConnection().createStatement();
        String sql = "DELETE FROM COMMANDS;";
        stmt.executeUpdate(sql);
//        Database.getConnection().commit();
        stmt.close();
    }

    @Override
    public void deleteCommandsFromGame(int gameID) throws SQLException {
        Statement stmt = Database.getConnection().createStatement();
        String sql = "DELETE from COMMANDS where GAMEID =" + gameID + ";";
        stmt.executeUpdate(sql);
//        Database.getConnection().commit();
        stmt.close();
    }
}
