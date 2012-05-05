/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package SequentialRecommender;

import java.util.ArrayList;

/**
 *
 * @author sarahejones, sns
 */
public class Neighborhood {
    private static int k;
    private ArrayList<ItemSimilarity> neighbors;

    public int getK() {
        return k;
    }

    public void insert(ItemSimilarity is) {
       double newSimilarity = is.getSimilarity();
       if (newSimilarity > neighbors.get(neighbors.size() -1).getSimilarity()) {
           int i;
           /* TO DO : change this to binary search */
           for (i = neighbors.size() - 1; newSimilarity > neighbors.get(i).getSimilarity(); i--) {
               ;
           }
           int insertSpot = i + 1;
           neighbors.add(insertSpot, is);

           if (neighbors.size() > k)
               neighbors.remove(neighbors.size());
       }
    }

    public void print() {
        for (ItemSimilarity is : neighbors) {
            System.out.println("-" + is.getNeighborItem().getId() + "\t" + is.getSimilarity());
        }
    }
}
