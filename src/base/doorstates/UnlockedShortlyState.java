package base.doorstates;

import base.Door;
import base.DoorState;
import base.States;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Observer;
import java.util.Observable;
import base.Clock;

/**
 * State that indicates that the door is unlocked shortly and the actions to do.
 */
public final class UnlockedShortlyState extends DoorState implements Observer {

  private static final Logger logger = LoggerFactory.getLogger("base.doorstates.UnlockedShortlyState");
  private static final Logger loggerFita2 = LoggerFactory.getLogger("base.doorstates.UnlockedShortlyStateF2");

  private LocalDateTime startTime;

  /**
   * Create a new unlocked shortly state.
   *
   * @param door The door that the state belongs to
   */


  public UnlockedShortlyState(Door door) {
    super(door);
    name = States.UNLOCKED_SHORTLY;
    this.startTime = LocalDateTime.now();
    loggerFita2.debug("Singelton instance called");
    Clock.getInstance().addObserver(this);
    logger.info("Door id: {} set to UnlockedShortlyState at {}", door.getId(), startTime);
  }

  /**
   * Called when the timer updates.
   *
   * @param obs The observable timer
   * @param arg The argument passed by the observable (optional)
   */
  @Override
  public void update(Observable obs, Object arg) {
    LocalDateTime now = LocalDateTime.now();
    Duration duration = Duration.between(startTime, now);

    // Check if 10000 milliseconds (10 second) have passed
    if (duration.toMillis() >= 10000) {
      if (door.isClosed()) {
        door.setDoorState(new LockedState(door));
        logger.info("Timer expired: Door id: {} is now locked.", door.getId());
      } else {
        door.setDoorState(new ProppedState(door));
        logger.info("Timer expired: Door id: {} is now propped.", door.getId());
      }
      obs.deleteObserver(this);
    }
  }

  /**
   * The actions that will be done in the state when the door is locked.
   */
  public void lock() {
    logger.info("Lock action called on door id: {} while in UnlockedShortlyState.", door.getId());
    logger.warn("Cannot lock an unlocked shortly door");
  }

  /**
   * The actions that will be done in the state when the door is unlocked.
   */
  public void unlock() {
    logger.info("Unlock action called on door id: {} while in UnlockedShortlyState.", door.getId());
    logger.warn("Cannot unlock an unlocked shortly door");
  }

  /**
   * The actions that will be done in the state when the door is unlocked shortly.
   */
  public void unlockShortly() {
    logger.info("Unlock shortly action called on door id: {}. Resetting timer.", door.getId());
    logger.warn("Cannot perform unlockShortly two times on the same door");
  }
}
