/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Database;

import Database.Primitives.Similarity;
import Database.Primitives.Song;
import Main.Main;
import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author sarahejones, sns
 */
public class Similarities {
    private int k;
    private ArrayList<Similarity> neighbors;

    public Similarities() {
        k = Main.getOptions().getK();
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
           neighbors.remove(0);
    }

    public void print() {
        for (Similarity is : neighbors) {
            System.out.println("-" + "\t"+ is.getNeighborSong().getID() + "\t" + is.getSimilarity());
        }
    }
    
    public double getSimilarity(Song song) {
        for (Similarity sim : neighbors)
            if (song.getID() == sim.getNeighborSong().getID())
                sim.getSimilarity();
        return 0;
    }
    
    public boolean contains(Song song) {
        for (Similarity sim : neighbors)
            if (song.getID() == sim.getNeighborSong().getID())
                return true;
        return false;
    }
}
