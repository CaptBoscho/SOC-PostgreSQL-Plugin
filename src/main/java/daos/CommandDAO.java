package daos;

import database.Database;
import dto.IDTO;

import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by Kyle 'TMD' Cornelison on 4/2/2016.
 */
public class CommandDAO implements ICommandDAO {


    @Override
    public void addCommand(IDTO dto) {
        try {
            Statement st = Database.getConnection().createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public IDTO getCommands(IDTO dto) {
        try {
            Statement st = Database.getConnection().createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void deleteAllCommands(IDTO dto) {
        try {
            Statement st = Database.getConnection().createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteCommandsFromGame(IDTO dto) {
        try {
            Statement st = Database.getConnection().createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
