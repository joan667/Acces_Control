package base.doorstates;

import base.Door;
import base.DoorState;
import base.States;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * State that indicates that the door is locked and the actions to do.
 */
public final class LockedState extends DoorState {

  private static final Logger logger = LoggerFactory.getLogger("base.doorstates.LockedState");

  /**
   * Create a new locked state.
   *
   * @param door The door that the state belongs to
   */
  public LockedState(Door door) {
    super(door);
    name = States.LOCKED;
    logger.info("Door id: {} set to LockedState.", door.getId());
  }

  /**
   * The actions that will be done in the state when the door is opened.
   */
  public void open() {
    logger.warn("Attempted to open door id: {} while it is locked.", door.getId());
    System.out.println("The door is locked and cannot be opened. Unlock it first.");
  }

  /**
   * The actions that will be done in the state when the door is locked.
   */
  public void lock() {
    logger.info("Lock action attempted on door id: {}, but it is already locked.", door.getId());
    System.out.println("The door is already locked.");
  }
}
