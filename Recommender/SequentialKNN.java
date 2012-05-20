/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Recommender;

import Database.Primitives.Similarity;
import Database.Primitives.Song;
import Database.Primitives.User;
import Database.Songs;
import Database.Users;
/**
 *
 * @author sarahejones, sns
 */
public class SequentialKNN implements Recommender{
    
     /* could precompute  this w/ map reduce: finding k nearest neighbors */
    @Override
    public void createNeighborhoods(Songs items, Users users, int k) {
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
    
    @Override
    public void recommendSong(User active, Songs songs, double threshold) {                
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

