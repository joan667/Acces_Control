package base.doorstates;

import base.Clock;
import base.Door;
import base.DoorState;
import base.Observable;
import base.States;

/**
 * State that indicates that the door is unlocked shortly and the actions to do.
 */
public final class UnlockedShortlyState extends DoorState implements Observable {

  private Clock clock;

  /**
   * Create a new unlocked state.
   *
   * @param door The door that the state belongs to
   */
  public UnlockedShortlyState(Door door) {
    super(door);
    name = States.UNLOCKED_SHORTLY;
    startTimer();
  }

  @Override
  public void update() {
    // Check if the door is closed and lock it
    if (door.isClosed()) {
      door.setDoorState(new LockedState(door));
      System.out.println("Timer expired: The door is now locked.");

      // Check if the door is opened and prop it
    } else {
      door.setDoorState(new ProppedState(door));
      System.out.println("Timer expired: The door is now propped.");
    }
  }

  /**
   * The actions that will be done in the state when the door is locked.
   */
  public void lock() {
    // Stop the timer
    stopTimer();

    // Call the super method
    super.lock();
  }

  /**
   * The actions that will be done in the state when the door is unlocked.
   */
  public void unlock() {
    // Stop the timer
    stopTimer();

    // Call the super method
    super.unlock();
  }

  /**
   * The actions that will be done in the state when the door is unlocked shortly.
   */
  public void unlockShortly() {
    // Reset the timer
    resetTimer();
    System.out.println("The door is now unlocked shortly.");
  }

  /**
   * Start a timer of 10 seconds to lock the door automatically.
   */
  private void startTimer() {
    clock = new Clock(10000, this);
  }

  /**
   * Reset the timer.
   */
  private void resetTimer() {
    clock.reset(10000);
    System.out.println("The unlocked shortly timer has been reset.");
  }

  /**
   * Stop the timer.
   */
  private void stopTimer() {
    clock.cancel();
    System.out.println("The unlocked shortly timer has been stopped.");
  }
}
