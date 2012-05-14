/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package SequentialRecommender;

import Database.Parser;
import Database.Primitives.Similarity;
import Database.Primitives.Song;
import Database.Primitives.User;
import Database.Songs;
import Database.Users;
import java.util.ArrayList;
/**
 *
 * @author sarahejones, sns
 */
public class SequentialKNN {

    public static void main(String args[]) {
        //parse file
        Songs songs = new Songs();
        Users users = new Users();
        if (args.length == 2) {
            Parser parser = new Parser(songs, users, args[1]);
            parser.read();
        }
        else {
            System.err.println("Database filename required.");
            System.exit(1);
        }
        createNeighborhoods(songs.getSongList(), users.getUserList());  
        //create neighborhood
    }
    
     /* could precompute  this w/ map reduce: finding k nearest neighbors */
    public static void createNeighborhoods(ArrayList<Song> items, ArrayList<User> users) {

    //forall items i  //ith iteration
        for (Song i: items) {

    //    forall items j  //split this into N parts
            for (Song j : items) {
                double numerator = 0, denominator_left = 0, denominator_right = 0;

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


                Similarity is = new Similarity( j, numerator / Math.sqrt(denominator_left * denominator_right));

                //attempt to add to neighborhood (it will only be added if it should be)
                i.getNeighborhood().insert(is);
            }
            i.print();
        }
    }



}
