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
