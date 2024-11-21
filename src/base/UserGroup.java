package base;

import java.time.LocalDateTime;
import java.util.ArrayList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



/**
 * A class that represents a user group.
 */
public class UserGroup {

  private static final Logger logger = LoggerFactory.getLogger("base.UserGroup");

  private final String id;
  private final Schedule schedule;
  private final ArrayList<Area> areas = new ArrayList<>();
  private final ArrayList<String> actions = new ArrayList<>();
  private final ArrayList<User> users = new ArrayList<>();

  /**
   * Create a new user group with an id and a schedule.
   *
   * @param id       The id of the user group
   * @param schedule The schedule of the user group
   */
  public UserGroup(String id, Schedule schedule) {
    logger.info("Creating user group with id: {}", id);
    this.id = id;
    this.schedule = schedule;
    logger.info("User group created successfully with id: {}", id);
  }

  /**
   * Create a new user group with an id, a schedule, and users.
   *
   * @param id       The id of the user group
   * @param schedule The schedule of the user group
   * @param users    The users to add
   */
  public UserGroup(String id, Schedule schedule, User... users) {
    logger.info("Creating user group with id: {} and adding initial users.", id);
    this.id = id;
    this.schedule = schedule;
    for (User user : users) {
      addUser(user);
    }
    logger.info("User group created successfully with id: {}", id);
  }

  /**
   * Add a user to the group.
   *
   * @param user The user to add
   * @throws IllegalArgumentException If the user is already in the group
   */
  public void addUser(User user) {
    logger.debug("Attempting to add user: {} to group: {}", user.getName(), id);
    if (users.contains(user)) {
      logger.warn("User: {} is already in group: {}", user.getName(), id);
      throw new IllegalArgumentException("User already exists in group");
    }
    if (user.getGroup() != this) {
      logger.info("User: {} belongs to another group. Reassigning to group: {}",
          user.getName(), id);
      user.setGroup(this);
      return;
    }
    users.add(user);
    logger.info("User: {} added successfully to group: {}", user.getName(), id);
  }

  /**
   * Remove a user from the group.
   *
   * @param user The user to remove
   * @throws IllegalArgumentException If the user does not exist in the group
   */
  public void removeUser(User user) {
    logger.debug("Attempting to remove user: {} from group: {}", user.getName(), id);
    if (!users.contains(user)) {
      logger.warn("User: {} does not exist in group: {}", user.getName(), id);
      throw new IllegalArgumentException("User does not exist in group");
    }
    users.remove(user);
    logger.info("User: {} removed successfully from group: {}", user.getName(), id);
  }

  /**
   * Add an area to the group.
   *
   * @param area The area to add
   * @throws IllegalArgumentException If the area is already in the group
   */
  public void addArea(Area area) {
    logger.debug("Attempting to add area to group: {}", id);
    if (areas.contains(area)) {
      logger.warn("Area already exists in group: {}", id);
      throw new IllegalArgumentException("Area already exists in group");
    }
    areas.add(area);
    logger.info("Area added successfully to group: {}", id);
  }

  /**
   * Add multiple areas to the group.
   *
   * @param areas The areas to add
   */
  public void addAreas(Area... areas) {
    for (Area area : areas) {
      addArea(area);
    }
  }

  /**
   * Add an action to the group.
   *
   * @param action The action to add
   * @throws IllegalArgumentException If the action is already in the group or invalid
   */
  public void addAction(String action) {
    logger.debug("Attempting to add action: {} to group: {}", action, id);
    if (actions.contains(action)) {
      logger.warn("Action: {} already exists in group: {}", action, id);
      throw new IllegalArgumentException("Action already exists in group");
    }
    if (!Actions.getActions().contains(action)) {
      logger.error("Action: {} is not valid for group: {}", action, id);
      throw new IllegalArgumentException("Action is not valid");
    }
    actions.add(action);
    logger.info("Action: {} added successfully to group: {}", action, id);
  }

  /**
   * Add multiple actions to the group.
   *
   * @param actions The actions to add
   */
  public void addActions(String... actions) {
    for (String action : actions) {
      addAction(action);
    }
  }

  /**
   * Check if the group has access to a specific space.
   *
   * @param space The space to check
   * @return True if the group has access to the space, false otherwise
   */
  public boolean hasAccess(Space space) {
    logger.debug("Checking access to space: {} for group: {}", space.getId(), id);
    for (Area area : areas) {
      if (area instanceof Space && area.equals(space)) {
        logger.info("Group: {} has access to space: {}", id, space.getId());
        return true;
      } else if (area instanceof Partition) {
        if (((Partition) area).getSpaces().contains(space)) {
          logger.info("Group: {} has access to space: {} through partition.", id, space.getId());
          return true;
        }
      }
    }
    logger.info("Group: {} does not have access to space: {}", id, space.getId());
    return false;
  }

  /**
   * Check if the group has access to a specific action in the current time.
   *
   * @param action   The action to check
   * @param datetime The date and time to check
   * @return True if the group has access to the action, false otherwise
   */
  public boolean canPerform(String action, LocalDateTime datetime) {
    logger.debug("Checking if group: {} can perform action: {} at datetime: {}",
        id, action, datetime);
    if (!actions.contains(action)) {
      logger.info("Group: {} cannot perform action: {}. Action not allowed.", id, action);
      return false;
    }
    boolean inSchedule = schedule.isInSchedule(datetime);
    logger.info("Action check for group: {} to perform: {} at datetime: {} resulted in: {}",
        id, action, datetime, inSchedule);
    return inSchedule;
  }

  /**
   * Get the ID of the group.
   *
   * @return The ID of the group
   */
  public String getId() {
    logger.debug("Fetching ID for group: {}", id);
    return id;
  }

  /**
   * Get the users in the group.
   *
   * @return The users in the group
   */
  public ArrayList<User> getUsers() {
    logger.debug("Fetching users for group: {}", id);
    return users;
  }
}
