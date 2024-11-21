package base.doorstates;

import base.Door;
import base.DoorState;
import base.States;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * State that indicates that the door is unlocked and the actions to do.
 */
public final class UnlockedState extends DoorState {

  private static final Logger logger = LoggerFactory.getLogger("base.doorstates.UnlockedState");

  /**
   * Create a new unlocked state.
   *
   * @param door The door that the state belongs to
   */
  public UnlockedState(Door door) {
    super(door);
    name = States.UNLOCKED;
    logger.info("Door id: {} set to UnlockedState.", door.getId());
  }

  /**
   * The actions that will be done in the state when the door is unlocked.
   */
  public void unlock() {
    logger.warn("Unlock action called on door id: {}, but it is already unlocked.", door.getId());
    System.out.println("The door is already unlocked.");
  }

  /**
   * The actions that will be done in the state when the door is unlocked shortly.
   */
  public void unlockShortly() {
    logger.warn("Unlock shortly action called on door id: {},"
        + " but the door needs to be locked first.", door.getId());
    System.out.println("The door needs to be locked first.");
  }
}
