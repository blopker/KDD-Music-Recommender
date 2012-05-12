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
    private int ratingCount = 1;
    private Similarities similarities;
    
    public Song(int id, int rating){
        this.id = id;
        this.totalRating = rating;
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

    public void print() {
        System.out.println(id);
        similarities.print();
    }
}
