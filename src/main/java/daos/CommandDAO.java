package daos;

import server.persistence.Database;
import server.persistence.dto.CommandDTO;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Kyle 'TMD' Cornelison on 4/2/2016.
 */
public class CommandDAO implements ICommandDAO {


    @Override
    public void addCommand(CommandDTO dto) throws SQLException, UnsupportedEncodingException {
        System.out.println(dto.toString());
        Statement stmt = Database.getConnection().createStatement();
        String sql = "INSERT INTO COMMANDS (GAMEID,COMMANDBLOB) "
                + "VALUES (" + dto.getGameID() + ", '" + dto.getCommand() + "' );";
        stmt.executeUpdate(sql);
        stmt.close();
//        Database.getConnection().commit();
    }

    @Override
    public List<CommandDTO> getCommands(int gameID) throws SQLException, UnsupportedEncodingException {
        Statement stmt = Database.getConnection().createStatement();
        ResultSet rs = stmt.executeQuery( "SELECT * FROM COMMANDS WHERE GAMEID = " + gameID + " ORDER  BY ID DESC;" );
        List<CommandDTO> commands = new ArrayList<>();
        while (rs.next()) {
            /*String other = rs.getString("commandblob").substring(1, rs.getString("commandblob").length() - 1);
            String[] strings =  other.split(", ");
            byte[] bytes = new byte[strings.length];
            for (int i = 0; i < strings.length; i++) {
                bytes[i] = Byte.parseByte(strings[i]);
            }

            String str = new String(bytes);*/

            System.out.println("getting: " + rs.getString("commandblob"));
//            System.out.println("getting: " + str);

            CommandDTO command = new CommandDTO(rs.getInt("gameid"), rs.getString("commandblob")/*str*/);
            commands.add(command);
        }
        rs.close();
        stmt.close();
        return commands;
    }

    @Override
    public List<CommandDTO> getAllCommands() throws SQLException, UnsupportedEncodingException {
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
