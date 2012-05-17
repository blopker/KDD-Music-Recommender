/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package SequentialRecommender;

import Database.KDDParser;
import Database.NeighborhoodParser;
import Database.Primitives.Similarity;
import Database.Primitives.Song;
import Database.Primitives.User;
import Database.Songs;
import Database.Users;
import java.util.ArrayList;
import java.util.Scanner;
/**
 *
 * @author sarahejones, sns
 */
public class SequentialKNN {

    private static int k;               //k for k-NN
    private static double threshold;    //prediction threshold for query
    
    public static int getK() {
        return k;
    }
    public static void main(String args[]) {
        //parse file
        Songs songs = new Songs();
        Users users = new Users();
                for (String a : args) {
            System.out.println(a);
        }
        if (args.length > 2) {
            if (args[0].charAt(0) == '-') {

                switch (args[0].charAt(1)) {

                    case 'c':
                        if (args.length == 3) {
                            k = Integer.parseInt(args[2]);
                            KDDParser parser = new KDDParser(songs, users, args[1]);
                            parser.read();
                            createNeighborhoods(songs, users);  

                        }
                        else commandLineError();
                        break;
                    case 'q':
                        if (args.length == 4) {
                            threshold = Double.parseDouble(args[3]);
                            NeighborhoodParser parser = new NeighborhoodParser(songs, users, args[1]);  //alternatively print out users that rated that item
                            parser.read();
                            KDDParser userParser = new KDDParser(songs, users, args[2]);
                            userParser.read();
                            Scanner in = new Scanner(System.in);
                            int line;
                            do {
                                System.out.println("Enter user id");
                                line = Integer.parseInt(in.nextLine());
                                User u = users.getUser(line);
                                if (u == null) {
                                    System.out.println("Invalid user id");
                                    continue;
                                }
                                recommendSong(u, songs);
                            } while(in.hasNext());
                            
                        }
                        else commandLineError();
                        break;
                    default:
                        commandLineError();

                }
            }
            else commandLineError();

        } else commandLineError();
    }
    
    private static void commandLineError() {
        System.err.println("Incorrect format.");
        System.out.println("The -c option creates the neighborhood.  The -q option is used to perform queries.");
        System.out.println("1) java SequentialKNN -c <kdd_file> k");
        System.out.println("2) java SequentialKNN -q <neighborhood_file> <kdd_file> threshold");
        System.exit(1);
    }
    
     /* could precompute  this w/ map reduce: finding k nearest neighbors */
    public static void createNeighborhoods(Songs items, Users users) {

    //forall items i  //ith iteration
        for (Song i: items) {

    //    forall items j  //split this into N parts
            for (Song j : items) {
                double numerator = 0, denominator_left = 0, denominator_right = 0;
        
                if (j.equals(i))
                    continue;
    //        forall users user
                for (User user : users) {
                    double num = 0, den_l = 0, den_r = 0;
                    if (user.rated(i) && user.rated(j)) {
                        double iTmp = user.getRating(i) - user.getAvgRating();
                        double jTmp = user.getRating(j) - user.getAvgRating();
                        
                        num = iTmp * jTmp;
                        den_l = iTmp * iTmp;
                        den_r = jTmp * jTmp;
                    }
                    numerator += num;
                    denominator_left += den_l;
                    denominator_right += den_r;
                }
                /*
                 * sim will equal 1.0 if both songs are rated the same (have the same difference, for each user)
                 * NaN if no users have rated both songs
                 * If negative, should not recommend...
                 */
                double sim = numerator / Math.sqrt(denominator_left * denominator_right);
                if (!Double.isNaN(sim)) {
                    Similarity is = new Similarity( j, sim);
                    i.getNeighborhood().insert(is);
                }

                //attempt to add to neighborhood (it will only be added if it should be)
            }
            i.print();
        }
    }
    
    public static void recommendSong(User active, Songs songs) {

//      forall items i
        for (Song s: songs) { 
//        forall neighborhood_i Union items_rated_by_user(active) item
            double numerator = 0, denominator = 0, predictedRating;
            for (Song ratedByActive : active.getRatings()) {
                if (!active.rated(s) && s.getNeighborhood().contains(ratedByActive)) {  //& rating > 50  ??
                    double similarity = ratedByActive.getSimilarity(s);
//                numerator += math ... similarity(i, item) * active.rating(item) …
                    numerator += similarity * active.getRating(ratedByActive);
//                denominator += |similarity(i, item)|                    
                    denominator += Math.abs(similarity);
                }
            }
            predictedRating = numerator / denominator;
            //add item to set of recommended items if preQdicted_rating is “good enough”\
            if (predictedRating >= threshold)
                System.out.println(s + "\t" + predictedRating);
           
        }
    }

}

