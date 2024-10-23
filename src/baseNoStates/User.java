package baseNoStates;

public class User {
    private final String name;
    private final String credential;
    private UserGroup group;

    /**
     * Create a new user with a name and a credential.
     *
     * @param name The name of the user
     * @param credential The credential of the user
     */
    public User(String name, String credential) {
        // Set the name and credential of the user
        this.name = name;
        this.credential = credential;
    }

    /**
     * Create a new user with a name, a credential, and a group.
     *
     * @param name The name of the user
     * @param credential The credential of the user
     * @param group The group of the user
     */
    public User(String name, String credential, UserGroup group) {
        // Set the name and credential of the user
        this.name = name;
        this.credential = credential;

        // Set the group of the user
        this.setGroup(group);
    }

    /**
     * Check if the user has access to a specific space.
     *
     * @param space The space to check
     * @return True if the user has access to the space, false otherwise
     */
    public boolean hasAccess(Space space) {
        // Check if the user is not in a group
        if (group == null)
            return false;

        // Check if the user group has access to the space
        return group.hasAccess(space);
    }

    /**
     * Check if the user has access to a specific action.
     *
     * @param action The action to check
     * @return True if the user has access to the action, false otherwise
     */
    public boolean canPerform(String action) {
        // Check if the user is not in a group
        if (group == null)
            return false;

        // Check if the user group has access to the action
        return group.canPerform(action);
    }

    /**
     * Set the group of the user.
     *
     * @param group The group of the user
     */
    public void setGroup(UserGroup group) {
        // Check if the user is already in a group and remove it
        if (this.group != null)
            this.group.removeUser(this);

        // Set the group of the user
        this.group = group;

        // Add the user to the group
        group.addUser(this);
    }

    /**
     * Get the name of the user.
     *
     * @return The name of the user
     */
    public String getName() {
        return name;
    }

    /**
     * Get the credential of the user.
     *
     * @return The credential of the user
     */
    public String getCredential() {
        return credential;
    }

    /**
     * Get the group of the user.
     *
     * @return The group of the user
     */
    public UserGroup getGroup() {
        return group;
    }

    /**
     * Convert the user to a data string.
     *
     * @return The JSON string of the user data
     */
    @Override
    public String toString() {
        return "User{name=" + name + ", credential=" + credential + ", group=" + group + "}";
    }

}
