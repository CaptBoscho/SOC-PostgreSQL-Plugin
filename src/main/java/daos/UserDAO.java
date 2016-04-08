package daos;

import database.Database;
import dto.*;
import exceptions.UserTableException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.sql.SQLException;
import java.sql.Statement;

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
    public void addUser(IDTO dto) throws UserTableException, SQLException {
        if(dto instanceof AddUserDTO){
            Statement stmt = Database.getConnection().createStatement();
            String sql = "INSERT INTO USERS (ID,NAME,USERNAME,PASSWORD) "
                    + "VALUES (" + ((AddUserDTO) dto).getID() + ", " + ((AddUserDTO) dto).getName() + ", "
                    + ((AddUserDTO) dto).getUserName() + ", " + ((AddUserDTO) dto).getPassword() + " );";
            stmt.executeUpdate(sql);
            stmt.close();
            Database.getConnection().commit();

        } else {
            throw new UserTableException("Wrong DTO");
        }
    }

    /**
     * Handles verifying user which returns userID
     * Getting the current game model
     * getting a list of Commands
     *
     * @param dto
     * @return
     */
    @Override
    public IDTO getUsers(IDTO dto) throws SQLException, UserTableException {
        if(dto instanceof GetAllUsersDTO) {
            Statement stmt = Database.getConnection().createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM USERS;");
            while(rs.next()){
                UserDTO user = new UserDTO();
                user.setName(rs.getString("name"));
                user.setUserName(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                ((GetAllUsersDTO) dto).addUser(user);
            }
            rs.close();
            stmt.close();
            return dto;
        } else {
            throw new UserTableException("wrong dto");
        }
    }


    /**
     * Mostly be used for deleting commands every n
     * moves.
     *
     * @param dto
     */
    @Override
    public void deleteUsers(IDTO dto) throws SQLException, UserTableException {
        if(dto instanceof DeleteAllUsersDTO) {
            Statement stmt = Database.getConnection().createStatement();
            String sql = "DELETE FROM USERS;";
            stmt.executeUpdate(sql);
            Database.getConnection().commit();
            stmt.close();
        } else {
            throw new UserTableException("wrong dto");
        }
    }
}
