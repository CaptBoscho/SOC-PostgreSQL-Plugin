package daos;

import dto.IDTO;

/**
 * Created by Kyle 'TMD' Cornelison on 4/5/2016.
 */
public interface IUserDAO {
    /**
     * Handles adding a user
     * @param dto
     */
    void addObject(IDTO dto);

    /**
     * Handles verifying user which returns userID
     *
     * @param dto
     * @return
     */
    IDTO readData(IDTO dto);


    /**
     * delete a user
     * @param dto
     */
    void deleteData(IDTO dto);
}
