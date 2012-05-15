package Database;

import Database.Primitives.Song;
import Database.Primitives.User;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public abstract class Parser {
        Songs songs;
        Users users;
        FileInputStream file;
        


        public Parser(Songs s, Users u, String f)  {
            songs = s;
            users = u;
            try {
                file = new FileInputStream(f);
            } catch (FileNotFoundException e) {
                System.err.println("Unable to open database: " + f);
                e.printStackTrace();
            }
        }

        public void read() {
            System.out.println("Reading database...");
            StringBuilder text = new StringBuilder();
            String NL = System.getProperty("line.separator");
                Scanner scanner = new Scanner(file);
              while (scanner.hasNextLine()){
                format(scanner.nextLine());
              }
              scanner.close();
            System.out.println("Database loaded.");
        }

        protected abstract void format(String dataLine) ;
         
        


}

