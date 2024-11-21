package base;

import java.util.ArrayList;

/**
 * A class that contains the actions that can be performed on a door.
 */
public final class Actions {
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
    // Check if the actions are already set and return them
    if (actions != null) {
      return actions;
    }

    // Create the actions list and return it
    actions = new ArrayList<>();
    actions.add(OPEN);
    actions.add(CLOSE);
    actions.add(UNLOCK_SHORTLY);
    actions.add(UNLOCK);
    actions.add(LOCK);
    return actions;
  }
}
