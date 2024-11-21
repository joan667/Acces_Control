package base;

import java.time.LocalDate;
import java.util.ArrayList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * A class that contains the user groups and the users in the system.
 */
public final class DirectoryUserGroups {

  private static final Logger logger = LoggerFactory.getLogger("base.DirectoryUserGroups");
  private static final ArrayList<User> users = new ArrayList<>();

  /**
   * Create the user groups and add some users to them.
   */
  public static void makeUserGroups() {
    logger.info("Starting to create user groups and users.");

    // Employees group
    logger.info("Creating 'employees' user group.");
    ArrayList<Day> employeesDays = Day.getWeekDays();
    ArrayList<DaySchedule> employeesDaySchedules = new ArrayList<>();
    for (Day day : employeesDays) {
      employeesDaySchedules.add(new DaySchedule(day, 9, 17));
    }
    Schedule employeesSchedule = new Schedule(
        LocalDate.of(2024, 9, 1),
        LocalDate.of(2025, 3, 1),
        employeesDaySchedules
    );
    UserGroup employees = new UserGroup("employees", employeesSchedule);
    employees.addActions(
        Actions.OPEN,
        Actions.CLOSE,
        Actions.UNLOCK_SHORTLY
    );
    employees.addAreas(
        DirectoryAreas.findAreaById("ground_floor"),
        DirectoryAreas.findAreaById("floor1"),
        DirectoryAreas.findAreaById("exterior"),
        DirectoryAreas.findAreaById("stairs")
    );
    users.add(new User("Ernest", "74984", employees));
    users.add(new User("Eulalia", "43295", employees));
    logger.info("Finished creating 'employees' user group.");

    // Managers group
    logger.info("Creating 'managers' user group.");
    ArrayList<Day> managersDays = Day.getWeekDays();
    managersDays.add(new Day(Day.SATURDAY));
    ArrayList<DaySchedule> managersDaySchedules = new ArrayList<>();
    for (Day day : managersDays) {
      managersDaySchedules.add(new DaySchedule(day, 8, 20));
    }
    Schedule managersSchedule = new Schedule(
        LocalDate.of(2024, 9, 1),
        LocalDate.of(2025, 3, 1),
        managersDaySchedules
    );
    UserGroup managers = new UserGroup("managers", managersSchedule);
    managers.addActions(
        Actions.OPEN,
        Actions.CLOSE,
        Actions.UNLOCK_SHORTLY,
        Actions.LOCK,
        Actions.UNLOCK
    );
    managers.addAreas(
        DirectoryAreas.getRootArea()
    );
    users.add(new User("Manel", "95783", managers));
    users.add(new User("Marta", "05827", managers));
    logger.info("Finished creating 'managers' user group.");

    // Admin group
    logger.info("Creating 'admin' user group.");
    ArrayList<Day> adminDays = Day.getAllDays();
    ArrayList<DaySchedule> adminDaySchedules = new ArrayList<>();
    for (Day day : adminDays) {
      adminDaySchedules.add(new DaySchedule(day, 1, 24));
    }
    Schedule adminSchedule = new Schedule(
        LocalDate.of(2024, 1, 1),
        LocalDate.of(2100, 12, 31),
        adminDaySchedules
    );
    UserGroup admin = new UserGroup("admin", adminSchedule);
    admin.addActions(
        Actions.OPEN,
        Actions.CLOSE,
        Actions.UNLOCK_SHORTLY,
        Actions.LOCK,
        Actions.UNLOCK
    );
    admin.addAreas(DirectoryAreas.getRootArea());
    users.add(new User("Ana", "11343", admin));
    logger.info("Finished creating 'admin' user group.");

    logger.info("User groups and users creation completed.");
  }

  /**
   * Find a user by its credential.
   *
   * @param credential The credential of the user
   * @return The user with the given credential, or null if the user is not found
   */
  public static User findUserByCredential(String credential) {
    logger.debug("Searching for user with credential: {}", credential);
    for (User user : users) {
      if (user.getCredential().equals(credential)) {
        logger.info("User found with credential: {}", credential);
        return user;
      }
    }
    logger.warn("User with credential {} not found.", credential);
    return null;
  }
}
