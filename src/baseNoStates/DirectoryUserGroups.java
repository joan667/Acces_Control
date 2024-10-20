package baseNoStates;

import java.util.ArrayList;

public final class DirectoryUserGroups {
  private static final ArrayList<User> users = new ArrayList<User>();
  private static final ArrayList<UserGroup> userGroups = new ArrayList<UserGroup>();

  /**
   * Create the user groups and add some users to them.
   */
  public static void makeUserGroups() {
    // Create the user groups
    UserGroup employees = new UserGroup("employees");
    UserGroup managers = new UserGroup("managers");
    UserGroup admin = new UserGroup("admin");

    // Add some users with no privileges:
    // - Users without any privilege, just to keep temporally users instead of deleting them, this is to withdraw all
    //   permissions but still to keep user data to give back permissions later.
    User bernat = new User("Bernat", "12345");
    User blai = new User("Blai", "77532");

    // Add some users with "employees" privileges:
    // - Days: Sep. 1 this year to Mar. 1 next year
    // - Week: Mon. - Fri. 9:00 am - 5:00 pm
    // - Some actions: Just "shortly unlock"
    // - Some areas: "ground floor", "floor 1", "exterior", "stairs" (this, for all), that is, everywhere but the parking
    users.add(new User("Ernest", "74984", employees));
    users.add(new User("Eulalia", "43295", employees));

    // Add some users with "managers" privileges:
    // - Days: Sep. 1 2024 to Mar. 1 2025
    // - Week: Mon - Sat. 8:00 am - 8:00 pm
    // - All actions
    // - All areas
    users.add(new User("Manel", "95783", managers));
    users.add(new User("Marta", "05827", managers));

    // Add some users with "admin" privileges:
    // - Days: Jan. 1 2024 to Dec. 31 2100
    // - All week
    // - All actions
    // - All areas
    users.add(new User("Ana", "11343", admin));
  }

  /**
   * Find a user by its credential.
   *
   * @param credential The credential of the user
   * @return The user with the given credential, or null if the user is not found
   */
  public static User findUserByCredential(String credential) {
    // Loop through all users
    for (User user : users)

      // Check if the user has the given credential and return it
      if (user.getCredential().equals(credential))
        return user;

    // If the user is not found, print an error message and return null
    System.out.println("user with credential " + credential + " not found");
    return null;
  }

}
