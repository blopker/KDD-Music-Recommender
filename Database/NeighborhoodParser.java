package Database;

import Database.Primitives.Similarity;
import Database.Primitives.Song;

public class NeighborhoodParser extends Parser {

    private Song currentSong;

    public NeighborhoodParser(String f) {
        super(f);
    }

    @Override
    protected void format(String dataLine) {
        if (dataLine.contains("-")) {
            formatSimilarity(dataLine);
        } else {
            formatSong(dataLine);
        }
    }

    private void formatSong(String dataLine) {
        if (dataLine != null) {
            currentSong = songs.getSong(Integer.parseInt(dataLine));
            if (currentSong == null) {
                System.err.println("Reading Neighborhood database found Song that wasn't already constructed.  Exiting.");
                System.exit(1);
            }

        } else {
            System.err.println("Unexpected format for song line: " + dataLine);
        }
    }

    private void formatSimilarity(String dataLine) {
        String[] splitData = dataLine.split("\t");
        if ((splitData != null) && (splitData.length == 3)) {
            int neighborID = Integer.parseInt(splitData[1]);
            Song neighbor = songs.getSong(neighborID);
            if (neighbor == null) {
                System.err.println("Reading Neighborhood database found Song that wasn't already constructed.  Exiting.");
                System.exit(1);
            }
            double neighborSim = Double.parseDouble(splitData[2]);
            Similarity sim = new Similarity(neighbor, neighborSim);
            currentSong.addToNeighborhood(sim);
        } else {
            System.err.println("Unexpected format for similarity line: " + dataLine);
        }
    }
}
