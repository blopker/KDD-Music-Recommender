package Database;

import Database.Primitives.Song;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 *
 * @author ninj0x
 */
public class Songs implements Iterable<Song> {

    private Map<Integer, Song> songs;
    private int count = 0;

    public Songs() {
        songs = new HashMap<Integer, Song>();
    }

    public void addSong(Song song) {
        if (songs.containsKey(song.getID())) {
            songs.get(song.getID()).joinRating(song.getRating(), song.getRatingCount());
        } else {
            songs.put(song.getID(), song);
        }
        count++;
    }
    
    public int getCount(){
        return count;
    }

    public Song getSong(int id) {
        return songs.get(id);
    }
    
    public boolean containsSong(int id){
        return songs.containsKey(id);
    }
    

    @Override
    public Iterator<Song> iterator() {
        return songs.values().iterator();
    }
}
