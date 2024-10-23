package baseNoStates;

import java.util.ArrayList;

public class UserGroup {

    private final String id;
    private final Schedule schedule;
    private ArrayList<User> users = new ArrayList<User>();

    /**
     * Create a new user group with an id and a schedule.
     *
     * @param id The id of the user group
     * @param schedule The schedule of the user group
     */
    public UserGroup(String id, Schedule schedule) {
        this.id = id;
        this.schedule = schedule;
    }

    /**
     * Create a new user group with an id, a schedule and a user.
     *
     * @param id The id of the user group
     * @param schedule The schedule of the user group
     * @param users The users to add
     */
    public UserGroup(String id, Schedule schedule, User... users) {
        // Set the id of the user group
        this.id = id;

        // Set the schedule of the user group
        this.schedule = schedule;

        // Add the users to the group
        for (User user : users)
            addUser(user);
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

    /**
     * Get the users in the group.
     *
     * @return The users in the group
     */
    public ArrayList<User> getUsers() {
        return users;
    }
}
