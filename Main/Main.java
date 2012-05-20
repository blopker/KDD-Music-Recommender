package Main;

import Database.KDDParser;
import Database.NeighborhoodParser;
import Database.Parser;
import Database.Primitives.User;
import Database.Songs;
import Database.Users;
import Recommender.Recommender;
import Recommender.SequentialKNN;
import java.util.Scanner;
import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;

/*
 * To change this template, choose Tools | Templates and open the template in
 * the editor.
 */
/**
 *
 * @author ninj0x
 */
public class Main {

    private static KNNOptions options = new KNNOptions();

    public static KNNOptions getOptions() {
        return options;
    }

    public static void main(String args[]) {

        // Parse the command line options
        CmdLineParser parser = new CmdLineParser(options);
        try {
            parser.parseArgument(args);

        } catch (CmdLineException ex) {
            usage(parser);
            commandLineError(ex);
        } 
        try {
            run();
        } catch (IndexOutOfBoundsException ex) {
            System.err.println("Array Index error");
            usage(parser);
        }
        

    }

    private static void commandLineError(CmdLineException ex) {
        System.out.println(ex.getMessage());
        CmdLineParser parser = ex.getParser();
        usage(parser);
    }

    private static void usage(CmdLineParser parser) {
        System.out.println("Usage:\njava -jar KDD-Music-Recommender.jar -k N DATABASE\njava -jar KDD-Music-Recommender.jar -q -t D -n NEIGHBOR_FILE DATABASE");
        parser.printUsage(System.out);
        System.exit(1);
    }

    private static void run() {
        Recommender recommender = getRecommender();
        // Are we in calc or query mode?
        switch (options.getMode()) {
            case CALC:
                calculate(recommender);
                break;
            case QUERY:
                query(recommender);
                break;
            default:
                break;
        }
    }

    // Stub, change to dynamically switch to parallel calculation.
    private static Recommender getRecommender() {
        return new SequentialKNN();
    }

    private static void calculate(Recommender recommender) {
        Parser parser = new KDDParser(options.getDatabasePath());

        Songs songs = new Songs();
        Users users = new Users();
        parser.parse(songs, users);

        recommender.createNeighborhoods(songs, users, options.getK());
    }

    private static void query(Recommender recommender) {
        Songs songs = new Songs();
        Users users = new Users();

        Parser kddParser = new KDDParser(options.getDatabasePath());
        kddParser.parse(songs, users);

        Parser nbrParser = new NeighborhoodParser(options.getNeighborhoodFilePath());  //alternatively print out users that rated that item
        nbrParser.parse(songs, users);

        Scanner in = new Scanner(System.in);
        int line;
        System.out.println("Enter user id");

        while (in.hasNext()) {
            line = Integer.parseInt(in.nextLine());
            User u = users.getUser(line);
            if (u == null) {
                System.out.println("Invalid user id");
                continue;
            }
            recommender.recommendSong(u, songs,options.getThreshold());
            System.out.println("Enter user id");
        }
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
