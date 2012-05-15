/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Database.Primitives;


/**
 *
 * @author sarahejones, sns
 */
public class Similarity implements Comparable<Similarity> {
    private Song neighbor;
    private double similarity;

    public Similarity(Song neighbor, double s) {
        this.neighbor = neighbor;
        similarity = s;
    }

    public double getSimilarity() {
        return similarity;
    }

    public Song getNeighborSong() {
        return neighbor;
    }

    public int compareTo(Similarity s) {
        double sim_other = s.getSimilarity();
        if (similarity == sim_other)
            return 0;
        else if (similarity > sim_other)
            return 1;
        else
            return -1;
    }


}
