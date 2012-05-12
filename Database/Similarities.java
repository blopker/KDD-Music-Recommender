/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Database;

import Database.Primitives.Similarity;
import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author sarahejones, sns
 */
public class Similarities {
    private static int k;
    private ArrayList<Similarity> neighbors;

    public Similarities() {
        neighbors = new ArrayList<Similarity>();
    }

    public int getK() {
        return k;
    }

    public void insert(Similarity is) {
       int index = Collections.binarySearch(neighbors, is);
       if (index < 0)
           neighbors.add(-index -1, is);
       while (neighbors.size() > k)
           neighbors.remove(k);
    }

    public void print() {
        for (Similarity is : neighbors) {
            System.out.println("-" + is.getNeighborItem().getID() + "\t" + is.getSimilarity());
        }
    }
}
