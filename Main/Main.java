package Main;

import Database.KDDParser;
import Database.Parser;
import Database.Songs;
import Database.Users;
import Recommender.Recommender;
import Recommender.SequentialKNN;
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
        usage(parser);
        try {
            parser.parseArgument(args);
        } catch (CmdLineException ex) {
            commandLineError(ex);
        }

        run();

////        parse file
//        Songs songs = new Songs();
//        Users users = new Users();
//        for (String a : args) {
//            System.out.println(a);
//        }
//        if (args.length > 2) {
//            if (args[0].charAt(0) == '-') {
//
//                switch (args[0].charAt(1)) {
//
//                    case 'c':
//                        if (args.length == 3) {
//                            k = Integer.parseInt(args[2]);
//                            KDDParser parser = new KDDParser(songs, users, args[1]);
//                            parser.read();
//                            createNeighborhoods(songs, users);
//
//                        } else {
//                            commandLineError();
//                        }
//                        break;
//                    case 'q':
//                        if (args.length == 4) {
//                            threshold = Double.parseDouble(args[3]);
//                            NeighborhoodParser parser = new NeighborhoodParser(songs, users, args[1]);  //alternatively print out users that rated that item
//                            parser.read();
//                            KDDParser userParser = new KDDParser(songs, users, args[2]);
//                            userParser.read();
//                            Scanner in = new Scanner(System.in);
//                            int line;
//                            do {
//                                System.out.println("Enter user id");
//                                line = Integer.parseInt(in.nextLine());
//                                User u = users.getUser(line);
//                                if (u == null) {
//                                    System.out.println("Invalid user id");
//                                    continue;
//                                }
//                                recommendSong(u, songs);
//                            } while (in.hasNext());
//
//                        } else {
//                            commandLineError();
//                        }
//                        break;
//                    default:
//                        commandLineError();
//                }
//            } else {
//                commandLineError();
//            }
//
//        } else {
//            commandLineError();
//        }
    }

    private static void commandLineError(CmdLineException ex) {
        System.out.println(ex.getMessage());
        CmdLineParser parser = ex.getParser();
        usage(parser);
    }

    private static void usage(CmdLineParser parser) {
        System.out.println("Usage:\njava -jar KDD-Music-Recommender.jar -k N DATABASE\njava -jar KDD-Music-Recommender.jar -q N -t D -n NEIGHBOR_FILE DATABASE");
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
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
