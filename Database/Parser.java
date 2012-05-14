package Database;

import Database.Primitives.Song;
import Database.Primitives.User;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Parser {
        private Songs songs;
        private Users users;
        private User currentUser;
        private FileInputStream file;
        


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

        private void format(String dataLine) {
            if(dataLine.contains("|")){
                    formatUser(dataLine);
            } else {
                    formatSong(dataLine);
            }
        }

        private void formatSong(String dataLine) {
            int[] splitData = strArrayToIntArray(dataLine.split("\t"));
            if (splitData.length == 2) {
                Song newSong = new Song(splitData[0], splitData[1]);
                currentUser.addRating(newSong);
                songs.addSong(newSong);
                
            }
            else {
                System.err.println("Unexpected format for song line: " + dataLine);
            }
        }

        private void formatUser(String dataLine) {
            String[] splitData = dataLine.split("|");
            if (splitData != null) {
                currentUser = new User(strArrayToIntArray(splitData)[0]);
                users.addUser(currentUser);
            }
            else 
                System.err.println("Unexpected format for user line: " + dataLine);
        }

        private int[] strArrayToIntArray(String[] line) {
            int[] infoLine = new int[line.length];
            for (int i = 0; i < line.length; i++) {
                    int info = (line[i].toLowerCase() == "none")?-1:Integer.                                                                                                                                                             valueOf(line[i]).intValue();
                    infoLine[i] = info;
            }
            return infoLine;
        }

}

