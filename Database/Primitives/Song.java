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
    private Similarities similarities;
    
    public Song(int id, int rating){
        this.id = id;
        this.totalRating = rating;
        ratingCount = 1;
    }
    public Song(int id) {
        this.id = id;
        this. totalRating = 0;
        ratingCount = 0;
    }
    
    public void addRating(int rating){
        totalRating += rating;
        ratingCount++;
    }
    
    public void joinRating(int rating, int count){
        totalRating += rating;
        ratingCount += count;
    }
    
    public int getRatingCount(){
        return ratingCount;
    }
    
    public int getRating(){
        return totalRating/ratingCount;
    }
    
    public int getID(){
        return id;
    }

    public Similarities getNeighborhood() {
        return similarities;
    }
    public void addToNeighborhood(Similarity sim) {
        similarities.insert(sim);
    }
    
    public double getSimilarity(Song s) {
        return similarities.getSimilarity(s);
    }

    public void print() {
        System.out.println(id);
        similarities.print();
    }
}
