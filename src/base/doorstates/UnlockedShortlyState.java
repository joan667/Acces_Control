package base.doorstates;

import base.Clock;
import base.Door;
import base.DoorState;
import base.Observable;
import base.States;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * State that indicates that the door is unlocked shortly and the actions to do.
 */
public final class UnlockedShortlyState extends DoorState implements Observable {

  private static final Logger logger =
      LoggerFactory.getLogger("base.doorstates.UnlockedShortlyState");

  private Clock clock;

  /**
   * Create a new unlocked shortly state.
   *
   * @param door The door that the state belongs to
   */
  public UnlockedShortlyState(Door door) {
    super(door);
    name = States.UNLOCKED_SHORTLY;
    logger.info("Door id: {} set to UnlockedShortlyState.", door.getId());
    startTimer();
  }

  @Override
  public void update() {
    if (door.isClosed()) {
      door.setDoorState(new LockedState(door));
      logger.info("Timer expired: Door id: {} is now locked.", door.getId());
      System.out.println("Timer expired: The door is now locked.");
    } else {
      door.setDoorState(new ProppedState(door));
      logger.info("Timer expired: Door id: {} is now propped.", door.getId());
      System.out.println("Timer expired: The door is now propped.");
    }
  }

  /**
   * The actions that will be done in the state when the door is locked.
   */
  public void lock() {
    logger.info("Lock action called on door id: {} while in UnlockedShortlyState.", door.getId());
    stopTimer();
    super.lock();
  }

  /**
   * The actions that will be done in the state when the door is unlocked.
   */
  public void unlock() {
    logger.info("Unlock action called on door id: {} while in UnlockedShortlyState.", door.getId());
    stopTimer();
    super.unlock();
  }

  /**
   * The actions that will be done in the state when the door is unlocked shortly.
   */
  public void unlockShortly() {
    logger.info("Unlock shortly action called on door id: {}. Resetting timer.", door.getId());
    resetTimer();
    System.out.println("The door is now unlocked shortly.");
  }

  /**
   * Start a timer of 10 seconds to lock the door automatically.
   */
  private void startTimer() {
    logger.debug("Starting timer for door id: {} in UnlockedShortlyState.", door.getId());
    clock = new Clock(10000, this);
    logger.info("Timer started for door id: {}.", door.getId());
  }

  /**
   * Reset the timer.
   */
  private void resetTimer() {
    logger.debug("Resetting timer for door id: {}.", door.getId());
    clock.reset(10000);
    System.out.println("The unlocked shortly timer has been reset.");
    logger.info("Timer reset for door id: {}.", door.getId());
  }

  /**
   * Stop the timer.
   */
  private void stopTimer() {
    logger.debug("Stopping timer for door id: {}.", door.getId());
    clock.cancel();
    System.out.println("The unlocked shortly timer has been stopped.");
    logger.info("Timer stopped for door id: {}.", door.getId());
  }
}
