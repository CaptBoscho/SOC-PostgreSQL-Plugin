package main;

import database.Database;

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
        }
    }
}
