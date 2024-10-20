package baseNoStates;

import java.util.ArrayList;

public class UserGroup {

    //TODO: Add the root area.

    private final String id;
    private ArrayList<User> users = new ArrayList<User>();

    /**
     * Create a new user group with an id.
     *
     * @param id The id of the user group
     */
    public UserGroup(String id) {
        this.id = id;
    }

    /**
     * Add a user to the group.
     *
     * @param user The user to add
     * @throws IllegalArgumentException If the user is already in the group
     */
    public void addUser(User user) {
        // Check if the user is already added
        if (users.contains(user))
            throw new IllegalArgumentException("User already exists in group");

        // Check if the user is already in another group and change it
        if (user.getGroup() != this) {
            user.setGroup(this);
            return;
        }

        // Add the user to the group list
        users.add(user);
    }

    /**
     * Remove a user from the group.
     *
     * @param user The user to remove
     * @throws IllegalArgumentException If the user does not exist in the group
     */
    public void removeUser(User user) {
        // Check if the user is in the group
        if (!users.contains(user))
            throw new IllegalArgumentException("User does not exist in group");

        // Remove the user from the group list
        users.remove(user);
    }

    /**
     * Get the users in the group.
     *
     * @return The users in the group
     */
    public String getId() {
        return id;
    }
}
