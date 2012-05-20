package Database;

import Database.Primitives.Song;
import Database.Primitives.User;


public class KDDParser extends Parser {

    private User currentUser;

    public KDDParser(String f) {
        super(f);
    }

    @Override
    protected void format(String dataLine) {
        if (dataLine.contains("|")) {
            formatUser(dataLine);
        } else {
            formatSong(dataLine);
        }
    }

    private void formatSong(String dataLine) {
        //song\trating
        int[] splitData = strArrayToIntArray(dataLine.split("\t"));
        if (splitData.length == 2) {
            Song newSong;
            if (songs.containsSong(splitData[0])) {
                newSong = songs.getSong(splitData[0]);
                newSong.addRating(splitData[1]);
            } else {
                newSong = new Song(splitData[0], splitData[1]);
            }
            songs.addSong(newSong);

            currentUser.addRating(new Song(splitData[0], splitData[1]));

        } else {
            //System.err.println("Unexpected format for song line: " + dataLine);
        }
    }

    private void formatUser(String dataLine) {
        String[] splitData = dataLine.split("\\|");
        if (splitData != null) {
            currentUser = new User(strArrayToIntArray(splitData)[0]);
            users.addUser(currentUser);
        } else {
            System.err.println("Unexpected format for user line: " + dataLine);
        }
    }

    private int[] strArrayToIntArray(String[] line) {
        int[] infoLine = new int[2];
        for (int i = 0; i < 2; i++) {
            //none is a keyword in the KDD database
            
                int info = (line[i].toLowerCase() == "none") ? -1 : Integer.valueOf(line[i]).intValue();
                infoLine[i] = info;

        }
        return infoLine;
    }
}
