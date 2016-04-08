package daos;

import dto.IDTO;
import exceptions.UserTableException;

import java.sql.SQLException;

/**
 * Created by Kyle 'TMD' Cornelison on 4/5/2016.
 */
public interface IUserDAO {
    /**
     * Handles adding a user
     * @param dto
     */
    void addUser(IDTO dto) throws UserTableException, SQLException;

    /**
     * Handles verifying user which returns userID
     *
     * @param dto
     * @return
     */
    IDTO getUsers(IDTO dto) throws SQLException, UserTableException;


    /**
     * delete a user
     * @param dto
     */
    void deleteUsers(IDTO dto);
}
