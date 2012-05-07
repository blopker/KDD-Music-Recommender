package Database;

import Database.Primitives.User;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 *
 * @author ninj0x
 */
public class Users implements Iterable<User> {

    private Map<Integer, User> users;

    protected Users() {
        users = new HashMap<Integer, User>();
    }

    protected void addUser(User user) {
            users.put(user.getID(), user);
    }

    protected User getUser(int id) {
        return users.get(id);
    }

    @Override
    public Iterator<User> iterator() {
        return users.values().iterator();
    }
}
