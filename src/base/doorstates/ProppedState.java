package base.doorstates;

import base.Door;
import base.DoorState;
import base.States;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * State that indicates that the door is propped and the actions to do.
 */
public final class ProppedState extends DoorState {

  private static final Logger logger = LoggerFactory.getLogger("base.doorstates.ProppedState");

  /**
   * Create a new propped state.
   *
   * @param door The door that the state belongs to
   */
  public ProppedState(Door door) {
    super(door);
    name = States.PROPPED;
    logger.info("Door id: {} set to ProppedState.", door.getId());
  }

  /**
   * The actions that will be done in the state when the door is closed.
   */
  public void close() {
    logger.info("Closing door id: {} from ProppedState. It will be locked automatically.",
        door.getId());
    door.setClosed(true);
    door.setDoorState(new LockedState(door));
    System.out.println("The door is now closed and has been locked.");
    logger.info("Door id: {} has been closed and locked.", door.getId());
  }

  /**
   * The actions that will be done in the state when the door is locked.
   */
  public void lock() {
    logger.warn("Attempted to lock door id: {} while it is propped. Close it first.", door.getId());
    System.out.println("Close the door and it will be locked automatically.");
  }
}
