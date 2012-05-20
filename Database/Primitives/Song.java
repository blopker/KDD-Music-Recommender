package Database.Primitives;

import Database.Similarities;
import java.util.List;

/**
 *
 * @author ninj0x
 */
public class Song {

    private int id;
    private int totalRating;
    private int ratingCount;
    private Similarities similarities = new Similarities();

    public Song(int id, int rating) {
        this.id = id;
        this.totalRating = rating;
        ratingCount = 1;
    }

    public Song(int id) {
        this.id = id;
        this.totalRating = 0;
        ratingCount = 0;
    }

    public void addRating(int rating) {
        totalRating += rating;
        ratingCount++;
    }

    public void joinRating(int rating, int count) {
        totalRating += rating;
        ratingCount += count;
    }

    public int getRatingCount() {
        return ratingCount;
    }

    /**
     * Avgerage Rating is getRating()/getRatingCount()
     * @return
     */
    public int getRating() {
        return totalRating;
    }

    public int getID() {
        return id;
    }

    public Similarities getNeighborhood() {
        if (similarities == null) {
            similarities = new Similarities();
        }
        return similarities;
    }

    public void addToNeighborhood(Similarity sim) {
        if (similarities == null) {
            similarities = new Similarities();
        }
        similarities.insert(sim);
    }

    public double getSimilarity(Song s) {
        if (similarities == null) {
            similarities = new Similarities();
        }
        return similarities.getSimilarity(s);
    }

    public void print() {
        if (similarities == null) {
            similarities = new Similarities();
        }
        System.out.println(id);
        similarities.print();
    }

    @Override
    public boolean equals(Object o) {
    if (o instanceof Song) {
      Song other = (Song) o;
      return (id == other.id);
    }
    return false;
  }
}
