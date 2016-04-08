package daos;

import database.Database;
import dto.*;
import exceptions.UserTableException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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
    public void addUser(UserDTO dto) throws UserTableException, SQLException {
        Statement stmt = Database.getConnection().createStatement();
        String sql = "INSERT INTO USERS (ID,NAME,USERNAME,PASSWORD) "
                + "VALUES (" + dto.getId() + ", " + dto.getName() + ", "
                + dto.getUserName() + ", " + dto.getPassword() + " );";
        stmt.executeUpdate(sql);
        stmt.close();
        Database.getConnection().commit();
    }

    /**
     * Handles verifying user which returns userID
     * Getting the current game model
     * getting a list of Commands
     *
     * @return
     */
    @Override
    public List<UserDTO> getUsers() throws SQLException, UserTableException {
        Statement stmt = Database.getConnection().createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM USERS;");
        List<UserDTO> users = new ArrayList<>();
        while(rs.next()){
            UserDTO user = new UserDTO();
            user.setId(rs.getInt("id"));
            user.setName(rs.getString("name"));
            user.setUserName(rs.getString("username"));
            user.setPassword(rs.getString("password"));
            users.add(user);
        }
        rs.close();
        stmt.close();
        return users;
    }


    /**
     * Mostly be used for deleting commands every n
     * moves.
     *
     */
    @Override
    public void deleteUsers() throws SQLException, UserTableException {
        Statement stmt = Database.getConnection().createStatement();
        String sql = "DELETE FROM USERS;";
        stmt.executeUpdate(sql);
        Database.getConnection().commit();
        stmt.close();
    }
}
