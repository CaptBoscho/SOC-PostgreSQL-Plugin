package main;

import server.persistence.Database;
import server.persistence.dto.CommandDTO;
import server.persistence.dto.GameDTO;
import server.persistence.dto.UserDTO;

import javax.xml.crypto.Data;
import java.sql.SQLException;
import java.util.Scanner;

/**
 * @author Derek Argueta
 */
public class CLI {

    public static void main(String[] args) {
        Database database = Database.getInstance();

        Scanner scanner = new Scanner(System.in);
        while(true) {
            String input = scanner.nextLine();
            if(input.equals("quit")) {
                database.shutdown();
                System.exit(0);
            }

            if(input.equals("clear")) {
                database.clear();
            }

            if(input.equals("show")) {
                for (GameDTO game : Database.getInstance().getAllGames()) {
                    System.out.println(game.toString());
                }

                for (UserDTO user : Database.getInstance().getUsers()) {
                    System.out.println(user.toString());
                }

                for (CommandDTO command : Database.getInstance().getAllCommands()) {
                    System.out.println(command.toString());
                }
                System.out.println("All Done");
            }

            if(input.equals("test")) {
                Database.getInstance().addCommand(new CommandDTO(2, "this is the command"));
                Database.getInstance().addUser(new UserDTO(0, "danny", "danny"));
                Database.getInstance().addUser(new UserDTO(0, "test", "test"));
                Database.getInstance().addUser(new UserDTO(2, "ashley", "ashley"));
            }

            if(input.equals("drop")) {
                try {
                    Database.getInstance().dropTables();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
