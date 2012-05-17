package Database.Primitives;

import Database.Songs;

/**
 *
 * @author ninj0x
 */
public class User {
    private Songs ratings;
    private final int id;
    private int sumRatings = 0;
    
    public User(int id){
        this.id = id;
        ratings = new Songs();
    }
    
    public int getID(){
        return id;
    }
    
    public Songs getRatings(){
        return ratings;
    }
    
    public int getRating(Song song){
        return getRating(song.getID());
        
    }
    
    public int getRating(int id){
        return ratings.getSong(id).getRating();
    }
    
    public void addRating(Song song){
        ratings.addSong(song);
        sumRatings += song.getRating();
    }
    
    public double getAvgRating(){
        return sumRatings/ratings.getCount();
    }
    
    public boolean rated(Song song){
        return rated(song.getID());
    }
    
    public boolean rated(int id){
        return ratings.containsSong(id);
    }
    
}
