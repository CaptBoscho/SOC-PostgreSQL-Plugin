package dto;

import java.util.List;

/**
 * Created by boscho on 4/6/16.
 */
public class GetAllUsersDTO implements IDTO {

    public List<UserDTO> getUsers() {
        return users;
    }

    public void setUsers(List<UserDTO> users) {
        this.users = users;
    }

    //Strings stored in JSON format
    List<UserDTO> users;
}
