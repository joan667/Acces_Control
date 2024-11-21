package base;

import base.doorstates.LockedState;
import base.doorstates.UnlockedShortlyState;
import base.doorstates.UnlockedState;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A class that manages the state of a door.
 */
public abstract class DoorState {

  private static final Logger logger = LoggerFactory.getLogger("base.DoorState");

  protected String name = "unknown";
  protected final Door door;

  /**
   * Create a new door state.
   *
   * @param door The door that the state belongs to
   */
  public DoorState(Door door) {
    logger.debug("Creating DoorState for door id: {}", door.getId());
    this.door = door;
  }

  /**
   * Get the name of the state.
   *
   * @return The name of the state
   */
  public String getStateName() {
    logger.debug("Getting state name for door id: {}, state: {}", door.getId(), name);
    return name;
  }

  /**
   * The actions that will be done in the state when the door is opened.
   */
  public void open() {
    if (!this.door.isClosed()) {
      logger.info("Door id: {} is already open.", door.getId());
      return;
    }
    this.door.setClosed(false);
    logger.info("Door id: {} is now open.", door.getId());
  }

  /**
   * The actions that will be done in the state when the door is closed.
   */
  public void close() {
    if (this.door.isClosed()) {
      logger.info("Door id: {} is already closed.", door.getId());
      return;
    }
    this.door.setClosed(true);
    logger.info("Door id: {} is now closed.", door.getId());
  }

  /**
   * The actions that will be done in the state when the door is locked.
   */
  public void lock() {
    if (!this.door.isClosed()) {
      logger.warn("Cannot lock door id: {} because it is not closed.", door.getId());
      return;
    }
    this.door.setDoorState(new LockedState(this.door));
    logger.info("Door id: {} is now locked.", door.getId());
  }

  /**
   * The actions that will be done in the state when the door is unlocked.
   */
  public void unlock() {
    this.door.setDoorState(new UnlockedState(this.door));
    logger.info("Door id: {} is now unlocked.", door.getId());
  }

  /**
   * The actions that will be done in the state when the door is unlocked shortly.
   */
  public void unlockShortly() {
    this.door.setDoorState(new UnlockedShortlyState(this.door));
    logger.info("Door id: {} is now unlocked shortly.", door.getId());
  }
}
