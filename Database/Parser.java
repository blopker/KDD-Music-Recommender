package Database;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public abstract class Parser {

    Songs songs;
    Users users;
    FileInputStream file;

    public Parser(String database_file) {
        try {
            file = new FileInputStream(database_file);
        } catch (FileNotFoundException e) {
            System.err.println("Unable to open database: " + database_file);
            e.printStackTrace();
        }
    }

    public void parse(Songs s, Users u) {
        songs = s;
        users = u;
        System.out.println("Reading database...");
        StringBuilder text = new StringBuilder();
        String NL = System.getProperty("line.separator");
        Scanner scanner = new Scanner(file);
        while (scanner.hasNextLine()) {
            format(scanner.nextLine());
        }
        scanner.close();
        System.out.println("Database loaded.");
    }

    protected abstract void format(String dataLine);
}
