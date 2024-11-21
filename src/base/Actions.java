package base;

import java.util.ArrayList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



/**
 * A class that contains the actions that can be performed on a door.
 */
public final class Actions {

  private static final Logger logger = LoggerFactory.getLogger("base.Actions");
  public static final String LOCK = "lock";
  public static final String UNLOCK = "unlock";
  public static final String UNLOCK_SHORTLY = "unlock_shortly";

  public static final String OPEN = "open";
  public static final String CLOSE = "close";

  private static ArrayList<String> actions;

  /**
   * Get the actions that can be performed on a door.
   *
   * @return The actions that can be performed on a door
   */
  public static ArrayList<String> getActions() {
    // Log an error if the actions list is null unexpectedly
    if (actions == null) {
      logger.error("The actions list is unexpectedly null. Initializing a new list.");
    }

    // Check if the actions are already set and return them
    if (actions != null) {
      // Log if returning an already initialized list of actions
      logger.info("Returning the existing actions list with size: {}", actions.size());
      return actions;
    }

    // Log when initializing the actions list for the first time
    logger.info("Initializing the actions list for the first time.");
    actions = new ArrayList<>();
    try {
      actions.add(OPEN);
      actions.add(CLOSE);
      actions.add(UNLOCK_SHORTLY);
      actions.add(UNLOCK);
      actions.add(LOCK);

      // Log when the actions list has been successfully initialized
      logger.info("Actions list initialized successfully with {} actions.", actions.size());
    } catch (Exception e) {
      // Log an error if an exception occurs during initialization
      logger.error("An error occurred while initializing the actions list: ", e);
    }

    return actions;
  }
}
