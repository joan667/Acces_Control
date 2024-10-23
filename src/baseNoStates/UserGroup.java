package baseNoStates;

import java.util.ArrayList;

public class UserGroup {

    private final String id;
    private final Schedule schedule;
    private final ArrayList<Area> areas = new ArrayList<Area>();
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
     * Add an area to the group.
     *
     * @param area The area to add
     * @throws IllegalArgumentException If the area is already in the group
     */
    public void addArea(Area area) {
        // Check if the area is already added
        if (areas.contains(area))
            throw new IllegalArgumentException("Area already exists in group");

        // Add the area to the group list
        areas.add(area);
    }

    /**
     * Remove an area from the group.
     *
     * @param area The area to remove
     * @throws IllegalArgumentException If the area does not exist in the group
     */
    public void removeArea(Area area) {
        // Check if the area is in the group
        if (!areas.contains(area))
            throw new IllegalArgumentException("Area does not exist in group");

        // Remove the area from the group list
        areas.remove(area);
    }

    /**
     * Check if the group has access to a specific space.
     *
     * @param space The space to check
     * @return True if the group has access to the space, false otherwise
     */
    public boolean hasAccess(Space space) {
        // Loop through all areas
        for (Area area : areas) {

            // Check if the area is a space and if it is the same space
            if (area instanceof Space) {
                if (area.equals(space))
                    return true;

            // Check if the area is a partition and if it contains the space
            } else if (area instanceof Partition) {
                ArrayList<Space> spaces = ((Partition) area).getSpaces();
                if (spaces.contains(space))
                    return true;
            }
        }

        // If the group does not have access to the space, return false
        return false;
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
