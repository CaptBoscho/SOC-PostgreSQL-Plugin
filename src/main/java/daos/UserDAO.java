package daos;

import server.persistence.Database;
import server.persistence.dto.UserDTO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kyle 'TMD' Cornelison on 4/2/2016.
 */
public class UserDAO implements IUserDAO {

    /**
     * Handles adding a user,
     * adding a command
     * adding a game
     *
     * @param dto
     */
    @Override
    public int addUser(UserDTO dto) throws SQLException {
        Statement stmt = Database.getConnection().createStatement();
        ResultSet rs = stmt.executeQuery("SELECT ID FROM USERS ORDER BY ID DESC LIMIT 1;");

        int newID;
        if (!rs.isBeforeFirst()) {
            // no users in database
            newID = 0;
        } else {
            rs.next();
            newID = rs.getInt("id") + 1;
        }
        rs.close();
        stmt.close();

        Statement finalStatement = Database.getConnection().createStatement();
        String sql = "INSERT INTO USERS (ID,NAME,USERNAME,PASSWORD) "
                + "VALUES (" + newID +  ", " + dto.getUserName() + ", " + dto.getPassword() + " );";
        finalStatement.executeUpdate(sql);
        finalStatement.close();
        Database.getConnection().commit();

        return newID;
    }

    /**
     * Handles verifying user which returns userID
     * Getting the current game model
     * getting a list of Commands
     *
     * @return
     */
    @Override
    public List<UserDTO> getUsers() throws SQLException {
        Statement stmt = Database.getConnection().createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM USERS;");
        List<UserDTO> users = new ArrayList<>();
        while(rs.next()){
            UserDTO user = new UserDTO(rs.getInt("id"), rs.getString("username"), rs.getString("password"));
            users.add(user);
        }
        rs.close();
        stmt.close();
        return users;
    }

    /**
     * Mostly be used for deleting commands every n moves.
     *
     */
    @Override
    public void deleteUsers() throws SQLException {
        Statement stmt = Database.getConnection().createStatement();
        String sql = "DELETE FROM USERS;";
        stmt.executeUpdate(sql);
        Database.getConnection().commit();
        stmt.close();
    }
}
