package Database.Primitives;

/**
 *
 * @author ninj0x
 */
public class Song {
    private int id;
    private int totalRating;
    private int ratingCount = 1;
    
    public Song(int id, int rating){
        this.id = id;
        this.totalRating = rating;
    }
    
    public void addRating(int rating){
        totalRating += rating;
        ratingCount++;
    }
    
    public void addAvgRating(int rating, int count){
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
}
