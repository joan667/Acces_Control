package base;

import java.time.LocalDateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



/**
 * A class that represents a user.
 */
public class User {

  private static final Logger logger = LoggerFactory.getLogger("base.User");

  private final String name;
  private final String credential;
  private UserGroup group;

  /**
   * Create a new user with a name and a credential.
   *
   * @param name       The name of the user
   * @param credential The credential of the user
   */
  public User(String name, String credential) {
    logger.info("Creating user with name: {} and credential: {}", name, credential);
    this.name = name;
    this.credential = credential;
    logger.info("User created successfully with name: {} and credential: {}", name, credential);
  }

  /**
   * Create a new user with a name, a credential, and a group.
   *
   * @param name       The name of the user
   * @param credential The credential of the user
   * @param group      The group of the user
   */
  public User(String name, String credential, UserGroup group) {
    logger.info("Creating user with name: {} and credential: {}", name, credential);
    this.name = name;
    this.credential = credential;
    this.setGroup(group);
    logger.info("User created successfully with name: {} and credential: {}.", name, credential);
  }

  /**
   * Check if the user has access to a specific space.
   *
   * @param space The space to check
   * @return True if the user has access to the space, false otherwise
   */
  public boolean hasAccess(Space space) {
    logger.debug("Checking access for user: {} to space: {}", name, space.getId());
    if (group == null) {
      logger.warn("User: {} does not belong to any group. Access denied.", name);
      return false;
    }
    boolean access = group.hasAccess(space);
    logger.info("Access check for user: {} to space: {} resulted in: {}",
        name, space.getId(), access);
    return access;
  }

  /**
   * Check if the user has access to a specific action in the current time.
   *
   * @param action   The action to check
   * @param datetime The date and time to check
   * @return True if the user has access to the action, false otherwise
   */
  public boolean canPerform(String action, LocalDateTime datetime) {
    logger.debug("Checking if user: {} can perform action: {} at datetime: {}",
        name, action, datetime);
    if (group == null) {
      logger.warn("User: {} does not belong to any group. Action denied.", name);
      return false;
    }
    boolean canPerform = group.canPerform(action, datetime);
    logger.info("Action check for user: {} to perform: {} at datetime: {} resulted in: {}",
        name, action, datetime, canPerform);
    return canPerform;
  }

  /**
   * Set the group of the user.
   *
   * @param group The group of the user
   */
  public void setGroup(UserGroup group) {
    logger.info("Setting group for user: {}.", name);
    if (this.group != null) {
      logger.info("Removing user: {} from current group.", name);
      this.group.removeUser(this);
    }
    this.group = group;
    group.addUser(this);
    logger.info("User: {} added to new group.", name);
  }

  /**
   * Get the name of the user.
   *
   * @return The name of the user
   */
  public String getName() {
    logger.debug("Fetching name for user: {}", name);
    return name;
  }

  /**
   * Get the credential of the user.
   *
   * @return The credential of the user
   */
  public String getCredential() {
    logger.debug("Fetching credential for user: {}", name);
    return credential;
  }

  /**
   * Get the group of the user.
   *
   * @return The group of the user
   */
  public UserGroup getGroup() {
    logger.debug("Fetching group for user: {}", name);
    return group;
  }

  /**
   * Convert the user to a data string.
   *
   * @return The JSON string of the user data
   */
  @Override
  public String toString() {
    logger.debug("Converting user: {} to string.", name);
    return "User{name=" + name + ", credential=" + credential
        + ", group=" + (group != null ? "assigned" : "none") + "}";
  }
}
